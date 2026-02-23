import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CRead {
	static File easyC = new File("easyMap1C.txt");
	static File mediumC = new File("mediumMapC.txt");
	//static File hard = new File("hardMaps.txt");
	
	public static String[][] readC(File file){
		try(Scanner scanner = new Scanner(file)){
			
			int x = 0;
			while (scanner.hasNextLine()) {
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public static void main(String[] args) {


	}

}
