public class Sprite {
	public double x,y;
	public int width,height;
	public double hspeed,vspeed;
	private int stageX,stageY;
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
		//TODO
		return false;
	}
	
	public boolean checkVertCollide(Sprite in){
		//TODO
		return false;
	}
	
	public int getPixelX(){
		return (int)x;
	}
	public int getPixelY(){
		return (int)y;
	}
}
