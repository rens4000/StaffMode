package nl.rens4000.staffmode;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import nl.rens4000.staffmode.commands.StaffModeCommand;
import nl.rens4000.staffmode.utils.ChatUtils;
import nl.rens4000.staffmode.utils.ModeUtils;

public class StaffMode extends JavaPlugin {
	
	//Variables
	private static StaffMode staffMode;
	private static ChatUtils chatUtils;
	private static ModeUtils modeUtils;
	
	//Onenable
	@Override
	public void onEnable() {
		PluginManager pm = Bukkit.getPluginManager();
		//Initialize variables
		staffMode = this;
		chatUtils = new ChatUtils();
		modeUtils = new ModeUtils();
		//Initializing events
		pm.registerEvents(new Events(), staffMode);
		//Initializing commands
		getCommand("StaffMode").setExecutor(new StaffModeCommand());
		
	}
	
	//Instance getter
	public static StaffMode getInstance() {
		return staffMode;
	}
	
	//ChatUtils getter
	public static ChatUtils getChatUtils() {
		return chatUtils;
	}
	
	//ModeUtils getter
		public static ModeUtils getModeUtils() {
			return modeUtils;
		}

}
