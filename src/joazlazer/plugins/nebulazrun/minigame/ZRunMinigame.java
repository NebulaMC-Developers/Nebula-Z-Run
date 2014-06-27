package joazlazer.plugins.nebulazrun.minigame;

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
}
