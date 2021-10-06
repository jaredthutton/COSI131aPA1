package cs131.pa1.filter.sequential;


import java.util.Iterator;
import java.util.List;
/**
 * The main implementation of the REPL loop (read-eval-print loop). It reads
 * commands from the user, parses them, executes them and displays the result.
 * 
 * @author cs131a, Jared Hutton
 *
 */
import java.util.Scanner;

import cs131.pa1.filter.Message;


public class SequentialREPL {
	/**
	 * the path of the current working directory
	 */
	static String currentWorkingDirectory;

	/**
	 * The main method that will execute the REPL loop
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		currentWorkingDirectory = System.getProperty("user.dir");
		Scanner input = new Scanner(System.in);
		System.out.print(Message.WELCOME);
		
		boolean keepLooping = true;
		while(keepLooping) {
			System.out.print(Message.NEWCOMMAND);
			String in = input.nextLine();

			//exits repl
			if(in.equals("exit")) {
				keepLooping = false;
				break;
			// calls createFiltersFromCommand to create filers
			} else if(!in.trim().equals("")){
				List<SequentialFilter> filters = SequentialCommandBuilder.createFiltersFromCommand(in);
				//checks if filters is null to avoid nullpointer
				if(filters != null) {
					//iterates over list
					Iterator<SequentialFilter> iter = filters.iterator();
					while(iter.hasNext()) {
						iter.next().process();
					}
				}
			}
		}
		System.out.print(Message.GOODBYE);
	}

}
