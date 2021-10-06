package cs131.pa1.filter.sequential;

import cs131.pa1.filter.Message;
/**
 * Grep filter only puts lines that contain a substring into the output
 * @author Jared Hutton
 *
 */
public class GrepFilter extends SequentialFilter{
	
	public String subString;
	/**
	 * Constructs Grep filter and checks if a parameter is needed
	 * @param line
	 * @throws Exception
	 */
	public GrepFilter(String line) throws Exception {
		super();
		String[] commands = line.split(" ");
		if(commands.length > 1) {
			subString = commands[1];
		} else {
			System.out.printf(Message.REQUIRES_PARAMETER.toString(), line);
			throw new Exception();
		}
	}

	/**
	 * checks if the line contains substring, if it does, returns it to output
	 */
	@Override
	protected String processLine(String line) {
		if(line.contains(subString)) {
			return line;
		} else {
			return null;
		}
	}

}
