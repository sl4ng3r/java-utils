package de.slfw.io.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import de.slfw.util.LineOperator;

/**
 * 
 * <p>
 * Different file operations.
 * </p>
 *
 * @author Alex S.
 * 
 *
 */
public class FileOperator {

	private static Vector<FileOperationListener> listeners = new Vector<FileOperationListener>();
	private static List<PercentOfProcessingListener> processListeners = new ArrayList<PercentOfProcessingListener>();

	private static final int NOLINES = -1;

	private static List<String> listAll(String path, ArrayList<String> filenames) {
		File[] files = getFileList(path);
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory()) {
					listAll(f.getAbsolutePath(), filenames);
				}
				filenames.add(f.getName());
			}
			return filenames;
		} else
			return Collections.emptyList();
	}

	public static String getFileExtention(File file) {
		if (file != null) {
			String filePath = file.getAbsolutePath();
			return filePath.substring(filePath.lastIndexOf(".") + 1);
		}
		return null;
	}

	public static String getFileExtention(String path) {
		if (path != null && path.lastIndexOf(".") != -1) {
			return path.substring(path.lastIndexOf(".") + 1);
		}
		return null;
	}
	
	public static boolean hasFileExtention(File file, String extention) {
		return extention.equals(getFileExtention(file));
	}

	/**
	 * Creates a new file at <b>path</b> and fills it with the input string.
	 * 
	 * @param path  the filename with the directory
	 * @param input the string to write
	 * @return the new file
	 * @throws IOException
	 */
	public static File createNewFile(String path, String input) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();
		FileWriter fw = new FileWriter(f);
		fw.write(input);
		fw.flush();
		return f;
	}

	/**
	 * Creates a new file at <b>path</b> and fills it with the input string.
	 * 
	 * @param path  the filename with the directory
	 * @param lines for each line in the {@link LineOperator} a new line is written
	 *              in the file
	 * @return the new file
	 * @throws IOException
	 */
	public static File createNewFile(String path, LineOperator<String> lines) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		for (String s : lines) {
			bw.write(s);
			bw.newLine();
		}
		bw.flush();
		return f;
	}

	/**
	 * Lists all file names and directory names in a list of strings.
	 * 
	 * @param path Directory path
	 * @return the names of directories and files
	 */
	public static List<String> listAll(String path) {
		ArrayList<String> list = new ArrayList<String>();
		return listAll(path, list);
	}

	/**
	 * Returns all files with the spezified extention. An example for an extention
	 * is ".txt".
	 * 
	 * @param path      the directory
	 * @param extention the extention
	 * @return a {@link List} of {@link File}s
	 */
	public static List<File> getAllFilesWithExtention(String path, String extention) {
		ArrayList<File> list = new ArrayList<File>();
		for (File f : getAllFiles(path, new ArrayList<File>(), false)) {
			String name = f.getName();
			if (name.length() > extention.length()) {
				name = name.substring(name.length() - extention.length());

				if (extention.equals(name)) {
					list.add(f);
				}
			}
		}
		return list;
	}

	/**
	 * Lists only the files in the directory and all subdirectories.
	 * 
	 * @param path Directory path
	 * @return List of filenames
	 */
	public static List<String> listAllFiles(String path) {
		ArrayList<String> list = new ArrayList<String>();
		for (File f : getAllFiles(path, new ArrayList<File>(), false)) {
			list.add(f.getName());
		}
		return list;
	}

	/**
	 * Lists only files only in the directory but <b>not</b> in the subdirectories.
	 * 
	 * @param path
	 * @return List of filenames
	 */
	public static List<String> listAllFilesInDirectory(String path) {
		ArrayList<String> filenames = new ArrayList<String>();
		File[] files = getFileList(path);
		if (files != null) {
			for (File f : files) {
				if (f.isFile())
					filenames.add(f.getName());
			}
			return filenames;
		} else
			return Collections.emptyList();
	}

	public static List<File> getAllEntriesOfDirectory(String directory, boolean recursive, boolean includeDirectories) {
		if (recursive) {
			ArrayList<File> files = new ArrayList<File>();
			return getAllFiles(directory, files, includeDirectories);
		} else {
			return getAllEntriesInDirectory(directory, includeDirectories);
		}
	}

	/**
	 * Returns all files in the directory and the subdirectory. No directories are
	 * returned.
	 * 
	 * @param directory the root directory
	 * @param recursive If you want all Files in all sub directories set recursive
	 *                  true
	 * @return List of files
	 */
	public static List<File> getAllFiles(String directory, boolean recursive) {
		return getAllEntriesOfDirectory(directory, recursive, false);
	}

	private static List<File> getAllFiles(String directory, ArrayList<File> files, boolean includeDirectories) {
		File[] filesInDir = getFileList(directory);
		if (filesInDir != null) {
			for (File f : filesInDir) {
				if (f.isDirectory()) {
					if (includeDirectories) {
						files.add(f);
					}
					getAllFiles(f.getAbsolutePath(), files, includeDirectories);
				} else
					files.add(f);
			}
			return files;
		} else
			return Collections.emptyList();
	}

	private static List<File> getAllEntriesInDirectory(String directory, boolean includeDirectories) {
		ArrayList<File> files = new ArrayList<File>();
		File[] filesInDir = getFileList(directory);
		if (filesInDir != null) {
			for (File f : filesInDir) {
				if (f.isFile()) {
					files.add(f);
				} else if (includeDirectories && f.isDirectory()) {
					files.add(f);
				}
			}
			return files;
		} else
			return Collections.emptyList();
	}

	private static List<File> getAllFilesInDirectory(String directory) {
		return getAllEntriesInDirectory(directory, false);
	}

	/**
	 * Lists all the filenames without the extention. <blockquote> Example:<br>
	 * myTextfile.txt --> myTextfile </blockquote>
	 * 
	 * @param list
	 * @return A list of the filenames
	 */
	public static List<String> getOnlyFileNames(List<String> list) {
		ArrayList<String> returnList = new ArrayList<String>();
		for (String f : list) {
			returnList.add(getOnlyFileName(f));
		}
		return returnList;
	}

	private static File[] getFileList(String path) {
		File dir = new File(path);
		if (dir.exists()) {
			return dir.listFiles();
		}
		return null;
	}

	public static boolean numberFiles(String directory, boolean recursive) {
		List<File> files = getAllFiles(directory, recursive);
		boolean success = true;
		for (int i = 1; i <= files.size(); i++) {
			File f = files.get(i - 1);
			if (renameFileName(f, String.valueOf(i)) == false) {
				success = false;
			}
		}
		return success;
	}

	/**
	 * Returns the filename without the extention.
	 * 
	 * @param filenameWithExtention
	 * @return Returns a filename without the extention
	 */
	public static String getOnlyFileName(String filenameWithExtention) {
		int index = filenameWithExtention.indexOf(".");
		while (filenameWithExtention.indexOf(".", index + 1) != -1) {
			index = filenameWithExtention.indexOf(".", index + 1);
		}
		return index != -1 ? filenameWithExtention.substring(0, index) : filenameWithExtention;
	}

	public static boolean renameFileName(File file, String newFilename) {
		String filename = file.getName();
		String extention = filename.substring(getOnlyFileName(filename).length());
		return file
				.renameTo(new File(file.getParentFile().getAbsolutePath() + File.separator + newFilename + extention));
	}

	public static boolean renameExtention(File file, String newExtention) {
		return file.renameTo(new File(file.getParentFile().getAbsolutePath() + File.separator
				+ getOnlyFileName(file.getName()) + "." + newExtention));
	}

	/**
	 * Changes the extentions for all files within an directory or additional in all
	 * subdirectories.
	 * 
	 * @param path         Directory path
	 * @param newExtention The new Extention
	 * @param recursive    Look in subdirectories
	 * @return If operation was successful
	 */
	public static boolean renameExtentions(String path, String newExtention, boolean recursive) {
		File[] files = getFileList(path);
		boolean ok = true;
		if (files != null) {
			for (File f : files) {
				if (recursive) {
					if (f.isDirectory()) {
						renameExtentions(f.getAbsolutePath(), newExtention, recursive);
					}
				}
				if (f.isFile()) {
					if (!renameExtention(f, newExtention)) {
						ok = false;
					}
				}
			}
			return ok;
		}
		return false;
	}

	/**
	 * Reads countByte bytes of an file.
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @param countByte  amount of bytes
	 * @throws IOException
	 */
	public static void readFileByBytes(File inputFile, File outputFile, long countByte) throws IOException {
		readFileByBytes(inputFile, outputFile, 1, countByte);
	}

	/**
	 * Reads countByte bytes of an file starting at startbyte.
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @param startbyte
	 * @param countByte
	 * @throws IOException
	 */
	public static void readFileByBytes(File inputFile, File outputFile, long startbyte, long countByte)
			throws IOException {
		FileInputStream in = new FileInputStream(inputFile);
		FileOutputStream out = new FileOutputStream(outputFile);
		if (countByte > inputFile.length())
			countByte = inputFile.length();

		long toRead = countByte - in.skip(startbyte - 1);
		for (long i = 0; i < toRead; i++) {
			out.write(in.read());
		}
	}

	private static void getAllLinesWithString(File inputFile, File outputFile, String inputString, long prozentcalc,
			long lines, FileOperationListener listener) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));

		long proz = prozentcalc;
		long timebefore = System.currentTimeMillis();
		long count = 0;
		int i = 1;
		String line;

		boolean goOn = true;

		while ((line = in.readLine()) != null && goOn) {
			if (lines != NOLINES) {
				if (count < lines) {
					if (line.indexOf(inputString) != -1) {
						out.write(line);
						out.newLine();
						count++;
					}
				} else
					goOn = false;
			} else {
				if (line.indexOf(inputString) != -1) {
					out.write(line);
					out.newLine();
				}
				count = count + line.length() + 2;
			}
			if (count >= proz) {
				fireEvent(String.valueOf(i * 10), "%", listener);
				proz = proz + prozentcalc;
				i++;
			}
		}
		out.flush();
		if (i < 10)
			fireEvent("100", "%", listener);
		fireEvent(String.valueOf((System.currentTimeMillis() - timebefore) / 1000), "Seconds", listener);
	}

	/**
	 * Reads all lines in a file with the spezified string.
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @param inputString The searchable text
	 * @param listener    The listener is used to get the % finished of the
	 *                    operation
	 * @throws IOException
	 */
	public static void getAllLinesWithString(File inputFile, File outputFile, String inputString,
			FileOperationListener listener) throws IOException {
		long prozentcalc = inputFile.length() / 10;
		getAllLinesWithString(inputFile, outputFile, inputString, prozentcalc, NOLINES, listener);
	}

	/**
	 * Reads a number of lines in a file with the spezified string
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @param inputString The searchable text
	 * @param lines       The number of lines to read
	 * @param listener    The listener is used to get the % finished of the
	 *                    operation
	 * @throws IOException
	 */
	public static void getNumberOfLinesWithString(File inputFile, File outputFile, String inputString, long lines,
			FileOperationListener listener) throws IOException {
		long prozentcalc = lines / 10;
		getAllLinesWithString(inputFile, outputFile, inputString, prozentcalc, lines, listener);
	}

	public static List<FileSearchResult> searchFilesWithContainingString(List<File> files, String searchString,
																		 boolean caseSensitive, Integer percentDistance) throws IOException {
		List<FileSearchResult> fileSearchResults = new ArrayList<FileSearchResult>();

		Integer amountOfFiles = files.size();
		Integer counter = 0;
		Integer percent = 0;
		informListeners(percent);
		Integer amountForTenPercent = amountOfFiles * percentDistance / 100; // 104 --> 10 --> 4 fehlen

		for (File f : files) {
			counter++;
			if (counter >= amountForTenPercent) {
				percent = percent + percentDistance;
				if (percent < 100) {
					informListeners(percent);
				}
				counter = 0;
			}

			if (searchIfStringInFile(f, searchString, caseSensitive)) {
				fileSearchResults.add(new FileSearchResult.Builder().setPath(f.getAbsolutePath()).build());
			}
		}
		informListeners(100);
		return fileSearchResults;
	}

	public static List<FileSearchResult> searchFilesWithContainingString(File directory, String searchString,
			boolean caseSensitive, Integer percentDistance) throws IOException {
		return searchFilesWithContainingString(directory.getAbsolutePath(), searchString, caseSensitive,
				percentDistance);
	}

	public static List<FileSearchResult> searchFilesWithContainingString(String directory, String searchString,
			boolean caseSensitive, Integer percentDistance) throws IOException {
		List<File> files = getAllFiles(directory, true);
		return searchFilesWithContainingString(files, searchString, caseSensitive, percentDistance);
	}

	private static void informListeners(Integer percent) {
		for (PercentOfProcessingListener listeners : processListeners) {
			listeners.firePercent(percent);
		}
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

	/**
	 * An output method. Can be used with the file operations for example.
	 * 
	 * @param list
	 */
	public static void printList(List list) {
		for (Object entry : list) {
			System.out.println(entry);
		}
	}

	private static synchronized void fireEvent(String msg, String type, FileOperationListener listener) {
		listener.fire(msg, type);
	}

	public static void addPercentListener(PercentOfProcessingListener listener) {
		processListeners.add(listener);
	}

	/**
	 * Adds an listener.
	 * 
	 * @param listener
	 */
	public static void addListener(FileOperationListener listener) {
		listeners.add(listener);
	}

	public static void removeListener(FileOperationListener listener) {
		listeners.remove(listener);
	}
}
