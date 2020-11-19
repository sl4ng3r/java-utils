package de.sltest.comparator;
import java.util.Collections;
import java.util.Vector;

import de.slfw.comparator.AbstractComparator;
import de.slfw.comparator.DummyComparator;
import de.slfw.comparator.ExceptionListener;

public class Testclass implements ExceptionListener {

	/**
	 * @param args
	 */
	public static void main(String[] args)  {
		
		Testclass t = new Testclass();
		t.start();
		
		
	}
	
	private void start(){
		
		
		Vector aVec = new Vector();	
		
		aVec.add(new MyTestObject("1","*"));
		aVec.add(new MyTestObject("2","37"));
		aVec.add(new MyTestObject("3","-5"));
		aVec.add(new MyTestObject("4","§OU"));
		aVec.add(new MyTestObject("5",null));
		aVec.add(new MyTestObject("6","x"));
		aVec.add(new MyTestObject("7","§$$$"));
		aVec.add(new MyTestObject("8","-9"));
		aVec.add(new MyTestObject("9","%"));
		aVec.add(new MyTestObject("10","+2"));
		aVec.add(new MyTestObject("11","2"));
		aVec.add(new MyTestObject("12","bla"));
		aVec.add(new MyTestObject("13","+2"));
		aVec.add(new MyTestObject("14","38"));
		aVec.add(new MyTestObject("15","3"));
		aVec.add(new MyTestObject("16","2"));
		aVec.add(new MyTestObject("17","-2"));
		aVec.add(new MyTestObject("18","-5"));
				
		printVector(aVec);
		
		DummyComparator com = new DummyComparator(AbstractComparator.SORTED,"getValue");
		com.registerListener(this);
		Collections.sort(aVec, com);
		
		
		printVector(aVec);
		System.out.println("_---_---_-----------------");
		
		//Collections.sort(aVec,new NormalSortComparator());
		printVector(aVec);
		
		String sortOrder[] = {"x", "-5","+2"};
		String sortOrder2[] = {"-9", "%"};
		
		//ComparatorFrontEnd comp = new ComparatorFrontEnd(sortOrder2, AbstractComparator.END|AbstractComparator.SORTED|AbstractComparator.DESC);
		ComparatorFrontEnd comp = new ComparatorFrontEnd();
		
		comp.setMode(AbstractComparator.ASC | AbstractComparator.END);
		
		
		comp.setSortOrder(sortOrder);
		
		Collections.sort(aVec, comp);
		printVector(aVec);
		comp.setMode(AbstractComparator.FRONT);
		comp.setSortOrder(sortOrder2);
		Collections.sort(aVec, comp);
		
		
		
		
		printVector(aVec);
		
		comp.setMode(comp.getMode()|AbstractComparator.REVERSE);
		Collections.sort(aVec, comp);
		printVector(aVec);
		
		//comp.setSortOrder(sortOrder);
		
		//comp.setMode(AbstractComparator.FRONT | AbstractComparator.DESC);
		//Collections.sort(aVec, comp);
		
		comp.setMode(AbstractComparator.SORTED);
		
		Collections.sort(aVec, comp);
		
		printVector(aVec);
		
		Collections.sort(aVec, comp);
		
		printVector(aVec);
		
		Collections.sort(aVec, comp);
		
		printVector(aVec);
		comp.setMode(AbstractComparator.DESC );
		Collections.sort(aVec, comp);
		
		printVector(aVec);
		
		comp.setMode( AbstractComparator.DESC );
		Collections.sort(aVec, comp);
		
		printVector(aVec);
		
		comp.setMode(AbstractComparator.DESC |AbstractComparator.REVERSE );
		
		Collections.sort(aVec, comp);
		
		printVector(aVec);
		
		comp.setMode(AbstractComparator.DESC);
		
		Collections.sort(aVec, comp);
		
		printVector(aVec);
		
		
		
		
		
		
		
		
	}
	
	
	private static void printVector(Vector<MyTestObject> vector){
		for(MyTestObject obj : vector){
			System.out.print(obj.getValue() + ", ");
		}
		System.out.println("");
		for(MyTestObject obj : vector){
			System.out.print(obj.getKey() + ", ");
		}
		System.out.println("\n");
	}


	public void fireException(Exception e) {
		e.printStackTrace();	
	}
}
