import java.awt.image.BufferedImage;
import java.io.File;

public class Sprite {
	public double x,y;
	public int width,height;
	public double hspeed,vspeed;
	private int stageX,stageY;
	private BufferedImage im;
	
	public Sprite(){
	}
	
	public Sprite(int stageXIn,int stageYIn){
		this();
		this.stageX=stageXIn;
		this.stageY=stageYIn;
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
