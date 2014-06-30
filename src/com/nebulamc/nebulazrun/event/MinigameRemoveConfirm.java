package com.nebulamc.nebulazrun.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.nebulamc.nebulazrun.NebulaZRun;
import com.nebulamc.nebulazrun.minigame.ZRunMinigame;
import com.nebulamc.nebulazrun.util.PendingChange;

public class MinigameRemoveConfirm extends ChatExpectation {
	
	NebulaZRun plugin;
	PendingChange removeChange;

	public MinigameRemoveConfirm(String username, NebulaZRun plugin, PendingChange removeChange) {
		super(new String[] { "yes", "no" }, username);
		this.plugin = plugin;
		this.removeChange = removeChange;
	}
	
	@Override
	public void handleChat(String text, Player sender) {
		if(text.equalsIgnoreCase("yes")) {
			if(plugin.Changes.contains(removeChange)) {
				plugin.Changes.remove(removeChange);
				plugin.consumeChange(removeChange);
			}
			else throw new NullPointerException();
		}
		else handleCancel(sender);
	}
	
	@Override
	public void handleCancel(Player sender) {
		sender.sendMessage(ChatColor.RED + "Cancelled the removal of '" + ((ZRunMinigame)removeChange.newObject).name + ".'");
		if(plugin.Changes.contains(removeChange)) {
			plugin.Changes.remove(removeChange);
		}
		else throw new NullPointerException();
	}
	
	@Override
	public boolean handleInvalid(String invalidText, Player sender) {
		sender.sendMessage(ChatColor.RED + "Invalid keyword.");
		return true;
	}
	
	@Override
	public void handleReExpect(String message, Player player) {
		player.sendMessage(ChatColor.AQUA + "Are you sure you want to remove this minigame? Type yes, no, or cancel.");
	}
}
