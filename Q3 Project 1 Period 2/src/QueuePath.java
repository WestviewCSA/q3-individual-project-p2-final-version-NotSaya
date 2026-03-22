import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueuePath {
	
	static File file = new File("mediumMap1.txt");;
	
	public QueuePath() {
	}

	public static Coord Wolverine(int xBound, int yBound, int room) {
		
		
		MapRead mapread = new MapRead();
		String[][] map = mapread.readMap(file);
		
		
		int[] num = new int[2];
		
		for (int r = xBound; r <= yBound; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if (map[r][c].equals("W")) {
					num[0] = r;
					num[1] = c;
				}
			}
		}
		
		Coord wolverine = new Coord("W", num[0], num[1], room);
		
		return wolverine;
		
	}
	
	public static void Queue() {
		 
		MapRead mapread = new MapRead();
		String[][] map = mapread.readMap(file);
		String[] dimensions = mapread.dimensions(file);
		
		int xBound = Integer.parseInt(dimensions[0]);
		int yBound = Integer.parseInt(dimensions[1]);
		
		Coord wolverine = Wolverine(0, xBound-1, 0);
		
		int r = wolverine.getX();
		int c = wolverine.getY();
		
		
		int room = 0;
		
		
		int x = 0;
		int y = 0;
		
		boolean run = true; 
		
		String symbol = "";
		
		Queue<Coord> queue = new LinkedList<>();
		
		HashMap<String, String> store = new HashMap<String, String>();
		
		
		HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
		
		Coord temp;
		
		Coord dollar = new Coord("$", 0, 0, 0);
		
		
		queue.add(wolverine);
		
		while(run && !queue.isEmpty()) {
			temp = queue.remove();
			
			int roomIndex = temp.getX() / xBound;
			int rowS = roomIndex * xBound;
			int rowE = rowS + xBound - 1;
			
			
			if (temp.getX() != rowS) {
				
				
				if (map[temp.getX()][temp.getY()].equals("$")) {
					x = temp.getX()-1;
					y = temp.getY();
					dollar = new Coord("$", x, y, room);
					store.put((dollar.getX()) + ", " + (dollar.getY()), (temp.getX()) + ", " + (temp.getY()));
					System.out.println("key: " + (dollar.getX()) + ", " + (dollar.getY()) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					run = false;
				}
				
				else if (map[temp.getX()-1][temp.getY()].equals("|") && !visited.containsKey((temp.getX()-1) + ", " + temp.getY())) {
					int newRStart = (roomIndex+1) * xBound;
					int newREnd = newRStart + xBound - 1;
					Coord newW = Wolverine(newRStart, newREnd, room+1);
					queue.add(newW);
					visited.put((newW.getX()) + ", " + (newW.getY()), true);

					store.put((newW.getX()) + ", " + newW.getY(), (temp.getX()) + ", " + temp.getY());
					System.out.println("key: " + (newW.getX()) + ", " + newW.getY() + "; map: " + (temp.getX()) + ", " + temp.getY());
					
					
				}
				
				
				else if (map[temp.getX()-1][temp.getY()].equals(".") && !visited.containsKey((temp.getX()-1) + ", " + temp.getY())) {
					queue.add(new Coord(".", temp.getX()-1, temp.getY(), room));
					visited.put((temp.getX()-1) + ", " + (temp.getY()), true);
					
					
					store.put((temp.getX()-1) + ", " + temp.getY(), (temp.getX()) + ", " + temp.getY());
					System.out.println("key: " + (temp.getX()-1) + ", " + temp.getY() + "; map: " + (temp.getX()) + ", " + temp.getY());
					
					
				}
				
			}
			
			if (!(temp.getX() >= rowE)) {
				
				if (map[temp.getX()+1][temp.getY()].equals("$"))  {
					x = temp.getX()+1;
					y = temp.getY();
					dollar = new Coord("$", x, y, room);
					store.put((dollar.getX()) + ", " + (dollar.getY()), (temp.getX()) + ", " + (temp.getY()));
					System.out.println("key: " + (dollar.getX()) + ", " + (dollar.getY()) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					run = false;
				}
				
				else if (map[temp.getX()+1][temp.getY()].equals("|") && !visited.containsKey((temp.getX()+1) + ", " + temp.getY())) {
					int newRStart = (roomIndex+1) * xBound;
					int newREnd = newRStart + xBound - 1;
					Coord newW = Wolverine(newRStart, newREnd, room+1);
					queue.add(newW);
					visited.put((newW.getX()) + ", " + (newW.getY()), true);

					store.put((newW.getX()) + ", " + newW.getY(), (temp.getX()) + ", " + temp.getY());
					System.out.println("key: " + (newW.getX()) + ", " + newW.getY() + "; map: " + (temp.getX()) + ", " + temp.getY());
					
					
				}
				
				else if (!map[temp.getX()+1][temp.getY()].equals("@") && !visited.containsKey(temp.getX()+1 + ", " + (temp.getY()))) {
					queue.add(new Coord(".", temp.getX()+1, temp.getY(), room));
					visited.put((temp.getX()+1) + ", " + (temp.getY()), true);
					
					
					store.put((temp.getX()+1) + ", " + temp.getY(), (temp.getX()) + ", " + temp.getY());
					System.out.println("key: " + (temp.getX()+1) + ", " + temp.getY() + "; map: " + (temp.getX()) + ", " + temp.getY());
					
					
				}
				
			}
			
			if (temp.getY() != map[0].length-1) {
				
				if (map[temp.getX()][temp.getY()+1].equals("$"))  {
					x = temp.getX();
					y = temp.getY()+1;
					dollar = new Coord("$", x, y, room);
					store.put((dollar.getX()) + ", " + (dollar.getY()), (temp.getX()) + ", " + (temp.getY()));
					System.out.println("key: " + (dollar.getX()) + ", " + (dollar.getY()) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					run = false;
				}
				
				else if (map[temp.getX()][temp.getY()+1].equals("|") && !visited.containsKey((temp.getX()) + ", " + (temp.getY()+1))) {
					int newRStart = (roomIndex+1) * xBound;
					int newREnd = newRStart + xBound - 1;
					Coord newW = Wolverine(newRStart, newREnd, room+1);
					queue.add(newW);
					visited.put((newW.getX()) + ", " + (newW.getY()), true);

					store.put((newW.getX()) + ", " + newW.getY(), (temp.getX()) + ", " + temp.getY());
					System.out.println("key: " + (newW.getX()) + ", " + newW.getY() + "; map: " + (temp.getX()) + ", " + temp.getY());
					
					
				}
				
				else if (!map[temp.getX()][temp.getY()+1].equals("@")&& !visited.containsKey((temp.getX()) + ", " + (temp.getY()+1))) {
					queue.add(new Coord(".", temp.getX(), temp.getY()+1, room));
					visited.put((temp.getX()) + ", " + (temp.getY()+1), true);
					
					store.put((temp.getX()) + ", " + (temp.getY()+1),(temp.getX()) + ", " + (temp.getY()));
					System.out.println("key: " + (temp.getX()) + ", " + (temp.getY()+1) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					
					
				}
				
			}
			
			if (temp.getY() != 0) {
				
				if (map[temp.getX()][temp.getY()-1].equals("$"))  {
					x = temp.getX();
					y = temp.getY()-1;
					dollar = new Coord("$", x, y, room);
					store.put((dollar.getX()) + ", " + (dollar.getY()), (temp.getX()) + ", " + (temp.getY()));
					System.out.println("key: " + (dollar.getX()) + ", " + (dollar.getY()) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					run = false;
				}
				
				else if (map[temp.getX()][temp.getY()-1].equals("|") && !visited.containsKey((temp.getX()) + ", " + (temp.getY()-1))) {
					int newRStart = (roomIndex+1) * xBound;
					int newREnd = newRStart + xBound - 1;
					Coord newW = Wolverine(newRStart, newREnd, room+1);
					queue.add(newW);
					visited.put((newW.getX()) + ", " + (newW.getY()), true);

					store.put((newW.getX()) + ", " + newW.getY(), (temp.getX()) + ", " + temp.getY());
					System.out.println("key: " + (newW.getX()) + ", " + newW.getY() + "; map: " + (temp.getX()) + ", " + temp.getY());
					
					
				}
				
				else if (!map[temp.getX()][temp.getY()-1].equals("@")&& !visited.containsKey((temp.getX()) + ", " + (temp.getY()-1))) {
					queue.add(new Coord(".", temp.getX(), temp.getY()-1, room));
					visited.put((temp.getX()) + ", " + (temp.getY()-1), true);
					
				
					store.put((temp.getX()) + ", " + (temp.getY()-1), (temp.getX()) + ", " + (temp.getY()));
					System.out.println("key: " + (temp.getX()) + ", " + (temp.getY()-1) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					
					
				}
				
				
			}
			
			
		}
		
		
		
		String temp1 = store.get((dollar.getX() + ", " + dollar.getY()));
		
		String startW = (wolverine.getX() + ", " + wolverine.getY());
		
		
		
		while(!temp1.equals(startW)) {

			if (map[Integer.parseInt(temp1.substring(0, temp1.indexOf(",")))][Integer.parseInt(temp1.substring(temp1.indexOf(" ") + 1))].equals("W")) {
				
			}
			else {
				map[Integer.parseInt(temp1.substring(0, temp1.indexOf(",")))][Integer.parseInt(temp1.substring(temp1.indexOf(" ") + 1))] = "+";
			}
			temp1 = store.get(temp1);
		}
		
		MapRead.printMap(map);
		
		
		

	}
	
	public static void main(String[] args) {
		Queue();
	}

}