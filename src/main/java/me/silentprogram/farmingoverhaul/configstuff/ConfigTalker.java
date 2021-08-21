package me.silentprogram.farmingoverhaul.configstuff;

import me.silentprogram.farmingoverhaul.FarmingOverhaul;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigTalker {
	FarmingOverhaul plugin;
	FileConfiguration config;
	
	public ConfigTalker(FarmingOverhaul plugin) {
		this.plugin = plugin;
		config = plugin.getConfig();
	}
	
	public int getPlantDelay() {
		if(config.getBoolean("enable-plant-timer")){
			return config.getInt("plant-delay") * 60000;
		}
		return 1;
	}
	public boolean isCanEnabled(){
		return config.getBoolean("wateringcan-enabled");
	}
	public boolean isAntiInbreedEnabled(){
		return config.getBoolean("anti-inbreeding-enabled");
	}
	public boolean isSkyLightEnabled(){
		return config.getBoolean("sky-light-enabled");
	}
}
