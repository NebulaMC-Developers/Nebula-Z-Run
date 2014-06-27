package joazlazer.plugins.nebulazrun.event;

import joazlazer.plugins.nebulazrun.NebulaZRun;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
	
	public NebulaZRun minigame;
	
	public PlayerInteractListener(NebulaZRun m) {
		 minigame  = m;
	}
	 
	public void onInteract(PlayerInteractEvent e) {
		 if(e.getItem().getType() == Material.BLAZE_ROD) {
			 if(e.getItem().getItemMeta().hasDisplayName()) System.out.println("hasDisplayName() : true");
			 else System.out.println("hasDisplayName : false");
		 }
	}
}
