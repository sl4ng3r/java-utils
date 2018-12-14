package de.sltest.io.fileSearch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import de.slfw.io.fileSearch.FileSearcher;

public class TestFileSearch {

	
	private FileSearcher fs;
	private final String searchDirectory = Thread.currentThread().getContextClassLoader().getResource("testfolder_search").getFile();
	
	
	@Before
	public void init() {
		fs = new FileSearcher();
		
	}
	
	
	@Test
	public void searchDirectory() throws IOException {
		Path path = new File(searchDirectory).toPath();
		fs.setSearchDirectory(path);
		fs.search();
		
	}
	
	
}
