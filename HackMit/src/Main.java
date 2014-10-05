import java.awt.Button;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JApplet;


@SuppressWarnings("serial")
public class Main extends JApplet implements ActionListener {
  public String satInput;
  public TextField input = new TextField(40);
  public Label inputLabel = new Label("Input your 3sat");
  Button render = new Button("Render Level");
  public int numVars;
  public ArrayList<Clause> clauses = new ArrayList<Clause>();


  public boolean check(boolean[] on, int n, ArrayList<Clause> clauses) {
    for (Clause clause : clauses) {
      boolean ok = false;
      for (int i = 0; i < 3; i++) {
        if (on[clause.vars[i]] == clause.varNegs[i]) {
          ok = true;
        }
      }
      if (!ok) {
        return false;
      }
    }
    return true;
  }

  public boolean recur(boolean[] on, int n, ArrayList<Clause> clauses, int depth) {
    if (depth == n) {
      return check(on, n, clauses);
    }

    on[depth] = false;
    if (recur(on, n, clauses, depth + 1)) {
      return true;
    }

    on[depth] = true;
    if (recur(on, n, clauses, depth + 1)) {
      return true;
    }

    return false;
  }

  public boolean isGood(ArrayList<Clause> clauses) {
    int n = 0;
    for (Clause clause : clauses) {
      for (int i = 0; i < 3; i++) {
        n = Math.max(clause.vars[i], n + 1);
      }
    }

    boolean on[] = new boolean[n];
    return recur(on, n, clauses, 0);
  }



  @Override
  public void init() {
    this.setSize(500, 500);
    this.setLayout(null);

    inputLabel.setBounds(10, 10, 200, 20);

    input.setBounds(10, 40, 200, 20);
    this.add(inputLabel);
    this.add(input);
    input.addActionListener(this);



    render.setBounds(10, 80, 100, 20);
    this.add(render);
    render.addActionListener(this);
  }

