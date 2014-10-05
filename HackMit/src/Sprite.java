import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	public double x,y;
	public int width,height;
	public double hspeed,vspeed;
	private int stageX,stageY;
	private BufferedImage im;
	public int id;
	
	public static final int MARIO=0;
	public static final int KOOPA=1;
	public static final int KOOPA_SHELL=2;
	public static final int GOOMBA=3;
	public static final int MUSHROOM=4;
	public static final int QUESTION_BLOCK=5;
	public static final int BRICK=6;
	public static final int BLOCK=7;
	public static final int SUPER_MARIO=8;
	public static final int CROUCH_MARIO=9;
	
	public static final int SHELL_SPEED=12;
	public static final double gravity=256;
	
	public String[] sup = {"Mario", "Koopa", "Koopa shell", "Goomba", "Mushroom", "Question block",
      "Brick", "Block", "Super mario","Crouch Mario"};

  	
  	public String toString() {
    	return sup[id] + " " + getPixelX() + " " + getPixelY();
  	}
	
	
	public Sprite(int idin){
		this.id=idin;
		File imf;
		switch(id){
		case KOOPA: imf=new File("koopa_red.gif"); break;
		case KOOPA_SHELL: imf=new File("koopa_shell_red.gif"); break;
		case GOOMBA: imf=new File("goomba.jpg"); break;
		case MUSHROOM: imf=new File("mushroom.png"); break;
		case QUESTION_BLOCK: imf=new File("mystery_box.gif"); break;
		case BRICK: imf=new File("brick.png"); break;
		case BLOCK: imf=new File("empty_block.gif"); break;
		case SUPER_MARIO: imf=new File("mario_large.gif"); break;
		case CROUCH_MARIO: imf=new File("mario_crouch.gif"); break;
		default: imf=new File("mario_small.gif"); break;
		}
		try {
			im=ImageIO.read(imf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		width=im.getWidth();
		height=im.getHeight();
	}
	
	public Sprite(int stageXIn,int stageYIn, int idin){
		this(idin);
		this.stageX=stageXIn;
		this.stageY=stageYIn;
	}
	
	public Sprite(double xIn, double yIn, int stageXIn, int stageYIn, int idin){
		this(stageXIn, stageYIn, idin);
		x = xIn;
		y = yIn;
	}
	
	public Sprite(double xIn, double yIn, int stageXIn, int stageYIn, int idin, double hspeedIn){
		this(xIn, yIn, stageXIn, stageYIn, idin);
		hspeed=hspeedIn;
	}
	
	public boolean checkHorCollide(Sprite in){
		int leftX = getPixelX();
		int rightX = leftX+width-1;
		int otherLeftX = in.getPixelX();
		int otherRightX = otherLeftX+in.width-1;
		return (leftX>=otherLeftX && leftX <= otherRightX) || (rightX >= otherLeftX && rightX <= otherRightX);
	}
	
	public boolean checkVertCollide(Sprite in){
		int topY = getPixelY();
		int botY = topY+height-1;
		int otherTopY = in.getPixelY();
		int otherBotY = otherTopY+in.width-1;
		return (topY>=otherTopY && topY <= otherBotY) || (botY >= otherTopY && botY <= otherBotY);
	}
	
	public int getPixelX(){
		return (int)(x*stageX);
	}
	public int getPixelY(){
		return (int)(y*stageY);
	}
	public void setX(int pixelX){
		x = ((double)pixelX)/stageX;
	}
	public void setY(int pixelY){
		y = ((double)pixelY)/stageY;
	}
	
	public void draw(Graphics g, int winX, int winY, int winWidth, int winHeight){
		int pixelX = getPixelX();
		int pixelY = getPixelY();
//		System.out.println("X is: " + pixelX + " and Y is: " + pixelY);
		if(pixelX+width>=winX && pixelY+height>=winY && pixelX<=winX+winWidth && pixelY<=winY+winHeight){
			g.drawImage(im, pixelX-winX, pixelY-winY, null);
		}
	}
}
