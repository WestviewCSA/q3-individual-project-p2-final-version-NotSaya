import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapRead {
	static File easy = new File("easyMap1.txt");
	static File medium = new File("mediumMap.txt");
	static File hard = new File("hardMaps.txt");
	
	static File easyC = new File("easyMap1C.txt");
	static File mediumC = new File("mediumMapC.txt");
	//static File hard = new File("hardMaps.txt");
	
	public static String[][] readMap(File file){
		
		
		try (Scanner scanner = new Scanner(file)){
			String firstLine = scanner.nextLine();
			String[] dimensions = firstLine.split(" ");

			int y = Integer.parseInt(dimensions[0]) * Integer.parseInt(dimensions[2]);
			int x = Integer.parseInt(dimensions[1]);
			
			String[][] map = new String[x][y];
			
			for (int i = 0; i < map.length; i++) {
				map[i] = scanner.nextLine().split(" ");
			}
			
			return map;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static String[][] readC(File file){
		try(Scanner scanner1 = new Scanner(file)){
			
			int y = 0;
			while (scanner1.hasNextLine()) {
				y++;
				scanner1.nextLine();
				System.out.println("hello");
			}
			
			String firstLine = scanner1.nextLine();
			String[] dimensions = firstLine.split(" ");
			int x = Integer.parseInt(dimensions[1]);
			
			System.out.println("hello");
			
			String[][] map = new String[x][y];
			
			for (int i = 0; i < map.length; i++) {
				map[i] = scanner1.nextLine().split(" ");
			}
			
			return map;
			
		} catch (FileNotFoundException e) {
	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		String[][] newMap = readMap(easy);
		
		for (int r = 0; r < newMap.length; r++) {
			for (int c = 0; c < newMap[0].length; c++) {
				System.out.print(newMap[r][c]);
			}
			System.out.println();
		}
		
		String[][] cMap = readC(easyC);
		if (cMap == null) {
			System.out.println("null");
		}
		else {
			for (int g = 0; g < cMap.length; g++) {
				for (int h = 0; h < cMap[0].length; h++) {
					System.out.print(cMap[g][h]);
				}
				System.out.println();
			}
		}
	}
}
