package de.sltest.comparator;

import de.slfw.comparator.AbstractComparator;
import de.slfw.comparator.CompareHelper;

public class MyTestComparator extends AbstractComparator {

	
	
	
	
	public MyTestComparator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyTestComparator(String[] sortOrder, int mode) {
		super(sortOrder, mode);
		// TODO Auto-generated constructor stub
	}

	public int compare(Object obj1, Object obj2) {
		// TODO Auto-generated method stub
		return CompareHelper.sortFrontOrEnd(getSortOrder(), ((MyTestObject)obj1).getValue(), ((MyTestObject)obj2).getValue(), getMode());
	}

}
