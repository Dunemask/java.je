/**
 * 
 */
package elijah;

import dunemask.util.FileUtil;

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
		Story.score+=10;
		System.out.println("You climb up the wall and your belly urges you towards the fruit \r\n" );
		switch(Story.getChoice(new String[] {"Eat Fruit","Ignore Wall"})) {
		case 1: eatFruit();
		break;
		case 2: ignoreFruit();
			break;
		
		}
		
	}

	/**
	 * 
	 */
	private static void ignoreFruit() {
		Story.score+=10;
		System.out.println("You find a breech in the leaves step through and fall 2 feet you see an old bunker ahead of you");
		switch(Story.getChoice(new String[] {"Run To Bunker","Find Another Way"})){
		case 1: System.out.println("A mother velociraptor sniffs you out and you make your way through it's belly");
				System.out.println("YOu STUPID! #21");
				Story.score-=50;
				Story.youDead();
			break;
		case 2: findAnotherWay();

			break;
		
		}
		
	}

	/**
	 * 
	 */
	public static void findAnotherWay() {
		Story.score+=10;
		//Jason TODO mazegame
		System.out.println("You get to the bunker, You Fina  aphone and an old arcade machine");
		
		switch(Story.getChoice(new String[] {"Pick Up Phone","Play The Game"})){
		case 1: 
			System.out.println("You touch the phone that had an electric charge of 9234234235345 volts that run through you. You look like albert Einstein");
			Story.showImage(FileUtil.getResource("resources/alby.jpg"));
			Story.score+=80;
			Story.youDead();
			break;
		case 2: 
			arcadeMachine();
			break;
		
		}
		
	}

	/**
	 * 
	 */
	private static void arcadeMachine() {
		
		
	}

	/**
	 * 
	 */
	private static void eatFruit() {
		Story.score+=10;
		System.out.println("The Fruit Tastes Petrid (Get the reference) you start feeling sick");
		switch(Story.getChoice(new String[] {"Keep It In","Give Fruit Up"})){
		case 1: keepFruitIn();	
			break;
		case 2: System.out.println("Your body falls to ground covered in the half digested fruit that kiled you");
			Story.score+=5;
			Story.youDead();
			break;
		
		}
		
	}

	/**
	 * 
	 */
	private static void keepFruitIn() {
		Story.score+=10;
		//TODO Jason Fruit stuff
		System.out.println("You Slowly Climb to the top of the trees, You see a flying Pteradactyle shooting 900 mph at you face");
		switch(Story.getChoice(new String[] {"Jump","Duck"})) {
		case 1:
			System.out.println("You jump up and the Pteradactyle misses you!");
			System.out.println("Gravity Suddenly Inverts and multiplies to be 5000M/s^2");
			System.out.println("Your imprint is left in the ground, for generations to come");
			Story.score+=30;
			Story.youDead();
			break;
		case 2:
			flappyTerroInit();
			break;
		}
		
		
	}

	/**
	 * 
	 */
	private static void flappyTerroInit() {
		Story.score+=10;
		System.out.println("The Pteradatyle Grabs you and flies, you to its babies,");
		//TODO Jason Flaappy
		jason.FlappyPterodactyl p = new jason.FlappyPterodactyl();
		System.out.println(" you will beocome part of a new Dino Generation!");
		Story.score += 50;
		Story.youDead();
		
		
		
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
		Story.score+=10;
		System.out.println("You Walk through the medow when suddenly a wild TREX bush appears, without the bush part");
		switch(Story.getChoice(new String[] {"Pet The Trex","Run"})) {
		case 1:
			System.out.println("You stair up into the uncaring void of his mouth, your eyes meat and he eats you");
			Story.score+=100;
			Story.youDead();
			break;
		case 2:
			System.out.println("He sees you trying to escape trips and falls on you...");
			Story.score+=5;
			Story.youDead();
			break;
		
		
		}
		
		
	}

	/**
	 * 
	 */
	private static void swimIntoPond() {
		Story.score+=10;
		System.out.println("Dodge The Killer Fish and make it to the bottom of the pond!");
		//TODO jason Kiler Fish Program
		
		System.out.println("You find a hatch");
		switch(Story.getChoice(new String[] {"Open hatch","Swim Back Up"})) {
		case 1:
			System.out.println("Your Sucked Into The Deep Abyss, You Know you died in an epic way");
			Story.score+=20;
			Story.youDead();
			break;
		case 2: 
			System.out.println("You Struggle Swimming up to the surface yoru lungs give way");
			System.out.println("You backed out to soon...");
			Story.score-=20;
			Story.youDead();
			break;
		}
		
		
	}

}
