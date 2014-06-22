package joazlazer.plugins.nebulazrun.event;

import org.bukkit.entity.Player;

public class ChatExpectation {
	public String[] Expectations;
	public String username;
	public ChatExpectation(String [] expectations, String name) {
		this.Expectations = expectations;
		this.username = name;
	}
	public void handleChat(String message, Player sender) {
		
	}
	public void handleCancel(Player sender) {
		
	}
	/**
	 * Method for handling when invalid text is submitted.
	 * @param invalidText
	 * @param sender
	 * @return Whether to continue and ask again.
	 */
	public boolean handleInvalid(String invalidText, Player sender) {
		return true;
	}
	public void handleReExpect(String message, Player player) {
		
	}
}
