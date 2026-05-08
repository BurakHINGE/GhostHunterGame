package GhostHunterINC;

abstract public class Token extends GameObject {
	protected int tick = 0;
	
	abstract void applyEffect(Hunter hunter);
}
