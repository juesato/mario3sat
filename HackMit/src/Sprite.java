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
	public final int GUMBA=3;
	public final int MUSHROOM=4;
	public final int QUESTION_BLOCK=5;
	public final int BRICK=6;
	public final int BLOCK=7;
	public final int SUPER_MARIO=8;
	
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
