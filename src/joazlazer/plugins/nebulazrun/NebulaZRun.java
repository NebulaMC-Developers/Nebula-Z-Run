package joazlazer.plugins.nebulazrun;

import java.util.ArrayList;

import joazlazer.plugins.nebulazrun.minigame.ZRunMinigame;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NebulaZRun extends JavaPlugin  {
	
	public Configuration Config;
	public ArrayList<ZRunMinigame> minigames;
	
	public NebulaZRun() {
		Config = new Configuration();
		minigames = new ArrayList<ZRunMinigame>();
	}

	@Override
	public void onEnable() {
		Config.loadFromFile(this);
	}
	
	@Override
	public void onDisable() {
		Config.saveToFile(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String text, String[] args) { 
		if(text.equalsIgnoreCase("zrun")) {
			parseCentralCommand(sender, args);
			return true;
		}
		return false;
	}
	
	public void parseCentralCommand(CommandSender sender, String[] args) {
		if(args.length <= 0) {
			printAllCommands(sender);
		}
		else if(hasPerms(sender, args)) {
			if(args[0].equalsIgnoreCase("list")) {
				if(args.length == 1) {
					displayZRunList(sender);
				}
				else displayError(sender, "Incorrect usage. Try /zrun list");
			}
		}
	}
	
	public void printAllCommands(CommandSender s) {
		msg(s, ChatColor.GRAY + repeat('-', 19) + "[" + ChatColor.DARK_PURPLE + "Nebula " + ChatColor.DARK_AQUA + "Z-Run" + ChatColor.GRAY + "]" + repeat('-', 20));
		msg(s, formatHelp("list", "Displays a list of all minigame instances."));
	}
	
	public String repeat(char character, int times) {
		String str = "";
		for(int i = 1; i <= times; i++) {
			str += character;
		}
		return str;
	}
	
	public String formatHelp(String expectedArgs, String description) {
		return ChatColor.GOLD + " - " + ChatColor.GREEN + "/zrun " + expectedArgs + ChatColor.GOLD + " - " + description;
	}
	
	public boolean hasPerms(CommandSender sender, String[] args) {
		if(!(sender instanceof Player)) return true;
		else {
			if(((Player)sender).isOp()) return true;
			else return false;
		}
	}
	
	public void msg(CommandSender sender, String message) {
		sender.sendMessage(message);
	}
	
	public void displayZRunList(CommandSender sender) {
		msg(sender, "Currently registered zrun minigame instances.");
		for(int i = 0; i < minigames.size(); i++) {
			
		}
	}
	
	public void displayError(CommandSender sender, String text) {
		msg(sender, ChatColor.RED + "Error: " + text);
	}
}
