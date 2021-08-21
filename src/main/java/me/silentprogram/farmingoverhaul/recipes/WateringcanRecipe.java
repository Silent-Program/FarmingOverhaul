package me.silentprogram.farmingoverhaul.recipes;

import me.silentprogram.farmingoverhaul.FarmingOverhaul;
import me.silentprogram.farmingoverhaul.items.ItemHandler;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class WateringcanRecipe {
	FarmingOverhaul plugin;
	
	public WateringcanRecipe(FarmingOverhaul plugin){
		this.plugin = plugin;
	}

	
	public ShapedRecipe createCanRecipe(){
		ItemStack item = ItemHandler.getWateringCan(plugin);
		ShapedRecipe canRecipe = new ShapedRecipe(new NamespacedKey(plugin, "wateringCan"), item);
		
		canRecipe.shape("   ",
		                "IWB",
		                "III");
		
		canRecipe.setIngredient('I', Material.IRON_INGOT);
		canRecipe.setIngredient('B', Material.IRON_BARS);
		canRecipe.setIngredient('W', Material.WATER_BUCKET);
		
		
		return canRecipe;
	}
}
