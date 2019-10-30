package me.boyjamal.kits;

import org.bukkit.plugin.java.JavaPlugin;

import me.boyjamal.kits.commands.KitCommand;
import me.boyjamal.kits.utils.CooldownManager;
import me.boyjamal.kits.utils.StorageManager;

public class Main extends JavaPlugin {

	private static Main instance;
	
	public void onEnable()
	{
		instance = this;
		StorageManager.loadFiles();
		
		registerCommands();
		registerListeners();
	}
	
	public void onDisable()
	{
		CooldownManager.saveCooldowns();
		//end events
	}
	
	public void registerCommands()
	{
		getCommand("kits").setExecutor(new KitCommand());
	}
	
	public void registerListeners()
	{
		//register click events
		//register gui events
	}
	
	public static Main getInstance()
	{
		return instance;
	}
}
