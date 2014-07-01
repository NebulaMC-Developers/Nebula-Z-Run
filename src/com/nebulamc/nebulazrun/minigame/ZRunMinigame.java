package com.nebulamc.nebulazrun.minigame;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

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

	public void saveToFile(String string, FileConfiguration config) {
		if(state == MinigameState.DISABLED) config.set(string + ".disabled", true);
		else config.set(string + ".disabled", false);
	}
}
