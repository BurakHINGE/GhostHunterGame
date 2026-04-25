package GhostHunterINC;

public class RangeToken extends Token {
	ConfigManager config = new ConigManager();
	@Override
	public boolean checkCollision() {
		//If collides with hunter call applyEffect method.
		return false;
	}

	@Override
	void applyEffect(Hunter hunter) {
		//Get the increase amount from config.
		int increase_amount; 
		
		//Increase the range with the given amount
		
		
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		
	}

}
