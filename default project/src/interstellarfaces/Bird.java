/** 
 * Dunemask Created This File on the Main Repository
 * @contact Dunemask at dunemask@gmail.com
 * commit = false
 * File: interstellarfaces.Bird.java
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
public class Bird implements BirdActions{

	private String color;
	private String name;

	/**
	 * 
	 */
	public Bird(String name,String color) {
		this.setName(name);
		this.setColor(color);
	}

	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}



	@Override
	public void fly(){
		System.out.println(getName()+": Flap");
	}
	
	

}
