package de.slfw.io.file.stringsearch;

import java.io.File;
import java.util.List;

public class SearchContentInFilesProcessor {

	
	private List<File> files;
	private FileContentSearcher  stringSearcher;
	
	public List<File> search(SearchParams searchParams) {
		for(File file : files) {
			FileContentSearcher.searchContent(file, searchParams);//stringSearcher.search(searchParams);
		}
		return null;
	}
	
}
