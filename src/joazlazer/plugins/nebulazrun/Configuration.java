package joazlazer.plugins.nebulazrun;

import java.util.Set;

import joazlazer.plugins.nebulazrun.minigame.ZRunMinigame;

import org.bukkit.configuration.ConfigurationSection;

public class Configuration {
	public void loadFromFile(NebulaZRun plugin) {
		loadMinigames(plugin);
	}
	
	public void saveToFile(NebulaZRun plugin) {
		saveMinigames(plugin);
	}
	
	public void saveMinigames(NebulaZRun plugin) {
		ConfigurationSection minigameSection = plugin.getConfig().createSection("zrun.minigames");
		for(int i = 0; i < plugin.minigames.size(); i++) {
			ConfigurationSection currentSection = minigameSection.createSection(plugin.minigames.get(i).name);
			plugin.minigames.get(i).saveToFile(currentSection);
		}
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
