package GhostHunterINC;

public class RangeToken extends Token {
	ConfigManager config = new ConfigManager();
	@Override
	public boolean checkCollision() {
		//If collides with hunter call applyEffect method.
		return false;
	}

	@Override
	void applyEffect(Hunter hunter) {
		int increase_amount = config.getRangeTokenIncrease(); 
		
		//Increase the range with the given amount
		hunter.scanner.expandRange(increase_amount);
		
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		
	}

}
