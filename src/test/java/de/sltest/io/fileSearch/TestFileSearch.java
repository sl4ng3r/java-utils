package de.sltest.io.fileSearch;

import de.slfw.io.file.filesearch.FileSearchParams;
import de.slfw.io.file.filesearch.FileSearcher;
import de.slfw.io.file.filesearch.FilesFoundListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static de.sltest.TestConstants.NUMBEROFFILESIN_TESTFOLDER_SEARCH;
import static de.sltest.TestConstants.NUMBEROFFILESIN_TESTFOLDER_SEARCH_FIRSTLEVEL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFileSearch implements FilesFoundListener{

	
	private FileSearcher fs;
	private  Path path;
	private List<File> foundFiles;
	private final long MAXPOOLSIZE = 10000;
	
	
	@BeforeEach
	public void init() {
		path = new File(Thread.currentThread().getContextClassLoader().getResource("testfolder_search").getFile()).toPath();
		foundFiles = new ArrayList<>();
		
	}
	
	
	@Test
	public void testSearchDirectoryFull() throws IOException {
		
		FileSearchParams fileSearchParams = new FileSearchParams.Builder(path, MAXPOOLSIZE).withRecursive(true).build();
		fs = new FileSearcher(fileSearchParams, this);
		fs.startSearching();
		assertEquals(NUMBEROFFILESIN_TESTFOLDER_SEARCH, foundFiles.size(),"Not the number of files found");
		
	}

	@Test
	public void testSearchDirectoryFirstDepth() throws IOException {
		
		FileSearchParams fileSearchParams = new FileSearchParams.Builder(path, MAXPOOLSIZE).withRecursive(false).build();
		fs = new FileSearcher(fileSearchParams, this);
		fs.startSearching();
		assertEquals( NUMBEROFFILESIN_TESTFOLDER_SEARCH_FIRSTLEVEL, foundFiles.size(),"Not the number of files found");
		
	}

	@Override
	public void foundFiles(List<File> files) {
		foundFiles.addAll(files);		
	}
	
	
}
