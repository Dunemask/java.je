/**
 * 
 */
package elijah;

/**
 * @author Karib
 *
 */
public class JasonReadStory {

	/**
	 * 
	 */
	public static void wallOfTrees() {
		Story.score+=10;
		System.out.println("You come to a wall made of tightly woven trees.\r\n" + 
				"It is about 150 feet tall, and spans as far\r\n" + 
				"as you can see. You see some vines that make \r\n" + 
				"it possible to climb the trees.");
		int c = Story.getChoice(new String[] {"Look for a way around","Climb the wall of trees"});
		switch(c) {
		case 1: findPond();
			break;
		case 2: climbWall();
			break;
		}
		
		
		
	}

	/**
	 * 
	 */
	private static void climbWall() {
		
		
	}

	/**
	 * 
	 */
	private static void findPond() {
		Story.score+=10;
		System.out.println("You travel for only about 7 steps, and suddenly \r\n" + 
				"you come across a meadow next to a large pond.\r\n" + 
				"There is a faint glow emanating out of the pond.\r\n" + 
				"\r\n" + 
				"if (You go into the meadow){\r\n" + 
				"You prance into the meadow, and find a large\r\n" + 
				"footprint of a T-Rex. You notice that the large rock\r\n" + 
				"right in front of you is not a rock at all. It is a \r\n" + 
				"Muderdering Marchine.");
		int c = Story.getChoice(new String[] {"Swim Into Pond","Go Through Medow"});
		switch(c) {
		case 1: swimIntoPond(); break;
		case 2: goThroughmedow(); break;
		
		}
		
	}

	/**
	 * 
	 */
	private static void goThroughmedow() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	private static void swimIntoPond() {
		System.out.println("Dodge The Killer Fish and make it to the bottom of the pond!");
		//TODO jason Kiler Fish Program
		
	}

}
