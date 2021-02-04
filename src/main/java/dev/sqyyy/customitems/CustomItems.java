package dev.sqyyy.customitems;

import dev.sqyyyapis.storageexplorers.GsonExplorer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class CustomItems extends JavaPlugin {

    final String path = "./plugins/CustomItems/config.json";

    @Override
    public void onEnable() {
        baseInit();
    }

    @Override
    public void onDisable() {
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

        
    }
}
