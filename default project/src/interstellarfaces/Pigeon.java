/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: interstellarfaces.Pigeon.java
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
public class Pigeon extends Bird{

	/**
	 * @param name
	 * @param color
	 */
	public Pigeon(String color) {
		super("Pigeon", color);
		
		
		
	}
	
	@Override
	public void fly() {
		System.out.println("Pigeon: Poops on people while flying");
	}



}
