package me.silentprogram.farmingoverhaul.listeners;

import me.silentprogram.farmingoverhaul.FarmingOverhaul;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class Listeners implements Listener {
	FarmingOverhaul plugin;
	EntityHandler entityHandler;
	
	public Listeners(FarmingOverhaul plugin) {
		this.plugin = plugin;
		entityHandler = new EntityHandler(plugin);
	}
	
	//Plant related listeners
	@EventHandler
	public void onBlockGrow(BlockGrowEvent event) {
		if (plugin.configTalker.isCanEnabled())
			PlantHandler.plantWateredHandler(event, plugin);
		if(plugin.configTalker.isSkyLightEnabled())
			PlantHandler.plantLightHandler(event);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		PlantHandler.removeData(event, plugin);
	}
	
	//Player related listeners
	@EventHandler
	public void onPlayerSneak(PlayerToggleSneakEvent event) {
		if (plugin.configTalker.isCanEnabled())
			WateringcanHandler.activateWateringCan(event, plugin);
	}
	
	@EventHandler
	public void onPlayerRightClick(PlayerInteractEvent event) {
		if (plugin.configTalker.isCanEnabled())
			WateringcanHandler.refillWateringCan(event, plugin);
	}
	
	//Entity related listeners
	@EventHandler
	public void onEntityBreed(EntityBreedEvent event) {
		if (plugin.configTalker.isAntiInbreedEnabled())
			entityHandler.assignParentsToChild(event);
	}
	
	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent event) {
		entityHandler.assignParents(event);
	}
	
}
