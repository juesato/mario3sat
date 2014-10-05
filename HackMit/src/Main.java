import java.applet.Applet;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;


public class Main extends JApplet implements ActionListener{
	public String satInput;
	public TextField input=new TextField(40);
	public Label inputLabel=new Label("Input your 3sat");
	Button render = new Button("Render Level");
	public int numVars;
	public ArrayList<Clause> clauses=new ArrayList<Clause>();
	
	public void init(){
		this.setSize(500, 500);
		this.setLayout(null);
		
		inputLabel.setBounds(10,10,200,20);
		
		input.setBounds(10,40,200,20);
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
			parse(satInput);
			input.setEditable(false);
			inputLabel.setText("Play Mario to solve your 3sat!");
			
			/*(for(String x : vars){
				System.out.println(x);
			}
			for(Clause x:clauses){
				System.out.println("vars " + x.vars[1] + " varNegs " + x.varNegs[1]);
			}
			*/
		}
		else if(e.getSource()==render){
			GameFrame f = new GameFrame();
		}
		
	}
	
	public void parse(String satInput){
		String[] ors=satInput.split("&");
		ArrayList<String> vars=new ArrayList<String>();
		for(String x : ors){
			if(x.equals("[&]"))
				break;
			Clause clause=new Clause();
			int start=x.indexOf('(');
			int stop=x.indexOf(')');
			x=x.substring(start+1, stop);
			
			String[] varExps=x.split("[|]");
			
			int counter=0;
			for(int i=0;i<varExps.length;i++){					
				String y=varExps[i];
				if(y.equals("|"))
					continue;
				//System.out.println("x " + x + " i " + i + " y " + y + "\n" );
				if(y.charAt(0)=='!'){
					clause.varNegs[counter]=false;
					y=y.substring(1);
				}
				if(vars.indexOf(y)>=0){
					clause.vars[counter]=vars.indexOf(y);
				}
				else
				{
					vars.add(y);
					clause.vars[counter]=vars.size()-1;
				}
				counter++;
			}
			clauses.add(clause);
			numVars=vars.size();
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
