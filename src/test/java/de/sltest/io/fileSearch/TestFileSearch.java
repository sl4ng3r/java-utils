package de.sltest.io.fileSearch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.slfw.io.fileSearch.FileSearcher;
import de.slfw.io.fileSearch.FilesFoundListener;

public class TestFileSearch implements FilesFoundListener{

	
	private FileSearcher fs;
	private final String searchDirectory = Thread.currentThread().getContextClassLoader().getResource("testfolder_search").getFile();
	
	
	@Before
	public void init() {
		Path path = new File(searchDirectory).toPath();
		fs = new FileSearcher(path, this, 10000);
		
	}
	
	
	@Test
	public void searchDirectory() throws IOException {
		fs.search();
		
	}


	@Override
	public void foundFiles(List<File> files) {
		// TODO Auto-generated method stub
		
	}
	
	
}
