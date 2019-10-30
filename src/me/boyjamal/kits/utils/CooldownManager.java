package me.boyjamal.kits.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.boyjamal.kits.Main;

public class CooldownManager {
	
	private static HashMap<String,List<CooldownUtil>> activeCooldowns = new HashMap<>();
	
	public static void loadCooldowns()
	{
		if (StorageManager.getCooldownsYML() != null)
		{
			FileConfiguration cooldownYML = StorageManager.getCooldownsYML();
			ConfigurationSection cooldown = cooldownYML.getConfigurationSection("cooldowns");
			if (cooldown != null)
			{
				for (String keys : cooldown.getKeys(false))
				{
					List<CooldownUtil> pCooldowns = new ArrayList<>();
					ConfigurationSection playerKits = cooldown.getConfigurationSection(keys + ".kits");
					if (playerKits != null)
					{
						for (String kitsDetails : playerKits.getKeys(false))
						{
							int timeLeft;
							try {
								timeLeft = playerKits.getInt(kitsDetails + ".timeLeft");
							} catch (Exception e) {
								continue;
							}
							pCooldowns.add(new CooldownUtil(kitsDetails,timeLeft));
						}
						activeCooldowns.put(keys, pCooldowns);
					}
				}
				
				try {
					StorageManager.getCooldownsYML().set("cooldowns", null);
					StorageManager.getCooldownsYML().save(StorageManager.getCooldownFile());
				} catch (Exception e) {
					return;
				}
			}
			
		} else {
			for (int i = 0; i<=3; i++)
			{
				Bukkit.getLogger().severe("Plugin shutting down. Cooldown file not loaded in!");
			}
			Bukkit.getPluginManager().disablePlugin(Main.getInstance());
			return;
		}
	}
	
	
	public static void saveCooldowns()
	{
		if (!(activeCooldowns.isEmpty()))
		{
			if (StorageManager.getCooldownsYML() != null && StorageManager.getCooldownsYML().getConfigurationSection("cooldowns") != null)
			{
				try {
					StorageManager.getCooldownsYML().set("cooldowns", null);
					StorageManager.getCooldownsYML().save(StorageManager.getCooldownFile());
				} catch (Exception e) {
					return;
				}
			}
			
			FileConfiguration config = StorageManager.getCooldownsYML();
			ConfigurationSection cooldowns = config.getConfigurationSection("cooldowns");
			if (cooldowns == null)
			{
				config.createSection("cooldowns");
			}
			
			for (Entry<String,List<CooldownUtil>> set : activeCooldowns.entrySet())
			{
				ConfigurationSection uuid = cooldowns.createSection(set.getKey());
				for (CooldownUtil each : set.getValue())
				{
					int timeLeft = MainUtils.getTimeLeft(each);
					uuid.set(each.getName()+".timeLeft", timeLeft);
				}
			}
			
			try {
				config.save(StorageManager.getCooldownFile());
			} catch (Exception e) {
				return;
			}
		}
	}
	
	public static void addCooldown(String kitName, Player p)
	{
		
	}
	
	public static void removeCooldown(String kitName, Player p)
	{
		
	}
	
	public static HashMap<String,List<CooldownUtil>> getCooldowns()
	{
		return activeCooldowns;
	}

}
