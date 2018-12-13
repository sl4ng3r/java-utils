package de.slfw.io.file.stringSearch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWalker {

	
	private Path rootDirectory;
	private WalkingCompleteListener walkingCompleteListener;
	
	public FileWalker(Path rootDirectory) {
		super();
		this.rootDirectory = rootDirectory;
	}

	
	
	
	
	public void startWalking() {
		try {
			
			FileVisitor fileVisitor = new FileVisitor(rootDirectory);
			Files.walkFileTree(rootDirectory, new FileVisitor( rootDirectory));
			
			
			
			WalkingCompleteResult walkingCompleteResult = new WalkingCompleteResult();
			walkingCompleteResult.setCompleteFileSize(fileVisitor.getVisitedFileSize());
			walkingCompleteListener.complete(walkingCompleteResult);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	




	
	
}
