import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Opt {
	
	
static File file = new File("hardMap1.txt");;
	
	public Opt() {
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
	
public static Coord Door(int xBound, int yBound, int room) {
		
		
		MapRead mapread = new MapRead();
		String[][] map = mapread.readMap(file);
		
		
		int[] num = new int[2];
		
		for (int r = xBound; r <= yBound; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if (map[r][c].equals("|")) {
					num[0] = r;
					num[1] = c;
				}
			}
		}
		
		Coord Door = new Coord("|", num[0], num[1], room);
		
		return Door;
		
	}
	
	public static Coord findDollar(File file) {
		MapRead mapread = new MapRead();
		String[][] map = mapread.readMap(file);
		
		int[] num = new int[2];
		int room = 0;
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				
				if (map[r][c].equals("|")) {
					room++;
				}
				if (map[r][c].equals("$")) {
					num[0] = r;
					num[1] = c;
				}
			}
		}
		
		
		Coord dollar = new Coord("$", num[0], num[1], room);
		
		return dollar;
	}
	
	public static void OptPath() {
		 
		MapRead mapread = new MapRead();
		String[][] map = mapread.readMap(file);
		String[] dimensions = mapread.dimensions(file);
		
		int xBound = Integer.parseInt(dimensions[0]);
		int yBound = Integer.parseInt(dimensions[1]);
		
		Coord wolverine = Wolverine(0, xBound-1, 0);
		System.out.println(wolverine.getX() + ", " + wolverine.getY() + ", " + wolverine.getRoom());
		
		Coord dollar = findDollar(file);
		System.out.println(dollar.getX() + ", " + dollar.getY() + ", " + dollar.getRoom());
		
		int r = wolverine.getX();
		int c = wolverine.getY();
		
		
		int room = 0;
		int roomBack = dollar.getRoom();
		
		
		int x = 0;
		int y = 0;
		
		boolean run = true; 
		
		String symbol = "";
		
		Queue<Coord> queue = new LinkedList<>();
		
		Queue<Coord> queueBack = new LinkedList<>();
		
		HashMap<String, String> store = new HashMap<String, String>();
		
		HashMap<String, String> storeBack = new HashMap<String, String>();
		
		HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
		
		HashMap<String, Boolean> visitedBack = new HashMap<String, Boolean>();
		
		Coord temp;
		
		Coord tempBack;
		
		String intersection = "";
		
		
		queue.add(wolverine);
		
		queueBack.add(dollar);
		
		visited.put((wolverine.getX()) + ", " + (wolverine.getY()), true);
		
		visitedBack.put((dollar.getX()) + ", " + (dollar.getY()), true);
		
		while(run && !queue.isEmpty() && !queueBack.isEmpty()) {
			temp = queue.remove();
			tempBack = queueBack.remove();
			
			int roomIndex = temp.getX() / xBound;
			int rowS = roomIndex * xBound;
			int rowE = rowS + xBound - 1;
			
			int roomIndexBack = tempBack.getX() / xBound;
			int rowSBack = roomIndexBack * xBound;
			int rowEBack = rowSBack + xBound - 1;
			
			//up
			if (temp.getX() != rowS) {
				
				
				/*if (map[temp.getX()][temp.getY()].equals("$")) {
					x = temp.getX()-1;
					y = temp.getY();
					dollar = new Coord("$", x, y, room);
					store.put((dollar.getX()) + ", " + (dollar.getY()), (temp.getX()) + ", " + (temp.getY()));
					System.out.println("key: " + (dollar.getX()) + ", " + (dollar.getY()) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					run = false;
				}*/
				
				if (map[temp.getX()-1][temp.getY()].equals("|") && !visited.containsKey((temp.getX()-1) + ", " + temp.getY())) {
					int newRStart = (roomIndex+1) * xBound;
					int newREnd = newRStart + xBound - 1;
					Coord newW = Wolverine(newRStart, newREnd, room+1);
					queue.add(newW);
					visited.put((newW.getX()) + ", " + (newW.getY()), true);
					if (visitedBack.containsKey((newW.getX()) + ", " + (newW.getY()))) {
						intersection = newW.getX() + ", " + newW.getY();
						run = false;
					}

					store.put((newW.getX()) + ", " + newW.getY(), (temp.getX()) + ", " + temp.getY());
					//System.out.println("key: " + (newW.getX()) + ", " + newW.getY() + "; map: " + (temp.getX()) + ", " + temp.getY());
					
					
				}
				
				
				else if (map[temp.getX()-1][temp.getY()].equals(".") && !visited.containsKey((temp.getX()-1) + ", " + temp.getY())) {
					queue.add(new Coord(".", temp.getX()-1, temp.getY(), room));
					visited.put((temp.getX()-1) + ", " + (temp.getY()), true);
					
					if (visitedBack.containsKey((temp.getX()-1) + ", " + (temp.getY()))) {
						intersection = (temp.getX()-1) + ", " + (temp.getY());
						run = false;
					}
					
					
					store.put((temp.getX()-1) + ", " + temp.getY(), (temp.getX()) + ", " + temp.getY());
					//System.out.println("key: " + (temp.getX()-1) + ", " + temp.getY() + "; map: " + (temp.getX()) + ", " + temp.getY());
			
				}
				
				
			}
			//up backwards
			if (tempBack.getX() != rowSBack) {
				System.out.println("up");
				
				/*if (map[tempBack.getX()][tempBack.getY()].equals("$")) {
					x = tempBack.getX()-1;
					y = tempBack.getY();
					dollar = new Coord("$", x, y, roomBack);
					storeBack.put((dollar.getX()) + ", " + (dollar.getY()), (tempBack.getX()) + ", " + (tempBack.getY()));
					System.out.println("key: " + (dollar.getX()) + ", " + (dollar.getY()) + "; map: " + (tempBack.getX()) + ", " + (tempBack.getY()));
					run = false;
				}*/
				
				if (map[tempBack.getX()-1][tempBack.getY()].equals("W") && !visitedBack.containsKey((tempBack.getX()-1) + ", " + tempBack.getY())) {
					int newRStartBack = (roomIndexBack-1) * xBound;
					int newREndBack = newRStartBack + xBound - 1;
					System.out.println((newRStartBack) + ", " + (newREndBack) + ", " + (roomBack-1));
					Coord newDoor = Door(newRStartBack, newREndBack, roomBack-1);
					queueBack.add(newDoor);
					visitedBack.put((newDoor.getX()) + ", " + (newDoor.getY()), true);
					System.out.println((newDoor.getX()) + ", " + (newDoor.getY()));
					if (visited.containsKey((newDoor.getX()) + ", " + (newDoor.getY()))) {
						intersection = (newDoor.getX()) + ", " + (newDoor.getY());
						run = false;
					}

					storeBack.put((newDoor.getX()) + ", " + newDoor.getY(), (tempBack.getX()) + ", " + tempBack.getY());
					System.out.println(storeBack.get((newDoor.getX()) + ", " + newDoor.getY()));
					System.out.println("BACKWARDS key: " + (newDoor.getX()) + ", " + newDoor.getY() + "; map: " + (tempBack.getX()) + ", " + tempBack.getY());
					
					
				}
				
				
				else if (map[tempBack.getX()-1][tempBack.getY()].equals(".") && !visitedBack.containsKey((tempBack.getX()-1) + ", " + tempBack.getY())) {
					queueBack.add(new Coord(".", tempBack.getX()-1, tempBack.getY(), roomBack));
					visitedBack.put((tempBack.getX()-1) + ", " + (tempBack.getY()), true);
					System.out.println((tempBack.getX()-1) + ", " + (tempBack.getY()-1));
					if (visited.containsKey((tempBack.getX()+1) + ", " + (tempBack.getY()))) {
						intersection = (tempBack.getX()-1) + ", " + (tempBack.getY());
						run = false;
					}
					
					
					storeBack.put((tempBack.getX()-1) + ", " + tempBack.getY(), (tempBack.getX()) + ", " + tempBack.getY());
					System.out.println("BACKWARDS key: " + (tempBack.getX()-1) + ", " + tempBack.getY() + "; map: " + (tempBack.getX()) + ", " + tempBack.getY());
					
					
				}
				else if (map[temp.getX()][temp.getY()].equals("$")){
					
				}
				
			}
			
			
			//down
			if (!(temp.getX() >= rowE)) {
				
				/*if (map[temp.getX()+1][temp.getY()].equals("$"))  {
					x = temp.getX()+1;
					y = temp.getY();
					dollar = new Coord("$", x, y, room);
					store.put((dollar.getX()) + ", " + (dollar.getY()), (temp.getX()) + ", " + (temp.getY()));
					System.out.println("key: " + (dollar.getX()) + ", " + (dollar.getY()) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					run = false;
				}*/
				
				if (map[temp.getX()+1][temp.getY()].equals("|") && !visited.containsKey((temp.getX()+1) + ", " + temp.getY())) {
					int newRStart = (roomIndex+1) * xBound;
					int newREnd = newRStart + xBound - 1;
					Coord newW = Wolverine(newRStart, newREnd, room+1);
					queue.add(newW);
					visited.put((newW.getX()) + ", " + (newW.getY()), true);
					if (visitedBack.containsKey((newW.getX()) + ", " + (newW.getY()))) {
						intersection = newW.getX() + ", " + newW.getY();
						run = false;
					}

					store.put((newW.getX()) + ", " + newW.getY(), (temp.getX()) + ", " + temp.getY());
					//System.out.println("key: " + (newW.getX()) + ", " + newW.getY() + "; map: " + (temp.getX()) + ", " + temp.getY());
					
					
				}
				
				else if (!map[temp.getX()+1][temp.getY()].equals("@") && !visited.containsKey(temp.getX()+1 + ", " + (temp.getY()))) {
					queue.add(new Coord(".", temp.getX()+1, temp.getY(), room));
					visited.put((temp.getX()+1) + ", " + (temp.getY()), true);
					if (visitedBack.containsKey((temp.getX()+1) + ", " + (temp.getY()))) {
						intersection = (temp.getX()+1) + ", " + (temp.getY());
						run = false;
					}
					
					
					store.put((temp.getX()+1) + ", " + temp.getY(), (temp.getX()) + ", " + temp.getY());
					//System.out.println("key: " + (temp.getX()+1) + ", " + temp.getY() + "; map: " + (temp.getX()) + ", " + temp.getY());
					
					
				}
				
			}
			
			//down backwards
			if (!(tempBack.getX() >= rowEBack)) {
				System.out.println("down");
				/*if (map[tempBack.getX()+1][tempBack.getY()].equals("$"))  {
					x = tempBack.getX()+1;
					y = tempBack.getY();
					dollar = new Coord("$", x, y, roomBack);
					storeBack.put((dollar.getX()) + ", " + (dollar.getY()), (tempBack.getX()) + ", " + (tempBack.getY()));
					System.out.println("key: " + (dollar.getX()) + ", " + (dollar.getY()) + "; map: " + (tempBack.getX()) + ", " + (tempBack.getY()));
					run = false;
				}*/
				
				if (map[tempBack.getX()+1][tempBack.getY()].equals("W") && !visitedBack.containsKey((tempBack.getX()+1) + ", " + tempBack.getY())) {
					int newRStartBack = (roomIndexBack-1) * xBound;
					int newREndBack = newRStartBack + xBound - 1;
					Coord newDoor = Door(newRStartBack, newREndBack, roomBack+1);
					queueBack.add(newDoor);
					visitedBack.put((newDoor.getX()) + ", " + (newDoor.getY()), true);
					System.out.println((newDoor.getX()) + ", " + (newDoor.getY()));
					if (visited.containsKey((newDoor.getX()) + ", " + (newDoor.getY()))) {
						intersection = (newDoor.getX()) + ", " + (newDoor.getY());
						run = false;
					}

					storeBack.put((newDoor.getX()) + ", " + newDoor.getY(), (temp.getX()) + ", " + tempBack.getY());
					System.out.println(storeBack.get((newDoor.getX()) + ", " + newDoor.getY()));
					System.out.println("BACKWARDS key: " + (newDoor.getX()) + ", " + newDoor.getY() + "; map: " + (tempBack.getX()) + ", " + tempBack.getY());
					
					
				}
				
				else if (!map[tempBack.getX()+1][tempBack.getY()].equals("@") && !visitedBack.containsKey(tempBack.getX()+1 + ", " + (tempBack.getY()))) {
					queueBack.add(new Coord(".", tempBack.getX()+1, tempBack.getY(), roomBack));
					visitedBack.put((tempBack.getX()+1) + ", " + (tempBack.getY()), true);
					System.out.println((tempBack.getX()+1) + ", " + (tempBack.getY()));
					if (visited.containsKey((tempBack.getX()+1) + ", " + (tempBack.getY()))) {
						intersection = (tempBack.getX()+1) + ", " + (tempBack.getY());
						run = false;
					}
					
					
					storeBack.put((tempBack.getX()+1) + ", " + tempBack.getY(), (tempBack.getX()) + ", " + tempBack.getY());
					System.out.println("BACKWARDS key: " + (tempBack.getX()+1) + ", " + tempBack.getY() + "; map: " + (tempBack.getX()) + ", " + tempBack.getY());
					
					
				}
				else if (map[temp.getX()][temp.getY()].equals("$")){
					
				}
				
			}
			
			//right
			
			if (temp.getY() != map[0].length-1) {
				
				/*if (map[temp.getX()][temp.getY()+1].equals("$"))  {
					x = temp.getX();
					y = temp.getY()+1;
					dollar = new Coord("$", x, y, 0);
					store.put((dollar.getX()) + ", " + (dollar.getY()), (temp.getX()) + ", " + (temp.getY()));
					System.out.println("key: " + (dollar.getX()) + ", " + (dollar.getY()) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					run = false;
				}*/
				
				if (map[temp.getX()][temp.getY()+1].equals("|") && !visited.containsKey((temp.getX()) + ", " + (temp.getY()+1))) {
					int newRStart = (roomIndex+1) * xBound;
					int newREnd = newRStart + xBound - 1;
					Coord newW = Wolverine(newRStart, newREnd, room+1);
					queue.add(newW);
					visited.put((newW.getX()) + ", " + (newW.getY()), true);
					if (visitedBack.containsKey((newW.getX()) + ", " + (newW.getY()))) {
						intersection = newW.getX() + ", " + newW.getY();
						run = false;
					}

					store.put((newW.getX()) + ", " + newW.getY(), (temp.getX()) + ", " + temp.getY());
					//System.out.println("key: " + (newW.getX()) + ", " + newW.getY() + "; map: " + (temp.getX()) + ", " + temp.getY());
					
					
				}
				
				else if (!map[temp.getX()][temp.getY()+1].equals("@")&& !visited.containsKey((temp.getX()) + ", " + (temp.getY()+1))) {
					queue.add(new Coord(".", temp.getX(), temp.getY()+1, room));
					visited.put((temp.getX()) + ", " + (temp.getY()+1), true);
					if (visitedBack.containsKey((temp.getX()) + ", " + (temp.getY()+1))) {
						intersection = (temp.getX()) + ", " + (temp.getY()+1);
						run = false;
					}
					
					store.put((temp.getX()) + ", " + (temp.getY()+1),(temp.getX()) + ", " + (temp.getY()));
					//System.out.println("key: " + (temp.getX()) + ", " + (temp.getY()+1) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					
					
				}
				
			}
			
			//right backwards
			
			if (tempBack.getY() != map[0].length-1) {
				System.out.println("right");
				/*if (map[tempBack.getX()][tempBack.getY()+1].equals("$"))  {
					x = tempBack.getX();
					y = tempBack.getY()+1;
					dollar = new Coord("$", x, y, roomBack);
					storeBack.put((dollar.getX()) + ", " + (dollar.getY()), (tempBack.getX()) + ", " + (tempBack.getY()));
					System.out.println("key: " + (dollar.getX()) + ", " + (dollar.getY()) + "; map: " + (tempBack.getX()) + ", " + (tempBack.getY()));
					run = false;
				}*/
				
				if (map[tempBack.getX()][tempBack.getY()+1].equals("W") && !visitedBack.containsKey((tempBack.getX()) + ", " + (tempBack.getY()+1))) {
					int newRStartBack = (roomIndexBack-1) * xBound;
					int newREndBack = newRStartBack + xBound - 1;
					Coord newDoor = Door(newRStartBack, newREndBack, roomBack+1);
					queueBack.add(newDoor);
					visitedBack.put((newDoor.getX()) + ", " + (newDoor.getY()), true);
					System.out.println((newDoor.getX()) + ", " + (newDoor.getY()));
					if (visited.containsKey((newDoor.getX()) + ", " + (newDoor.getY()))) {
						intersection = (newDoor.getX()) + ", " + (newDoor.getY());
						run = false;
					}

					storeBack.put((newDoor.getX()) + ", " + newDoor.getY(), (temp.getX()) + ", " + tempBack.getY());
					System.out.println(storeBack.get((newDoor.getX()) + ", " + newDoor.getY()));
					System.out.println("BACKWARDS key: " + (newDoor.getX()) + ", " + newDoor.getY() + "; map: " + (tempBack.getX()) + ", " + tempBack.getY());
					
					
				}
				
				else if (!map[tempBack.getX()][tempBack.getY()+1].equals("@")&& !visitedBack.containsKey((tempBack.getX()) + ", " + (tempBack.getY()+1))) {
					queueBack.add(new Coord(".", tempBack.getX(), tempBack.getY()+1, roomBack));
					visitedBack.put((tempBack.getX()) + ", " + (tempBack.getY()+1), true);
					System.out.println((tempBack.getX()) + ", " + (tempBack.getY()+1));
					if (visited.containsKey((tempBack.getX()) + ", " + (tempBack.getY()+1))) {
						intersection = (tempBack.getX()) + ", " + (tempBack.getY()+1);
						run = false;
					}
					
					storeBack.put((tempBack.getX()) + ", " + (tempBack.getY()+1),(tempBack.getX()) + ", " + (tempBack.getY()));
					
					System.out.println("BACKWARDS key: " + (tempBack.getX()) + ", " + (tempBack.getY()+1) + "; map: " + (tempBack.getX()) + ", " + (tempBack.getY()));
					
					
				}
				else if (map[temp.getX()][temp.getY()].equals("$")){
					
				}
				
			}
			
			//left
			
			if (temp.getY() != 0) {
				
				/*if (map[temp.getX()][temp.getY()-1].equals("$"))  {
					x = temp.getX();
					y = temp.getY()-1;
					dollar = new Coord("$", x, y, room);
					store.put((dollar.getX()) + ", " + (dollar.getY()), (temp.getX()) + ", " + (temp.getY()));
					System.out.println("key: " + (dollar.getX()) + ", " + (dollar.getY()) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					run = false;
				}*/
				
				if (map[temp.getX()][temp.getY()-1].equals("|") && !visited.containsKey((temp.getX()) + ", " + (temp.getY()-1))) {
					int newRStart = (roomIndex+1) * xBound;
					int newREnd = newRStart + xBound - 1;
					Coord newW = Wolverine(newRStart, newREnd, room+1);
					queue.add(newW);
					visited.put((newW.getX()) + ", " + (newW.getY()), true);
					if (visitedBack.containsKey((newW.getX()) + ", " + (newW.getY()))) {
						intersection = newW.getX() + ", " + newW.getY();
						run = false;
					}

					store.put((newW.getX()) + ", " + newW.getY(), (temp.getX()) + ", " + temp.getY());
					//System.out.println("key: " + (newW.getX()) + ", " + newW.getY() + "; map: " + (temp.getX()) + ", " + temp.getY());
					
					
				}
				
				else if (!map[temp.getX()][temp.getY()-1].equals("@")&& !visited.containsKey((temp.getX()) + ", " + (temp.getY()-1))) {
					queue.add(new Coord(".", temp.getX(), temp.getY()-1, room));
					visited.put((temp.getX()) + ", " + (temp.getY()-1), true);
					
					if (visitedBack.containsKey((temp.getX()) + ", " + (temp.getY()-1))) {
						intersection = (temp.getX()) + ", " + (temp.getY()-1);
						run = false;
					}
					
				
					store.put((temp.getX()) + ", " + (temp.getY()-1), (temp.getX()) + ", " + (temp.getY()));
					//System.out.println("key: " + (temp.getX()) + ", " + (temp.getY()-1) + "; map: " + (temp.getX()) + ", " + (temp.getY()));
					
					
				}
				
				
			}
			
			//left backwards
			if (tempBack.getY() != 0) {
				System.out.println("left");
				/*if (map[tempBack.getX()][tempBack.getY()-1].equals("$"))  {
					x = tempBack.getX();
					y = tempBack.getY()-1;
					dollar = new Coord("$", x, y, roomBack);
					storeBack.put((dollar.getX()) + ", " + (dollar.getY()), (tempBack.getX()) + ", " + (tempBack.getY()));
					System.out.println("key: " + (dollar.getX()) + ", " + (dollar.getY()) + "; map: " + (tempBack.getX()) + ", " + (tempBack.getY()));
					run = false;
				}*/
				
				
				if (map[tempBack.getX()][tempBack.getY()-1].equals("W") && !visitedBack.containsKey((tempBack.getX()) + ", " + (tempBack.getY()-1))) {
					int newRStartBack = (roomIndexBack-1) * xBound;
					int newREndBack = newRStartBack + xBound - 1;
					Coord newDoor = Door(newRStartBack, newREndBack, roomBack+1);
					queueBack.add(newDoor);
					visitedBack.put((newDoor.getX()) + ", " + (newDoor.getY()), true);
					System.out.println((newDoor.getX()) + ", " + (newDoor.getY()));
					if (visited.containsKey((newDoor.getX()) + ", " + (newDoor.getY()))) {
						intersection = (newDoor.getX()) + ", " + (newDoor.getY());
						run = false;
					}

					storeBack.put((newDoor.getX()) + ", " + newDoor.getY(), (temp.getX()) + ", " + tempBack.getY());
					System.out.println(storeBack.get((newDoor.getX()) + ", " + newDoor.getY()));
					System.out.println("BACKWARDS key: " + (newDoor.getX()) + ", " + newDoor.getY() + "; map: " + (tempBack.getX()) + ", " + tempBack.getY());
					
					
				}
				
				else if (!map[tempBack.getX()][tempBack.getY()-1].equals("@")&& !visitedBack.containsKey((tempBack.getX()) + ", " + (tempBack.getY()-1))) {
					queueBack.add(new Coord(".", tempBack.getX(), tempBack.getY()-1, roomBack));
					visitedBack.put((tempBack.getX()) + ", " + (tempBack.getY()-1), true);
					System.out.println((tempBack.getX()) + ", " + (tempBack.getY()-1));
					if (visited.containsKey((tempBack.getX()) + ", " + (tempBack.getY()-1))) {
						intersection = (tempBack.getX()) + ", " + (tempBack.getY()-1);
						run = false;
					}
				
					storeBack.put((tempBack.getX()) + ", " + (tempBack.getY()-1), (tempBack.getX()) + ", " + (tempBack.getY()));
					System.out.println("BACKWARDS key: " + (tempBack.getX()) + ", " + (tempBack.getY()-1) + "; map: " + (tempBack.getX()) + ", " + (tempBack.getY()));
					
					
				}
				else if (map[temp.getX()][temp.getY()].equals("$")){
					
				}
				
				
			}
			
			
		}
		
		
		System.out.println();
		System.out.println(intersection);
		
		String temp1 = intersection;
		String temp2 = intersection;
		//System.out.println(temp2);
		
		String startW = (wolverine.getX() + ", " + wolverine.getY());
		String startDollar = (dollar.getX() + ", " + dollar.getY());
		
		
		/*for (int i = 0; i < store.size(); i++) {
			System.out.println(temp1);
			temp1 = store.get(temp1);
		}*/
		
		
		/*for (int m = 0; m < storeBack.size(); m++) {
			System.out.println(temp2);
			temp2 = storeBack.get(temp2);
		}*/
		
		while (!(temp1 == null)) {
			System.out.println(temp1);
			if (map[Integer.parseInt(temp1.substring(0, temp1.indexOf(",")))][Integer.parseInt(temp1.substring(temp1.indexOf(" ") + 1))].equals("W")) {
				
			}
			else {
				map[Integer.parseInt(temp1.substring(0, temp1.indexOf(",")))][Integer.parseInt(temp1.substring(temp1.indexOf(" ") + 1))] = "+";
			}
			temp1 = store.get(temp1);
		}
		
		while (!(temp2==null)) {
			System.out.println(temp2);
			if (map[Integer.parseInt(temp2.substring(0, temp2.indexOf(",")))][Integer.parseInt(temp2.substring(temp2.indexOf(" ") + 1))].equals("$")) {
				
			}
			else {
				map[Integer.parseInt(temp2.substring(0, temp2.indexOf(",")))][Integer.parseInt(temp2.substring(temp2.indexOf(" ") + 1))] = "+";
			}
			temp2 = storeBack.get(temp2);
		}
		
		MapRead.printMap(map);
		
		
		

	}

	public static void main(String[] args) {
		OptPath();

	}

}
