package de.sltest.io;

import de.slfw.io.FileOperator;
import de.slfw.io.FileSearchResult;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class TestFindFilesInDirectory {


	
	

	@Test
	public void testFindStringInFile() throws IOException{
		boolean searchIfStringInFile = FileOperator.searchIfStringInFile(new File(Thread.currentThread().getContextClassLoader().getResource("testfolder_search/directory2/dir2test.txt").getFile()), "Blubb", false);
		assertTrue(searchIfStringInFile);
	}
	
	
	@Test
	public void testFindFilesWithString() throws IOException {
		List<FileSearchResult> searchFilesWithContainingString = FileOperator.searchFilesWithContainingString(new File(Thread.currentThread().getContextClassLoader().getResource("testfolder_search").getFile()), "blubb", false, 5);
		assertEquals(4, searchFilesWithContainingString.size());
	}
	
	
}
