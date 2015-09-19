package textbuddy;

import java.io.*;
import java.util.ArrayList;

/**
 * The ReadWrite class implements functions to access the file.
 * @author YuTing
 *
 */
public class ReadWrite {
	// Error Message
	private static final String ERROR_FILE_NOT_FOUND = "Error: File cannot be found.";
	private static final String ERROR_READ_FILE = "Error: File cannot be read.";
	private static final String ERROR_WRITE_FILE = "Error: File cannot be written.";
	private static final String ERROR_CREATE_FILE = "Error: The file is unable to be created.";
	
	private static String _path = null;
	
	/**
	 * Set the file path.
	 * @param path - file path
	 */
	private static void setPath(String path) {
		_path = path;
	}
	
	/**
	 * Get the file name.
	 * @return file name
	 */
	protected static String getName() {
		File file = new File(_path);	
		return file.getName();
	}
	
	/**
	 * Check if the file exists. If the file does not exist, prompt to
	 * ask whether the user would like to create the new file. Otherwise,
	 * set the path for future usage.
	 * @param path - file path
	 */
	protected static void checkFile(String path) {
		File file = new File(path);
		
		setPath(path);
		if (!isFileExists(file)) {
			createFile();
		}
	}
	
	/**
	 * Delete the file if it exists.
	 * @param path - file path
	 */
	protected static void deleteFile(String path) {
		File file = new File(path);
		
		if (isFileExists(file)) {
			file.delete();
		}
	}
	
	/**
	 * Check if the given file path exists.
	 * @param path - file path
	 * @return true if file exists; false otherwise
	 */
	private static boolean isFileExists(File file) {
		return file.exists();
	}
	
	/**
	 * Create the file.
	 */
	private static void createFile() {
		try {
			File file = new File(_path);
			file.createNewFile();
		} catch (IOException e) {
			Message.show(Message.format(ERROR_CREATE_FILE));
		}
	}
	
	/**
	 * Read lines from the file.
	 * @return an Arraylist of the lines from the file. 
	 */
	protected static ArrayList<String> getLines() {
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			String line = null;
			FileReader fileReader = new FileReader(_path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
			
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			Message.show(Message.format(ERROR_FILE_NOT_FOUND));
		} catch (IOException e) {
			Message.show(Message.format(ERROR_READ_FILE));
		}
		
		return lines;
	}
	
	/**
	 * Write the lines into the file.
	 * @param lines - lines to be written into the file
	 */
	protected static void writeLines(ArrayList<String> lines) {
		try {
			FileWriter fileWriter = new FileWriter(_path);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			for (String line: lines) {
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
			
			bufferedWriter.close();
		} catch (FileNotFoundException e) {
			Message.show(Message.format(ERROR_FILE_NOT_FOUND));
		} catch(IOException e) {
			Message.show(Message.format(ERROR_WRITE_FILE));
		}
	}
}
