import java.io.File;

public class Incoordinate {
	
	private static File file;
	
	public Incoordinate(File f) {
		file = f;
	}

	public static String[][] createC() {
		MapRead mapread = new MapRead();
		String[] dimensions = mapread.dimensions(file);
		int x = Integer.parseInt(dimensions[0]);
		int y = Integer.parseInt(dimensions[1]);
		int rooms = Integer.parseInt(dimensions[2]);
		String[][] map = new String[x*rooms][y];
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = ".";
			}
		}
		String[][] mapC = mapread.readMap(file);
		
		for (int mc = 1; mc < mapC.length; mc++) {
			String[] symbols = mapC[mc];
			int xc = Integer.parseInt(symbols[1]);
			int yc = Integer.parseInt(symbols[2]);
			int rc = Integer.parseInt(symbols[3]);
			map[xc + x * rc][yc] = symbols[0];
		}
		
		return map;

	}

}
