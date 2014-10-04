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
			
		}
		
		
	}
	
	public class Clause{
		int[] vars=new int[3];
		boolean[] varNegs=new boolean[3];
	
	}

}
