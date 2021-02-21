package dev.sqyyy.customitems;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import dev.sqyyy.customitems.commands.GetCommand;
import dev.sqyyyapis.storageexplorers.GsonExplorer;
import dev.sqyyyapis.storageexplorers.GsonFile;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.*;
import org.bukkit.Color;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class CustomItems extends JavaPlugin {

    public final String prefix = "[CustomItems] ";
    public final List<String> newItems = new ArrayList<>();
    public static String path = "./plugins/CustomItems/";
    public GsonFile config;
    public static CustomItems pl;

    @Override
    public void onEnable() {
        baseInit();
        commandsInit();
        recipesInit();
        initSchedulers();
        pl = this;
    }

    private void initSchedulers() {
        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(p -> {
                    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getModifiers().forEach( m -> {
                        if (m.getName().equalsIgnoreCase("artificial.health"))
                        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).removeModifier(m);
                    });
                    p.getAttribute(Attribute.GENERIC_ARMOR).getModifiers().forEach( m -> {
                        if (m.getName().equalsIgnoreCase("artificial.armor"))
                        p.getAttribute(Attribute.GENERIC_ARMOR).removeModifier(m);
                    });
                    p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getModifiers().forEach( m -> {
                        if (m.getName().equalsIgnoreCase("artificial.damage"))
                        p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).removeModifier(m);
                    });
                    p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getModifiers().forEach( m -> {
                        if (m.getName().equalsIgnoreCase("artificial.speed"))
                        p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).removeModifier(m);
                    });
                    List<String> artifacts = new ArrayList<>();
                    for (ItemStack item : p.getInventory().getContents()) {
                        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                        NBTTagCompound nbt = nmsItem.getTag();
                        if (nbt == null) continue;
                        if (nbt.getBoolean("artifact")) {
                            if (artifacts.contains(nbt.getString("key"))) continue;
                            artifacts.add(nbt.getString("key"));
                            if (!GsonExplorer.find(path + "items/" + nbt.getString("key") + ".json")) continue;
                            GsonFile file = GsonExplorer.getGson(path + "items/" + nbt.getString("key") + ".json");
                            if (!file.has("Modifiers")) continue;
                            JsonObject obj = file.get("Modifiers").getAsJsonObject();
                            if (obj.has("Health")) {
                                double value = obj.get("Health").getAsDouble();
                                p.getAttribute(Attribute.GENERIC_MAX_HEALTH).addModifier(new AttributeModifier("artificial.health", value, AttributeModifier.Operation.ADD_NUMBER));
                            }
                            if (obj.has("Armor")) {
                                double value = obj.get("Armor").getAsDouble();
                                p.getAttribute(Attribute.GENERIC_ARMOR).addModifier(new AttributeModifier("artificial.armor", value, AttributeModifier.Operation.ADD_NUMBER));
                            }
                            if (obj.has("Damage")) {
                                double value = obj.get("Damage").getAsDouble();
                                p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).addModifier(new AttributeModifier("artificial.damage", value, AttributeModifier.Operation.ADD_NUMBER));
                            }
                            if (obj.has("Speed")) {
                                double value = obj.get("Speed").getAsDouble();
                                p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).addModifier(new AttributeModifier("artificial.speed", value, AttributeModifier.Operation.ADD_NUMBER));
                            }
                        }
                    }
                });
            }
        }, 40, 40);
    }

    private void recipesInit() {
        for (String newItem : newItems) {
            GsonFile f = GsonExplorer.getGson(path + "items/" + newItem + ".json");
            if (f.has("Recipe")) {
                if (f.get("Recipe").isJsonObject()) {
                    JsonObject object = f.get("Recipe").getAsJsonObject();
                    NamespacedKey key = new NamespacedKey(this, newItem);
                    ItemStack item = getItem(newItem);
                    if (item == null) continue;
                    ShapedRecipe recipe = new ShapedRecipe(key, item);
                    if (!object.has("shape")) continue;
                    if (!object.get("shape").isJsonArray()) continue;
                    recipe.shape(object.get("shape").getAsJsonArray().get(0).getAsString(),object.get("shape").getAsJsonArray().get(1).getAsString(),object.get("shape").getAsJsonArray().get(2).getAsString());
                    if (!object.has("Items")) continue;
                    if (!object.get("Items").isJsonArray()) continue;
                    for (JsonElement use : object.get("Items").getAsJsonArray()) {
                        if (!use.isJsonObject()) continue;
                        JsonObject obj = use.getAsJsonObject();
                        if (!obj.has("char") || !obj.has("Material")) continue;
                        try {
                            recipe.setIngredient(obj.get("char").getAsCharacter(), Material.valueOf(obj.get("Material").getAsString().toUpperCase()));
                        } catch (IllegalArgumentException e) {
                            continue;
                        }
                    }
                    Bukkit.addRecipe(recipe);
                } else {
                    System.out.println("ObjectTypeException: caused by items/" + newItem + ".json: \"Slots must be an array!\"");
                }
            } else if (f.has("ARecipe")) {
                if (f.get("ARecipe").isJsonArray()) {
                    JsonArray array = f.get("ARecipe").getAsJsonArray();
                    AdvancedRecipe recipe = new AdvancedRecipe(getItem(newItem));
                    for (int i = 0; i < 9; i++) {
                        if (array.get(i).getAsString().startsWith("#")) {
                            recipe.keyedItems[i] = new ItemChoice(array.get(i).getAsString().replaceFirst("#", ""));
                        } else {
                            try {
                                recipe.keyedItems[i] = new ItemChoice(Material.valueOf(array.get(i).getAsString().toUpperCase()));
                            } catch (IllegalArgumentException exception) {
                                recipe.keyedItems[i] = new ItemChoice("");
                            }
                        }
                    }
                } else {
                    System.out.println("ObjectTypeException: caused by items/" + newItem + ".json: \"ARecipe must be an array!\"");
                }
            }
        }
    }

    @Override
    public void onDisable() {
        baseUnload();
    }

    public final void baseInit() {
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        File file = new File(path + "config.json");
        if (!file.exists()) {
            if (!file.getParentFile().mkdirs()) {
                this.getLogger().info("Could not create path to config file!");
                this.getServer().getPluginManager().disablePlugin(CustomItems.getPlugin(CustomItems.class));
            }
            try {
                file.createNewFile();
                GsonExplorer.createBaseGson(path + "config.json");
            } catch (IOException ioException) {
                this.getLogger().info("Could not create config file!");
                this.getServer().getPluginManager().disablePlugin(CustomItems.getPlugin(CustomItems.class));
            }
            config = GsonExplorer.getGson(path + "config.json");
            config.save(true);
        }
        //set config to config.json
        config = GsonExplorer.getGson(path + "config.json");
        //load item entries
        if (config.has("Items")) {
            for (JsonElement param : config.getAsJsonArray("Items")) {
                File var1 = new File(path + "items/" + param.getAsString() + ".json");
                if (var1.exists()) {
                    newItems.add(param.getAsString().toLowerCase());
                    continue;
                }
                System.out.println(prefix + param.getAsString() + " could not be found!");
            }
        } else {
            config.add("Items", new JsonArray());
        }
        //load item template
        File template = new File(path + "items/template.json");
        try {
            file.createNewFile();
            FileWriter templateWriter = new FileWriter(template.getPath());
            templateWriter.write("{\n\t\"DisplayName\":\"&0&lTemplate &f&litem\",\n\t\"Material\":\"IRON_INGOT\",\n\t\"Attributes\": " +
                    "[\n\t\t{\"id\": \"GENERIC_ATTACK_DAMAGE\", \"name\": \"generic.attackDamage\", \"value\": 10.0, \"slots\": [\"OFF_HAND\", \"HAND\"]}\n\t],\n\t\"Unbreakable\": true\n}");
            templateWriter.flush();
            boolean contains = false;
            for (JsonElement param : config.get("Items").getAsJsonArray()) {
                if (param.getAsString().equalsIgnoreCase("template")) {
                    contains = true;
                    break;
                }
            }
            if (!contains) config.get("Items").getAsJsonArray().add("template");
        } catch (IOException ignored) {
            System.out.println(prefix + "Template could not be loaded!");
        }
        config.save(true);
    }

    public final void baseUnload() {
    }

    public final void commandsInit() {
        this.getCommand("get").setExecutor(new GetCommand());
        this.getCommand("get").setPermission("customitems.get");
        this.getCommand("get").setTabCompleter(new TabCompleter() {
            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                List<String> completions = new ArrayList<>();
                if (sender instanceof Player) {
                    for (String newItem : newItems) {
                        if (newItem.contains(args[0].toLowerCase())) {
                            completions.add(newItem);
                        }
                    }
                }
                return completions;
            }
        });
    }

    public static ItemStack getItem(String key) {
        ItemStack item = new ItemStack(Material.AIR);
        GsonFile file = GsonExplorer.getGson(path + "items/" + key.toLowerCase() + ".json");
        if (!file.has("Material")) {
            System.out.println("NoMaterialException: caused by items/" + key.toLowerCase() + ".json");
            return null;
        }
        try {
            item.setType(Material.valueOf(file.get("Material").getAsString().toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: caused by items/" + key + ".json: \"Non valid Material!\"");
            return null;
        }
        ItemMeta meta = item.getItemMeta();
        if (file.has("DisplayName")) {
            String str = ChatColor.translateAlternateColorCodes('&', file.get("DisplayName").getAsString());
            meta.setDisplayName("§r" + str);
        } else {
            if (key.length() > 0) {
                char[] name = key.toCharArray();
                name[0] = Character.toUpperCase(name[0]);
                meta.setDisplayName(String.copyValueOf(name));
            } else {
                meta.setDisplayName("§f§lnull");
            }
        }
        if (file.has("Attributes")) {
            if (file.get("Attributes").isJsonArray()) {
                for (JsonElement param : file.get("Attributes").getAsJsonArray()) {
                    if (!param.isJsonObject()) continue;
                    JsonObject object = param.getAsJsonObject();
                    try {
                        if (object.has("id") && object.has("name") && object.has("value")) {
                            if (object.has("slots")) {
                                if (object.get("slots").isJsonArray()) {
                                    for (JsonElement param2 : object.get("slots").getAsJsonArray()) {
                                        meta.addAttributeModifier(Attribute.valueOf(object.get("id").getAsString().toUpperCase()),
                                                new AttributeModifier(UUID.randomUUID(), object.get("name").getAsString(),
                                                        object.get("value").getAsDouble(), AttributeModifier.Operation.ADD_NUMBER,
                                                        EquipmentSlot.valueOf(param2.getAsString().toUpperCase())));
                                    }
                                } else {
                                    System.out.println("ObjectTypeException: caused by items/" + key + ".json: \"Slots must be an array!\"");
                                }
                            } else {
                                meta.addAttributeModifier(Attribute.valueOf(object.get("id").getAsString().toUpperCase()),
                                        new AttributeModifier(UUID.randomUUID(), object.get("name").getAsString(),
                                                object.get("value").getAsDouble(), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
                            }
                        } else {
                            System.out.println("IllegalArgumentException: caused by items/" + key + ".json: \"Could not find field name, value or id!\"");
                        }
                    } catch (IllegalArgumentException exception) {
                        System.out.println("IllegalArgumentException: caused by items/" + key + ".json: \"Non valid Attribute!\"");
                    }
                }
            } else {
                System.out.println("ObjectTypeException: caused by items/" + key + ".json: \"Attributes must be an array!\"");
            }
        }
        if (file.has("Unbreakable")) {
            meta.setUnbreakable(file.get("Unbreakable").getAsBoolean());
        }
        if (file.has("CustomModelData")) {
            meta.setCustomModelData(file.get("CustomModelData").getAsInt());
        }
        if (file.has("ItemFlags")) {
            if (file.get("ItemFlags").isJsonArray()) {
                for (JsonElement param : file.get("ItemFlags").getAsJsonArray()) {
                    meta.addItemFlags(ItemFlag.valueOf(param.getAsString().toUpperCase()));
                }
            } else {
                System.out.println("ObjectTypeException: caused by items/" + key + ".json: \"ItemFlags must be an array!\"");
            }
        }
        if (file.has("Lore")) {
            if (file.get("Lore").isJsonArray()) {
                List<String> lore = new ArrayList<>();
                for (JsonElement param : file.get("Lore").getAsJsonArray()) {
                    lore.add(ChatColor.translateAlternateColorCodes('&', param.getAsString()));
                }
                meta.setLore(lore);
            } else {
                System.out.println("ObjectTypeException: caused by items/" + key + ".json: \"Lore must be an array!\"");
            }
        }
        if (file.has("Enchants")) {
            if (file.get("Enchants").isJsonArray()) {
                for (JsonElement param : file.get("Enchants").getAsJsonArray()) {
                    if (param.isJsonObject()) {
                        JsonObject obj = param.getAsJsonObject();
                        NamespacedKey.minecraft(obj.get("id").getAsString());
                        meta.addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(obj.get("id").getAsString())), obj.get("lvl").getAsInt(), false);
                    } else {
                        System.out.println("ObjectTypeException: caused by items/" + key + ".json: \"Enchants must be an array within objects!\"");
                    }
                }
            } else {
                System.out.println("ObjectTypeException: caused by items/" + key + ".json: \"Enchants must be an array!\"");
            }
        }
        item.setItemMeta(meta);
        if (item.getType().equals(Material.PLAYER_HEAD)) {
            if (file.has("texture")) {
                String texture = file.get("texture").getAsString();
                SkullMeta skull = (SkullMeta)meta;
                GameProfile profile = new GameProfile(UUID.randomUUID(), null);
                profile.getProperties().put("textures", new Property("textures", texture));

                try {
                    Field field = skull.getClass().getDeclaredField("profile");
                    field.setAccessible(true);
                    field.set(skull, profile);
                    field.setAccessible(false);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                item.setItemMeta(skull);
            }
        }
        if (item.getType().name().toLowerCase().contains("leather_")) {
            if (file.has("Color")) {
                if (file.get("Color").isJsonObject()) {
                    int r = file.get("Color").getAsJsonObject().get("r").getAsInt();
                    int g = file.get("Color").getAsJsonObject().get("g").getAsInt();
                    int b = file.get("Color").getAsJsonObject().get("b").getAsInt();
                    LeatherArmorMeta armor = (LeatherArmorMeta)item.getItemMeta();
                    armor.setColor(Color.fromRGB(r,g,b));
                    armor.addItemFlags(ItemFlag.HIDE_DYE);
                    item.setItemMeta(armor);
                }
            }
        }
        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound nbt = nmsItem.getTag();
        if (nbt == null) return null;
        nbt.setString("key", key);
        if (file.has("Artifact")) {
            if (file.get("Artifact").getAsBoolean()) nbt.setBoolean("artifact", true);
        }
        item = CraftItemStack.asBukkitCopy(nmsItem);
        return item;
    }
}