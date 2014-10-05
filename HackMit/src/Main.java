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
	
    public static final int[] VARIABLE = {21,22};
    public static final int[][] CLAUSE = {{31,32,33,34,35,36},{37,38,39,40,41,42}};
    public static final int PATH_U = 50;
    public static final int PATH_D = 51;
    public static final int PATH_L = 52;
    public static final int PATH_R = 53;
    public static final int PATH_DROP = 54;
    
    public static final int ELBOW_UL = 60;
    public static final int ELBOW_UR = 61;
    public static final int ELBOW_DL = 62;
    public static final int ELBOW_DR = 63;
    public static final int ELBOW_T = 64; // T-shaped
    
    public static final int CROSSOVER_UR = 10;
    public static final int CROSSOVER_UL = 11;
    public static final int CROSSOVER_DR = 12;
    public static final int CROSSOVER_DL = 13;
    
    public static final int EMPTY = 123;
    
    public static final int BIG_NUMBER = 10;

    public int smartAdd(int cur, int added) {
        if (cur != 0) {
            return added;
        }
        if ((cur == PATH_U && added == PATH_L) || (cur == PATH_L && added == PATH_U)) {
            return CROSSOVER_UL;
        }
        if ((cur == PATH_U && added == PATH_R) || (cur == PATH_R && added == PATH_U)) {
            return CROSSOVER_UR;
        }
        if ((cur == PATH_D && added == PATH_L) || (cur == PATH_L && added == PATH_D)) {
            return CROSSOVER_DL;
        }
        if ((cur == PATH_D && added == PATH_R) || (cur == PATH_D && added == PATH_R)) {
            return CROSSOVER_DR;
        }
        else {
            System.out.println("SOMETHING HAS GONE VERY WRONG!!!!");
            System.out.println(cur);
            System.out.println(added);
            return -1;
        }
    }
    
    public ArrayList<ArrayList<Integer>> getSymbolicGrid(ArrayList<Clause> clauseList, int vars) {    

        int c = clauseList.size();
        int v = vars;
        int rows = 6 * v + 3;
        int cols = 6 * c + 4 * v + 1;
        
        /* We will later combine paths into crossover gadgets. Yes, x, y is out of order */
        int[][] tempGrid = new int[rows + 2][cols + 2] ;
        
        /* Set all variable gadgets */
        int var_row = 2*v;
        int var_col = 2*v + 1;
        
        int clause_row = var_row + 4*v + 1;
        int clause_col = var_col;
        
        
        int cur_x = var_col;
        for (int i = 0; i < v; i++) {
            tempGrid[var_row][cur_x] = smartAdd(tempGrid[var_row][cur_x], VARIABLE[0]);
            
            int x = cur_x;
            int y = var_row - 1;
            /*                 
             *             VARIABLE
             *                 |
             *         ________J
             *        |
             *        |_______________
             *             |      |  |
             *           CLAUSE   C  C
             * */
            for (int j = 0; j < 2*i; j++) {
                tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_D);
                y++;
            }
            tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_UL);
            for (; x > 2*i + 1; x--) {
                tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_L);
            }
            tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_DR);
            for (; y > clause_row + 2*i + 1; y--) {
                tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_D);
            }
            tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_UR);
            
            
            cur_x++;
            tempGrid[var_row][cur_x] = smartAdd(tempGrid[var_row][cur_x], VARIABLE[1]);
            cur_x++;
            tempGrid[var_row][cur_x] = smartAdd(tempGrid[var_row][cur_x], EMPTY);
            cur_x++;
        }   
        
        return new ArrayList<ArrayList<Integer>>();
    }
    
    String test = "(A|B|C)&(!A|!B|!C)";
	
}
