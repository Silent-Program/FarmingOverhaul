package me.silentprogram.farmingoverhaul.items;

import me.silentprogram.farmingoverhaul.FarmingOverhaul;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class WateringCan extends ItemStack implements CustomItem {
	
	public WateringCan(FarmingOverhaul plugin) {
		super(Material.CLOCK);
		
		ItemMeta canMeta = this.getItemMeta();
		PersistentDataContainer canData = canMeta.getPersistentDataContainer();
		List<String> lore = ItemHandler.getCanLore(10);
		canData.set(new NamespacedKey(plugin, "isWateringCan"), PersistentDataType.BYTE, (byte) 1);
		canData.set(new NamespacedKey(plugin, "waterLeft"), PersistentDataType.INTEGER, 10);
		
		canMeta.setLore(lore);
		canMeta.setDisplayName(ChatColor.DARK_BLUE + "Watering Can");
		
		this.setItemMeta(canMeta);
	}
	
	@Override
	public String getName() {
		return "WateringCan";
	}
}
