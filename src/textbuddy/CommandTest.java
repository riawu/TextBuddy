package textbuddy;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CommandTest {
	private static ArrayList<String> _testLines;
	private static ByteArrayOutputStream _output = new ByteArrayOutputStream();
	
	private static final String TEST_PATH = "testfile.txt";
	
	private static final String TEST_LINE_1 = "Butterfly";
	private static final String TEST_LINE_2 = "Tortise";
	private static final String TEST_LINE_3 = "Zebra";
	private static final String TEST_LINE_4 = "Flamingo";
	private static final String TEST_LINE_5 = "Dolphin";
	private static final String TEST_LINE_6 = "Whale";
	
	
	private static final String TEST_DISPLAY_TEXT = "1. " + TEST_LINE_1 + "\n\n" +
													"2. " + TEST_LINE_2 + "\n\n" +
													"3. " + TEST_LINE_3 + "\n\n" +
													"4. " + TEST_LINE_4 + "\n\n" +
													"5. " + TEST_LINE_5 + "\n\n";
	
	private static final String TEST_SORT_TEXT =	"1. " + TEST_LINE_1 + "\n\n" +
													"2. " + TEST_LINE_5 + "\n\n" +
													"3. " + TEST_LINE_4 + "\n\n" +
													"4. " + TEST_LINE_2 + "\n\n" +
													"5. " + TEST_LINE_3 + "\n\n";
	
	private static final String TEST_SEARCH_TEXT =	"4. " + TEST_LINE_4 + "\n\n" +
													"5. " + TEST_LINE_5 + "\n\n";
	
	private static final String TEST_SEARCH_NOT_FOUND = "no result is found\n\n";

	@Before
	public void initialize() {
		System.setOut(new PrintStream(_output));
		ReadWrite.checkFile(TEST_PATH);
		
		_testLines = new ArrayList<String>();
		add();
		
		// re-start catching the output
		_output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(_output));
	}
	
	@After
	public void cleanUp() {
		// delete the test file
		ReadWrite.deleteFile(TEST_PATH);
	}
	
	@Test
	public void testAdd() {		
		addIndividual(TEST_LINE_6);
		
		// check if the line is added to the file
		assertEquals(_testLines, ReadWrite.getLines());
	}
	
	@Test
	public void testDisplay() {
		Command.display();
		
		// check if the content is displayed correctly
		assertEquals(TEST_DISPLAY_TEXT, _output.toString());
	}

	@Test
	public void testDelete() {
		int index = 1;
		
		Command.delete(index);
		_testLines.remove(index - 1);
		
		// check if the line is deleted from the file
		assertEquals(_testLines, ReadWrite.getLines());
	}
	
	@Test
	public void testClear() {
		Command.clear();
		_testLines.removeAll(_testLines);
		
		// check if the file is empty
		assertEquals(_testLines, ReadWrite.getLines());
	}
	
	private void add() {
		addIndividual(TEST_LINE_1);
		addIndividual(TEST_LINE_2);
		addIndividual(TEST_LINE_3);
		addIndividual(TEST_LINE_4);
		addIndividual(TEST_LINE_5);
	}
	
	private void addIndividual(String line) {
		Command.add(line);
		_testLines.add(line);
	}

}
