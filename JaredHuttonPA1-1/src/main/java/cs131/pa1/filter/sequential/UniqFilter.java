package cs131.pa1.filter.sequential;

import java.util.HashSet;

/**
 * Uniq filter returns lines only once to the output
 * @author Jared Hutton
 *
 */
public class UniqFilter extends SequentialFilter{
	public HashSet<String> seenStrings;
	
	/**
	 * constructs uniq filter with hashset
	 */
	public UniqFilter() {
		seenStrings = new HashSet<String>();
	}

	@Override
	/**
	 * checks if line has been seen, if so returns null. If not, adds line to hashset and returns line to output
	 */
	protected String processLine(String line) {
		if(seenStrings.contains(line)) {
			return null;
		} else {
			seenStrings.add(line);
			return line;
		}
	}
	
}
