import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class QueueCmd {
	
	private static File file;
	
	public QueueCmd(File input) {
		file = input;
	}

	public static void main(String[][] args) {
		Queue<Coord> queue = new LinkedList();
		
		MapRead mapread = new MapRead();
		String[][] map = mapread.readMap(file);
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map.length; c++) {
				if (map[r][c].equals("W")) {
					Coord wolverine = new Coord("W", )
				}
			}
		}

	}

}
