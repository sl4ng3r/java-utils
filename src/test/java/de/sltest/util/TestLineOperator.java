package de.sltest.util;

import de.slfw.util.LineOperator;

public class TestLineOperator {

	
	public static void main(String[] args) {
		LineOperator<String> o = new LineOperator<String>();
		o.addLine("bla");
		o.addLine("fu");
		
		System.out.println(o.getLinePoll());
		System.out.println(o.getLinePoll());
	}
}
