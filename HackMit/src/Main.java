import java.applet.Applet;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;


public class Main extends JApplet implements ActionListener{
	public String satInput;
	TextField input=new TextField(40);
	Button render = new Button("Render Level");
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
		
		render.setBounds(10,80,100,20);
		this.add(render);
		render.addActionListener(this);
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
		else if(e.getSource()==render){
			GameFrame f = new GameFrame();
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
