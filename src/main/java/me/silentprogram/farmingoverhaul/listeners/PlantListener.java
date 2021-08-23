package me.silentprogram.farmingoverhaul.listeners;

import me.silentprogram.farmingoverhaul.FarmingOverhaul;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlantListener {
	
	
	public static void plantWateredHandler(BlockGrowEvent event, FarmingOverhaul plugin) {
		
		Block block = event.getBlock();
		PersistentDataContainer chunkData = block.getChunk().getPersistentDataContainer();
		long delay = plugin.getConfigTalker().getPlantDelay();
		NamespacedKey blockKey = new NamespacedKey(plugin, block.getX() + "_" + block.getY() + "_" + block.getZ() + "_wateredTime");
		//Check if block is specific type of plant if not, exit the event
		if (!(block.getType() == Material.CARROT || block.getType() == Material.WHEAT || block.getType() == Material.BEETROOTS || block.getType() == Material.POTATOES))
			return;
		if (!(chunkData.has(blockKey, PersistentDataType.LONG))) {
			event.setCancelled(true);
			return;
		}
		if ((chunkData.get(blockKey, PersistentDataType.LONG) + delay) > System.currentTimeMillis()) {
			event.setCancelled(true);
			return;
		}
		chunkData.remove(blockKey);
		
	}
	
	
	public static void removeData(BlockBreakEvent event, FarmingOverhaul plugin) {
		Block block = event.getBlock();
		PersistentDataContainer chunkData = block.getChunk().getPersistentDataContainer();
		NamespacedKey blockKey = new NamespacedKey(plugin, block.getX() + "_" + block.getY() + "_" + block.getZ() + "_wateredTime");
		
		if (!(block.getType() == Material.CARROT || block.getType() == Material.WHEAT || block.getType() == Material.BEETROOTS || block.getType() == Material.POTATOES))
			return;
		if (!(chunkData.has(blockKey, PersistentDataType.LONG)))
			return;
		
		chunkData.remove(blockKey);
	}
	public static void plantLightHandler(BlockGrowEvent event){
		if(event.getBlock().getLightFromSky() < 1){
			event.setCancelled(true);
		}
	}
	
}
