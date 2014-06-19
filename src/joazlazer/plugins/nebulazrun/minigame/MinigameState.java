package joazlazer.plugins.nebulazrun.minigame;

import org.bukkit.ChatColor;

public enum MinigameState {
	IDLE,
	COUNTDOWN,
	INGAME,
	DISABLED;
	
	public ChatColor toColor() {
		switch(this) {
			case IDLE: {
				return ChatColor.GOLD;
			}
			case COUNTDOWN: {
				return ChatColor.YELLOW;
			}
			case INGAME: {
				return ChatColor.GREEN;
			}
			default: {
				return ChatColor.RED;
			}
		}
	}
}
