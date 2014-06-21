package joazlazer.plugins.nebulazrun.event;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class ChatHandler {
	public ArrayList<ChatExpectation> Expectations;
	public ArrayList<String> ExpectingUsernames;
	
	public ChatHandler() {
		Expectations = new ArrayList<ChatExpectation>();
		ExpectingUsernames = new ArrayList<String>();
	}
	
	public void addChatExpectation(ChatExpectation chatExpectation, Player sender) {
		if(chatExpectation == null || sender == null || sender.getName() == null || sender.getName().isEmpty()) {
			throw new IllegalArgumentException();
		}
		Expectations.add(chatExpectation);
		ExpectingUsernames.add(sender.getName());
	}
}
