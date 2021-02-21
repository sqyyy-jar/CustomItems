package dev.sqyyy.customitems;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AdvancedRecipe {
    public static List<AdvancedRecipe> recipes = new ArrayList<>();
    public AdvancedRecipe(ItemStack itemStack) {
        recipes.add(this);
        this.result = itemStack;
    }
    public ItemChoice[] keyedItems = new ItemChoice[9];
    public ItemStack result;
}
