package de.sltest.io;

import java.io.File;

import de.slfw.io.FileOperator;

public class TestFileOperatior {

	public static void main(String[] args) {

		String fileExtention = FileOperator.getFileExtention(new File("C:\\blubb\\bla.xml"));
		System.out.println(fileExtention);
	}

}
