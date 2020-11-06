package de.sltest.io.file.stringsearch;

import de.slfw.io.file.stringsearch.FileContentSearchParams;
import de.slfw.io.file.stringsearch.FileContentSearcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

public class TestStringSearch {

	private Path searchDirectory;
	private FileContentSearcher stringSearcher;
	private final static long POOLSIZEBYTES = 10000;
	private final static String searchString = "theSearchString";

	@BeforeEach
	public void initialize() {
		 searchDirectory = new File(
					Thread.currentThread().getContextClassLoader().getResource("testfolder_search").getFile()).toPath();
//		FileSearcher fileSearcher = new FileSearcher(searchDirectory, POOLSIZEBYTES);	
//		
//		stringSearcher = new StringSearcher(fileSearcher);
	
	}

	@Test
	public void testStringSearch() {
		
		FileContentSearchParams searchParams = FileContentSearchParams.builder().withCaseSensitive(false).withSearchString(searchString).build();
		//stringSearcher.startSearch(searchParams);
	}

}
