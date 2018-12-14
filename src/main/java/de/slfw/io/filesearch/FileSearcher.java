package de.slfw.io.filesearch;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

public class FileSearcher implements MaxEntriesOfPoolReachedListener {
	
	

	private FilesFoundListener filesFoundListener;
	private FileSearchParams fileSearchParams;
	
	
	


	


	public FileSearcher(FileSearchParams fileSearchParams, FilesFoundListener filesFoundListener) {
		super();
		this.fileSearchParams = fileSearchParams;
		this.filesFoundListener = filesFoundListener;
	}



	public void startSearching() throws IOException {		
		FilePool filePool = new FilePool(this, fileSearchParams.getFilePackageSize());
		FileVisitorImpl fileVisitor = new FileVisitorImpl(fileSearchParams.getSearchDirectory(), filePool);
		Boolean recursive = fileSearchParams.getRecursive();
		if(recursive) {
			Files.walkFileTree(fileSearchParams.getSearchDirectory(), fileVisitor);
		} 
		else {
			Files.walkFileTree(fileSearchParams.getSearchDirectory(),EnumSet.noneOf(FileVisitOption.class), 1, fileVisitor);
		}
	}

	@Override
	public void clearPool(List<File> cachedFiles) {
		filesFoundListener.foundFiles(cachedFiles);
	}
	
	
	public FileSearchParams getFileSearchParams() {
		return fileSearchParams;
	}
	
	public void setFileSearchParams(FileSearchParams fileSearchParams) {
		this.fileSearchParams = fileSearchParams;
	}

	public void setFilesFoundListener(FilesFoundListener filesFoundListener) {
		this.filesFoundListener = filesFoundListener;
	}
	
}
