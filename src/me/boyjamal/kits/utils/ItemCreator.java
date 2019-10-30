package me.boyjamal.kits.utils;

import java.util.List;

import org.bukkit.inventory.ItemStack;

public class ItemCreator {
	
	private ItemStack item;
	private List<String> actions;
	
	public ItemCreator(ItemStack item, List<String> actions)
	{
		this.item = item;
		this.actions = actions;
	}
	
	public ItemStack getItem()
	{
		return item;
	}
	
	public List<String> getActions()
	{
		return actions;
	}

}
