package me.boyjamal.kits.utils;

import java.util.List;

public class GuiManager {

	private String name;
	private int slots;
	private List<KitUtil> items;
	
	public GuiManager(String name, int slots, List<KitUtil> items)
	{
		this.name = name;
		this.slots = slots;
		this.items = items;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Integer getSlots()
	{
		return slots;
	}
	
	public List<KitUtil> getItems()
	{
		return items;
	}
	
}
