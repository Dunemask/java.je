package aug;

public class FoodBot {

	public int health;
	public int pos;

	public FoodBot() {
		this.setHealth(10);
		this.pos = 0;
	}


	
	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	public int lastMove;
}
