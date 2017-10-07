package nl.rens4000.staffmode.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import nl.rens4000.staffmode.StaffMode;
import nl.rens4000.staffmode.utils.ChatUtils;
import nl.rens4000.staffmode.utils.ModeUtils;

public class StaffModeCommand implements CommandExecutor {
	
	
	private ChatUtils chatUtils = StaffMode.getChatUtils();
	private ModeUtils modeUtils = StaffMode.getModeUtils();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("staffmode")) {
			
			if(!(sender instanceof Player)) {
				sender.sendMessage(chatUtils.ERROR + "You must be a player to execute this command.");
				return false;
			}
			
			Player p = (Player) sender;
			
			if(p.hasPermission("StaffMode.Admin")) {
				
				if(!modeUtils.getPlayers().contains(p.getName())) {
					modeUtils.add(p.getName());
					p.sendMessage(chatUtils.PREFIX + "You've been set into StaffMode!");
					return false;
				}
				
				if(modeUtils.getPlayers().contains(p.getName())) {
					modeUtils.remove(p.getName());
					p.sendMessage(chatUtils.PREFIX + "You've been removed from StaffMode!");
					return false;
				}
				
			}
			
		}
		return false;
	}

}
