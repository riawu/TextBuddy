package textbuddy;

/**
 * This Message class implements functions to print messages.
 * @author YuTing
 *
 */
public class Message {
	/**
	 * Print the message.
	 * @param message - message to be printed
	 */
	public static void show(String message) {
		System.out.println(message);
	}
	
	/**
	 * Print the message without going to the next line.
	 * @param message - message to be printed
	 */
	public static void showWithoutNextLine(String message) {
		System.out.print(message);
	}
	
	/**
	 * Format the message to be displayed.
	 * @param message - type of message
	 * @param args - parameter(s)
	 * @return message
	 */
	public static String format(String message, Object... args) {
		return String.format(message, args) + "\n";
	}
}
