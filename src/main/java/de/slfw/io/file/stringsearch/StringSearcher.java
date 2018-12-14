package de.slfw.io.file.stringsearch;

import java.io.File;
import java.util.List;

import de.slfw.io.filesearch.FileSearcher;
import de.slfw.io.filesearch.FilesFoundListener;

public class StringSearcher implements FilesFoundListener{

	
	private FileSearcher fileSearcher;
	private SearchParams searchParams;
	
	public StringSearcher(FileSearcher fileSearcher, SearchParams searchParams) {
		this.searchParams = searchParams;
		this.fileSearcher = fileSearcher;
		this.fileSearcher.setFilesFoundListener(this);
	}


	@Override
	public void foundFiles(List<File> files) {
		
	}


	public void startSearch() {
		//fileSearcher.startSearching();
		
	}

	public SearchParams getSearchParams() {
		return searchParams;
	}
	
	
}
