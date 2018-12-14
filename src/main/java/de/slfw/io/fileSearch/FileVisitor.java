package de.slfw.io.fileSearch;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import javax.inject.Inject;

public class FileVisitor implements java.nio.file.FileVisitor<Path> {
	
	private final Path startDir;
	
	
	private FilePool filePool;
	private long visitedFileSize = 0;
	
	public FileVisitor(Path startDir, FilePool filePool) {
		super();
		this.startDir = startDir;
		this.filePool = filePool;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		boolean finishedSearch = Files.isSameFile(dir, startDir);
	    if (finishedSearch) {
	    	filePool.flush();
	        return FileVisitResult.TERMINATE;
	    }
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		filePool.addFile(file, attrs);
		visitedFileSize = visitedFileSize + attrs.size();
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	public long getVisitedFileSize() {
		return visitedFileSize;
	}
	
}
