package de.slfw.io.file.stringsearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import de.slfw.io.file.FileOperator;

public class FileContentSearcher {


	public static boolean searchContent(File f, FileContentSearchParams searchParams) {
		boolean contains = false;
		
		if(searchParams != null && !FileOperator.hasFileExtention(f, searchParams.getExtention()))
			return false;
		
		try {
			contains = searchIfStringInFile(f, searchParams.getSearchString(), searchParams.isCaseSensitive());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		boolean contains = FileConvertServiceImpl.contains(f.toPath(), searchParams.getSearchString(), searchParams.isCaseSensitive());
	

		return contains;
	}
	

	public static boolean searchIfStringInFile(File f, String searchString, boolean caseSensitive) throws IOException {
		FileInputStream fis = new FileInputStream(f);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {

			String line;

			if (!caseSensitive) {
				searchString = searchString.toUpperCase();
			}

			while ((line = br.readLine()) != null) {
				if (!caseSensitive) {
					line = line.toUpperCase();
				}
				if (line.contains(searchString)) {
					return true;
				}
			}
		}
		return false;
	}
}
