package de.slfw.comparator;

import java.util.Comparator;

/**
 * 
 * @author Alex S.
 *
 * Description:
 * <p>You need to extend this class to have a compatible Comparator for the Comparehelper.</p>
 *
 * @param <T>
 */
public abstract class AbstractComparator<T> implements Comparator<T>{
	
	
	

	public final static int END = 1;
    public final static int FRONT = 2;
    public final static int SORTED = 4;
    public final static int NONE = 8;
    public final static int ASC = 16;
    public final static int DESC = 32;
    public final static int REVERSE = 64;
    
    
    public final static String[] NULLARRAY = new String[0];
	
	private String sortOrder[];
	private int mode;
		
	/**
	 * If the default contructor is called, sorted is initialized with an NULLARRAY. Therefore 
	 * the Comparehelper will not sort the values. 
	 */
	public AbstractComparator(){	
    //Es wurde keine sortOrder übergeben. Sort Order wird ein null-Array zugewiesen
    //wodurch sortOrder (z.b. im Comparehelper) nicht beachtet wird
		sortOrder = NULLARRAY;
	}
	
	/**
	 * No sort Order is spezified.
	 * @param mode - witch mode you choose to sort
	 */
	 public AbstractComparator(int mode){
	   this.mode = mode;
	   sortOrder = NULLARRAY;
	 }
  
	/**
	 * @param sortOrder The sortOrder
	 * @param mode witch mode you choose to sort
	 */
	public  AbstractComparator(String[] sortOrder, int mode){
		this.sortOrder = sortOrder;
		this.mode = mode;
	}
	
	/**
	 * @return Returns the mode.
	 */
	public int getMode() {
		return mode;
	}
	/**
	 * @param mode The mode to set.
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}
	/**
	 * @return Returns the sortOrder.
	 */
	public String[] getSortOrder() {
		return sortOrder;
	}
	/**
	 * @param sortOrder The sortOrder to set.
	 */
	public void setSortOrder(String[] sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	
	
	
}
