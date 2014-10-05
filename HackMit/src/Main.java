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
      
      ArrayList<ArrayList<Integer>> grid = AbstractMapGrid.getSymbolicGrid(clauses, numVars);

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
}
