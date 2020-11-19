package de.slfw.io.file.filesearch;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class FileVisitorImpl implements FileVisitor<Path> {
	
	private final Path startDir;
	
	
	private AbstractFilePool filePool;

	
	public FileVisitorImpl(Path startDir, AbstractFilePool filePool) {
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
		if(!attrs.isDirectory())
		{
			filePool.addFile(file, attrs);
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	
	
}
