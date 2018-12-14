package de.sltest.io.file.stringsearch;

import java.io.File;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

import de.slfw.io.file.stringsearch.SearchParams;
import de.slfw.io.file.stringsearch.StringSearcher;
import de.slfw.io.filesearch.FileSearcher;

public class TestStringSearch {

	private Path searchDirectory;
	private StringSearcher stringSearcher;
	private final static long POOLSIZEBYTES = 10000;
	private final static String searchString = "theSearchString";

	@Before
	public void initialize() {
		 searchDirectory = new File(
					Thread.currentThread().getContextClassLoader().getResource("testfolder_search").getFile()).toPath();
//		FileSearcher fileSearcher = new FileSearcher(searchDirectory, POOLSIZEBYTES);	
//		
//		stringSearcher = new StringSearcher(fileSearcher);
	
	}

	@Test
	public void testStringSearch() {
		
		SearchParams searchParams = SearchParams.builder().withCaseSensitive(false).withSearchString(searchString).build();
		//stringSearcher.startSearch(searchParams);
	}

}
