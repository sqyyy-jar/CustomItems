package dev.sqyyy.customitems;

import org.bukkit.Material;

public class ItemChoice {

    public boolean isCustom = false;
    public String key = "";
    public Material material = null;

    public ItemChoice(String key) {
        isCustom = true;
        this.key = key;
    }
    public ItemChoice(Material material) {
        isCustom = false;
        this.material = material;
    }
}
