package de.slfw.comparator;



/**
 * 
 * <p>A dummy comparator with a reflection method call.</p>
 * <p>
 * To use this comparator you only have to specify the name of the method witch returns the values
 * you want to compare. It is easy to use but not as fast as an normal comparator. To get better performance
 * it is recommended to extend the AbstractComparator in an seperate class. 
 * </p>
 *
 *<blockquote>
 *<b>Example:</b><br>
 *<code>
 *{@link DummyComparator} comp = new {@link DummyComparator}({@link AbstractComparator}.SORTED , "getName");<br>
 *Collections.sort(comp);
 *<code>
 *</blockquote>
 * @author Alex S.
 * 
 */
public class DummyComparator extends AbstractComparator<Object>{

	
	
	private String methodName;
	private ExceptionListener listener = null;
	
	
	
	public DummyComparator(String methodName) {
		super();
		this.methodName = methodName;
	}

	public DummyComparator(int mode,String methodName) {
		super(mode);
		this.methodName = methodName;
	}

	public DummyComparator(String[] sortOrder, int mode,String methodName) {
		super(sortOrder, mode);
		this.methodName = methodName;
	}
	
	public void registerListener(ExceptionListener listener){
		this.listener = listener;
	}


	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	private synchronized String getValue(Object o){
		try {
			 java.lang.reflect.Method m = o.getClass().getMethod(methodName, null);
			 return (String)m.invoke(o, null);		
		} catch (Exception e) {
			if(listener != null){
				listener.fireException(e);
			}
		}
		return null;
	}
	
	public int compare(Object o1, Object o2) {		
		return CompareHelper.sortFrontOrEnd(getSortOrder(), getValue(o1) ,getValue(o2), getMode());
	}

	
	
}
