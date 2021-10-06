package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cs131.pa1.filter.Message;
/**
 * Supposed to redirect input to new file, I couldn't implement it so it does nothing
 * @author Jared Hutton
 *
 */
public class RedirectFilter extends SequentialFilter{
	
	/**
	 * constructs redirect filter
	 * @param line
	 * @throws Exception
	 */
	public RedirectFilter(String line) throws Exception {
		super();

	}
	
	/**
	 * does nothing
	 */
	public void process() {
		while(!isDone()) {
			processLine(input.poll());
		}
	}

	@Override
	/**
	 * does nothing
	 */
	protected String processLine(String line) {

		return null;
	}
}
