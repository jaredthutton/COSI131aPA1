package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cs131.pa1.filter.Message;

/**
 * The cat filter, uses a scanner to read a file and line by line adds to the output queue
 * 
 * @author Jared Hutton
 *
 */
public class CatFilter extends SequentialFilter{
	
	public Scanner fileScanner;
	/**
	 * Cat Filter Constructor, creates file scanner and checks to make sure syntax is correct
	 * and file exists
	 * 
	 * @param line
	 * @throws Exception
	 */
	public CatFilter(String line) throws Exception {
		super();
		
		String[] commands = line.split(" ");
		String fileName;
		
		if(commands.length == 1) {
			System.out.printf(Message.REQUIRES_PARAMETER.toString(), line);
			throw new Exception();
		}
		
		fileName = commands[1];
		try {
			fileScanner = new Scanner(new File(SequentialREPL.currentWorkingDirectory + File.separator + fileName));
		} catch (FileNotFoundException e) {
			System.out.printf(Message.FILE_NOT_FOUND.toString(), line);
			throw new Exception();
		}
	}

	/**
	 * overrides process, calls processLine and returns result to output
	 */
	public void process() {
		while(true) {
			String processedLine = processLine("");
			if(processedLine == null) {
				break;
			}
			output.add(processedLine);
		}
	}
	
	@Override
	/**
	 * returns next line which is then pushed to output queue
	 */
	protected String processLine(String line) {
		if(fileScanner.hasNextLine()) {
			return fileScanner.nextLine();
		} else {
			return null;
		}
	}

}
