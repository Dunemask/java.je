package game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import dunemask.util.FileUtil;

public class Player {
	int x;
	int y;
	int vx = 0;
	int vy = 0;
	JLabel lab = new JLabel("Player");
	Board pan = null;
	KeyList key = new KeyList();
	public Player(Board pane,int xx, int yy) {
		x=xx;
		y=yy;
		pan = pane;
		lab.setSize(16,16);
		pan.add(lab);
		pan.setFocusable(true);
		pan.requestFocus();
		pan.addKeyListener(key);
		lab.setIcon(new ImageIcon(FileUtil.getResource("game/resources/player.png").getPath()));
	}

	public void update() {
		x+=vx;
		y+=vy;
		if(x<0)
			x=0;
		if(x>512-16)
			x=512-16;
		if(1==1) {
			if (DetectBlok(x+15,y+14)||DetectBlok(x+15,y+1)) {
				x-=vx;
				vx=0;
			}
			if (DetectBlok(x,y+14)||DetectBlok(x,y+1)) {
				x-=vx;
				vx=0;
			}
		if (DetectBlok(x-0,y+15)||DetectBlok(x+15,y+15)) {
			y-=vy;
			vy=0;
			
			if (key.Output()[38]==1) {
				if (!(DetectBlok(x-0,y+0)||DetectBlok(x+15,y+0))) {
					vy = -15;
				}
			}
		} else {
			vy++;
		}
		if (DetectBlok(x-0,y+0)||DetectBlok(x+15,y+0)) {
			y-=vy;
			vy=1;
		}
		if (key.Output()[37]==1) {
			vx += -5;
		}
		if (key.Output()[39]==1) {
			vx += 5;
		}
		
		}
		//System.out.println("X:" + x +" Y:" + y +" XV:" + vx +" YV:" + vy );
		vx = (vx * 60)/100;
		if (vy>15) {vy=15;}
		lab.setLocation(x, y);
	}
	public boolean DetectBlok(int x,int y) {
		if (pan.getAllSquares()[(x/16)][((y)/16)] != null) {
		return true;
		}else {
		return false;
		}
	}
}
