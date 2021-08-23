package me.silentprogram.farmingoverhaul.listeners;

import me.silentprogram.farmingoverhaul.FarmingOverhaul;
import org.bukkit.Effect;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class EntityListener {
	//Create namespaced key once here
	private final NamespacedKey fatherKey;
	private final NamespacedKey motherKey;
	
	public EntityListener(FarmingOverhaul plugin) {
		//Assign values to the variables
		fatherKey = new NamespacedKey(plugin, "father");
		motherKey = new NamespacedKey(plugin, "mother");
	}
	
	public void assignParentsToChild(EntityBreedEvent event) {
		//Create these so i dont have to type them over and over again.
		PersistentDataContainer fatherData = event.getFather().getPersistentDataContainer();
		PersistentDataContainer motherData = event.getMother().getPersistentDataContainer();
		PersistentDataContainer childData = event.getEntity().getPersistentDataContainer();
		Random randNum = new Random();
		
		//Check if parents have null parents
		if (isNull(fatherData.get(fatherKey, PersistentDataType.STRING), fatherData.get(motherKey, PersistentDataType.STRING))) {
			assignUUID(fatherData);
		}
		if (isNull(motherData.get(fatherKey, PersistentDataType.STRING), motherData.get(motherKey, PersistentDataType.STRING))) {
			assignUUID(motherData);
		}
		
		//Check if parents are related, if so YEET THE CHILD
		if (areRelated(fatherData, motherData) || isNull(fatherData.get(fatherKey, PersistentDataType.STRING), fatherData.get(motherKey, PersistentDataType.STRING), motherData.get(fatherKey, PersistentDataType.STRING), motherData.get(motherKey, PersistentDataType.STRING))) {
			event.setCancelled(true);
			event.getFather().getLocation().getWorld().playEffect(event.getFather().getLocation(), Effect.EXTINGUISH, 1);
			return;
		}
		//Give the child a random key from father and mother. (possibly rework this later as a child can have no common keys with sibling)
		if (randNum.nextBoolean()) {
			childData.set(fatherKey, PersistentDataType.STRING, fatherData.get(fatherKey, PersistentDataType.STRING));
		} else {
			childData.set(fatherKey, PersistentDataType.STRING, fatherData.get(motherKey, PersistentDataType.STRING));
		}
		if (randNum.nextBoolean()) {
			childData.set(motherKey, PersistentDataType.STRING, motherData.get(fatherKey, PersistentDataType.STRING));
		} else {
			childData.set(motherKey, PersistentDataType.STRING, motherData.get(motherKey, PersistentDataType.STRING));
		}
	}
	
	//Assign a father and mother key to entity's on spawn
	public void assignParents(EntitySpawnEvent event) {
		//Create variables
		Entity entity = event.getEntity();
		PersistentDataContainer entityData = entity.getPersistentDataContainer();
		//Check if spawned entity is of type==breedable
		if (entity instanceof Breedable) {
			//Check if the entity is a baby
			if (!(((Breedable) entity).canBreed())) return;
			entityData.set(fatherKey, PersistentDataType.STRING, UUID.randomUUID().toString());
			entityData.set(motherKey, PersistentDataType.STRING, UUID.randomUUID().toString());
		}
	}
	
	//Calculate if two mobs are related
	private boolean areRelated(PersistentDataContainer fatherData, PersistentDataContainer motherData) {
		List<String> fatherStrings = new ArrayList<>();
		List<String> motherStrings = new ArrayList<>();
		fatherStrings.add(fatherData.get(fatherKey, PersistentDataType.STRING));
		fatherStrings.add(fatherData.get(motherKey, PersistentDataType.STRING));
		motherStrings.add(motherData.get(motherKey, PersistentDataType.STRING));
		motherStrings.add(motherData.get(fatherKey, PersistentDataType.STRING));
		for (String i : fatherStrings) {
			if (motherStrings.contains(i)) return true;
		}
		return false;
	}
	
	//CHeck if any values are null
	private boolean isNull(Object... args) {
		for (Object arg : args) {
			if (arg == null) return true;
		}
		return false;
	}
	
	private void assignUUID(PersistentDataContainer... persistentDataContainers) {
		for (PersistentDataContainer entityData : persistentDataContainers) {
			entityData.set(fatherKey, PersistentDataType.STRING, UUID.randomUUID().toString());
			entityData.set(motherKey, PersistentDataType.STRING, UUID.randomUUID().toString());
		}
	}
	
}