import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class MapGrid {
	
	public Map<Integer, String> gadgetIdToName = new HashMap<>();
	public Map<Integer, Boolean> gadgetIdToFlip = new HashMap<>();
	
	public ArrayList<Sprite> sprites = new ArrayList<>();

	public MapGrid(ArrayList<ArrayList<Integer>> grid) {
		init();
		int rows = grid.size();
		System.out.println("ROWS");
		System.out.println(rows);
		int cols = grid.get(1).size();
		for (int r = 0; r < rows; r++) {
			ArrayList<Integer> cur = grid.get(r);
			for (int c = 0; c < cur.size(); c++) {
				int id = cur.get(c);
				if (gadgetIdToName.containsKey(id)) {
					Gadget g = new Gadget(gadgetIdToName.get(id), gadgetIdToFlip.get(id), rows, cols, r, c);
					System.out.println("POSITION R C " + r + " " + c + " OF TYPE " + gadgetIdToName.get(id) + ": HAS " + g.getSprites().size() + " SPRITES");
					for (Sprite s : g.getSprites()) {
						sprites.add(s);						
					}
				}				
			}
		}
	}
	
	public ArrayList<Sprite> allSprites() {
		return this.sprites;
	}
	
	public void init() {
		gadgetIdToName.put(21, "VARIABLE_L");
		gadgetIdToFlip.put(21,  false);
		gadgetIdToName.put(22, "VARIABLE_L");
		gadgetIdToFlip.put(22,  true);
		
//		gadgetIdToName.put(50, "PATH_UD");
//		gadgetIdToFlip.put(50,  false);
//		gadgetIdToName.put(51, "PATH_UD");
//		gadgetIdToFlip.put(51,  false);
//		gadgetIdToName.put(52, "PATH_RL");
//		gadgetIdToFlip.put(52,  false);
//		gadgetIdToName.put(53, "PATH_RL");
//		gadgetIdToFlip.put(53,  false);
		gadgetIdToName.put(54, "PATH_DROP");
		gadgetIdToFlip.put(54,  false);	
		
		gadgetIdToName.put(60, "ELBOW_UL");
		gadgetIdToFlip.put(60,  false);	
		gadgetIdToName.put(61, "ELBOW_UL");
		gadgetIdToFlip.put(61,  true);	
		gadgetIdToName.put(62, "ELBOW_DL");
		gadgetIdToFlip.put(62,  false);	
		gadgetIdToName.put(63, "ELBOW_DR");
		gadgetIdToFlip.put(63,  true);
		
		gadgetIdToName.put(64, "ELBOW_T");
		gadgetIdToFlip.put(64,  true);
		gadgetIdToName.put(65, "ELBOW_T_INV");
		gadgetIdToFlip.put(65,  true);	
		
		gadgetIdToName.put(10, "CROSSOVER_UR");
		gadgetIdToFlip.put(10, false);	
		gadgetIdToName.put(11, "CROSSOVER_UR");
		gadgetIdToFlip.put(11, true);
		gadgetIdToName.put(12, "CROSSOVER_DR");
		gadgetIdToFlip.put(12, false);		
		gadgetIdToName.put(13, "CROSSOVER_DL");
		gadgetIdToFlip.put(13, true);
		
//		gadgetIdToName.put(123, "EMPTY");
//		gadgetIdToFlip.put(123, false);
		
		gadgetIdToName.put(80, "EMPTY"); // "FINISH"
		gadgetIdToFlip.put(80, false);
	}
}
