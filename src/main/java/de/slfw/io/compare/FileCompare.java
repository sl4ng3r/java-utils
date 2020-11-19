
package de.slfw.io.compare;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import de.slfw.io.file.FileOperator;

/**
 * 
 * <p>
 * A class to compare files and direcotries.
 * </p>
 *
 * @author Alex S.
 * 
 *
 */
public class FileCompare {
	/**
	 * Compares two files if they are the same.
	 * 
	 * @param file1 first file
	 * @param file2 second file
	 * @return returns true if the files are the same
	 * @throws IOException
	 */
	public static boolean compare(File file1, File file2, boolean checkContent) throws IOException {
		if (!file1.exists() || !file2.exists()) {
			return false;
		}
		if (file1.length() != file2.length())
			return false;

		if (checkContent) {
			FileInputStream in1 = new FileInputStream(file1);
			FileInputStream in2 = new FileInputStream(file2);
			System.out.println("blubb");
			for (long i = 0; i < file1.length(); i++) {
				if (in1.read() != in2.read())
					return false;
			}
		}
		return true;
	}

	public static List<FileCompareMismatchBean> compareDirectories2(String path1, String path2) throws IOException {
		List<FileCompareMismatchBean> mismatches = new ArrayList<FileCompareMismatchBean>();

		List<File> allFiles = FileOperator.getAllEntriesOfDirectory(path1, false, true);
		List<File> allFiles2 = FileOperator.getAllEntriesOfDirectory(path2, false, true);

		return Collections.emptyList();
	}

	/**
	 * Compares two directory. Also subidrectories have been considered.
	 * 
	 * @param path1 First directory
	 * @param path2 Second directory
	 * @return returns a vector of mismatches
	 * @throws IOException
	 */
	public static List<FileCompareMismatchBean> compareDirectories(String path1, String path2, boolean checkContent) throws IOException {

		// Der Name des Ordners soll bei dem Root Ordner keine Rolle spielen
		File dir1 = new File(path1);
		File dir2 = new File(path2);

		if (dir1.isDirectory() && dir2.isDirectory()) {

			List<FileCompareMismatchBean> mismatches = new ArrayList<FileCompareMismatchBean>();
			HashMap<String, Vector<File>> map1 = pufFilesInMapByRelativePaths(dir1, path1);
			HashMap<String, Vector<File>> map2 = pufFilesInMapByRelativePaths(dir2, path2);

			
			ArrayList<String> cacheList = new ArrayList<String>();
			Set<String> list1 = map1.keySet();
			Set<String> list2 = map2.keySet();
			
			
			ArrayList<String> matches = new ArrayList<String>();
			for (String key : map1.keySet()) {
				if (map2.containsKey(key)) {
					matches.add(key);
				}
			}
			//geht alle verzeichnisse durch die in beiden root Verzeichnissen gefunden wurden und vergleicht die Dateien
			for (String key : matches) {
				Vector<File> filesList1 = map1.get(key);
				Vector<File> filesList2 = map2.get(key);
				mismatches.addAll(getMismatchesForDirectory(filesList1, filesList2, checkContent));
			}
			//entfernt die matches aus den beiden mapps
			for (String key : matches) {
				map1.remove(key);
				map2.remove(key);
			}
			
			for(List<File> files : map1.values()) {
				for(File file : files) {
					FileCompareMismatchBean fileCompareMismatchBean = new FileCompareMismatchBean(file.getAbsolutePath(), "");
					fileCompareMismatchBean.setOneSideEmpty(true);
					mismatches.add(fileCompareMismatchBean);
				}
			}
			
			for(List<File> files : map2.values()) {
				for(File file : files) {
					FileCompareMismatchBean fileCompareMismatchBean = new FileCompareMismatchBean( "", file.getAbsolutePath());
					fileCompareMismatchBean.setOneSideEmpty(true);
					mismatches.add(fileCompareMismatchBean);
				}
			}
			
			return mismatches;
		} else
			return Collections.EMPTY_LIST;
	}

