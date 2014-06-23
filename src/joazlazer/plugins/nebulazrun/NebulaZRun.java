package joazlazer.plugins.nebulazrun;

import java.util.ArrayList;

import joazlazer.plugins.nebulazrun.event.ChatExpectation;
import joazlazer.plugins.nebulazrun.event.ChatHandler;
import joazlazer.plugins.nebulazrun.event.EventHandler;
import joazlazer.plugins.nebulazrun.event.MinigameRemoveConfirm;
import joazlazer.plugins.nebulazrun.minigame.MinigameState;
import joazlazer.plugins.nebulazrun.minigame.ZRunMinigame;
import joazlazer.plugins.nebulazrun.util.PendingChange;
import joazlazer.plugins.nebulazrun.util.PendingChangeQueue;
import joazlazer.plugins.nebulazrun.util.PendingChangeType;

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
	public PendingChangeQueue Changes;
	
	public NebulaZRun() {
		Config = new Configuration();
		minigames = new ArrayList<ZRunMinigame>();
		Events = new EventHandler(this);
		Changes = new PendingChangeQueue();
		Chat = new ChatHandler();
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
			else if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("del") || args[0].equalsIgnoreCase("-")) {
				if(args.length >= 2) {
					String arg = args[1];
					ZRunMinigame minigame = null;
					if(isNumeric(arg)) {
						minigame = minigames.get(Integer.parseInt(arg));
					}
					else if(containsName(minigames, arg)) {
						minigame = getFromName(minigames, arg);
					}
					else {
						sender.sendMessage(ChatColor.RED + "Invalid usage. Correct usage is /zrun remove <[index]:[name]>");
					}
					if(minigame != null) {
						if(sender instanceof Player) {
							removeMinigameWithConfirm((Player)sender, minigame);
						}
						else removeMinigame(sender, minigame);
					}
				}
				else {
					sender.sendMessage(ChatColor.RED + "Invalid usage. Correct usage is /zrun remove <[index]:[name]>");
				}
			}
			else {
				sender.sendMessage(ChatColor.RED + "Unknown sub-command '" + args[0] + "'! Type /zrun to see a list of all of the sub-commands.");
			}
		}
	}
	
	private boolean containsName(ArrayList<ZRunMinigame> minigames2, String arg) {
		for(int i = 0; i < minigames2.size(); i++) {
			if(minigames2.get(i).name == arg) return true;
		}
		return false;
	}

	private ZRunMinigame getFromName(ArrayList<ZRunMinigame> minigames2,
			String arg) {
		for(int i = 0; i < minigames2.size(); i++) {
			if(minigames2.get(i).name == arg) return minigames2.get(i);
		}
		return null;
	}

	public void removeMinigameWithConfirm(CommandSender sender, ZRunMinigame minigame) {
		sender.sendMessage(ChatColor.AQUA + "Are you sure you want to remove this minigame? Type yes, no, or cancel.");
		PendingChange change = (new PendingChange()).setType(PendingChangeType.REMOVE).setNewObject(minigame).setChangeID("minigame_remove").setSender(sender);
		Changes.enqueue(change);
		Chat.addChatExpectation(new MinigameRemoveConfirm((((Player)sender).getName()), this, change), (Player)sender);
	}
	
	public void removeMinigame(CommandSender sender, ZRunMinigame minigame) {
		minigames.remove(minigame);
		sender.sendMessage(ChatColor.GREEN + "Successfully removed minigame '" + minigame.name + ".'");
	}
	
	public void printAllCommands(CommandSender s) {
		msg(s, ChatColor.GRAY + repeat('-', 19) + "[" + ChatColor.DARK_PURPLE + "Nebula " + ChatColor.DARK_AQUA + "Z-Run" + ChatColor.GRAY + "]" + repeat('-', 20));
		msg(s, formatHelp("list", "Displays a list of all minigame instances."));
		msg(s, formatHelp("remove <[index]:[name]>", "Removes the specified minigame."));
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
			Chat.Expectations.remove(expect);
			return;
		}
		else {
			boolean found = false;
			for(int i = 0; i < expect.Expectations.length; i++) {
				if(e.getMessage().equalsIgnoreCase(expect.Expectations[i])) {
					expect.handleChat(e.getMessage(), e.getPlayer());
					Chat.Expectations.remove(expect);
					found = true;
				}
			}
			if(!found) {
				if(expect.handleInvalid(e.getMessage(), e.getPlayer())) {
					expect.handleReExpect(e.getMessage(), e.getPlayer());
				}
				else {
					expect.handleCancel(e.getPlayer());
					Chat.Expectations.remove(expect);
					return;
				}
			}
		}
	}
	
	public void handleChat(AsyncPlayerChatEvent e) {
		
	}
	
	public void consumeChange(PendingChange change) {
		switch(change.changeID.toLowerCase()) {
			case "minigame_remove": {
				minigames.remove(change.newObject);
				((CommandSender)change.sender).sendMessage(ChatColor.GREEN + "Successfully removed minigame '" + ((ZRunMinigame)change.newObject).name + ".'");
			}
		}
	} 
	
	public boolean isNumeric(String text) {
		boolean end = true;
		for(int i = 0; i < text.length(); i++) {
			if(Character.isDigit(text.charAt(i))) {}
			else end = false;
		}
		return end;
	}
}
