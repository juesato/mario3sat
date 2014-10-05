import java.util.ArrayList;

public class Gadget {
  private static final int SIDE = 20;
  private static final int PIXELS = 32;
  private static final int DUDES = 5;

  private int[][] grid = new int[SIDE][SIDE];
  private ArrayList<Sprite> dudes;

  private String[][] maps = { {}, {},
      {
          "7777777.....77777777",
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
          "77777777.....7777777"
  }
  };
  private String[] types = {"VARIABLE", "CLAUSE", "CROSSOVER"};

  public Gadget(String homie, boolean reflect) {
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
          Sprite dude = new Sprite(SIDE * PIXELS * DUDES, SIDE * PIXELS * DUDES, grid[i][j]);
          dude.setGridX(i);
          dude.setGridY(j);
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