package textbuddy;

import java.util.Scanner;

/**
 * The TextBuddy class implements functions to manipulate text in a file.
 * @author YuTing
 *
 */
public class TextBuddy 
{
	private static final String MSG_WELCOME = "Welcome to TextBuddy. %1$s is ready for use.";
	private static final String MSG_COMMAND = "command: ";
	
	private static final String ERROR_NO_FILE = "Error: No file name.";
	private static final String ERROR_NO_INTEGER = "Error: Only integer is allowed for line number.";
	private static final String ERROR_INVALID_INPUT = "Error: Invalid input.";
	
	private static Scanner _scanner = new Scanner(System.in);
	
	// Options for command type
	private enum CmdType {
		ADD,
		DISPLAY,
		DELETE,
		DEL,
		CLEAR,
		EXIT,
		SORT,
		SEARCH,
		HELP,
		INVALID;
		
		private static CmdType toCmd(String word) {
			try {
				return valueOf(word);
			} catch (Exception e) {
				return INVALID;
			}
		}
	}
	
	public static void main(String[] args) {
		if (checkArgs(args)) {
			checkFile(args[0]);
			processCmd();
		}
	}
	
	/**
	 * Check if the file name is given.
	 * @param args - input argument
	 * @return true if the file name is given; false otherwise.
	 */
	private static boolean checkArgs(String[] args) {
		if (args.length == 0) {
			Message.show(Message.format(ERROR_NO_FILE));
			return false;
		}
		return true;
	}
	
	/**
	 * Check if the file exists. 
	 * @param path - file path
	 */
	private static void checkFile(String path) {
		ReadWrite.checkFile(path);
	}
	
	/**
	 * Read the input as word.
	 * @return input word
	 */
	private static String readWord() {
		String word = _scanner.next().toUpperCase();
		System.out.println();
		
		return word;
	}
	
	/**
	 * Read the input as line.
	 * @return input line
	 */
	private static String readLine() {
		try {
			return _scanner.nextLine().substring(1);
		} catch (IndexOutOfBoundsException e) {
			return "";
		}
	}
	
	/**
	 * Process according to the given command.
	 */
	private static void processCmd() {
		Message.show(Message.format(MSG_WELCOME, Command.getName()));
		
		while (true) {
			Message.showWithoutNextLine(MSG_COMMAND);
			switch(CmdType.toCmd(readWord())) {
				case ADD:
					Command.add(readLine());
					break;
				case DISPLAY:
					Command.display();
					break;
				case DELETE: case DEL:
					try {
						Command.delete(Integer.parseInt(readLine()));
					} catch (NumberFormatException e) {
						Message.show(Message.format(ERROR_NO_INTEGER));
					}
					break;
				case CLEAR:
					Command.clear();
					break;
				case EXIT:
					Command.exit();
					break;
				case HELP:
					Command.help();
					break;
				case SORT:
					Command.sort();
					break;
				case SEARCH:
					Command.search(readLine());
					break;
				default:
					Message.show(Message.format(ERROR_INVALID_INPUT));
			}
		}
	}
}
