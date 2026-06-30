package GhostHunterINC;

//The whole class is implemented by 150124012_Eyup_Turhan.

//Abstract class for all tokens in the game
abstract public class Token extends GameObject {
	// Timer variable used to make the floating animation of tokens
	protected int tick = 0;
	
	//Method that applies the tokens specific effect to the hunter
	abstract void applyEffect(Hunter hunter);
}
