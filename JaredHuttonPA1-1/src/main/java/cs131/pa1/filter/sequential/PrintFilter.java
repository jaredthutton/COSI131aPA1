package cs131.pa1.filter.sequential;
/**
 * Prints input to console
 * @author Jared Hutton
 *
 */
public class PrintFilter extends SequentialFilter {
	
	/**
	 * constructs print filter
	 */
	public PrintFilter() {
		super();
	}
	
	/**
	 * passes input to processLine
	 */
	public void process() {
		while(!isDone()) {
			processLine(input.poll());
		}
	}

	@Override
	/**
	 * prints line to console
	 */
	protected String processLine(String line) {
		System.out.println(line);
		return null;
	}

}
