package cs131.pa1.filter.sequential;

/**
 * adds current working directory to output
 * @author Jared Hutton
 *
 */
public class PwdFilter extends SequentialFilter {
	/**
	 * constructs PwdFilter
	 */
	public PwdFilter() {
		super();
	}
	
	/**
	 * calls processLine and adds it to output
	 */
	public void process() {
		output.add(processLine(""));
	}
	
	@Override
	/**
	 * returns currentWorkingDirectory from SequentialREPL
	 */
	public String processLine(String line) {
		return SequentialREPL.currentWorkingDirectory;
	}
}