	private static List<FileCompareMismatchBean> getMismatchesForDirectory(List<File> list1, List<File> list2, boolean checkContent) throws IOException{
		
		List<FileCompareMismatchBean> mismatches = new ArrayList<FileCompareMismatchBean>();
		
		for (File file1 : list1) {
			File file2 = getFileByName(list2, file1.getName());
			if(file2 != null ) {
				if(!compare(file1, file2, checkContent)){
					mismatches.add(new FileCompareMismatchBean(file1.getAbsolutePath(), file2.getAbsolutePath()));
				}
			}
		}
		
		for (File file1 : list1) {
			File file2 = getFileByName(list2, file1.getName());
			if(file2 == null) {
				FileCompareMismatchBean fileCompareMismatchBean= new FileCompareMismatchBean(file1.getAbsolutePath(), "");
				fileCompareMismatchBean.setOneSideEmpty(true);
				mismatches.add(fileCompareMismatchBean);
			}
		}
		
		for (File file2 : list2) {
			File file1 = getFileByName(list1, file2.getName());
			if(file1 == null) {
				FileCompareMismatchBean fileCompareMismatchBean= new FileCompareMismatchBean("", file2.getAbsolutePath());
				fileCompareMismatchBean.setOneSideEmpty(true);
				mismatches.add(fileCompareMismatchBean);
			}
		}
		return mismatches;
	}
	
	private static void removeKeys(List<String> keysToRemove, HashMap<String, Vector<File>> mapWithKeys) {
		for (String s : keysToRemove) {
			mapWithKeys.remove(s);
		}
	}

	private static File getFileByName(List<File> files, String filename) {
		for (File f : files) {
			if (filename.equals(f.getName())) {
				return f;
			}
		}
		return null;
	}

	// Nur Dateien mit dem selben Namen!
	private static void cleanDirectorys(Vector<File> dir1files, Vector<File> dir2files,
			Vector<FileCompareMismatchBean> mismatches) {
		HashMap<String, File> map = new HashMap<String, File>();
		HashMap<String, File> map2 = new HashMap<String, File>();
		Vector<File> cache = new Vector<File>();

		for (File f : dir1files) {
			map.put(f.getName(), f);
		}

		for (File f : dir2files) {
			if (map.containsKey(f.getName())) {
				cache.add(f);
			} else {
				mismatches.add(new FileCompareMismatchBean("", f.getAbsolutePath()));
			}
		}
		dir2files.retainAll(cache);

		for (File f : dir2files) {
			map2.put(f.getName(), f);
		}

		cache.removeAllElements();

		for (File f : dir1files) {
			if (map2.containsKey(f.getName())) {
				cache.add(f);
			} else {
				mismatches.add(new FileCompareMismatchBean(f.getAbsolutePath(), ""));
			}
		}
		dir1files.retainAll(cache);
	}

	/**
	 * A dummy print out method to see the differences between the two directories.
	 * 
	 * @param missmatches
	 */
	public static void printMissmatches(List<FileCompareMismatchBean> missmatches) {
		System.out.println("Directory1 <---> Directory2");

		for (FileCompareMismatchBean bean : missmatches) {
			System.out.println(bean.getDirectory1() + " <---> " + bean.getDirectory2());
		}
	}

	private static HashMap<String, Vector<File>> pufFilesInMapByRelativePaths(File root, String path) {
		HashMap<String, Vector<File>> map = new HashMap<String, Vector<File>>();
		String absolutePath = root.getAbsolutePath();
		List<File> list = FileOperator.getAllFiles(path, true);

		for (File f : list) {
			
			String relativeFilePath = f.getAbsolutePath().replace(absolutePath, "");
			String relativeDirectoryPath = relativeFilePath.substring(0 ,relativeFilePath.lastIndexOf("\\"));
			if (map.containsKey(relativeDirectoryPath)) {
				map.get(relativeDirectoryPath).add(f);
			} else {
				map.put(relativeDirectoryPath, new Vector<File>());
				map.get(relativeDirectoryPath).add(f);
			}
		}
		return map;
	}
}
