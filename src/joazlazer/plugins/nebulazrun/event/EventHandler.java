package joazlazer.plugins.nebulazrun.event;

import joazlazer.plugins.nebulazrun.NebulaZRun;

public class EventHandler {
	public PlayerChatListener chat;
	
	public void registerEvents(NebulaZRun p) {
		p.getServer().getPluginManager().registerEvents(chat, p);
	}
}
