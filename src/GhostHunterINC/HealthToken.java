package GhostHunterINC;

public class HealthToken extends Token {

	
	public boolean checkCollision() {
		//If collides with hunter call applyEffect method.
		
	}
	
	public void applyEffect(Hunter hunter) {
		//Change the getMaxHealth method with the correct one after its implemented
		int heal_amount = ConfigManager.getMaxHealth();
		
		hunter.heal(heal_amount);
	}
}
