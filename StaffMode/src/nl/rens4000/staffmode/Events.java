package nl.rens4000.staffmode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;


public class Events implements Listener {
	
	public Inventory GUI = Bukkit.createInventory(null, 45, ChatColor.RED + "Admin" + ChatColor.WHITE + "Teleporter");
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		if(StaffMode.getModeUtils().getPlayers().contains(e.getPlayer().getName())) {
			StaffMode.getModeUtils().remove(e.getPlayer().getName());
		}
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(String n : StaffMode.getModeUtils().getPlayers()) {
			    	Player p = Bukkit.getPlayer(n);
			    	for (Player players : Bukkit.getOnlinePlayers()) {
			    		if(StaffMode.getModeUtils().getPlayers().contains(players.getName()))
			    			return;
			    		players.hidePlayer(p);
			    	}
			    }
				
			}
		}.runTaskLater(StaffMode.getInstance(), 60);
	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
	    
	    for(String n : StaffMode.getModeUtils().getPlayers()) {
	    	Player p = Bukkit.getPlayer(n);
	    	for (Player players : Bukkit.getOnlinePlayers()) {
	    		if(StaffMode.getModeUtils().getPlayers().contains(players.getName()))
	    			return;
	    		players.hidePlayer(p);
	    	}
	    }
	    
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		if(e.getInventory().getName().equalsIgnoreCase(ChatColor.RED + "Admin" + ChatColor.WHITE + "Teleporter")) {
			Player i = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName());
			if(i == null)
				return;
			p.teleport(i);
			p.sendMessage(StaffMode.getChatUtils().PREFIX + "You teleported to: " + i.getName());
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getItem().getType() == Material.COMPASS) {
				if(!StaffMode.getModeUtils().getPlayers().contains(p.getName()))
					return;
				int c = 0;
				for(Player pl : Bukkit.getOnlinePlayers()) {
					ItemStack i = new ItemStack(Material.SKULL_ITEM, 1);
				    SkullMeta im = (SkullMeta) i.getItemMeta();
				    im.setOwner(pl.getName());
				    im.setDisplayName(pl.getName());;
				    i.setItemMeta(im);
					GUI.setItem(c, i);
					c++;
				}
			}
		}
	}

}
