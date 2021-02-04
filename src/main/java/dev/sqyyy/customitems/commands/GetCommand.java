package dev.sqyyy.customitems.commands;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.sqyyy.customitems.CustomItems;
import dev.sqyyyapis.storageexplorers.GsonExplorer;
import dev.sqyyyapis.storageexplorers.GsonFile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class GetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                if (!CustomItems.pl.newItems.contains(args[0])) return false;
                ItemStack item = new ItemStack(Material.AIR);
                GsonFile file = GsonExplorer.getGson(CustomItems.pl.path + "items/" + args[0].toLowerCase() + ".json");
                if (!file.has("Material")) {
                    sender.sendMessage("NoMaterialException: caused by items/" + args[0].toLowerCase() + ".json");
                    return true;
                }
                try {
                    item.setType(Material.valueOf(file.get("Material").getAsString().toUpperCase()));
                } catch (IllegalArgumentException e) {
                    sender.sendMessage("IllegalArgumentException: caused by items/" + args[0] + ".json: \"Non valid Material!\"");
                    return true;
                }
                ItemMeta meta = item.getItemMeta();
                if (file.has("DisplayName")) {
                    String str = ChatColor.translateAlternateColorCodes('&', file.get("DisplayName").getAsString());
                    meta.setDisplayName("§r" + str);
                } else {
                    if (args[0].length() > 0) {
                        char[] name = args[0].toCharArray();
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
                                            sender.sendMessage("ObjectTypeException: caused by items/" + args[0] + ".json: \"Slots must be an array!\"");
                                        }
                                    } else {
                                        meta.addAttributeModifier(Attribute.valueOf(object.get("id").getAsString().toUpperCase()),
                                                new AttributeModifier(UUID.randomUUID(), object.get("name").getAsString(),
                                                        object.get("value").getAsDouble(), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
                                    }
                                } else {
                                    sender.sendMessage("IllegalArgumentException: caused by items/" + args[0] + ".json: \"Could not find field name, value or id!\"");
                                }
                            } catch (IllegalArgumentException exception) {
                                sender.sendMessage("IllegalArgumentException: caused by items/" + args[0] + ".json: \"Non valid Attribute!\"");
                            }
                        }
                    } else {
                        sender.sendMessage("ObjectTypeException: caused by items/" + args[0] + ".json: \"Attributes must be an array!\"");
                    }
                }
                if (file.has("Unbreakable")) {
                    meta.setUnbreakable(file.get("Unbreakable").getAsBoolean());
                }
                if (file.has("CustomModelData")) {
                    meta.setCustomModelData(file.get("CustomModelData").getAsInt());
                }
                item.setItemMeta(meta);
                if (((Player) sender).getInventory().firstEmpty() != -1) ((Player) sender).getInventory().addItem(item);
                return true;
            }
            return false;
        }
        sender.sendMessage("§cYou have to be a player to execute this command.");
        return true;
    }
}
