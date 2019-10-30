package me.boyjamal.kits.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.boyjamal.kits.utils.CooldownManager;
import me.boyjamal.kits.utils.GuiManager;
import me.boyjamal.kits.utils.KitItem;
import me.boyjamal.kits.utils.KitUtil;
import me.boyjamal.kits.utils.MainUtils;
import me.boyjamal.kits.utils.StorageManager;

public class KitCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			return true;
		}
		Player p = (Player)sender;
		if (MainUtils.getKitInventory(p) != null)
		{
			//run event
			p.openInventory(MainUtils.getKitInventory(p));
			return true;
		} else {
			p.sendMessage(MainUtils.chatColor("&c&lERROR&7&o Kit Gui currently disabled. Please contact an Admin!"));
			return true;
		}
	}

}
