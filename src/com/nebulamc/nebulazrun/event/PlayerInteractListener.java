package com.nebulamc.nebulazrun.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.nebulamc.nebulazrun.NebulaZRun;

public class PlayerInteractListener implements Listener {
	
	public NebulaZRun minigame;
	
	public PlayerInteractListener(NebulaZRun m) {
		 minigame  = m;
	}
	 
	@org.bukkit.event.EventHandler
	public void onInteract(PlayerInteractEvent e) {
		 Action a = e.getAction();
		 ItemStack is = e.getItem();
		 
		 // If invalid, then return.
		 if(a == Action.PHYSICAL || is == null || is.getType() == Material.AIR || e.getClickedBlock() == null || e.getClickedBlock().getType() == Material.AIR)
			 return;
		 
		 // Ensure that the item clicked is a blaze rod and a selection wand.
		 if(is.getType() == Material.BLAZE_ROD) {
			 ItemMeta meta = is.getItemMeta();
			 if(meta.getLore().contains(ChatColor.GRAY + "selections for use with Nebula Z-Run.")) {
				 
			 }
		 }
	}
}
