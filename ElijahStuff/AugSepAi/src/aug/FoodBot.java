package aug;

import java.util.Random;

public class FoodBot {

	public int health;
	public int pos;
	public int[] inodes;
	public int[] onodes;

	public FoodBot(int inputSize) {
		Random r = new Random();
		this.setHealth(10);
		this.pos = 0;
		this.inodes = new int[inputSize];
		this.onodes = new int[inputSize-1];
		for(int i=0;i<inodes.length;i++) {
			inodes[i] = r.nextInt(10);
		}
		
		
		
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
