package de.sltest.algo;

import de.slfw.algo.Node;

public class TestTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String test ="saf";
		
		TestObj obj1 = new TestObj();
		TestObj obj2 = new TestObj();
		
		Node<String> n = new Node<String>(test);
		System.out.println(n.getValue());
		
		Node<TestObj> n2 = new Node<TestObj>(obj1);
		Node<TestObj> n3 = new Node<TestObj>(obj2);
		System.out.println(n2.getValue());
		System.out.println(n3.getValue());
	}

}
