package de.sltest.util;

import de.slfw.util.IOUtils;

public class TestIOUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Integer s = IOUtils.getOneRandom(1, 2, 3);
		System.out.println(s);

		
		String[] st = {"asdf", "ddd", "fff"};
		String str = IOUtils.getOneRandom(st);
		System.out.println(str);
		
		
		TestObj[] objects = {new TestObj("bla"), new TestObj("fu"), new TestObj("blafu")};
		TestObj obj = IOUtils.getOneRandom(objects);
		System.out.println(obj);
		
		
		
		
	}

}
