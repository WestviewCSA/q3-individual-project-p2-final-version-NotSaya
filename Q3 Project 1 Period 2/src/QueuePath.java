import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class QueuePath {
	
	static File file = new File("easyMap1.txt");;
	
	public QueuePath() {
	}

	public static Coord Wolverine() {
		
		
		MapRead mapread = new MapRead();
		String[][] map = mapread.readMap(file);
		
		
		int[] num = new int[2];
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map.length; c++) {
				if (map[r][c].equals("W")) {
					num[0] = r;
					num[1] = c;
				}
			}
		}
		
		Coord wolverine = new Coord("W", num[0], num[1], 0);
		
		return wolverine;
		
	}
	
	public static void Queue() {
		
		System.out.println(file.getAbsolutePath());
		MapRead mapread = new MapRead();
		String[][] map = mapread.readMap(file);
		
		System.out.println("After");
		
		Coord wolverine = Wolverine();
		
		int r = wolverine.getX();
		int c = wolverine.getY();
		
		int x = 0;
		int y = 0;
		
		boolean run = true; 
		
		String symbol = "";
		
		Queue<Coord> queue = new LinkedList<>();
		
		Coord temp;
		
		
		queue.add(wolverine);
		
		while(run) {
			temp = queue.remove();
			if (map[temp.getX()][temp.getY()-1].equals(".")) {
				queue.add(new Coord(".", temp.getX(), temp.getY(), 0));
			}
			else if (map[temp.getX()][temp.getY()-1].equals("$")) {
				x = temp.getX();
				y = temp.getY();
				run = false;
			}
			if (map[temp.getX()][temp.getY()+1].equals(".")) {
				queue.add(new Coord(".", temp.getX(), temp.getY(), 0));
			}
			else if (map[temp.getX()][temp.getY()+1].equals("$"))  {
				x = temp.getX();
				y = temp.getY();
				run = false;
			}
			if (map[temp.getX()+1][temp.getY()].equals(".")) {
				queue.add(new Coord(".", temp.getX(), temp.getY(), 0));
			}
			else if (map[temp.getX()+1][temp.getY()].equals("$"))  {
				x = temp.getX();
				y = temp.getY();
				run = false;
			}
			if (map[temp.getX()-1][temp.getY()].equals(".")) {
				queue.add(new Coord(".", temp.getX(), temp.getY(), 0));
			}
			else if (map[temp.getX()-1][temp.getY()].equals("$"))  {
				x = temp.getX();
				y = temp.getY();
				run = false;
			}
		}
		
		System.out.println(x);
	}
	
	public static void main(String[] args) {
		Queue();
	}

}
