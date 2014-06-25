package joazlazer.plugins.nebulazrun.minigame;

import joazlazer.plugins.nebulazrun.NebulaZRun;
import joazlazer.plugins.nebulazrun.event.MinigameCreateName;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MinigameCreationMode {
	
	public Player player;
	public NebulaZRun plugin;
	public String name;

	public MinigameCreationMode(NebulaZRun nebulaZRun, Player sender) {
		player = sender;
		plugin = nebulaZRun;
	}
	
	public void startName() {
		player.sendMessage(ChatColor.AQUA + "Type the name of the new minigame or 'cancel' to cancel creation mode.");
		MinigameCreateName nameCreate = new MinigameCreateName(player.getName(), plugin, this);
		plugin.Chat.addChatExpectation(nameCreate, player);
	}

	public void endName(String text) {
		name = text;
		end();
	}

	public void cancel() {
		player.sendMessage(ChatColor.GRAY + "Creation mode " + ChatColor.RED + "canceled" + ChatColor.GRAY + ".");
	}

	public void enable() {
		player.sendMessage(ChatColor.GRAY + "Creation mode " + ChatColor.GREEN + "enabled" + ChatColor.GRAY + ".");
		startName();
	}
	
	public void end() {
		player.sendMessage(ChatColor.GREEN + "New minigame called '" + name + "' has been successfully created.");
		player.sendMessage(ChatColor.GRAY + "Creation mode " + ChatColor.RED + "disabled" + ChatColor.GRAY + ".");
	}
}