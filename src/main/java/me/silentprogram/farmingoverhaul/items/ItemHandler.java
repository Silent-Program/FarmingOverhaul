package me.silentprogram.farmingoverhaul.items;

import me.silentprogram.farmingoverhaul.FarmingOverhaul;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

/*
Replace these methods with classes connected to an interface, and make this class a item builder using methods
from said interface
 */

public class ItemHandler {
	//Wateringcan Related things
	public static ItemStack getWateringCan(FarmingOverhaul plugin) {
		ItemStack wateringCan = new ItemStack(Material.CLOCK);
		ItemMeta canMeta = wateringCan.getItemMeta();
		PersistentDataContainer canData = canMeta.getPersistentDataContainer();
		List<String> lore = getCanLore(10);
		
		canData.set(new NamespacedKey(plugin, "isWateringCan"), PersistentDataType.BYTE, (byte) 1);
		canData.set(new NamespacedKey(plugin, "waterLeft"), PersistentDataType.INTEGER, 10);
		
		canMeta.setDisplayName(ChatColor.DARK_BLUE + "Watering Can");
		
		canMeta.setLore(lore);
		wateringCan.setItemMeta(canMeta);
		
		return wateringCan;
	}
	
	public static List<String> getCanLore(int waterAmount) {
		List<String> lore = new ArrayList<>();
		
		lore.add("");
		lore.add(ChatColor.AQUA + "Hold shift near crops to water them!");
		lore.add("");
		lore.add(ChatColor.AQUA + "Held water: " + ChatColor.GRAY + waterAmount + "/10");
		return lore;
	}
	
	//Food stuff
	public static ItemStack getBreadierBread(FarmingOverhaul plugin) {
		ItemStack item = new ItemStack(Material.CLOCK);
		ItemMeta itemMeta = item.getItemMeta();
		PersistentDataContainer itemData = itemMeta.getPersistentDataContainer();
		
		itemMeta.setDisplayName("Breadier Bread");
		
		item.setItemMeta(itemMeta);
		return item;
	}
}
