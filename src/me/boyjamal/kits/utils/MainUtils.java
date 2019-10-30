package me.boyjamal.kits.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MainUtils {

	public static String chatColor(String message)
	{
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
	public static List<String> listColor(List<String> messages)
	{
		List<String> translated = new ArrayList<>();
		for (String line : messages)
		{
			translated.add(chatColor(line));
		}
		return translated;
	}
	
	public static int getTimeLeft(CooldownUtil util)
	{
		return (int)((System.currentTimeMillis()+TimeUnit.SECONDS.toMillis(util.getTimeLeft()))-util.getLength());
	}
	
	public static Inventory getKitInventory(Player p)
	{
		if (StorageManager.getKitGui() != null)
		{
			GuiManager mang = StorageManager.getKitGui();
			Inventory inv = Bukkit.createInventory(null, mang.getSlots(),MainUtils.chatColor(mang.getName()));
			for (KitUtil items : mang.getItems())
			{
				if (items.hasPermission())
				{
					if (p.hasPermission(items.getPermission()))
					{
						if (CooldownManager.getCooldowns().containsKey(p.getUniqueId().toString()))
						{
							boolean next = false;
							for (CooldownUtil each : CooldownManager.getCooldowns().get(p.getUniqueId().toString()))
							{
								if (each.getName().equalsIgnoreCase(items.getName()))
								{
									if (items.getItems().getCooldown() != null)
									{
										inv.setItem(items.getSlot(), items.getItems().getCooldown().getItem());
										next = true;
									} else {
										continue;
									}
								}
							}
							
							if (next)
							{
								continue;
							}
							
							if (items.getItems().getAccess() != null)
							{
								inv.setItem(items.getSlot(), items.getItems().getAccess().getItem());
								continue;
							} else {
								continue;
							}
						} else {
							if (items.getItems().getAccess() != null)
							{
								inv.setItem(items.getSlot(), items.getItems().getAccess().getItem());
								continue;
							} else {
								continue;
							}
						}
					} else {
						if (items.getItems().getError() != null)
						{
							inv.setItem(items.getSlot(), items.getItems().getError().getItem());
							continue;
						} else {
							continue;
						}
					}
				} else {
					if (items.getItems().getAccess() != null)
					{
						inv.setItem(items.getSlot(), items.getItems().getAccess().getItem());
						continue;
					} else {
						continue;
					}
				}
			}
			return inv;
		} else {
			return null;
		}
	}
	
}
