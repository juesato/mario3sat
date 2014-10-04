import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;


public class Main extends Applet implements ActionListener{
	public String satInput;
	TextField input=new TextField(40);
	public int numVars;
	public ArrayList<Clause> clauses=new ArrayList<Clause>();
	
	public void init(){
		this.setSize(500, 500);
		this.setLayout(null);
		Label inputLabel=new Label("Input your 3sat");
		inputLabel.setBounds(10,10,100,20);
		
		input.setBounds(10,40,100,20);
		this.add(inputLabel);
		this.add(input);
		input.addActionListener(this);
		
	}
	public void paint(Graphics g){
		
	
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==input){
			satInput=input.getText();
			String[] ors=satInput.split("&");
			ArrayList<String> vars=new ArrayList<String>();
			for(String x : ors){
				Clause clause=new Clause();
				int start=x.indexOf('(');
				int stop=x.indexOf('(');
				x=x.substring(start+1, stop+1);
				String[] varExps=x.split("|");
				for(int i=0;i<3;i++){
					String y=varExps[i];
					if(y.charAt(0)=='!'){
						clause.varNegs[i]=false;
						y=y.substring(1);
					}
					if(vars.indexOf(y)>=0){
						clause.vars[i]=vars.indexOf(y);
					}
				}
			}
		}
		
		
	}
	
	public class Clause{
		public int[] vars=new int[3];
		public boolean[] varNegs={true,true,true};
		
		public Clause(){
			
		}
		
		public Clause(int[] varsIn,boolean[] varNegsIn){
			this.vars=varsIn;
			this.varNegs=varNegsIn;
		}
		
	}

}
