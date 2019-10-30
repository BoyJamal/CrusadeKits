package me.boyjamal.kits.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import me.boyjamal.kits.utils.KitUtil;
import me.boyjamal.kits.utils.MainUtils;
import me.boyjamal.kits.utils.StorageManager;

public class KitClick implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent e)
	{
		if (StorageManager.getKitGui() != null)
		{
			if (e.getInventory().getName().equalsIgnoreCase(MainUtils.chatColor(StorageManager.getKitGui().getName())))
			{
				e.setCancelled(true);
				for (KitUtil items : StorageManager.getKitGui().getItems())
				{
					if (items.getSlot() == e.getSlot())
					{
						//check status of clicked item
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e)
	{
		if (StorageManager.getKitGui() != null)
		{
			if (e.getInventory().getName().equalsIgnoreCase(MainUtils.chatColor(StorageManager.getKitGui().getName())))
			{
			}
		}
	}

}
