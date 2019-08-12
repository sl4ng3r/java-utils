package de.slfw.io.file.stringsearch;

import java.io.File;
import java.util.List;

import de.bdk.tools.filereader.FileConvertServiceImpl;
import de.slfw.io.FileOperator;
import de.slfw.io.filesearch.FileSearcher;
import de.slfw.io.filesearch.FilesFoundListener;

public class FileContentSearcher {

	private FileConvertServiceImpl fileConvertServiceImpl;

	public FileContentSearcher() {
		fileConvertServiceImpl = new FileConvertServiceImpl();
	}

	public static boolean searchContent(File f, SearchParams searchParams) {
		
		if(searchParams != null && !FileOperator.hasFileExtention(f, searchParams.getExtention()))
			return false;
		
		boolean contains = FileConvertServiceImpl.contains(f.toPath(), searchParams.getSearchString(), searchParams.isCaseSensitive());
		
		return contains;
	}

}
