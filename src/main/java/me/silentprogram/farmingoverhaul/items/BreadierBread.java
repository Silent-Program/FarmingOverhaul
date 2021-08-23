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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreadierBread extends ItemStack implements CustomItem{
	
	public BreadierBread(FarmingOverhaul plugin) {
		super(Material.BREAD);
		
		ItemMeta itemMeta = this.getItemMeta();
		PersistentDataContainer itemData = itemMeta.getPersistentDataContainer();
		List<String> lore = new ArrayList<>();
		itemData.set(new NamespacedKey(plugin, "isBreadierBread"), PersistentDataType.BYTE, (byte) 1);
		
		lore.add("");
		lore.add("Its breadier bread!");
		
		itemMeta.setLore(lore);
		itemMeta.setDisplayName(ChatColor.DARK_BLUE + "Breadier Bread");
		
		this.setItemMeta(itemMeta);
	}
	
	@Override
	public String getName() {
		return "breadier-bread";
	}
	
	@Override
	public String getRow1() {
		return "   ";
	}
	
	@Override
	public String getRow2() {
		return "BBB";
	}
	
	@Override
	public String getRow3() {
		return "   ";
	}
	
	@Override
	public Map<Character, Object> getIngredients() {
		Map<Character, Object> ingredients = new HashMap<>();
		
		ingredients.put('B', Material.BREAD);
		
		return ingredients;
	}
	
	@Override
	public ItemStack getItem() {
		return this;
	}
}
