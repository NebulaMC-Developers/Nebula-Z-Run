package joazlazer.plugins.nebulazrun.event;

import joazlazer.plugins.nebulazrun.NebulaZRun;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener{
	private NebulaZRun _plugin;
	
	public PlayerChatListener(NebulaZRun plugin) {
		_plugin = plugin;
	}
	
	@EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
		if(_plugin.cancelChat(e)) {
			e.setCancelled(true);
			_plugin.handleDirectedChat(e);
		}
		_plugin.handleChat(e);
	}
}
