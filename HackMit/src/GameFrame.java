import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.image.*;

public class GameFrame extends JFrame {
	
	GamePane gp;
	ArrayList<Sprite> sprites;
	ArrayList<Sprite> movingSprites;
	int winWidth, winHeight;
	int winX, winY;
	
	public GameFrame(){
		super("3-Sat as Mario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gp = new GamePane();
		setContentPane(gp);
		winWidth = 32*20;
		winHeight = 32*20;
		setSize(winWidth, winHeight);
		
		winX = 0;
		winY = 0;
		
		sprites = new ArrayList<Sprite>();
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 20; j++){
				sprites.add(new Sprite(i*32.0/winWidth, j*32.0/winHeight, winWidth, winHeight, Sprite.BRICK));
			}
		}
		
		setVisible(true);
	}
	
	public class GamePane extends JPanel{
		public void paintComponent(Graphics g){
//			try {
//				BufferedImage img = ImageIO.read(new File("brick.png"));
//				g.drawImage(img, 64,64,Color.white, null);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			for(Sprite s : sprites){
				System.out.println("Drawing " + s);
				s.draw(g, winX, winY, winWidth, winHeight);
			}
		}
	}
	
	public class GameThread extends Thread{
		boolean done = false;
		int freq=30;
		long stepSize = (long)(1000/freq);
		ArrayList<Sprite> sprites;
		ArrayList<Sprite> movingSprites;
		
		public GameThread(ArrayList<Sprite> spritesIn,ArrayList<Sprite> movingSpritesIn){
			this.sprites=spritesIn;
			this.movingSprites=movingSpritesIn;
		}
		
		public void run(){
			
			while(!done){
				long timestamp = System.currentTimeMillis();
				//Begin step computations
				for(Sprite sprite : movingSprites){
					sprite.x+=sprite.hspeed/freq;
					sprite.y+=sprite.vspeed/freq;
					for(Sprite sprite2:sprites){
						if(sprite.checkHorCollide(sprite2)){
							if(sprite.id==sprite.KOOPA_SHELL){
								if(sprite2.id==sprite.BRICK){
									sprites.remove(sprite2);
								}
								sprite.x=sprite2.x+(sprite2.x-sprite.x);
								sprite.hspeed*=-1;
							}
							if(sprite.id==sprite.MARIO){
								if(sprite2.id==sprite.KOOPA){
									
								}
							}
						}
					}
				}
				//End step computations
				gp.repaint();
				long left = timestamp + stepSize - System.currentTimeMillis();
				if(left > 0)
					delay(left);
			}
		}
		
		public void delay(long ms){
			try {
				Thread.sleep(ms);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
