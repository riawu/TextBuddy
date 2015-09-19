package textbuddy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The Command class implements several command functions.
 * @author YuTing
 *
 */
public class Command extends ReadWrite {
	private static final String MSG_ADD_TEXT = "added to %1$s: \"%2$s\"";
	private static final String MSG_DEL_TEXT = "deleted from %1$s: \"%2$s\"";
	private static final String MSG_CLEAR_TEXT = "all content deleted from %1$s";
	private static final String MSG_EMPTY_TEXT = "%1$s is empty";
	private static final String MSG_DISPLAY_LINE = "%1$s. %2$s";
	private static final String MSG_SEARCH_NOT_FOUND = "no result is found";
	
	private static final String ERROR_INVALID_LINE_NUMBER = "Error: Invalid line number.";
	
	/**
	 * Add the text to the file.
	 * @param newLine - new line to add to the file 
	 */
	public static void add(String newLine) {
		ArrayList<String> lines = getLines();
		
		lines.add(newLine);	
		writeLines(lines);
		
		Message.show(Message.format(MSG_ADD_TEXT, getName(), newLine));
	}
	
	/**
	 * Display the text in the file.
	 */
	public static void display() {
		ArrayList<String> lines = getLines();
		
		if (lines.size() == 0) {
			Message.show(Message.format(MSG_EMPTY_TEXT, getName()));
		}
		
		for (int i = 0; i < lines.size(); i++) {
			Message.show(Message.format(MSG_DISPLAY_LINE, Integer.toString(i + 1), lines.get(i)));
		}
	}
	
	/**
	 * Delete the line according to the given line number.
	 * @param num - line number
	 */
	public static void delete(int num) {
		ArrayList<String> lines = getLines();

		num--;
		
		if (num < 0 || num > lines.size() - 1) {
			Message.show(Message.format(ERROR_INVALID_LINE_NUMBER));
			return;
		}
		
		String tempLine = lines.get(num);
		lines.remove(num);
		writeLines(lines);
		
		Message.show(Message.format(MSG_DEL_TEXT, getName(), tempLine));
	}
	
	/**
	 * Clear the text in the file.
	 */
	public static void clear() {
		ArrayList<String> lines = new ArrayList<String>();
		writeLines(lines);
		
		Message.show(Message.format(MSG_CLEAR_TEXT, getName()));
	}
	
	/**
	 * Exit the application.
	 */
	public static void exit() {
		System.exit(0);
	}
	
	/**
	 * Sort the contents in the file.
	 */
	public static void sort() {
		ArrayList<String> lines = getLines();
		
		Collections.sort(lines);
		writeLines(lines);
		
		display();
	}
	
	/**
	 * Search the lines with the particular key.
	 * @param searchedKey - the key to search
	 */
	public static void search(String searchedKey) {

	}
	
	/**
	 * Provide the information of possible commands. 
	 */
	public static void help() {
		Message.show(Message.format("The available commands are as followed:"));

		Message.show(Message.format("add (string): "));
		Message.show(Message.format("\tAdd (string) into the text file."));

		Message.show(Message.format("display:"));
		Message.show(Message.format("\tDisplay the contents of the file."));

		Message.show(Message.format("delete (integer): "));
		Message.show(Message.format("\tDelete the line corresponding to the given line number."));

		Message.show(Message.format("clear: "));
		Message.show(Message.format("\tClear all the contents of the file."));


		Message.show(Message.format("exit: "));
		Message.show(Message.format("\tExit the application."));
	}
}
