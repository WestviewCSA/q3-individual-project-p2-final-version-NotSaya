import java.io.File;

public class Coord {
	
	private String name;
	private int x;
	private int y;
	private int room;

	public Coord(String symbol, int xC, int yC, int roomC) {
		name = symbol;
		x = xC;
		y = yC;
		room = roomC;
	}
	
	public String getSymbol() {
		return name;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getRoom() {
		return room;
	}
	
	
	public static void main(String[] args) {
	}

}
