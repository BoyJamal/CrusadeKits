package me.boyjamal.kits.utils;

public class CooldownUtil {
	
	private String name;
	private Long length;
	private int timeLeft;
	
	public CooldownUtil(String name, int timeLeft)
	{
		this.name = name;
		this.length = length;
		this.timeLeft = timeLeft;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Long getLength()
	{
		return length;
	}
	
	public int getTimeLeft()
	{
		return timeLeft;
	}

}
