package dev.sqyyy.customitems;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.sqyyy.customitems.commands.GetCommand;
import dev.sqyyy.customitems.event.MoveEvent;
import dev.sqyyyapis.storageexplorers.GsonExplorer;
import dev.sqyyyapis.storageexplorers.GsonFile;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class CustomItems extends JavaPlugin {

    public final String prefix = "[CustomItems] ";
    public final List<String> newItems = new ArrayList<>();
    public final String path = "./plugins/CustomItems/";
    public GsonFile config;
    public static CustomItems pl;

    @Override
    public void onEnable() {
        baseInit();
        commandsInit();
        pl = this;
        testEvents();
    }

    @Override
    public void onDisable() {
        baseUnload();
    }

    public final void baseInit() {
        this.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.getPluginManager().registerEvents(new EventListener(), CustomItems.pl);
            }
        }, 20);
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
                    completions.addAll(newItems);
                }
                return completions;
            }
        });
    }

    public static ItemStack getItem(Player player, String key) {
        ItemStack item = new ItemStack(Material.AIR);
        GsonFile file = GsonExplorer.getGson(CustomItems.pl.path + "items/" + key.toLowerCase() + ".json");
        if (!file.has("Material")) {
            player.sendMessage("NoMaterialException: caused by items/" + key.toLowerCase() + ".json");
            return null;
        }
        try {
            item.setType(Material.valueOf(file.get("Material").getAsString().toUpperCase()));
        } catch (IllegalArgumentException e) {
            player.sendMessage("IllegalArgumentException: caused by items/" + key + ".json: \"Non valid Material!\"");
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
                                    player.sendMessage("ObjectTypeException: caused by items/" + key + ".json: \"Slots must be an array!\"");
                                }
                            } else {
                                meta.addAttributeModifier(Attribute.valueOf(object.get("id").getAsString().toUpperCase()),
                                        new AttributeModifier(UUID.randomUUID(), object.get("name").getAsString(),
                                                object.get("value").getAsDouble(), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
                            }
                        } else {
                            player.sendMessage("IllegalArgumentException: caused by items/" + key + ".json: \"Could not find field name, value or id!\"");
                        }
                    } catch (IllegalArgumentException exception) {
                        player.sendMessage("IllegalArgumentException: caused by items/" + key + ".json: \"Non valid Attribute!\"");
                    }
                }
            } else {
                player.sendMessage("ObjectTypeException: caused by items/" + key + ".json: \"Attributes must be an array!\"");
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
                player.sendMessage("ObjectTypeException: caused by items/" + key + ".json: \"ItemFlags must be an array!\"");
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
                player.sendMessage("ObjectTypeException: caused by items/" + key + ".json: \"Lore must be an array!\"");
            }
        }
        item.setItemMeta(meta);

        net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound nbt = nmsItem.getTag();
        if (nbt == null) return null;
        nbt.setString("key", key);
        item = CraftItemStack.asBukkitCopy(nmsItem);
        return item;
    }

    public final void testEvents() {
        EventHandler.setMoveEvent("zombie_sword", new MoveEvent() {
            @Override
            public void onEvent(PlayerMoveEvent e, EquipmentSlot slot) {
                if (slot.equals(EquipmentSlot.HAND)) {
                    Particle.DustOptions dust = new Particle.DustOptions(
                            Color.fromRGB(180, 68, 32), 1);
                    e.getPlayer().spawnParticle(Particle.REDSTONE, e.getPlayer().getLocation().getX() + Math.random() - 0.5, e.getPlayer().getLocation().getY(),
                            e.getPlayer().getLocation().getZ() + Math.random() - 0.5, 0, 0, 0, 0, dust);
                }
            }
        });
    }
}