package me.silentprogram.farmingoverhaul;

import me.silentprogram.farmingoverhaul.configstuff.ConfigTalker;
import me.silentprogram.farmingoverhaul.listeners.Listeners;
import me.silentprogram.farmingoverhaul.recipes.RecipeHandler;
import org.bukkit.plugin.java.JavaPlugin;

/*

 -- Means it is finished

Animals:
  Breeding animals has benefit
   -- You cant breed the same family together
  Animals require space to live (maybe?)
Farming:
  Benefit to farming, probably potion effects/new food items
    Add breadier bread
  Enchant to break 4x4 crops
  Also find a way to make hoes useful
   -- If you dont take care of crops they dont grow
    --   To achieve this, add a NBT tag of the current time, or minecraft day.  And let them only water plants once a day
     --    then add a listener to check when a plant grows(BlockGrowEvent) check if it has been watered in the past day if not
      --   cancel the event, if it has let it grow, and reset the timer
     -- Add watering can or smthng,
      Make watering can require water
    Add a end-game block that auto-waters probably gained through farming or just a nether star
   -- If a plant is inside, it affects how it grows
     -- Use Block.getLightFromSky()
Possibly add:
  Falling trees enchant

 */


public final class FarmingOverhaul extends JavaPlugin {
	ConfigTalker configTalker;
	
	@Override
	public void onEnable() {
		startup();
	}
	
	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
	
	public void startup() {
		//Initialize config
		saveDefaultConfig();
		//Register my config talker
		configTalker = new ConfigTalker(this);
		configTalker.initializeItems();
		//Register listener
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
		//If the watering can is disabled then ignore this
		new RecipeHandler(this).registerRecipes();
	}
	
	public ConfigTalker getConfigTalker(){
		return configTalker;
	}
}