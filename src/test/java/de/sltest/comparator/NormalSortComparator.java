package de.sltest.comparator;

import java.util.Comparator;

import de.slfw.comparator.CompareHelper;

public class NormalSortComparator implements Comparator{

	
	
	public int compare(Object obj1, Object obj2) {
		return CompareHelper.normalSort((String)obj1, (String)obj2 );
	}

}
