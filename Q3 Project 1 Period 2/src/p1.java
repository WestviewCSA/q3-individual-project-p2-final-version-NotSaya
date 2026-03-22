import java.io.File;

public class p1 {
	
	private static File file;
	private static String[][] map;
	

	public static void main (String[] args) throws IllegalCommandLineInputsException{
		// TODO Auto-generated method stub
		String a = args[0];
		String[] commands = args[0].split(",");
		int tracker = 0;
		String runtime = "";
		boolean incoordinate = false;
		boolean outcoordinate = false;
		boolean time = false;
		
		for  (int m = 0; m < commands.length; m++) {
			if (commands[m].indexOf(".txt") != -1) {
				file = new File(commands[m]);
			}
			if (commands[m].equals("incoordinate")) {
				incoordinate = true;
			}
			if (commands[m].equals("outcoordinate")){
				outcoordinate = true;
			}
			if (commands[m].equals("time")){
				time = true;
			}
		}
		
		
		for  (int i = 0; i < commands.length; i++) {
			if (commands[i].equalsIgnoreCase("help")) {
				Help help = new Help();
				help.help();
			}
			if (commands[i].equalsIgnoreCase("queue")) {
				tracker++;
				QueuePath queue = new QueuePath(file);
				if (incoordinate) {
					queue.mapC();
				}
				if (time) {
					runtime = queue.runtime();
				}
				map = queue.Queue();
			}
			if (commands[i].equalsIgnoreCase("stack")) {
				tracker++;
				StackPath stack = new StackPath(file);
				if (incoordinate) {
					stack.mapC();
				}
				if (time) {
					runtime = stack.runtime();
				}
				map = stack.Stack();
			}
			if (commands[i].equalsIgnoreCase("opt")) {
				tracker++;
				Opt opt = new Opt(file);
				if (incoordinate) {
					opt.mapC();
				}
				if (time) {
					runtime = opt.runtime();
				}
				map = opt.OptPath();
			}
		}
		
		if (tracker == 1) {
			MapRead mapread = new MapRead();
			if (outcoordinate) {
				Outcoordinate out = new Outcoordinate(file, map);
				mapread.printMap(out.out());
			}
			else {
				mapread.printMap(map);
			}
			if (time) {
				System.out.println(runtime);
			}
		}
		else {
			throw new IllegalCommandLineInputsException("Please type ONE of stack, queue, or opt");
		}

	}

}
