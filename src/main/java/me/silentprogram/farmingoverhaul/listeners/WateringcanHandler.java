package me.silentprogram.farmingoverhaul.listeners;

import me.silentprogram.farmingoverhaul.FarmingOverhaul;
import me.silentprogram.farmingoverhaul.items.ItemHandler;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.RayTraceResult;

import java.util.concurrent.ThreadLocalRandom;

public class WateringcanHandler {
	
	public static void activateWateringCan(PlayerToggleSneakEvent event, FarmingOverhaul plugin) {
		//Check if player is sneaking first
		if (!event.isSneaking()) return;
		//Initialize most variables
		Player plr = event.getPlayer();
		Location loc = plr.getLocation();
		ThreadLocalRandom random = ThreadLocalRandom.current();
		NamespacedKey waterKey = new NamespacedKey(plugin, "waterLeft");
		NamespacedKey canKey = new NamespacedKey(plugin, "isWateringCan");
		
		//Check if player is holder watering can
		if (plr.getInventory().getItemInMainHand() == null) return;
		if (plr.getInventory().getItemInMainHand().getType() == Material.AIR) return;
		if (!plr.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(canKey, PersistentDataType.BYTE))
			return;
		
		//Player is holding watering can, get can data
		ItemStack can = plr.getInventory().getItemInMainHand();
		ItemMeta canMeta = can.getItemMeta();
		PersistentDataContainer canData = canMeta.getPersistentDataContainer();
		
		int waterAmount = canData.get(waterKey, PersistentDataType.INTEGER);
		if (waterAmount < 1) {
			plr.sendMessage(ChatColor.AQUA + "You're all out of water!\n" + ChatColor.BLUE + "Right click a water block to refill!");
			return;
		}
		
		
		for (int x = loc.getBlockX() - 2; x <= loc.getBlockX() + 2; x++) {
			for (int y = loc.getBlockY() - 2; y <= loc.getBlockY() + 2; y++) {
				for (int z = loc.getBlockZ() - 2; z <= loc.getBlockZ() + 2; z++) {
					Block block = loc.getWorld().getBlockAt(x, y, z);
					if (!(block.getType() == Material.CARROT || block.getType() == Material.WHEAT || block.getType() == Material.BEETROOTS || block.getType() == Material.POTATOES))
						continue;
					PersistentDataContainer chunkData = block.getChunk().getPersistentDataContainer();
					NamespacedKey blockKey = new NamespacedKey(plugin, block.getX() + "_" + block.getY() + "_" + block.getZ() + "_wateredTime");
					//Put wateringcan stuff here (apply this NBT <block.getX() + "_" + block.getY() + "_" + block.getZ() + "_wateredTime">)
					canData.set(waterKey, PersistentDataType.INTEGER, waterAmount - 1);
					canMeta.setLore(ItemHandler.getCanLore(waterAmount - 1));
					can.setItemMeta(canMeta);
					for (int i = 0; i < 10; i++) {
						block.getWorld().spawnParticle(Particle.WATER_SPLASH, block.getX() + random.nextDouble(0.99), block.getY() + random.nextDouble(0.50), block.getZ() + random.nextDouble(0.99), 1);
					}
					if (chunkData.has(blockKey, PersistentDataType.LONG)) continue;
					chunkData.set(blockKey, PersistentDataType.LONG, System.currentTimeMillis());
				}
			}
		}
	}
	
	public static void refillWateringCan(PlayerInteractEvent event, FarmingOverhaul plugin) {
		Player plr = event.getPlayer();
		RayTraceResult rayTraceResult = plr.rayTraceBlocks(3, FluidCollisionMode.ALWAYS);
		NamespacedKey waterKey = new NamespacedKey(plugin, "waterLeft");
		NamespacedKey canKey = new NamespacedKey(plugin, "isWateringCan");
		
		//Check if player is holder watering can
		if (event.getHand() != EquipmentSlot.HAND) return;
		if (rayTraceResult == null) return;
		Block block = rayTraceResult.getHitBlock();
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if (plr.getInventory().getItemInMainHand() == null) return;
		if (plr.getInventory().getItemInMainHand().getType() == Material.AIR) return;
		if (!plr.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(canKey, PersistentDataType.BYTE))
			return;
		if (block == null) return;
		if (!(block.getType() == Material.WATER)) return;
		
		
		//Player is holding watering can, get can data
		ItemStack can = plr.getInventory().getItemInMainHand();
		ItemMeta canMeta = can.getItemMeta();
		PersistentDataContainer canData = canMeta.getPersistentDataContainer();
		
		canData.set(waterKey, PersistentDataType.INTEGER, 10);
		canMeta.setLore(ItemHandler.getCanLore(10));
		can.setItemMeta(canMeta);
		
		plr.sendMessage(ChatColor.AQUA + "Filled up bucket!");
	}
}