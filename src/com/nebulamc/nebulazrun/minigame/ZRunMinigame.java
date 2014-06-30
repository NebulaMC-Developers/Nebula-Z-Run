package com.nebulamc.nebulazrun.minigame;

import org.bukkit.configuration.ConfigurationSection;

public class ZRunMinigame {
	public ZRunMinigame() {
		
	}
	
	public ZRunMinigame(String _name, MinigameState _state) {
		name = _name;
		state = _state;
	}
	
	public ZRunMinigame(String name2) {
		name = name2;
	}

	public String name;
	public MinigameState state;
	
	public void loadFromFile(ConfigurationSection currentSection) {
		name = currentSection.getName();
		if(currentSection.getBoolean("disabled", false)) state = MinigameState.DISABLED;
		else state = MinigameState.IDLE;
		System.out.println(currentSection.getBoolean("disabled"));
		System.out.println(currentSection.getKeys(false).contains("disabled"));
	}

	public void saveToFile(ConfigurationSection currentSection) {
		if(state == MinigameState.DISABLED) currentSection.set("disabled", true);
		else currentSection.set("disabled", false);
	}
}
