import java.util.ArrayList;

public class Gadget {
  private static final int SIDE = 20;
  private static final int PIXELS = 32;
  private static final int DUDES = 1;
  int rows;
  int cols;
  int r;
  int c;

  private int[][] grid = new int[SIDE][SIDE];
  private ArrayList<Sprite> dudes = new ArrayList<Sprite>();

  private String[][] maps = {
      {"7777777......7777777",
          "7777777......7777777",
          "7777777......7777777",
          "7777777......7777777",
          "7777777......7777777",
          "7777777......7777777",
          "7777777......7777777",
          "7...................",
          "777777777777777777..",
          "7...................",
          "7..............77777",
          "7..............77777",
          "7..............77777",
          "7..............77777",
          "7..............77777",
          "7..............77777",
          "7..............77777",
          "7..............77777",
          "7..............77777",
          "7..............77777"},
      {"7777777......7777777",
          "7777777......7777777",
          "7777777.........7..7",
          "7777777.........7..7",
          "777777777777....7..7",
          "..........77....7...",
          "..........77....7...",
          "..........77....7...",
          ".......1..77..1.7...",
          "77777777..77..777...",
          "7...............7...",
          "7...............7..7",
          "7...............7..7",
          "7...............77.7",
          "7777............7..7",
          "7..6......77....6..7",
          "7..77777777777777777",
          "7.............7....7",
          "7.............7....7",
          "7777777.......777777"},
      {"7777777.....77777777",
          "......7......7.....7",
          "......77.....7.....7",
          "......7......7.....7",
          "7777777.....77777777",
          "......7......7......",
          "......77.....7......",
          "......7......7......",
          "......7.....77......",
          "......7......7......",
          "......77.....7......",
          "......7......7...5..",
          "......76666667......",
          "......7......7......",
          "..3.7..........7....",
          "77777776666667777777",
          "......7......7.....7",
          "......7.....77.....7",
          "......7......7.....7",
          "77777777.....7777777"},
      {"7777777......7777777",
          "7777777.....77777777",
          "7777777......7777777",
          "77777777.....7777777",
          "7777777......7777777",
          "7777777.....77777777",
          "7777777......7777777",
          "77777777.....7777777",
          "7777777......7777777",
          "7777777.....77777777",
          "7777777......7777777",
          "77777777.....7777777",
          "7777777......7777777",
          "7777777.....77777777",
          "7777777......7777777",
          "77777777.....7777777",
          "7777777......7777777",
          "7777777.....77777777",
          "7777777......7777777",
          "7777777......7777777"},
      {
    	  "77777777777777777777",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "77777777777777777777",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "77777777777777777777"
    	  },
	  {
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777",
		  "7777777......7777777"
		  },
      {"77777777.....7777777",
          "7777777......7777777",
          "7777777.....77777777",
          "7777777......7777777",
          "77777777.....7777777",
          "7777777......7777777",
          "7777777.....77777777",
          "......7......7777777",
          "......77.....7777777",
          ".............7777777",
          "............77777777",
          ".............7777777",
          ".............7777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777"},
      {"77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          ".............7777777",
          ".............7777777",
          ".............7777777",
          ".............7777777",
          ".............7777777",
          ".............7777777",
          "7777777......7777777",
          "7777777.....77777777",
          "7777777......7777777",
          "77777777.....7777777",
          "7777777......7777777",
          "7777777.....77777777",
          "7777777......7777777"},
      {"77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "....................",
          "....................",
          "....................",
          "....................",
          "....................",
          "....................",
          "7777777......7777777",
          "7777777.....77777777",
          "7777777......7777777",
          "77777777.....7777777",
          "7777777......7777777",
          "7777777.....77777777",
          "7777777......7777777"},
      {"7777777......7777777",
          "7777777.....77777777",
          "7777777......7777777",
          "77777777.....7777777",
          "7777777......7777777",
          "7777777.....77777777",
          "7777777......7777777",
          "......77............",
          "....................",
          ".............7......",
          "....................",
          "......7.............",
          "....................",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777",
          "77777777777777777777"},
      {"7...................",
          "7...................",
          "77..................",
          "7...................",
          "7.....77777777777777",
          "7.....7............7",
          "77....7............7",
          "7.....7............7",
          "7....77............7",
          "7..................7",
          "7.....1............7",
          "7777777............7",
          "7..................7",
          "7..................7",
          "7..................7",
          "7..................7",
          "7..................7",
          "7..77777777777777777",
          "7..6................",
          "77777777777777777777"},
      {"...................7",
          "...........7.......7",
          ".......7...........7",
          "...................7",
          "77777777777777777777",
          "7..................7",
          "7..................7",
          "7..................7",
          "7..................7",
          "7..................7",
          "7..................7",
          "7..................7",
          "7..................7",
          "7..................7",
          "7..................7",
          "7..................7",
          "7..................7",
          "7..................7",
          "7..................7",
          "77777777777777777777"},
      {"77777777777777777777",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "77777777777777777777",
    	  "....................",
    	  "....................",
    	  "....................",
    	  "77777777777777777777"},
      {"77777777777777777777",
          "..............7.....",
          "..............7.....",
          "..............7.....",
          "77777777......7.....",
          ".......7......7.....",
          ".......7......7.....",
          ".......7......7.....",
          ".......7......7.....",
          ".......7......7.....",
          ".......7......7.....",
          ".......7......7.....",
          ".......7......7.....",
          ".......6......6.....",
          ".......6......6.....",
          "77777777777777777777",
          "7..................7",
          "7..................7",
          "7..................7",
          "77777777777777777777"},
      {
    	  "77777777777777777777",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "7..................7",
    	  "77777777777777777777"
    	  }

  };
  private String[] types = {"VARIABLE_L", "CROSSOVER_DR", "CROSSOVER_UR", "PATH_UD", "PATH_RL", "PATH_DROP", "ELBOW_UL",
      "ELBOW_DL", "ELBOW_T", "ELBOW_T_INV", "CLAUSE_A", "CLAUSE_B", "CLAUSE_C", "CLAUSE_D", "EMPTY"};

  public Gadget(String homie, boolean reflect, int rows, int cols, int r, int c) {
	this.rows = rows;
	this.cols = cols;
	this.r = r;
	this.c = c;
    for (int i = 0; i < types.length; i++) {
      if (homie.equals(types[i])) {
        read(i);
      }
    }

    if (reflect) {
      reflect();
    }
    setDudes();
  }

  public ArrayList<Sprite> getSprites() {
    return dudes;
  }

  private void setDudes() {
    for (int i = 0; i < SIDE; i++) {
      for (int j = 0; j < SIDE; j++) {
        if (grid[i][j] != -1) {
          Sprite dude = new Sprite(SIDE * PIXELS * rows, SIDE * PIXELS * cols, grid[i][j]);
          dude.setX(j * PIXELS + c * SIDE * PIXELS);
          dude.setY(i * PIXELS + r * SIDE * PIXELS);
          dudes.add(dude);
        }
      }
    }
  }

  private void read(int index) {
    for (int i = 0; i < SIDE; i++) {
      for (int j = 0; j < SIDE; j++) {
        if (maps[index][i].charAt(j) == '.') {
          grid[i][j] = -1;
        } else {
          grid[i][j] = maps[index][i].charAt(j) - '0';
        }
      }
    }
  }

  private void reflect() {
    for (int i = 0; i < SIDE; i++) {
      for (int j = 0; j < SIDE / 2; j++) {
        int temp = grid[i][j];
        grid[i][j] = grid[i][SIDE - j - 1];
        grid[i][SIDE - j - 1] = temp;
      }
    }
  }
}
