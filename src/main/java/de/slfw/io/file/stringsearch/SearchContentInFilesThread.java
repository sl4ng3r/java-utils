package de.slfw.io.file.stringsearch;

import java.io.File;
import java.util.List;

public class SearchContentInFilesThread  implements Runnable{

	private List<File> files;
	private FileContentSearchParams searchParams;
	private List<File> results;
	
	public SearchContentInFilesThread(final List<File> files, FileContentSearchParams searchParams, List<File> results) {
		super();
		this.files = files;
		this.searchParams = searchParams;
		this.results = results;
	}


	
//	public List<File> search(FileContentSearchParams searchParams) {
//		for(File file : files) {
//			FileContentSearcher.searchContent(file, searchParams);//stringSearcher.search(searchParams);
//		}
//		return null;
//	}

	@Override
	public void run() {
		for(File file : files) {
			if(FileContentSearcher.searchContent(file, searchParams)) {
				results.add(file);
			}
		}
	}

	
}
