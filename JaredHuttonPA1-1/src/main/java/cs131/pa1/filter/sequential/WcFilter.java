package cs131.pa1.filter.sequential;

/**
 * Wc filter pipes linecount, wordcount, and charcount to output
 * @author Jared Hutton
 *
 */
public class WcFilter extends SequentialFilter{
	
	public int lineCount;
	public int wordCount;
	public int charCount;
	
	/**
	 * constructs wordcount filter
	 */
	public WcFilter() {
		super();
	}
	
	/**
	 * calls process line and returns result to output
	 */
	public void process() {
		if(isDone()) {
			output.add(processLine(null));
		} else {
			super.process();
		}
	}

	@Override
	/**
	 * if the line is null it returns result, otherwise it continues to increase values
	 */
	protected String processLine(String line) {
		if(line == null) {
			return lineCount + " " + wordCount + " " + charCount;
		}
		if(isDone()) {
			String[] wordCountArray = line.split(" ");
			wordCount += wordCountArray.length;
			String[] charCountArray = line.split("|");
			charCount += charCountArray.length;
			return ++lineCount + " " + wordCount + " " + charCount;
		} else {
			lineCount++;
			String[] wordCountArray = line.split(" ");
			wordCount += wordCountArray.length;
			String[] charCountArray = line.split("|");
			charCount += charCountArray.length;
			return null;
		}
	}

}
