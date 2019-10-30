package me.boyjamal.kits.utils;

import java.util.List;

public class KitUtil {

	private Long cooldown;
	private boolean hasPermission;
	private String permission;
	private String name;
	private int slot;
	private KitItem items;
	
	public KitUtil(Long cooldown, boolean hasPermission, String permission, String name, int slot, KitItem items)
	{
		this.cooldown = cooldown;
		this.hasPermission = hasPermission;
		this.permission = permission;
		this.name = name;
		this.slot = slot;
		this.items = items;
	}
	
	public int getSlot()
	{
		return slot;
	}
	
	public Long getCooldown()
	{
		return cooldown;
	}
	
	public boolean hasPermission()
	{
		return hasPermission;
	}
	
	public String getPermission()
	{
		return permission;
	}
	
	public String getName()
	{
		return name;
	}
	
	public KitItem getItems()
	{
		return items;
	}
	
}
