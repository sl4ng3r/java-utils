package de.slfw.io.file.stringsearch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.slfw.io.file.filesearch.FileSearchParams;
import de.slfw.io.file.filesearch.FileSearcher;
import de.slfw.io.file.filesearch.FilesFoundListener;

public class FileProcessorManager {

	public void startSearchForContent(FileSearchParams fileSearchParams,
			FileContentSearchParams fileContentSearchParams) throws IOException {
		List<File> results = new ArrayList<>();
		ExecutorService executor = Executors.newFixedThreadPool(10);

		FilesFoundListener filesFoundListener = files -> executor
				.execute(new SearchContentInFilesThread(files, fileContentSearchParams, results));

//		new FilesFoundListener() {
//			@Override
//			public void foundFiles(List<File> files) {
//				SearchContentInFilesThread searchContentInFilesThread =
//				executor.execute( new SearchContentInFilesThread(files,fileContentSearchParams, results));
//
//			}
//		};
		FileSearcher fs = new FileSearcher(fileSearchParams, filesFoundListener);
		fs.startSearching();
		
	}
}
