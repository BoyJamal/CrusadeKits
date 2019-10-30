package me.boyjamal.kits.utils;

import java.util.List;

public class GuiItem {
	
	private int slot;
	private List<KitUtil> items;
	private List<String> actions;
	
	public GuiItem(int slot, List<KitUtil> items)
	{
		this.slot = slot;
		this.items = items;
		this.actions = actions;
	}
	
	public int getSlot()
	{
		return slot;
	}
	
	public List<KitUtil> getItems()
	{
		return items;
	}

}
