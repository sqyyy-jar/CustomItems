package dev.sqyyy.customitems;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import dev.sqyyyapis.storageexplorers.GsonExplorer;
import dev.sqyyyapis.storageexplorers.GsonFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class CustomItems extends JavaPlugin {

    final List<String> newItems = new ArrayList<>();
    final String path = "./plugins/CustomItems/config.json";
    GsonFile config;

    @Override
    public void onEnable() {
        baseInit();
    }

    @Override
    public void onDisable() {
        baseUnload();
    }

    public final void baseInit() {
        File file = new File(path);
        if (!file.exists()) {
            if (!file.getParentFile().mkdirs()) {
                this.getLogger().info("Could not create path to config file!");
                this.getServer().getPluginManager().disablePlugin(CustomItems.getPlugin(CustomItems.class));
            }
            try {
                file.createNewFile();
                GsonExplorer.createBaseGson(path);
            } catch (IOException ioException) {
                this.getLogger().info("Could not create config file!");
                this.getServer().getPluginManager().disablePlugin(CustomItems.getPlugin(CustomItems.class));
            }
        }

        config = GsonExplorer.getGson(path);
        if (config.has("Items")) {
            for (JsonElement param : config.getAsJsonArray("Items")) {
                newItems.add(param.getAsString());
            }
        } else {
            config.add("Items", new JsonArray());
        }
    }

    public final void baseUnload() {
        if (config != null) {
            config.save(true);
        }
    }
}
