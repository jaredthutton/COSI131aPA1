package cs131.pa1.filter.sequential;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cs131.pa1.filter.Message;

/**
 * This class manages the parsing and execution of a command. It splits the raw
 * input into separated subcommands, creates subcommand filters, and links them
 * into a list.
 * 
 * @author cs131a, Jared Hutton
 *
 */
public class SequentialCommandBuilder {
	/**
	 * Creates and returns a list of filters from the specified command
	 * 
	 * @param command the command to create filters from
	 * @return the list of SequentialFilter that represent the specified command
	 */
	public static List<SequentialFilter> createFiltersFromCommand(String command) {
		List<SequentialFilter> filters = new LinkedList<SequentialFilter>();
		
		String shortCommand = adjustCommandToRemoveFinalFilter(command);
		if(shortCommand == null) {
			return null;
		}
		
		String[] filterArray = shortCommand.split("\\|");
		for(int i = 0; i < filterArray.length; i++){
			SequentialFilter filter = constructFilterFromSubCommand(filterArray[i].trim());
			if(filter != null) {
				filters.add(filter);
			} else {
				return null;
			}
		}
		
		SequentialFilter lastFilter = determineFinalFilter(command);
		if(lastFilter == null) {
			return null;
		}
		filters.add(lastFilter);
		
		if(linkFilters(filters, command) == true) {
			return filters;
		} else {
			return null;
		}
	}

	/**
	 * Returns the filter that appears last in the specified command
	 * 
	 * @param command the command to search from
	 * @return the SequentialFilter that appears last in the specified command
	 */
	private static SequentialFilter determineFinalFilter(String command) {
		String[] redirect = command.split(">");
		if(redirect.length == 1) {
			return new PrintFilter();
		} else {
			try {
				return new RedirectFilter("> " + redirect[1]);
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * Returns a string that contains the specified command without the final filter
	 * 
	 * @param command the command to parse and remove the final filter from
	 * @return the adjusted command that does not contain the final filter
	 */
	private static String adjustCommandToRemoveFinalFilter(String command) {
		String[] endRemoval = command.split(">");
		if(endRemoval.length > 1) {
			if(endRemoval[0].trim().equals("")) {
				System.out.printf(Message.REQUIRES_INPUT.toString(), ("> " + endRemoval[1].trim()));
				return null;
			}
			if(endRemoval[1].contains("|")) {
				System.out.printf(Message.CANNOT_HAVE_OUTPUT.toString(), ">" + endRemoval[1].substring(0, endRemoval[1].indexOf("|")));
				return null;
			}
			if(endRemoval[1].equals("")) {
				System.out.printf(Message.REQUIRES_PARAMETER.toString(), ">");
			}
			if(endRemoval.length > 2) {
				System.out.printf(Message.CANNOT_HAVE_OUTPUT.toString(), endRemoval[1].trim());
				return null;
			}
		}
		return endRemoval[0];
	}

	/**
	 * Creates a single filter from the specified subCommand
	 * 
	 * @param subCommand the command to create a filter from
	 * @return the SequentialFilter created from the given subCommand
	 */
	private static SequentialFilter constructFilterFromSubCommand(String subCommand) {
		String[] command = subCommand.split(" ");
		SequentialFilter filter;
		
		try {
			switch (command[0]) {
			case "cat":
				filter = new CatFilter(subCommand);
				break;
			case "cd":
				filter = new CdFilter(subCommand);
				break;
			case "grep":
				filter = new GrepFilter(subCommand);
				break;
			case "head":
				filter = new HeadFilter();
				break;
			case "ls":
				filter = new LsFilter();
				break;
			case "pwd":
				filter = new PwdFilter();
				break;
			case "tail":
				filter = new TailFilter();
				break;
			case "uniq":
				filter = new UniqFilter();
				break;
			case "wc":
				filter = new WcFilter();
				break;
			default:
				System.out.printf(Message.COMMAND_NOT_FOUND.toString(), subCommand);
				return null;
			}
			
		} catch (Exception e) {
			return null;
		}
		return filter;
	}

	/**
	 * links the given filters with the order they appear in the list
	 * 
	 * @param filters the given filters to link
	 * @return true if the link was successful, false if there were errors
	 *         encountered. Any error should be displayed by using the Message enum.
	 */
	private static boolean linkFilters(List<SequentialFilter> filters, String command) {
		Iterator<SequentialFilter> iter = filters.iterator();
		SequentialFilter prev;
		SequentialFilter current = iter.next();
		
		if(current instanceof GrepFilter || current instanceof WcFilter || current instanceof HeadFilter || current instanceof TailFilter || current instanceof UniqFilter){
			System.out.printf(Message.REQUIRES_INPUT.toString(), command);
			return false;
		}
		
		while(iter.hasNext()) {
			prev = current;
			current = iter.next();
			
			if(current instanceof CdFilter || current instanceof CatFilter || current instanceof LsFilter || current instanceof PwdFilter) {
				String[] subCommands = command.split("\\|");
				String subCommand = subCommands[1];
				System.out.printf(Message.CANNOT_HAVE_INPUT.toString(), subCommand.trim());
				return false;
			}
			
			if(prev instanceof CdFilter && !(current instanceof PrintFilter)) {
				String[] subCommands = command.split("\\|");
				String subCommand = subCommands[0];
				System.out.printf(Message.CANNOT_HAVE_OUTPUT.toString(), subCommand.trim());
				return false;
			}
			prev.setNextFilter(current);
		}
		return true;
	}
}
