import java.io.File;

public class Outcoordinate {

	private static File file = new File("mediumMap1.txt");
	
	/*public Outcoordinate(File f) {
		file = f;
	}*/

	public static void main(String[] args) {
		MapRead mapRead = new MapRead();
		String[][] map = mapRead.readMap(file);
		int count = 0;
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if (!(map[r][c].equals("."))) {
					count++;
				}
			}
		}
		
		String[] dimensions = mapRead.dimensions(file);
		int length = Integer.parseInt(dimensions[0]);
		int spot = 0;
		String[][] mapC = new String[count][4];
		for (int t = 0; t < map.length; t++) {
			for (int v = 0; v < map[t].length; v++) {
				if (!(map[t][v].equals("."))) {
					String y = ""+ v;
					String ro = ""+ (t/length);
					String x = ""+ (t - (length * (t/length)));
					String[] row = new String[4];
					row[0] = map[t][v];
					row[1] = x;
					row[2] = y;
					row[3] = ro;
					mapC[spot] = row;
					spot++;
					
				}
			}
		}
		mapRead.printMap(mapC);
		
	}

}
