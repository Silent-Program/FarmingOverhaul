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
	EntityListener entityHandler;
	
	public Listeners(FarmingOverhaul plugin) {
		this.plugin = plugin;
		entityHandler = new EntityListener(plugin);
	}
	
	//Plant related listeners
	@EventHandler
	public void onBlockGrow(BlockGrowEvent event) {
		if (plugin.getConfigTalker().isCanEnabled())
			PlantListener.plantWateredHandler(event, plugin);
		if(plugin.getConfigTalker().isSkyLightEnabled())
			PlantListener.plantLightHandler(event);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		PlantListener.removeData(event, plugin);
	}
	
	//Player related listeners
	@EventHandler
	public void onPlayerSneak(PlayerToggleSneakEvent event) {
		if (plugin.getConfigTalker().isCanEnabled())
			ItemListener.activateWateringCan(event, plugin);
	}
	
	@EventHandler
	public void onPlayerRightClick(PlayerInteractEvent event) {
		if (plugin.getConfigTalker().isCanEnabled())
			ItemListener.refillWateringCan(event, plugin);
	}
	
	//Entity related listeners
	@EventHandler
	public void onEntityBreed(EntityBreedEvent event) {
		if (plugin.getConfigTalker().isAntiInbreedEnabled())
			entityHandler.assignParentsToChild(event);
	}
	
	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent event) {
		entityHandler.assignParents(event);
	}
	
}
