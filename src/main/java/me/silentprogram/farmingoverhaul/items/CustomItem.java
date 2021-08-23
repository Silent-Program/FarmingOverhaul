package me.silentprogram.farmingoverhaul.items;

import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface CustomItem {
	
	String getName();
	
	String getRow1();
	
	String getRow2();
	
	String getRow3();
	
	Map<Character, Object> getIngredients();
	
	ItemStack getItem();
	
	boolean isEnabled();
}
