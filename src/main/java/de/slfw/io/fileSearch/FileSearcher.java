package de.slfw.io.fileSearch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileSearcher implements MaxEntriesOfPoolReachedListener {
	
	
	private Path searchDirectory;
	private FilesFoundListener filesFoundListener;
	private long filePackageSize;
	
	
	
	public FileSearcher( Path searchDirectory, FilesFoundListener filesFoundListener, long filePackageSize) {
		super();
		this.filesFoundListener = filesFoundListener;
		this.filePackageSize = filePackageSize;
	}


	public void search() throws IOException {
		
		
		FilePool filePool = new FilePool(this, filePackageSize);
		FileVisitor fileVisitor = new FileVisitor(searchDirectory, filePool);
		Files.walkFileTree(searchDirectory, fileVisitor);
		
		
	}

	@Override
	public void clearPool(List<File> cachedFiles) {
		filesFoundListener.foundFiles(cachedFiles);
		//System.out.println(cachedFiles);
		
	}
	
	
	public void setSearchDirectory(Path searchDirectory) {
		this.searchDirectory = searchDirectory;
	}

}
