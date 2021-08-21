package me.silentprogram.farmingoverhaul.items;

import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.List;

public interface CustomItem {
	
	public String getName();
	
	public HashMap<NamespacedKey, Object> getData();
	
	public List<String> getLore();
	
	
	
}
