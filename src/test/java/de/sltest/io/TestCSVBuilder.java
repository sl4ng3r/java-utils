package de.sltest.io;

import java.io.IOException;

import de.slfw.io.CSVBuilder;

public class TestCSVBuilder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CSVBuilder builder = new CSVBuilder();
		try {
			builder.loadFile("testfolder/test.csv");
			builder.createFile("testfolder/test2.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

}
