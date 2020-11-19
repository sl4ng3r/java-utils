package de.slfw.io.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import de.slfw.util.StringUtil;

/**
 * 
 * <p>Generates a CSV File</p>
 *
 * @author Alex S.
 * 
 *
 */
public class CSVBuilder {

	
	
	private final String DEFAULTSEPERATOR = ";";
	private final String DEFAULTFILEPATH = "myCsv.csv";
	private StringBuffer buffer = new StringBuffer();
	boolean onlyOneOfEachLine;
	
	
	String seperator;
	String filepath;
	Collection<String> lines = null;
	
	
	/**
	 * The onlyOneOfEachLine is set false. Every line is added to the csv file.
	 */
	public CSVBuilder() {
		this(false);
	}
	
	/**
	 * Set true if you want only different lines in your csv file.
	 * @param onlyOneOfEachLine set true to add only different lines to the csv file
	 */
	public CSVBuilder(boolean onlyOneOfEachLine){
		this.onlyOneOfEachLine = onlyOneOfEachLine;		
		seperator = DEFAULTSEPERATOR;
		filepath = DEFAULTFILEPATH;
		initialLineBuffer();
	}
	
	/**
	 * In this constructor you can set the filePath where you want to save your csv file and the
	 * sperator to seperate the filds in the csv file. Default filepath is "myCsv.csv" and default 
	 * seperator is ";".
	 * @param filePath the filepath (where to generate the csv file)
	 * @param seperator the sperator to seperate the fields of a line
	 * @param onlyOneOfEachLine set true to save only different lines
	 */
	public CSVBuilder(String filePath,String seperator,boolean onlyOneOfEachLine){
		setFilepath(filePath);
		setSeperator(seperator);
		this.onlyOneOfEachLine = onlyOneOfEachLine;
		initialLineBuffer();
	}
	
	
	/**
	 * Adds a line to the seperator. For every refered object the ".toString" method is called and 
	 * is added to the line seperated by the set seperator. 
	 * <br>
	 * Example:
	 * <code>
	 * csvBuilder.addLine(o1.getName1, o1.getName2, o2.getRights);
	 * </code>
	 * This example adds a line with three fields to the csv file.
	 * @param strings an undefined amount of strings that were added to a line
	 */
	public void addLine(Object... strings){
		buffer.setLength(0);
		for(Object o : strings){
			buffer.append(o.toString());
			buffer.append(seperator);
		}
		buffer.setLength(buffer.length()-seperator.length());
		lines.add(buffer.toString());
	}
	
	

	private void createFile(boolean append) throws IOException{
		BufferedWriter fw = new BufferedWriter(new FileWriter(new File(getFilepath()),append));
		for(String l : lines){
			fw.write(l);
			fw.newLine();
		}
		fw.flush();
		fw.close();
	}
	
	/**
	 * Returns all lines which were added to the CSVBuilder. 
	 * @return all lines which were added
	 */
	public Collection<String> getAllLines(){
		return lines;
	}
	
	/**
	 * Loads a file into the CSVBuilder with an default seperator ";".
	 * @param csvFile the csv file to load
	 * @throws IOException
	 */
	public void loadFile(String csvFile) throws IOException{
		loadFile(csvFile, DEFAULTSEPERATOR);
	}

	/**
	 * Loads a file with a specified seperator into the CSVBuilder.
	 * @param csvFile the csv file to load
	 * @param seperator the seperator to seperate a fields in a line
	 * @throws IOException
	 */
	public void loadFile(String csvFile, String seperator) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(csvFile));
		String line;
		while((line = reader.readLine())!= null){
			List<String> tokens = StringUtil.seperate(line, seperator);
			addLine(tokens.toArray());
		}
		reader.close();
	}
	
	private void initialLineBuffer(){
		if(onlyOneOfEachLine){
			lines = new LinkedHashSet<String>();
		}
		else
		{
			lines = new ArrayList<String>();
		}
	}
	
	/**
	 * Creates a new file with the lines wich where added to the CSVBuilder.
	 * @throws IOException
	 */
	public void createFile() throws IOException{
		createFile(false);
	}

	/**
	 * Creates a new file at specified path with the lines  wich where added to the CSVBuilder.
	 * <br>
	 * Example:
	 * <code>
	 * csvBuilder.createFile("C:/test/myCsv.csv");
	 * </code>
	 * @param filePath the patch of the csv file
	 * @throws IOException
	 */
	public void createFile(String filePath) throws IOException{
		setFilepath(filePath);
		createFile();
	}
	
	/**
	 * Appends a csv file with the lines added in the CSVBuilder
	 * @throws IOException
	 */
	public void appendFile() throws IOException{
		createFile(true);
	}
	
	/**
	 * Appends a csv file at sepcified path with the lines added in the CSVBuilder.
	 * <br>
	 * Example:
	 * <code>
	 * csvBuilder.appendFile("C:/test/myCsv.csv");
	 * </code>
	 * @param filePath the path of the csv file
	 * @throws IOException
	 */
	public void appendFile(String filePath) throws IOException{
		setFilepath(filePath);
		appendFile();
	}
	
	/**
	 * Removes all lines which were added to the CSVBuilder. You can set onlyOneOfEachLine true
	 * if you want to add only different lines and not the same line twice.
	 * @param onlyOneOfEachLine set true to add only different lines to the csv file
	 */
	public void clearCsvGenerator(boolean onlyOneOfEachLine){
		this.onlyOneOfEachLine = onlyOneOfEachLine;
		initialLineBuffer();
	}

	/**
	 * Returns the size of 
	 * @return returs the amount of lines which were added. 
	 */
	public int size(){
		return lines.size();
	}
	
	/**
	 * @return Returns the filepath.
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * @param filepath The filepath to set.
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * @return Returns the seperator.
	 */
	public String getSeperator() {
		return seperator;
	}

	/**
	 * @param seperator The seperator to set.
	 */
	public void setSeperator(String seperator) {
		this.seperator = seperator;
	}
	
	
	
}
