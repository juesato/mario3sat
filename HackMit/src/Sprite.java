import java.awt.image.BufferedImage;
import java.io.File;

public class Sprite {
	public double x,y;
	public int width,height;
	public double hspeed,vspeed;
	private int stageX,stageY;
	private BufferedImage im;
	public int id;
	
	public final int MARIO=0;
	public final int KOOPA=1;
	public final int KOOPA_SHELL=2;
	public final int GOOMBA=3;
	public final int MUSHROOM=4;
	public final int QUESTION_BLOCK=5;
	public final int BRICK=6;
	public final int BLOCK=7;
	public final int SUPER_MARIO=8;
	public final int CROUCH_MARIO=9;
	
	public final int SHELL_SPEED=12;
	
	public String[] sup = {"Mario", "Koopa", "Koopa shell", "Gumba", "Mushroom", "Question block",
      "Brick", "Block", "Super mario","Crouch Mario"};

  	
  	public String toString() {
    	return sup[id] + " " + getPixelX() + " " + getPixelY();
  	}
	
	
	public Sprite(){
	}
	
	public Sprite(int stageXIn,int stageYIn){
		this();
		this.stageX=stageXIn;
		this.stageY=stageYIn;
	}
	
	public Sprite(int idin){
		this.id=idin;
	}
	
	public Sprite(int stageXIn,int stageYIn, int idin){
		this.id=idin;
	}
	
	public Sprite(double xIn,double yIn, int stageXIn,int stageYIn,int idin){
		this.id=idin;
		this.x=xIn;
		this.y=yIn;
		this.stageX=stageXIn;
		this.stageY=stageYIn;
	}
	
	public Sprite(double xIn,double yIn, int stageXIn,int stageYIn,int idin,double vspeedIn){
		this.id=idin;
		this.x=xIn;
		this.y=yIn;
		this.stageX=stageXIn;
		this.stageY=stageYIn;
		this.vspeed=vspeedIn;
	}
	
	public boolean checkHorCollide(Sprite in){
		int leftX = getPixelX();
		int rightX = leftX+width-1;
		int otherLeftX = in.getPixelX();
		int otherRightX = otherLeftX+in.width-1;
		return (leftX>=otherLeftX && leftX <= otherRightX) || (rightX >= otherLeftX && rightX <= otherRightX);
	}
	
	public boolean checkVertCollide(Sprite in){
		int leftY = getPixelY();
		int rightY = leftY+width-1;
		int otherLeftY = in.getPixelY();
		int otherRightY = otherLeftY+in.width-1;
		return (leftY>=otherLeftY && leftY <= otherRightY) || (rightY >= otherLeftY && rightY <= otherRightY);
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
}
