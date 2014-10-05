import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class GameFrame extends JFrame {
	
	GamePane gp;
	ArrayList<Sprite> sprites;
	ArrayList<Sprite> movingSprites;
	Sprite mario;
	int winWidth, winHeight;
	int winX, winY;
	int stageX,stageY;
	
	public GameFrame(){
		super("3-Sat as Mario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gp = new GamePane();
		setContentPane(gp);
		winWidth = 32*20;
		winHeight = 32*20;
		setSize(winWidth, winHeight);
		
		stageX = 32*40;
		stageY = 32*40;
		
		winX = 0;
		winY = 0;
		
		sprites = new ArrayList<Sprite>();
		movingSprites = new ArrayList<Sprite>();
		for(int i = 0; i < 40; i++){
			for(int j = 0; j < 40; j++){
				if(i==0||i==39||j==0||j==39)
					sprites.add(new Sprite(i*32.0/stageX, j*32.0/stageY, stageX, stageY, Sprite.BLOCK));
			}
		}
		
		mario = new Sprite(100.0/stageX, 100.0/stageY, stageX, stageY, Sprite.MARIO);
		sprites.add(mario);
		movingSprites.add(mario);
		
		GameThread gt = new GameThread(sprites, movingSprites);
		gt.start();
		
		setVisible(true);
		gp.requestFocusInWindow();
	}
	
	public class GamePane extends JPanel implements KeyListener{
		public GamePane(){
			addKeyListener(this);
		}
		
		public void paintComponent(Graphics g){
//			try {
//				BufferedImage img = ImageIO.read(new File("brick.png"));
//				g.drawImage(img, 64,64,Color.white, null);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			g.fillRect(0, 0, winWidth, winHeight);
			for(Sprite s : sprites){
//				System.out.println("Drawing " + s);
				s.draw(g, winX, winY, winWidth, winHeight);
			}
		}

		public void keyTyped(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
//			System.out.println("key pressed");
			if(e.getKeyCode()==KeyEvent.VK_LEFT){
				mario.hspeed=-175.0/stageX;
			}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
				mario.hspeed=175.0/stageX;
			}else if(e.getKeyCode()==KeyEvent.VK_UP){
				if(!mario.airborne){
					mario.airborne = true;
					mario.vspeed=-500.0/stageY;
				}
			}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
				if(mario.id==Sprite.SUPER_MARIO)
					mario.id=Sprite.CROUCH_MARIO;
			}
		}

		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_LEFT){
				if(mario.hspeed<0)
					mario.hspeed=0;
			}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
				if(mario.hspeed>0)
					mario.hspeed=0;
			}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
				if(mario.id==Sprite.CROUCH_MARIO)
					mario.id=Sprite.SUPER_MARIO;
			}
		}
	}
	
	public class GameThread extends Thread{
		boolean done = false;
		int freq=60;
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
					for(Sprite sprite2:sprites){
						if(sprite2==sprite) continue;
						if(sprite.checkHorCollide(sprite2) && sprite.checkVertCollide(sprite2)){
							System.out.println("hor collision between " + sprite + " and " + sprite2);
							if(sprite.id==Sprite.KOOPA_SHELL){
								if(sprite2.id==Sprite.BRICK){
									sprites.remove(sprite2);
								}
								if(sprite.hspeed>0)
									sprite.setX(sprite2.getPixelX()-sprite.width);
								else
									sprite.setX(sprite2.getPixelX()+sprite2.width);
								sprite.hspeed*=-1;
							}
							if(sprite.id==Sprite.MARIO){
								if(sprite.hspeed>0)
									sprite.setX(sprite2.getPixelX()-sprite.width);
								else
									sprite.setX(sprite2.getPixelX()+sprite2.width);
								
								
								if(sprite2.id==Sprite.KOOPA){
									
									sprites.add(new Sprite(sprite2.x,sprite2.y,stageX,stageY,Sprite.KOOPA_SHELL,Sprite.SHELL_SPEED));
									sprites.remove(sprite2);
								}
								
								if(sprite2.id==Sprite.GOOMBA){
									sprites.remove(sprite2);
									
								}
								if(sprite2.id==Sprite.MUSHROOM){
									sprites.add(new Sprite(sprite.x,sprite.y,stageX,stageY,Sprite.SUPER_MARIO));
									sprites.remove(sprite);
								}
							}
							if(sprite.id==Sprite.SUPER_MARIO){
								if(sprite.hspeed>0)
									sprite.setX(sprite2.getPixelX()-sprite.width);
								else
									sprite.setX(sprite2.getPixelX()+sprite2.width);
								if(sprite2.id==sprite.KOOPA){
									sprites.add(new Sprite(sprite2.x,sprite2.y,stageX,stageY,Sprite.KOOPA_SHELL,Sprite.SHELL_SPEED));
									sprites.remove(sprite2);
								}
								if(sprite2.id==Sprite.GOOMBA){
									sprites.add(new Sprite(sprite.x,sprite.y,stageX,stageY,Sprite.MARIO));
									sprites.remove(sprite);
									sprites.remove(sprite2);
									
								}
							}
						}
					}

					sprite.vspeed+=Sprite.gravity/stageY/freq;
					sprite.y+=sprite.vspeed/freq;

					for(Sprite sprite2:sprites){
						if(sprite2==sprite) continue;
						if(sprite.checkVertCollide(sprite2) && sprite.checkHorCollide(sprite2)){
//							System.out.println("vert collision between " + sprite + " and " + sprite2);
							if(sprite.id==Sprite.KOOPA_SHELL){								
								if(sprite.vspeed>0)
									sprite.setY(sprite2.getPixelY()-sprite.height);
								else
									sprite.setY(sprite2.getPixelY()+sprite2.height);
								sprite.vspeed=0;
							}
							if(sprite.id==Sprite.MARIO){
								if(sprite.vspeed>0){
									sprite.setY(sprite2.getPixelY()-sprite.height);
									sprite.airborne=false;
								}
								else
									sprite.setY(sprite2.getPixelY()+sprite2.height);
								if(sprite.vspeed>0)
									sprite.vspeed=0;
								
								if(sprite2.id==Sprite.KOOPA){
									sprites.add(new Sprite(sprite2.x,sprite2.y,stageX,stageY,Sprite.KOOPA_SHELL,Sprite.SHELL_SPEED));
									sprites.remove(sprite2);
									sprite.vspeed*=-1;
								}
								if(sprite2.id==Sprite.GOOMBA){
									sprites.remove(sprite2);
								}
								if(sprite2.id==Sprite.MUSHROOM){
									sprites.add(new Sprite(sprite.x,sprite.y,stageX,stageY,Sprite.SUPER_MARIO));
									sprites.remove(sprite);
								}
								if(sprite2.id==Sprite.QUESTION_BLOCK){
									if(sprite.vspeed<0){
										sprites.add(new Sprite(sprite2.x,sprite2.y,stageX,stageY,Sprite.BLOCK));
										sprites.add(new Sprite(sprite2.x,sprite2.y-sprite2.width,stageX,stageY,Sprite.MUSHROOM));
										sprites.remove(sprite2);
									}
								}
								
								
							}
							if(sprite.id==sprite.SUPER_MARIO){
								if(sprite.vspeed>0)
									sprite.setY(sprite2.getPixelY()-sprite.height);
								else
									sprite.setY(sprite2.getPixelY()+sprite2.height);
								if(sprite.vspeed>0)
									sprite.vspeed=0;
								if(sprite2.id==sprite.KOOPA){
									sprites.add(new Sprite(sprite2.x,sprite2.y,stageX,stageY,sprite.KOOPA_SHELL,sprite.SHELL_SPEED));
									sprites.remove(sprite2);
									sprite.vspeed*=-1;
								}
								if(sprite2.id==sprite.GOOMBA){
									sprites.add(new Sprite(sprite.x,sprite.y,stageX,stageY,sprite.MARIO));
									sprites.remove(sprite);
									sprites.remove(sprite2);
								}
								if(sprite2.id==sprite.QUESTION_BLOCK){
									if(sprite.vspeed<0){
										sprites.add(new Sprite(sprite2.x,sprite2.y,stageX,stageY,sprite.BLOCK));
										sprites.add(new Sprite(sprite2.x,sprite2.y-sprite2.width,stageX,stageY,sprite.MUSHROOM));
										sprites.remove(sprite2);
									}
								}
								if(sprite2.id==sprite.BRICK){
									if(sprite.vspeed<0){
										sprites.remove(sprite2);
										sprite.vspeed=0;
									}
								}
								
							}
							if(sprite.id==sprite.CROUCH_MARIO){
								if(sprite.vspeed>0)
									sprite.setY(sprite2.getPixelY()-sprite.height);
								else
									sprite.setY(sprite2.getPixelY()+sprite2.height);
								
								if(sprite2.id==sprite.KOOPA){
									sprites.add(new Sprite(sprite2.x,sprite2.y,stageX,stageY,sprite.KOOPA_SHELL,sprite.SHELL_SPEED));
									sprites.remove(sprite2);
								}
								if(sprite2.id==sprite.GOOMBA){
									sprites.add(new Sprite(sprite.x,sprite.y,stageX,stageY,sprite.MARIO));
									sprites.remove(sprite);
									sprites.remove(sprite2);
								}
								if(sprite2.id==sprite.BRICK){
									if(sprite.vspeed>0){
										sprites.remove(sprite2);
										sprite.vspeed=0;
									}
								}
								if(sprite.vspeed>0)
									sprite.vspeed=0;
							}
						}
						
					}
				}
				//End step computations
				winX = mario.getPixelX()-winWidth/2;
				winY = mario.getPixelY()-winWidth/2;
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
