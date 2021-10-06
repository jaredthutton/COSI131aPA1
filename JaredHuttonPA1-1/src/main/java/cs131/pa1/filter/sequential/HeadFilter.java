package cs131.pa1.filter.sequential;
/**
 * Head Filter takes the first 10 lines in an input and returns them. Returns all lines if there are less than 10
 * @author Jared Hutton
 *
 */
public class HeadFilter extends SequentialFilter {
	
	public int counter;
	
	/**
	 * constructs head filter with a counter of zero
	 */
	public HeadFilter() {
		super();
		counter = 0;
	}

	
	@Override
	/**
	 * Checks if the counter is equal to ten, if not, returns to output
	 */
	protected String processLine(String line) {
		if(counter == 10) {
			return null;
		} else {
			counter++;
			return line;
		}
	}

}
