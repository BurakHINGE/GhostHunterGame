package GhostHunterINC;

public class EyeToken extends Token {
	ConfigManager config = new ConfigManager();
	@Override
	public boolean checkCollision() {
		//If collides with hunter call applyEffect method.
		return false;
	}

	@Override
	//Make all the entities visible for given time.
	void applyEffect(Hunter hunter) {
		int time = config.getEyeTokenDuration();
		
		//Make all entities visible for given time
		
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		
	}

}
