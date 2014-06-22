package joazlazer.plugins.nebulazrun.event;

import joazlazer.plugins.nebulazrun.NebulaZRun;

public class EventHandler {
	public PlayerChatListener chat;
	
	public EventHandler(NebulaZRun plugin) {
		chat = new PlayerChatListener(plugin);
	}
	
	public void registerEvents(NebulaZRun p) {
		p.getServer().getPluginManager().registerEvents(chat, p);
	}
}
