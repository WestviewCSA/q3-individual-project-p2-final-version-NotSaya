import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueuePath {
	
	static File file = new File("easyMap2.txt");;
	
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
		 
		MapRead mapread = new MapRead();
		String[][] map = mapread.readMap(file);
		
		Coord wolverine = Wolverine();
		
		int r = wolverine.getX();
		int c = wolverine.getY();
		
		int x = 0;
		int y = 0;
		
		boolean run = true; 
		
		String symbol = "";
		
		Queue<Coord> queue = new LinkedList<>();
		
		Stack<Coord> stack = new Stack<Coord>();
		
		HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
		
		Coord temp;
		
		
		queue.add(wolverine);
		
		while(run && !queue.isEmpty()) {
			temp = queue.remove();
			
			if (temp.getX() != 0) {
				
				
				if (map[temp.getX()-1][temp.getY()].equals(".") && !visited.containsKey((temp.getX()-1) + ", " + temp.getY())) {
					System.out.println("up");
					queue.add(new Coord(".", temp.getX()-1, temp.getY(), 0));
					visited.put((temp.getX()-1) + ", " + (temp.getY()), true);
					stack.push(temp);
				}
				else if (map[temp.getX()-1][temp.getY()].equals("$")) {
					x = temp.getX()-1;
					y = temp.getY();
					run = false;
				}
				
			}
			
			if (temp.getX() != map.length-1) {
				
				if (map[temp.getX()+1][temp.getY()].equals(".") && !visited.containsKey(temp.getX()+1 + ", " + (temp.getY()))) {
					System.out.println("down");
					queue.add(new Coord(".", temp.getX()+1, temp.getY(), 0));
					visited.put(temp.getX() + ", " + (temp.getY()+1), true);
					stack.push(temp);
				}
				else if (map[temp.getX()+1][temp.getY()].equals("$"))  {
					x = temp.getX()+1;
					y = temp.getY();
					run = false;
				}
				
			}
			
			if (temp.getY() != 0) {
				
				if (map[temp.getX()][temp.getY()+1].equals(".")&& !visited.containsKey((temp.getX()) + ", " + (temp.getY()+1))) {
					System.out.println("right");
					queue.add(new Coord(".", temp.getX(), temp.getY()+1, 0));
					visited.put((temp.getX()+1) + ", " + temp.getY(), true);
					stack.push(temp);
				}
				else if (map[temp.getX()][temp.getY()+1].equals("$"))  {
					x = temp.getX();
					y = temp.getY()+1;
					run = false;
				}
				
			}
			
			if (temp.getY() != map[0].length-1) {
				
				if (map[temp.getX()][temp.getX()-1].equals(".")&& !visited.containsKey((temp.getX()) + ", " + (temp.getY()-1))) {
					System.out.println("left");
					queue.add(new Coord(".", temp.getX()-1, temp.getY(), 0));
					visited.put((temp.getX()) + ", " + (temp.getY()-1), true);
					stack.push(temp);
				}
				else if (map[temp.getX()][temp.getY()-1].equals("$"))  {
					x = temp.getX();
					y = temp.getY()-1;
					run = false;
				}
				
			}
			
		}
		
		System.out.println(x);
		
		Coord temp1;
		
		while (!stack.isEmpty()) {
			temp1 = stack.pop();
			if (!temp1.getSymbol().equals("W"))
			map[temp1.getX()][temp1.getY()] = "+";
		}
		
		MapRead.printMap(map);
	}
	
	public static void main(String[] args) {
		Queue();
	}

}
