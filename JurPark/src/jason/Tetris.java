/**
 * 
 */
package jason;

import java.awt.Color;
import java.awt.Component;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;;

/**
 * @author Jason Roberts
 *
 */
public class Tetris extends JFrame {
	ImageIcon img = new ImageIcon("src/resources/tetriss.png");
	int score=0;
	int block[][];
	int out;
	int[][][] pieces =
	{{
	{0,0,0,0,0},
	{0,0,1,1,0},
	{0,0,1,0,0},
	{0,0,1,0,0},
	{0,0,0,0,0}},
	{
	{0,0,0,0,0},
	{0,2,2,0,0},
	{0,0,2,0,0},
	{0,0,2,0,0},
	{0,0,0,0,0}},
	{
	{0,0,0,0,0},
	{0,0,3,0,0},
	{0,3,3,3,0},
	{0,0,0,0,0},
	{0,0,0,0,0}},
	{
	{0,0,0,0,0},
	{0,0,4,0,0},
	{0,0,4,4,0},
	{0,0,0,4,0},
	{0,0,0,0,0}},
	{
	{0,0,0,0,0},
	{0,0,5,0,0},
	{0,5,5,0,0},
	{0,5,0,0,0},
	{0,0,0,0,0}},
	{
	{0,0,0,0,0},
	{0,0,6,6,0},
	{0,0,6,6,0},
	{0,0,0,0,0},
	{0,0,0,0,0}},
	{
	{0,0,0,0,0},
	{0,0,7,0,0},
	{0,0,7,0,0},					
	{0,0,7,0,0},
	{0,0,7,0,0}}};
	int selected[][] = new int[5][5];
	int test[][] = new int[5][5];
	int x;
	int y;
	int casty;
	int squaresize = 4;
	int size= 10;
	int basesize = 600;
	int height = 19;
	int[] pool= new int[10];
	JPanel jpan;
	Random rn = new Random();
	JLabel scor = new JLabel("0");
	public Tetris() {
		score=0;
		squaresize = 23;
		size= 10;
		basesize = 600;
		height = 15;
		selected = pieces[rn.nextInt(7)];
		x=5;
		y=2;
		//SET UP DA MAIN FRAME
		this.setTitle("Tetris");
		this.setSize(280,500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		JLabel bg = new JLabel(img);
		bg.setLocation(0,0);
		bg.setSize(280, 500);
		
		
		
		block=new int[size][height];
		//JLabel l1 = new JLabel("Tetris");
		//l1.setBounds(5, 5, 50, 20);
		//l1.setHorizontalAlignment(4);
		KeyList key =new KeyList();
		addKeyListener(key);
		//add(l1);
		scor.setBounds(245, 20, 50, 20);
		add(scor);
		jpan = createsquares(squaresize,size,height);
		requestFocus();
		add(bg);
		int checker = 0;
		out=0;
		while(out==0) {
			for(int i = 0; i <10; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.Frame();
			repaint();
			FindMove(key,this);
			}
		if(this.Move(0,1)==true) {
			checker++;
		}
		if(checker>1) {
			if(this.NewBlock(false)) {
				out =1;
			};
			checker =0;
		}
		}
		System.out.println();
		
		
		
		
		
	}
	public JPanel createButton(int x, int y, int width, int height, int color) {
		JPanel jb= new JPanel(); 
		jb.setLocation(x, y);
		Color col = Color.black;
		switch(color){
		case 1: col=Color.red;
		break;
		case 2: col=Color.green;
		break;
		case 3: col=Color.magenta;
		break;
		case 4: col=Color.orange;
		break;
		case 5: col=Color.blue;
		break;
		case 6: col=Color.yellow;
		break;
		case 7: col=Color.cyan;
		break;
		case 8: col=Color.gray;
		break;
		default: col = Color.black;
		break;
		}
		jb.setBackground(col);
		jb.setSize(width, height);
		return jb;
	}
	public JPanel createsquares(int squaresize, int size, int height) {
		int gap=1;
		JPanel asdf= new JPanel();
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<height; j++) {
				if ((i>=x-2)&&(i<=x+2)&&(j>=y-2)&&(j<=y+2)&&!(selected[i-x+2][j-y+2]==0)){
					asdf.add(createButton(1 + i*squaresize,50+j*squaresize,squaresize-gap,squaresize-gap, selected[i-x+2][j-y+2]));
				}
				else if ((i>=x-2)&&(i<=x+2)&&(j>=casty-2)&&(j<=casty+2)&&!(selected[i-x+2][j-casty+2]==0)){
					asdf.add(createButton(1 + i*squaresize,50+j*squaresize,squaresize-gap,squaresize-gap, 8));
				}
				else{
					asdf.add(createButton(1 + i*squaresize,50+j*squaresize,squaresize-gap,squaresize-gap, block[i][j]));
				}
			}
		}
		asdf.setLayout(null);
		asdf.setLocation(20,25);
		asdf.setSize(600, 600);
		return asdf;
	}
	public void Frame() {
			remove(jpan);
			Cast();
			jpan = createsquares(squaresize,size,height);
			add(jpan);
			//repaint();
			//revalidate();
			 //System.out.print("dude");
	}
	public Boolean Move(int xd,int yd) {
		int ny=y+yd;
		int nx=x+xd;
		test = selected;
		Boolean hit = false;
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				if(!(test[i][j]==0)) {
					//System.out.print("("+(i-2+nx)+","+(j-2+ny)+")");
					if((i-2+nx>=0)&&(i-2+nx<size)&&(j-2+ny>=0)&&(j-2+ny<height)) {
						if(!(block[i-2+nx][j-2+ny]==0)) {
							hit=true;
						}
						//hit=true;
					} else {
					hit=true;
					}
				}
			}
		}
		//System.out.println(hit);
		if (!hit) {
			x+=xd;
			y+=yd;
		}
		return hit;
	}
	public Boolean Cast() {
		int ny=y+1;
		int nx=x;
		test = selected;
		Boolean hit = false;
		while (!hit) {
		hit = false;
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				if((test[i][j]==0)) {
					//System.out.print("("+(i-2+nx)+","+(j-2+ny)+")");
					if((i-2+nx>=0)&&(i-2+nx<size)&&(j-2+ny>=0)&&(j-2+ny<height)) {
						if(!(block[i-2+nx][j-2+ny]==0)) {
							hit=true;
						}
						//hit=true;
					} else {
					hit=true;
					}
				}
			}
		}
		//System.out.println(hit);
		if (!hit) {
			ny++;
		}
		}
		casty=ny-1;
		//System.out.println(casty);
		return hit;
	}
	public void Turn(int dgres) {
		int ny=y;
		int nx=x;
		test = new int[5][5];
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				test[4-i][j] = selected[j][i];
				//System.out.print(selected[j][i]);
			}
			//System.out.println();
		}
		Boolean hit = false;
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				if(!(test[i][j]==0)) {
					//System.out.print("("+(i-2+nx)+","+(j-2+ny)+")");
					if((i-2+nx>=0)&&(i-2+nx<size)&&(j-2+ny>=0)&&(j-2+ny<height)) {
						if(!(block[i-2+nx][j-2+ny]==0)) {
							hit=true;
						}
					} else {
					hit=true;
					}
				}
			}
		}
		if (!hit) {
			selected = test;
		}
	}
	public boolean NewBlock(Boolean slice) {
		if (slice){
			score += (casty-y);
			y = casty;
		}
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<height; j++) {
				if ((i>=x-2)&&(i<=x+2)&&(j>=y-2)&&(j<=y+2)&&!(selected[i-x+2][j-y+2]==0)){
					
					block[i][j]=selected[i-x+2][j-y+2];
					if(block[i][j]!=0) {
						//return true;
					}
				}
			}
		}
		Boolean snap=true;
		int streak=0;
		while (snap) {
			snap = false;
		for(int j = height-1; j>=0; j--) {
			int line = 0;
			for(int i = 0; i<size; i++) {
				if(!snap) {
					if (!(block[i][j]==0)){
						line++;
					}
				}else if(j>0){
					block[i][j]=block[i][j-1];
				}else {
					block[i][j]=0;
				}
			}
			if (line == 10)
			{
			streak++;
			snap = true;
			j++;
			score +=streak*100;
			
			}
		}
		}
		
		selected = pieces[rn.nextInt(7)];
		x=5;
		y=2;
		scor.setText(Integer.toString(score));
		return false;
	}
	public void Reset() {
		score=0;
		selected = pieces[rn.nextInt(7)];
		x=5;
		y=2;
		block=new int[size][height];
	}
	private void FindMove(KeyList key,Tetris bp) {
		if(key.Output()[37]==1) {
			bp.Move(-1,0);
		}
		if(key.Output()[39]==1) {
			bp.Move(1,0);
		}
		if(key.Output()[40]==1) {
			bp.Move(0,1);
		}
		if(key.Output()[38]==1) {
			bp.Turn(0);
			key.Output()[38]=2;
		}
		if(key.Output()[32]==1) {
			bp.Cast();
			if(bp.NewBlock(true)) {
				out=1;
			};
			key.Output()[32]=2;
		}
	}
}
