package me.silentprogram.farmingoverhaul.configstuff;

import me.silentprogram.farmingoverhaul.FarmingOverhaul;
import me.silentprogram.farmingoverhaul.items.BreadierBread;
import me.silentprogram.farmingoverhaul.items.CustomItem;
import me.silentprogram.farmingoverhaul.items.WateringCan;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class ConfigTalker {
	public Map<CustomItem, Boolean> items = new HashMap<>();
	public FileConfiguration config;
	FarmingOverhaul plugin;
	
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
	
	public void initializeItems() {
		items.put(new WateringCan(plugin), isCanEnabled());
		items.put(new BreadierBread(plugin), true);
	}
}
