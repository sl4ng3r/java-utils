package de.sltest.io.fileSearch;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.slfw.io.filesearch.FileSearchParams;
import de.slfw.io.filesearch.FileSearcher;
import de.slfw.io.filesearch.FilesFoundListener;

import static de.sltest.TestConstants.*;

public class TestFileSearch implements FilesFoundListener{

	
	private FileSearcher fs;
	private  Path path;
	private List<File> foundFiles;
	private final long MAXPOOLSIZE = 10000;
	
	
	@Before
	public void init() {
		path = new File(Thread.currentThread().getContextClassLoader().getResource("testfolder_search").getFile()).toPath();
		foundFiles = new ArrayList<>();
		
	}
	
	
	@Test
	public void testSearchDirectoryFull() throws IOException {
		
		FileSearchParams fileSearchParams = new FileSearchParams.Builder(path, MAXPOOLSIZE).withRecursive(true).build();
		fs = new FileSearcher(fileSearchParams, this);
		fs.startSearching();
		assertEquals("Not the number of files found", NUMBEROFFILESIN_TESTFOLDER_SEARCH, foundFiles.size());
		
	}

	@Test
	public void testSearchDirectoryFirstDepth() throws IOException {
		
		FileSearchParams fileSearchParams = new FileSearchParams.Builder(path, MAXPOOLSIZE).withRecursive(false).build();
		fs = new FileSearcher(fileSearchParams, this);
		fs.startSearching();
		assertEquals("Not the number of files found", NUMBEROFFILESIN_TESTFOLDER_SEARCH_FIRSTLEVEL, foundFiles.size());
		
	}

	@Override
	public void foundFiles(List<File> files) {
		foundFiles.addAll(files);		
	}
	
	
}
