import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapRead {
	/*static File easy = new File("easyMap1.txt");
	static File medium = new File("mediumMap.txt");
	static File hard = new File("hardMaps.txt");
	
	static File easyC = new File("easyMap1C.txt");
	static File mediumC = new File("mediumMapC.txt");
	//static File hard = new File("hardMaps.txt");*/
	
	
	public MapRead() {
		
	}
	
	/*public static String[][] readMap(File file){
		
		
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
		
	}*/
	
	public String[][] readMap(File file){
		try(Scanner scanner1 = new Scanner(file); Scanner scanner2 = new Scanner(file)){
			
			int y = 0;
			while (scanner1.hasNextLine()) {
				y++;
				scanner1.nextLine();
			}
			
			
			String firstLine = scanner2.nextLine();
			String[] dimensions = firstLine.split(" ");
			int x = Integer.parseInt(dimensions[1]);
			
			String[][] map = new String[y-1][x];
			
			
			for (int i = 0; i < map.length; i++) {
				map[i] = scanner2.nextLine().split(" ");
			}
			
			return map;
			
		} catch (FileNotFoundException e) {
	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void printMap(String[][] map) {
		if (map == null) {
			System.out.println("null");
		}
		else {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[0].length; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}
		System.out.println();
		}
	}
	
	/*public static void printCMap(String[][] cMap) {
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
	}*/
	
	public static String[] Dim(File file) {
		
		try (Scanner scanner = new Scanner(file)){
			String firstLine = scanner.nextLine();
			String[] dimensions = firstLine.split(" ");
			return dimensions;
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		File easy = new File("easyMap1.txt");
		File easyC = new File("easyMap1C.txt");
		
		
		
		/*String[][] newMap = readMap(easy);
		
		for (int r = 0; r < newMap.length; r++) {
			for (int c = 0; c < newMap[0].length; c++) {
				System.out.print(newMap[r][c]);
			}
			System.out.println();
		}
		System.out.println();
		
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
			}*/
	}
}
