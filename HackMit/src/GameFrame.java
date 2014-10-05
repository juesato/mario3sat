import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.image.*;

public class GameFrame extends JFrame {
	
	GamePane gp;
	
	public GameFrame(){
		super("3-Sat as Mario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gp = new GamePane();
		setContentPane(gp);
		setSize(32*20, 32*20);
		
		setVisible(true);
	}
	
	public class GamePane extends JPanel{
		public void paintComponent(Graphics g){
			try {
				BufferedImage img = ImageIO.read(new File("brick.png"));
				g.drawImage(img, 64,64,Color.white, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class GameThread extends Thread{
		boolean done = false;
		long stepSize = (long)(1000.0/30.0);
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
				for(Sprite x : movingSprites){
					
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
