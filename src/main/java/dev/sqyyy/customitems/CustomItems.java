package dev.sqyyy.customitems;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import dev.sqyyy.customitems.commands.GetCommand;
import dev.sqyyyapis.storageexplorers.GsonExplorer;
import dev.sqyyyapis.storageexplorers.GsonFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    }

    @Override
    public void onDisable() {
        baseUnload();
    }

    public final void baseInit() {
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
}
