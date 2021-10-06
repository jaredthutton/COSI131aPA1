package cs131.pa1.filter.sequential;

import java.io.File;
/**
 * Ls filter prints contents of current working directory
 * @author Jared Hutton
 *
 */
public class LsFilter extends SequentialFilter {
	
	File folder;
	File[] folderList;
	int fileCounter;
	
	/**
	 * constructs lsfilter and instanciates variables
	 */
	public LsFilter() {
		super();
		folder = new File(SequentialREPL.currentWorkingDirectory);
		folderList = folder.listFiles();
		fileCounter = 0;
	}
	
	/**
	 * checks if the fileCounter is less than the length, and adds the processed line to the output
	 */
	public void process() {
		while(fileCounter < folderList.length) {
			output.add(processLine(""));
		}
	}

	@Override
	/**
	 * returns folder name and increments file counter
	 */
	public String processLine(String line) {
		return(folderList[fileCounter++].getName());
	}

}
