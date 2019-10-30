package me.boyjamal.kits.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.boyjamal.kits.Main;

public class StorageManager {

	private static File kitsFile = new File(Main.getInstance().getDataFolder() + File.separator + "kits.yml");
	private static FileConfiguration kitsYML = null;
	
	private static File cooldownsFile = new File(Main.getInstance().getDataFolder() + File.separator + "data" + File.separator + "cooldowns.yml");
	private static FileConfiguration cooldownsYML = null;
	
	private static GuiManager mang = null;
	
	public static void loadFiles() 
	{	
		if (!(kitsFile.exists()))
		{
			if (!(kitsFile.getParentFile().exists()))
			{
				try {
					kitsFile.getParentFile().mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
			
			try {
				kitsFile.createNewFile();
				copy(Main.getInstance().getResource("kits.yml"),kitsFile);
				kitsYML = YamlConfiguration.loadConfiguration(kitsFile);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		} else {
			try {
				kitsYML = YamlConfiguration.loadConfiguration(kitsFile);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		
		if (kitsYML != null)
		{
			ConfigurationSection gui = kitsYML.getConfigurationSection("gui");
			if (gui != null)
			{
				List<KitUtil> items = new ArrayList<>();
				for (String keys : gui.getKeys(false))
				{
					ItemCreator accessItem = null;
					ItemCreator errorItem = null;
					ItemCreator cooldownItem = null;
					
					ConfigurationSection access = gui.getConfigurationSection("items.access");
					if (access != null)
					{
						int id = access.getInt(keys + ".id");
						short data = (short)access.getInt(keys + ".data");
						List<String> actions = access.getStringList(".actions");
						
						ItemStack accessItemStack = new ItemStack(id,1,data);
						ItemMeta im = accessItemStack.getItemMeta();
						im.setDisplayName(MainUtils.chatColor(access.getString(keys + ".name")));
						boolean hasLore = access.getBoolean(keys + ".hasLore");
						boolean glow = access.getBoolean(keys + ".glow");
						
						if (hasLore)
						{
							im.setLore(MainUtils.listColor(access.getStringList(keys + ".lore")));
						}
						
						accessItemStack.setItemMeta(im);
						if (glow)
						{
							im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							accessItemStack.setItemMeta(im);
							accessItemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
						}
						
						accessItem = new ItemCreator(accessItemStack,actions);
					}
					
					ConfigurationSection error = gui.getConfigurationSection("items.error");
					if (error != null)
					{
						int id = error.getInt(keys + ".id");
						short data = (short)error.getInt(keys + ".data");
						List<String> actions = error.getStringList(".actions");
						
						ItemStack accessItemStack = new ItemStack(id,1,data);
						ItemMeta im = accessItemStack.getItemMeta();
						im.setDisplayName(MainUtils.chatColor(error.getString(keys + ".name")));
						boolean hasLore = error.getBoolean(keys + ".hasLore");
						boolean glow = error.getBoolean(keys + ".glow");
						
						if (hasLore)
						{
							im.setLore(MainUtils.listColor(error.getStringList(keys + ".lore")));
						}
						
						accessItemStack.setItemMeta(im);
						if (glow)
						{
							im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							accessItemStack.setItemMeta(im);
							accessItemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
						}
						
						errorItem = new ItemCreator(accessItemStack,actions);
					}
					ConfigurationSection cooldown = gui.getConfigurationSection("items.cooldown");
					if (cooldown != null)
					{
						int id = cooldown.getInt(keys + ".id");
						short data = (short)cooldown.getInt(keys + ".data");
						List<String> actions = cooldown.getStringList(".actions");
						
						ItemStack accessItemStack = new ItemStack(id,1,data);
						ItemMeta im = accessItemStack.getItemMeta();
						im.setDisplayName(MainUtils.chatColor(cooldown.getString(keys + ".name")));
						boolean hasLore = cooldown.getBoolean(keys + ".hasLore");
						boolean glow = cooldown.getBoolean(keys + ".glow");
						
						if (hasLore)
						{
							im.setLore(MainUtils.listColor(cooldown.getStringList(keys + ".lore")));
						}
						
						accessItemStack.setItemMeta(im);
						if (glow)
						{
							im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							accessItemStack.setItemMeta(im);
							accessItemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
						}
						
						cooldownItem = new ItemCreator(accessItemStack,actions);
					}
					
					String permission = null;
					boolean hasPermission = false;
					int slot;
					Long cooldownTime = null;
					
					try {
						permission = gui.getString(keys + ".permission");
						cooldownTime = gui.getLong(keys + ".cooldown");
					} catch (Exception e) {}
					
					try {
						slot = gui.getInt(keys + ".slot");
					} catch (Exception e) {
						System.out.println(ChatColor.RED + "Invalid Slot in Kits (" + keys + ")");
						continue;
					}
					
					if (permission != null)
					{
						hasPermission = true;
					}
					
					if (cooldownTime == null)
					{
						cooldownTime = (long) 0;
					}
					
					if (accessItem != null && cooldownItem != null && errorItem != null)
					{
						items.add(new KitUtil(cooldownTime,hasPermission,permission,keys,slot,new KitItem(keys,accessItem,cooldownItem,errorItem)));
					} else if (accessItem != null) {
						items.add(new KitUtil(cooldownTime,hasPermission,permission,keys,slot,new KitItem(keys,accessItem)));
					}
				}
			}
		}
		
		if (!(cooldownsFile.exists()))
		{
			if (!(cooldownsFile.getParentFile().exists()))
			{
				try {
					cooldownsFile.getParentFile().mkdirs();
				} catch (Exception e) {
					for (int i = 0; i <= 3; i++)
					{
						Bukkit.getLogger().severe("Could not load Cooldown File properly. Plugin shutting down!");
					}
					Bukkit.getPluginManager().disablePlugin(Main.getInstance());
					return;
				}
			}
			
			try {
				cooldownsFile.createNewFile();
				copy(Main.getInstance().getResource("data/cooldowns.yml"),cooldownsFile);
				cooldownsYML = YamlConfiguration.loadConfiguration(cooldownsFile);
				CooldownManager.loadCooldowns();
			} catch (Exception e) {
				for (int i = 0; i <= 3; i++)
				{
					Bukkit.getLogger().severe("Could not load Cooldown File properly. Plugin shutting down!");
				}
				Bukkit.getPluginManager().disablePlugin(Main.getInstance());
				return;
			}
		} else {
			try {
				cooldownsYML = YamlConfiguration.loadConfiguration(cooldownsFile);
				CooldownManager.loadCooldowns();
			} catch (Exception e) {
				for (int i = 0; i <= 3; i++)
				{
					Bukkit.getLogger().severe("Could not load Cooldown File properly. Plugin shutting down!");
				}
				Bukkit.getPluginManager().disablePlugin(Main.getInstance());
				return;
			}
		}
	}
	
	private static void copy(InputStream in, File file)
    {
    	try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static GuiManager getKitGui()
	{
		return mang;
	}
	
	public static FileConfiguration getCooldownsYML()
	{
		return cooldownsYML;
	}
	
	public static File getCooldownFile()
	{
		return cooldownsFile;
	}
}
