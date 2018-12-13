package de.sltest.comparator;

import de.slfw.comparator.AbstractComparator;
import de.slfw.comparator.CompareHelper;

public class ComparatorFrontEnd extends AbstractComparator<MyTestObject> {


	public ComparatorFrontEnd(){
		super();
	}
	
	public ComparatorFrontEnd(String[] sortOrder, int mode){
		super(sortOrder, mode);
	}






	
	public int compare(MyTestObject obj1, MyTestObject obj2) {
		return CompareHelper.sortFrontOrEnd(getSortOrder(),obj1.getValue(), obj2.getValue(),getMode());
	}

	
	
	
}
