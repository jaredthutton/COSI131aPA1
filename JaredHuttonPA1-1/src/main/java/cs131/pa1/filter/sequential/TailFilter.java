package cs131.pa1.filter.sequential;

import java.util.Stack;

/**
 * The tail filter puts the last 10 lines of input into the output, or all lines if less than 10
 * @author Jared Hutton
 *
 */
public class TailFilter extends SequentialFilter{
	
	Stack<String> lines;
	Stack<String> linesReversed;
	int counter;
	
	/**
	 * constructs Tail filter with two stacks and a counter
	 */
	public TailFilter() {
		super();
		lines = new Stack<String>();
		linesReversed = new Stack<String>();
		counter = 0;
	}
	
	/**
	 * Takes input, polls the last ten into new stack to get order right, calls processline to add to output
	 */
	public void process() {
		while(!input.isEmpty()) {
			lines.push(input.poll());
		} 
		while(!lines.isEmpty() && counter < 10) {
			linesReversed.push(lines.pop());
			counter++;
		}
		while(!linesReversed.isEmpty()) {
			output.add(processLine(linesReversed.pop()));
		}

	}

	@Override
	/**
	 * returns line
	 */
	protected String processLine(String line) {
		return line;
	}

}
