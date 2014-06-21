package joazlazer.plugins.nebulazrun.event;

import org.bukkit.entity.Player;

public class MinigameRemoveConfirm extends ChatExpectation {

	public MinigameRemoveConfirm(String username) {
		super(new String[] { "yes", "no" }, username);
	}
	
	@Override
	public void handleChat(String text, Player sender) {
		
	}
	
	@Override
	public void handleCancel(Player sender) {
		
	}
	
	@Override
	public boolean handleInvalid(String invalidText, Player sender) {
		return true;
	}
}
