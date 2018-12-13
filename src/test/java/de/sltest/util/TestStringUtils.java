package de.sltest.util;

import java.util.List;

import de.slfw.util.StringUtil;

public class TestStringUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> strings = StringUtil.seperate("bla,fu,,eee,,d,adsf", ",");
		for(String s:strings){
			System.out.println("Ausgabe:"+s);
		}

	}

}
