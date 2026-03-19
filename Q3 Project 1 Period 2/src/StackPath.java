import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackPath {

static File file = new File("easyMap1.txt");;

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
	
	public static void Stack() {
		 
		MapRead mapread = new MapRead();
		String[][] map = mapread.readMap(file);
		
		Coord wolverine = Wolverine();
		
		int r = wolverine.getX();
		int c = wolverine.getY();
		
		int x = 0;
		int y = 0;
		
		boolean run = true; 
		
		String symbol = "";
		
		Stack<Coord> stack = new Stack<>();
		
		HashMap<String, String> store = new HashMap<String, String>();
		
		
		HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
		
		Coord temp;
		
		Coord dollar = new Coord("$", 0, 0, 0);
		
		
		stack.push(wolverine);
		
		while(run && !stack.isEmpty()) {
			temp = stack.pop();
			
			if (temp.getX() != 0) {
				
				if (map[temp.getX()][temp.getY()].equals("$")) {
					x = temp.getX()-1;
					y = temp.getY();
					dollar = new Coord("$", x, y, 0);
					store.put((dollar.getX()) + ", " + (dollar.getY()), (temp.getX()) + ", " + (temp.getY()));
					System.out.println("key: " + (dollar.getX()) + ", " + (dollar.getY()) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					run = false;
				}
				
				
				else if (map[temp.getX()-1][temp.getY()].equals(".") && !visited.containsKey((temp.getX()-1) + ", " + temp.getY())) {
					stack.push(new Coord(".", temp.getX()-1, temp.getY(), 0));
					visited.put((temp.getX()-1) + ", " + (temp.getY()), true);
					
					if (!store.containsValue(temp)) {
						store.put((temp.getX()-1) + ", " + temp.getY(), (temp.getX()) + ", " + temp.getY());
						System.out.println("key: " + (temp.getX()-1) + ", " + temp.getY() + "; map: " + (temp.getX()) + ", " + temp.getY());
					}
					
				}
				
			}
			
			if (temp.getX() != map.length-1) {
				
				if (map[temp.getX()+1][temp.getY()].equals("$"))  {
					x = temp.getX()+1;
					y = temp.getY();
					dollar = new Coord("$", x, y, 0);
					store.put((dollar.getX()) + ", " + (dollar.getY()), (temp.getX()) + ", " + (temp.getY()));
					System.out.println("key: " + (dollar.getX()) + ", " + (dollar.getY()) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					run = false;
				}
				
				else if (!map[temp.getX()+1][temp.getY()].equals("@") && !visited.containsKey(temp.getX()+1 + ", " + (temp.getY()))) {
					stack.push(new Coord(".", temp.getX()+1, temp.getY(), 0));
					visited.put((temp.getX()+1) + ", " + (temp.getY()), true);
					
					if (!store.containsValue(temp)) {
						store.put((temp.getX()+1) + ", " + temp.getY(), (temp.getX()) + ", " + temp.getY());
						System.out.println("key: " + (temp.getX()+1) + ", " + temp.getY() + "; map: " + (temp.getX()) + ", " + temp.getY());
					}
					
				}
				
			}
			
			if (temp.getY() != map[0].length-1) {
				
				if (map[temp.getX()][temp.getY()+1].equals("$"))  {
					x = temp.getX();
					y = temp.getY()+1;
					dollar = new Coord("$", x, y, 0);
					store.put((dollar.getX()) + ", " + (dollar.getY()), (temp.getX()) + ", " + (temp.getY()));
					System.out.println("key: " + (dollar.getX()) + ", " + (dollar.getY()) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					run = false;
				}
				
				else if (!map[temp.getX()][temp.getY()+1].equals("@")&& !visited.containsKey((temp.getX()) + ", " + (temp.getY()+1))) {
					stack.push(new Coord(".", temp.getX(), temp.getY()+1, 0));
					visited.put((temp.getX()) + ", " + (temp.getY()+1), true);
					
					if (!store.containsValue(temp)) {
						store.put((temp.getX()) + ", " + (temp.getY()+1),(temp.getX()) + ", " + (temp.getY()) );
						System.out.println("key: " + (temp.getX()) + ", " + (temp.getY()+1) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					}
					
				}
				
			}
			
			if (temp.getY() != 0) {
				
				if (map[temp.getX()][temp.getY()-1].equals("$"))  {
					x = temp.getX();
					y = temp.getY()-1;
					dollar = new Coord("$", x, y, 0);
					store.put((dollar.getX()) + ", " + (dollar.getY()), (temp.getX()) + ", " + (temp.getY()));
					System.out.println("key: " + (dollar.getX()) + ", " + (dollar.getY()) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					run = false;
				}
				
				else if (!map[temp.getX()][temp.getY()-1].equals("@")&& !visited.containsKey((temp.getX()) + ", " + (temp.getY()-1))) {
					stack.push(new Coord(".", temp.getX(), temp.getY()-1, 0));
					visited.put((temp.getX()) + ", " + (temp.getY()-1), true);
					
					if (!store.containsValue(temp)) {
						store.put((temp.getX()) + ", " + (temp.getY()-1), (temp.getX()) + ", " + (temp.getY()));
						System.out.println("key: " + (temp.getX()) + ", " + (temp.getY()-1) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					}
					
				}
				
				
			}
			
			
		}
		
		
		
		String temp1 = store.get((dollar.getX() + ", " + dollar.getY()));
		
		
		
		String temp2 = store.get(temp1);
		
		
		
		while(!map[Integer.parseInt(temp1.substring(0, temp1.indexOf(",")))][Integer.parseInt(temp1.substring(temp1.indexOf(" ") + 1))].equals("W")) {

			
			map[Integer.parseInt(temp1.substring(0, temp1.indexOf(",")))][Integer.parseInt(temp1.substring(temp1.indexOf(" ") + 1))] = "+";
			temp1 = store.get(temp1);
		}
		
		MapRead.printMap(map);
		
	}
		
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Stack();

	}

}
