/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: interstellarfaces.Penguin.java
 * Version: 0.1
 * info: (Information About The Class)
 * (To Change This Go To Window > Preferences 
 * > Java > Code Style > Code Templates)
 */
package interstellarfaces;

/**
 * @author karib
 *
 */
public class Penguin extends Bird {

	/**
	 * @param name
	 * @param color
	 */
	public Penguin() {
		super("Penguin", "Black");
		
		
	}
	
	
	@Override
	public void fly() {
		System.out.println("Cries because it can't fly");
	}

}
