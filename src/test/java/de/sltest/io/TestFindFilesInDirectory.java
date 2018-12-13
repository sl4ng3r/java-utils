package de.sltest.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.junit.Test;

import de.slfw.io.FileOperator;
import de.slfw.io.FileSearchResult;


public class TestFindFilesInDirectory {


	
	

	@Test
	public void testFindStringInFile() throws IOException{
		boolean searchIfStringInFile = FileOperator.searchIfStringInFile(new File(Thread.currentThread().getContextClassLoader().getResource("testfolder_search/directory2/dir2test.txt").getFile()), "Blubb", false);
		assertTrue(searchIfStringInFile);
	}
	
	
	@Test
	public void testFindFilesWithString() throws IOException {
		List<FileSearchResult> searchFilesWithContainingString = FileOperator.searchFilesWithContainingString(new File(Thread.currentThread().getContextClassLoader().getResource("testfolder_search").getFile()), "blubb", false, 5);
		
		assertEquals(searchFilesWithContainingString.size(), 2);
	}
	
	
}
