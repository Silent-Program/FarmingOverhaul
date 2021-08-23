package me.silentprogram.farmingoverhaul.configstuff;

import me.silentprogram.farmingoverhaul.FarmingOverhaul;
import me.silentprogram.farmingoverhaul.items.BreadierBread;
import me.silentprogram.farmingoverhaul.items.CustomItem;
import me.silentprogram.farmingoverhaul.items.WateringCan;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class ConfigTalker {
	public FileConfiguration config;
	Map<CustomItem, Boolean> items = new HashMap<>();
	FarmingOverhaul plugin;
	
	WateringCan wateringCan;
	BreadierBread breadierBread;
	
	public ConfigTalker(FarmingOverhaul plugin) {
		this.plugin = plugin;
		config = plugin.getConfig();
		initializeItems();
	}
	
	public int getPlantDelay() {
		if (config.getBoolean("enable-plant-timer")) {
			return config.getInt("plant-delay") * 60000;
		}
		return 1;
	}
	
	public boolean isCanEnabled() {
		return config.getBoolean("wateringcan-enabled");
	}
	
	public boolean isAntiInbreedEnabled() {
		return config.getBoolean("anti-inbreeding-enabled");
	}
	
	public boolean isSkyLightEnabled() {
		return config.getBoolean("sky-light-enabled");
	}
	
	public Map<CustomItem, Boolean> getItems() {
		return items;
	}
	
	public boolean isItemEnabled(String itemName){
		return config.getBoolean(itemName);
	}
	
	public void initializeItems() {
		breadierBread = new BreadierBread(plugin);
		wateringCan = new WateringCan(plugin);
		items.put(wateringCan, isCanEnabled());
		items.put(breadierBread, isItemEnabled(breadierBread.getName()));
	}
}
