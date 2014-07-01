package com.nebulamc.nebulazrun;

import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;

import com.nebulamc.nebulazrun.minigame.ZRunMinigame;

public class Configuration {
	public void loadFromFile(NebulaZRun plugin) {
		loadMinigames(plugin);
	}
	
	public void saveToFile(NebulaZRun plugin) {
		saveMinigames(plugin);
	}
	
	public void saveMinigames(NebulaZRun plugin) {
		for(int i = 0; i < plugin.minigames.size(); i++) {
			plugin.minigames.get(i).saveToFile("zrun.minigames." + plugin.minigames.get(i).name, plugin.getConfig());
		}
		plugin.saveConfig();
	}
	
	public void loadMinigames(NebulaZRun plugin) {
		ConfigurationSection minigameSection = plugin.getConfig().getConfigurationSection("zrun.minigames");
		Set<String> keys = minigameSection.getKeys(false);
		for(int i = 0; i < keys.size(); i++) {
			String currentName = (String) keys.toArray()[i];
			ConfigurationSection currentSection = minigameSection.getConfigurationSection(currentName);
			ZRunMinigame minigame= new ZRunMinigame();
			minigame.loadFromFile(currentSection);
			plugin.minigames.add(minigame);
		}
	}
}
