package dev.sqyyy.customitems.commands;

import dev.sqyyy.customitems.CustomItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                if (!CustomItems.pl.newItems.contains(args[0])) return false;
                ItemStack item = CustomItems.getItem(args[0]);
                if (item != null) ((Player) sender).getInventory().addItem(item);
                return true;
            }
            return false;
        }
        sender.sendMessage("§cYou have to be a player to execute this command.");
        return true;
    }
}
