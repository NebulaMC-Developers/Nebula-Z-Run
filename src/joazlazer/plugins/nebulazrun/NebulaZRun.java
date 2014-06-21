package joazlazer.plugins.nebulazrun;

import java.util.ArrayList;

import joazlazer.plugins.nebulazrun.event.ChatExpectation;
import joazlazer.plugins.nebulazrun.event.ChatHandler;
import joazlazer.plugins.nebulazrun.event.EventHandler;
import joazlazer.plugins.nebulazrun.event.MinigameRemoveConfirm;
import joazlazer.plugins.nebulazrun.minigame.MinigameState;
import joazlazer.plugins.nebulazrun.minigame.ZRunMinigame;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NebulaZRun extends JavaPlugin  {
	
	public Configuration Config;
	public ArrayList<ZRunMinigame> minigames;
	public EventHandler Events;
	public ChatHandler Chat;
	
	public NebulaZRun() {
		Config = new Configuration();
		minigames = new ArrayList<ZRunMinigame>();
	}

	@Override
	public void onEnable() {
		Config.loadFromFile(this);
		Events.registerEvents(this);
		minigames.add(new ZRunMinigame("ZRun1", MinigameState.IDLE));
		minigames.add(new ZRunMinigame("ZRun2", MinigameState.DISABLED));
		minigames.add(new ZRunMinigame("ZRun3", MinigameState.COUNTDOWN));
		minigames.add(new ZRunMinigame("ZRun4", MinigameState.INGAME));
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
			if(args[0].equalsIgnoreCase("list") && hasPerms(sender, args)) {
				if(args.length == 1) {
					displayZRunList(sender);
				}
				else displayError(sender, "Incorrect usage. Try /zrun list");
			}
			else if(args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help")) {
				printAllCommands(sender);
			}
			else if(args[0].equalsIgnoreCase("remove")) {
				Chat.addChatExpectation(new MinigameRemoveConfirm((((Player)sender).getName())), (Player)sender);
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
		msg(sender, ChatColor.AQUA + "Currently registered zrun minigame instances:");
		for(int i = 0; i < minigames.size(); i++) {
			ChatColor state = minigames.get(i).state.toColor();
			String currentLine = "" + ChatColor.GOLD;
			currentLine += " [" + i + "] ";
			currentLine += state + "";
			currentLine += minigames.get(i).name;
			msg(sender, currentLine);
			
		}
	}
	
	public void displayError(CommandSender sender, String text) {
		msg(sender, ChatColor.RED + "Error: " + text);
	}
	
	public boolean cancelChat(AsyncPlayerChatEvent e) {
		if(Chat.ExpectingUsernames.contains(e.getPlayer().getName())) {
			for(int i = 0; i < Chat.Expectations.size(); i++) {
				if(Chat.Expectations.get(i).username == e.getPlayer().getName()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void handleDirectedChat(AsyncPlayerChatEvent e) {
		ChatExpectation expect = null;
		for(int i = 0; i < Chat.Expectations.size(); i++) {
			if(Chat.Expectations.get(i).username == e.getPlayer().getName()) {
				expect = Chat.Expectations.get(i);
			}
		}
		if(expect == null) return;
		if(e.getMessage().equalsIgnoreCase("cancel")) {
			expect.handleCancel(e.getPlayer());
			return;
		}
		else {
			for(int i = 0; i < expect.Expectations.length; i++) {
				if(e.getMessage().equalsIgnoreCase(expect.Expectations[i])) {
					expect.handleChat(e.getMessage(), e.getPlayer());
				}
			}
		}
	}
	
	public void handleChat(AsyncPlayerChatEvent e) {
		
	}
}
