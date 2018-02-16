package jason;
import java.util.Random;
public class MazeGenerator {
	public int[][] mazeGen(int size,int iterations) {
		Random r = new Random();
		//Creates empty array
		int mg[][]= new int[size+1][size+1];
		//"Moves" to the middle
		int mx = size/2-1;
		int my = size/2-1;
		for(int i = 0; i <= iterations; i++) {
			mg[mx][my] = 1;//sets the point it is on to 1
			int choice = r.nextInt(4); // chooses the direction
			switch(choice) {
				case 0: if ((mx+2 < size )) {
							if(mg[mx+2][my] == 0) { //if in bounds and 2 in front of it is zero
								mg[mx+1][my]= 1;	//then sets the one in front to 1
							}
							mx += 2;				//always moves two unless outside of bounds
						}
						break;
				case 1: if ((mx-2 > 0)) {			//repeats for all 4 directions
					if(mg[mx-2][my] == 0) {			//could be expanded for 6 directions easily
						mg[mx-1][my]= 1;
					}
					mx -= 2;
				}
				break;
				case 2: if ((my+2 < size )) {
					if(mg[mx][my+2] == 0) {
						mg[mx][my+1]= 1;
					}
					my += 2;
				}
				break;
				case 3: if ((my-2 > 0)) {
					if(mg[mx][my-2] == 0) {
						mg[mx][my-1]= 1;
					}
					my -= 2;
				}
				break;
			}
		}
		return mg;
	}
}
