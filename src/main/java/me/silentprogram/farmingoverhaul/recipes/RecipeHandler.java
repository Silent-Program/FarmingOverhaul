package me.silentprogram.farmingoverhaul.recipes;

import me.silentprogram.farmingoverhaul.FarmingOverhaul;
import me.silentprogram.farmingoverhaul.items.CustomItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class RecipeHandler {
	FarmingOverhaul plugin;
	
	public RecipeHandler(FarmingOverhaul plugin) {
		this.plugin = plugin;
	}
	
	public void registerRecipes() {
		//Loop through items and create a recipe
		for (CustomItem item : plugin.getConfigTalker().getItems()) {
			if (!item.isEnabled()) continue;
			ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, item.getName()), item.getItem());
			recipe.shape(item.getRow1(), item.getRow2(), item.getRow3());
			//Loop through the ingredients, and set those
			for (Character key : item.getIngredients().keySet()) {
				if (item.getIngredients().get(key) instanceof ItemStack) {
					recipe.setIngredient(key, new RecipeChoice.ExactChoice((ItemStack) item.getIngredients().get(key)));
				} else if (item.getIngredients().get(key) instanceof Material) {
					recipe.setIngredient(key, (Material) item.getIngredients().get(key));
				}
			}
			plugin.getServer().addRecipe(recipe);
		}
	}
}
