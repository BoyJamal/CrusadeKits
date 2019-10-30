package me.boyjamal.kits.utils;

import org.bukkit.inventory.ItemStack;

public class KitItem {
	
	private String name;
	private ItemCreator access;
	private ItemCreator error;
	private ItemCreator cooldown;

	public KitItem(String name, ItemCreator access, ItemCreator error, ItemCreator cooldown)
	{
		this.name = name;
		this.access = access;
		this.error = error;
		this.cooldown = cooldown;
	}
	
	public KitItem(String name, ItemCreator access)
	{
		this.name = name;
		this.access = access;
	}
	
	public String getName()
	{
		return name;
	}
	
	public ItemCreator getAccess()
	{
		return access;
	}
	
	public ItemCreator getError()
	{
		return error;
	}
	
	public ItemCreator getCooldown()
	{
		return cooldown;
	}

}
