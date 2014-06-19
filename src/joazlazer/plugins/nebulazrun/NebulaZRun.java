package joazlazer.plugins.nebulazrun;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NebulaZRun extends JavaPlugin  {
	
	public Configuration Config;
	
	public NebulaZRun() {
		Config = new Configuration();
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
		else {
			if(hasPerms(sender, args)) {
				
			}
		}
	}
	
	public void printAllCommands(CommandSender s) {
		msg(s, ChatColor.GRAY + repeat('-', 19) + "[" + ChatColor.DARK_PURPLE + "Nebula " + ChatColor.DARK_AQUA + "Z-Run" + ChatColor.GRAY + "]" + repeat('-', 20));
		msg(s, formatHelp("yolo", "You only live once"));
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
}
