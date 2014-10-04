import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;


public class Main extends Applet implements ActionListener{
	public String satInput;
	
	public void init(){
		this.setSize(500, 500);
		this.setLayout(null);
		Label inputLabel=new Label("Input your 3sat");
		inputLabel.setBounds(10,10,100,20);
		TextField input=new TextField(40);
		input.setBounds(10,40,100,20);
		this.add(inputLabel);
		this.add(input);
		input.addActionListener(this);
		
	}
	public void paint(Graphics g){
		
	
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		
		
		
	}

}
