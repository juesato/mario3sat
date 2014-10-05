public class Sprite {
	public double x,y;
	public int width,height;
	public double hspeed,vspeed;
	private int stageX,stageY;
	
	public Sprite(){
		
	}
	
	public Sprite(int stageXIn,int stageYIn){
		this.stageX=stageXIn;
		this.stageY=stageYIn;
	}
	
	public int checkHorCollide(Sprite in){
		//TODO
		return 0;
	}
	
	public int checkVertCollide(Sprite in){
		//TODO
		return 0;
	}
	
	public int getPixelX(){
		return (int)x;
	}
	public int getPixelY(){
		return (int)y;
	}
}
