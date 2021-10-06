package cs131.pa1.filter.sequential;

import java.io.File;

import cs131.pa1.filter.Message;
/**
 * CdFilter is used to navigate directories. It can go up a file, down to a specific file, and show current directory
 * 
 * @author Jared Hutton
 *
 */
public class CdFilter extends SequentialFilter{
	
	public String currentDirectory;
	
	/**
	 * CdFilter constructor checks input to determine new directory
	 * @param line
	 * @throws Exception
	 */
	public CdFilter(String line) throws Exception {
		super();
		currentDirectory = SequentialREPL.currentWorkingDirectory;
		String[] commands = line.trim().split(" ");
		
		if(commands.length == 1) {
			System.out.printf(Message.REQUIRES_PARAMETER.toString(), line.trim());
			throw new Exception();
		}
		
		if(commands[1].equals("..")) {
			String temp = SequentialREPL.currentWorkingDirectory;
			temp = temp.substring(0, temp.lastIndexOf(File.separator));
			currentDirectory = temp;
		} else if(!commands[1].equals(".")) {
			String temp = SequentialREPL.currentWorkingDirectory;
			temp = temp + File.separator + commands[1];
			File test = new File(temp);
			if(test.isDirectory()) {
				currentDirectory = temp;
			} else {
				System.out.printf(Message.DIRECTORY_NOT_FOUND.toString(), line);
				throw new Exception();
			}
		}
	}
	
	/**
	 * calls processLine
	 */
	public void process() {
		processLine("");
	}

	@Override
	/**
	 * sets current working directory to new one
	 */
	protected String processLine(String line) {
		SequentialREPL.currentWorkingDirectory = currentDirectory;
		return null;
	}

}
