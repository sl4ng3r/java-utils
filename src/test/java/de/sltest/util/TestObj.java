package de.sltest.util;

public class TestObj {
	
	
	private String myString="";
	
	public TestObj(String myString) {
		this.myString = myString;
	}
	@Override
	public String toString() {
		return myString;
	}
}
