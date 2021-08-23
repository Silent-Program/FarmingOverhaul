package me.silentprogram.farmingoverhaul.items;

import me.silentprogram.farmingoverhaul.FarmingOverhaul;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WateringCan extends ItemStack implements CustomItem {
	boolean isEnabled;
	
	public WateringCan(FarmingOverhaul plugin) {
		super(Material.CLOCK);
		isEnabled = plugin.getConfigTalker().isCanEnabled();
		
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
		return "watering-can";
	}
	
	@Override
	public String getRow1() {
		return "   ";
	}
	
	@Override
	public String getRow2() {
		return "IWB";
	}
	
	@Override
	public String getRow3() {
		return "III";
	}
	
	
	@Override
	public Map<Character, Object> getIngredients() {
		Map<Character, Object> ingredients = new HashMap<>();
		
		ingredients.put('B', Material.IRON_BARS);
		ingredients.put('I', Material.IRON_INGOT);
		ingredients.put('W', Material.WATER_BUCKET);
		return ingredients;
	}
	
	@Override
	public ItemStack getItem() {
		return this;
	}
	
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
}
