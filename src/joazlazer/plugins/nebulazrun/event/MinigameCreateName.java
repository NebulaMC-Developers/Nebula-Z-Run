package joazlazer.plugins.nebulazrun.event;

import joazlazer.plugins.nebulazrun.NebulaZRun;
import joazlazer.plugins.nebulazrun.minigame.MinigameCreationMode;

import org.bukkit.entity.Player;

public class MinigameCreateName extends ChatExpectation {
	
	NebulaZRun plugin;
	MinigameCreationMode controller;

	public MinigameCreateName(String name, NebulaZRun p, MinigameCreationMode co) {
		super(new String[] { }, name);
		this.plugin = p;
		this.controller = co;
		setOverride(true);
	}
	
	@Override
	public boolean doHandle(String message, Player sender) {
		return true;
	}
	
	@Override
	public void handleChat(String text, Player sender) {
		controller.endName(text);
	}
	
	@Override
	public void handleCancel(Player sender) {
		// Called when player types 'cancel.'
		controller.cancel();
	}
	
	@Override
	public void handleReExpect(String message, Player player) {
		// Never going to be called.
	}
}
