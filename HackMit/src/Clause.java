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