  @Override
  public void paint(Graphics g) {


  }


  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == input) {
      satInput = input.getText();
      parse(satInput);
      
      ArrayList<ArrayList<Integer>> grid = getSymbolicGrid(clauses, numVars);

      if (isGood(clauses)) {
        System.out.println("YEEEEEEAH");
      } else {
        System.out.println("You dun fucked up");
      }

      System.out.println("ahuhfdljvd");

      input.setEditable(false);
      inputLabel.setText("Play Mario to solve your 3sat!");

      /*
       * (for(String x : vars){
       * System.out.println(x);
       * }
       * for(Clause x:clauses){
       * System.out.println("vars " + x.vars[1] + " varNegs " + x.varNegs[1]);
       * }
       */
    }
    else if (e.getSource() == render) {
      @SuppressWarnings("unused")
      GameFrame f = new GameFrame();
    }

  }

  public void parse(String satInput) {
    String[] ors = satInput.split("&");
    ArrayList<String> vars = new ArrayList<String>();
    for (String x : ors) {
      if (x.equals("[&]"))
        break;
      Clause clause = new Clause();
      int start = x.indexOf('(');
      int stop = x.indexOf(')');
      x = x.substring(start + 1, stop);

      String[] varExps = x.split("[|]");

      int counter = 0;
      for (int i = 0; i < varExps.length; i++) {
        String y = varExps[i];
        if (y.equals("|"))
          continue;
        // System.out.println("x " + x + " i " + i + " y " + y + "\n" );
        if (y.charAt(0) == '!') {
          clause.varNegs[counter] = false;
          y = y.substring(1);
        }
        if (vars.indexOf(y) >= 0) {
          clause.vars[counter] = vars.indexOf(y);
        }
        else
        {
          vars.add(y);
          clause.vars[counter] = vars.size() - 1;
        }
        counter++;
      }
      clauses.add(clause);
      numVars = vars.size();
    }
  }

  public class Clause {
    public int[] vars = new int[3];
    public boolean[] varNegs = {true, true, true};

    public Clause() {

    }

    public Clause(int[] varsIn, boolean[] varNegsIn) {
      this.vars = varsIn;
      this.varNegs = varNegsIn;
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
  public static final int ELBOW_T_INV = 65; // Upside-down T
  
  public static final int CROSSOVER_UR = 10;
  public static final int CROSSOVER_UL = 11;
  public static final int CROSSOVER_DR = 12;
  public static final int CROSSOVER_DL = 13;
  
  public static final int EMPTY = 123;
  
  public static final int FINISH = 80;
  
  public static final int BIG_NUMBER = 10;

  public int smartAdd(int cur, int added) {
      if (cur == 0) {
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
      if ((cur == PATH_DROP && added == PATH_L) || (cur == PATH_L && added == PATH_DROP)) {
      	System.out.println("WARNING: Drop changed to crossover");
      	return CROSSOVER_DL;
      }
      if ((cur == PATH_D && added == PATH_R) || (cur == PATH_D && added == PATH_R)) {
          return CROSSOVER_DR;
      }
      if ((cur == PATH_DROP && added == PATH_R) || (cur == PATH_R && added == PATH_DROP)) {
      	System.out.println("WARNING: Drop changed to crossover");
      	return CROSSOVER_DR;
      }
      if (added == ELBOW_T_INV || cur == ELBOW_T_INV) {
      	return ELBOW_T_INV;
      }
      else {
          System.out.println("SOMETHING HAS GONE VERY WRONG!!!!");
          System.out.println("CUR " + cur);
          System.out.println("ADDED " + added);
          return -1;
      }
  }
  
  public ArrayList<ArrayList<Integer>> getSymbolicGrid(ArrayList<Clause> clauseList, int vars) {    

      int c = clauseList.size();
      int v = vars;
      int rows = 6 * v + 4 + 3;
      int cols = 6 * c + 4 * v + 5;
      

      
      /* We will later combine paths into crossover gadgets. Yes, x, y is out of order */
      int[][] tempGrid = new int[rows][cols] ;
      
      /* Set all variable gadgets */
      int var_row = 2*v + 1;
      int var_col = 2*v + 1;
      
      int clause_row = var_row + 4*v + 3;
      int clause_col = var_col + 2;
      

      // We need to keep track of which variables are in which columns
      int[] colToClauseVar = new int[cols];
      for (int i = 0; i < cols; i++) {
      	colToClauseVar[i] = -999;
      }
      int clauseY = clause_row;
      int clauseX = clause_col;
      for (Clause clause : clauseList) {
      	for (int i = 0; i < 3; i++) {
      		colToClauseVar[clauseX] = clause.vars[i];
      		if (clause.varNegs[i] == false) {
          		colToClauseVar[clauseX] *= -1;
      		}
      		clauseX += 2;
      	}
      }
      for (int i = 0; i < cols; i++) {
      	System.out.println(colToClauseVar[i]);
      }
 
      
      
      int cur_x = var_col;
      for (int i = 0; i < v; i++) {
          tempGrid[var_row][cur_x] = smartAdd(tempGrid[var_row][cur_x], VARIABLE[0]);
          

          /*                 
           *             VARIABLE
           *                 |
           *         ________J
           *        |
           *        |_______________
           *             |      |  |
           *           CLAUSE   C  C
           * */
          int x = cur_x;
          int y = var_row + 1;
          
          tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_DROP);
          y++;
          for (int j = 0; j < 2*i; j++) {
              tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_D);
              y++;
          }
          tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_UL);
          x--;
          for (; x > 2*i + 1; x--) {
              tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_L);
          }
          tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_DR);
          y++;
          for (; y < clause_row - 2*i - 1; y++) {
              tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_D);
          }
          tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_UR);
          x++;
          for (; x < clause_col + 6*c + 2*v - 2*i - 1; x++) {
          	tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_R);
          }
          if (i == v-1) { // Go from last variable to check clauses
              tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_T_INV);
              int x_tmp = x+1;
              for (; x_tmp < cols - 1; x_tmp++) {
                  tempGrid[y][x_tmp] = smartAdd(tempGrid[y][x_tmp], PATH_R);                	
              }
              tempGrid[y][x_tmp] = smartAdd(tempGrid[y][x_tmp], ELBOW_DL);
              int y_tmp = y+1;
              for (; y_tmp < rows - 1; y_tmp++) {
              	tempGrid[y_tmp][x_tmp] = PATH_DROP;
              }
              tempGrid[y_tmp][x_tmp] = ELBOW_T_INV;
              x_tmp--;
          }
          else {
              tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_UL);
              y--;
          }
          for (; y > 2*i; y--) {
          	tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_U);            	
          }
          tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_DL);
          x--;
          for (; x > var_col + i*3; x--) {
          	tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_L);            	            	
          }
          tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_DR);
          y++;
          for (; y < var_row - 1; y++) {
          	tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_D);            	            	            	
          }
          tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_DROP);
          
          // Do the same thing for the next variable
          cur_x++;
          tempGrid[var_row][cur_x] = smartAdd(tempGrid[var_row][cur_x], VARIABLE[1]);
          
          x = cur_x;
          y = var_row + 1;
          tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_DROP);
          y++;
          for (int j = 0; j < 2*i + 1; j++) {
              tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_D);
              y++;
          }
          tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_UL);
          x--;
          for (; x > 2*i + 2; x--) {
              tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_L);
          }
          tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_DR);
          y++;
          for (; y < clause_row - 2*i - 2; y++) {
              tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_D);
          }
          tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_UR);
          x++;
          for (; x < clause_col + 6*c + 2*v - 2*i - 2; x++) {
          	tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_R);
          }

          if (i == v-1) { // Go from last variable to check clauses
              tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_T_INV);
              int x_tmp = x+1;
              for (; x_tmp < cols - 2; x_tmp++) {
                  tempGrid[y][x_tmp] = smartAdd(tempGrid[y][x_tmp], PATH_R);                	
              }
              tempGrid[y][x_tmp] = smartAdd(tempGrid[y][x_tmp], ELBOW_DL);
              int y_tmp = y+1;
              for (; y_tmp < rows - 1; y_tmp++) {
              	tempGrid[y_tmp][x_tmp] = PATH_DROP;
              }
              tempGrid[y_tmp][x_tmp] = ELBOW_T_INV;
              x_tmp--;
              // Wrap around the bottom
              for (; x_tmp > 1; x_tmp--) {
              	tempGrid[y_tmp][x_tmp] = smartAdd(tempGrid[y_tmp][x_tmp], PATH_L);
              }
              tempGrid[y_tmp][x_tmp] = smartAdd(tempGrid[y_tmp][x_tmp], ELBOW_UR);
              y_tmp--;
              tempGrid[y_tmp][x_tmp] = smartAdd(tempGrid[y_tmp][x_tmp], ELBOW_DR);
              x_tmp++;
              for (; x_tmp < clause_col; x_tmp++) {
                  tempGrid[y_tmp][x_tmp] = smartAdd(tempGrid[y_tmp][x_tmp], PATH_R);
              }
          }
          else {
              tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_UL);
              y--;
          }
          //
          
          for (; y > 2*i + 1; y--) {
          	tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_U);            	
          }
          tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_DL);
          x--;
          for (; x > var_col + i*3 + 1; x--) {
          	tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_L);            	            	
          }
          tempGrid[y][x] = smartAdd(tempGrid[y][x], ELBOW_DR);
          y++;
          for (; y < var_row - 1; y++) {
          	tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_D);            	            	            	
          }
          tempGrid[y][x] = smartAdd(tempGrid[y][x], PATH_DROP);            
          //
          cur_x++;
          
          tempGrid[var_row][cur_x] = smartAdd(tempGrid[var_row][cur_x], EMPTY);
          cur_x++;
      }
      
      // Add Clauses

      for (int i = 0; i < clauseList.size(); i++) {
          int y = clause_row;
      	int x = clause_col + 6 * i;
      	for (int j = 0; j < 6; j++) {
      		for (int k = 0; k < 2; k++) {
      			tempGrid[y+k][x+j] = smartAdd(tempGrid[y+k][x+j], CLAUSE[k][j]);
      		};
      	}
      }
      tempGrid[clause_row+1][clause_col + 6*clauseList.size()] = FINISH;
      
      for (int i = 0; i < rows; i++) {
      	for (int j = 0; j < cols; j++) {
      		System.out.print(tempGrid[i][j]);
      		System.out.print(",");
      	}
      	System.out.print("\n");
      }
      
      return new ArrayList<ArrayList<Integer>>();
  }
  
  String test = "(A|B|C)&(!A|!B|!C)";
	
}
