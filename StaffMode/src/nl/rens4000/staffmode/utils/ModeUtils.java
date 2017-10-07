package nl.rens4000.staffmode.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ModeUtils {
	
	private List<String> players = new ArrayList<String>();
	
	private Map<String, ItemStack[]> inventories = new HashMap<String, ItemStack[]>();
	
	private Map<String, ItemStack[]> armor = new HashMap<String, ItemStack[]>();
	
	public void add(String name) {
		Player p = Bukkit.getPlayer(name);
		players.add(name);
		inventories.put(name, p.getInventory().getContents());
		armor.put(name, p.getInventory().getArmorContents());
		p.getInventory().clear();
		p.setGameMode(GameMode.CREATIVE);
		
		//Admin item
		ItemStack i = new ItemStack(Material.COMPASS);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Admin" + ChatColor.WHITE + "Teleporter");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.WHITE + "Teleport to a player!");
		im.setLore(lore);
		i.setItemMeta(im);
		p.getInventory().setItem(5, i);
	}

	public List<String> getPlayers() {
		return players;
	}

	public Map<String, ItemStack[]> getInventories() {
		return inventories;
	}

	public Map<String, ItemStack[]> getArmor() {
		return armor;
	}
	
	
	
	public void remove(String name) {
		Player p = Bukkit.getPlayer(name);
		p.getInventory().clear();
		players.remove(name);
		p.getInventory().setArmorContents(armor.get(name));
		p.getInventory().setContents(inventories.get(name));
		armor.remove(name);
		inventories.remove(name);
		p.setGameMode(GameMode.SURVIVAL);
	}

}
