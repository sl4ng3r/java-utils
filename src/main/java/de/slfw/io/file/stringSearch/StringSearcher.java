package de.slfw.io.file.stringSearch;

import java.io.File;
import java.util.List;

import de.slfw.io.fileSearch.FileSearcher;
import de.slfw.io.fileSearch.FilesFoundListener;

public class StringSearcher implements FilesFoundListener{

	
	private FileSearcher fileSearcher;
	
	public StringSearcher(FileSearcher fileSearcher) {
		this.fileSearcher = fileSearcher;
	}






	@Override
	public void foundFiles(List<File> files) {
				
	}

}
