package de.sltest.util;

import java.io.File;
import java.io.IOException;

import de.slfw.util.Reflect;

public class TestReflect {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		try {
			Reflect.dummyPrint("FileOperator", Reflect.getAll("de.slfw.io.file.FileOperator", true,"|",true));
			//System.out.println(Reflect.getClassHeader("de.slfw.comparator.DummyComparator"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(Reflect.getPackageNameFromJavaFile(new File("src/main/de/slfw/io/FileCompare.java")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
