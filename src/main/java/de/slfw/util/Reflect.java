package de.slfw.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>A class to analyse different classes</p>
 *
 * @author Alex S.
 * 
 *
 */
public class Reflect {
	
	/**
	 * Returns class header, attributes, constructors and methods.
	 * @param cl the class to analyse
	 * @param onlyPublic set true to get only the public methods
	 * @param seperator the seperator of the output strings
	 * @param loadExtends set true to load all which was extended
	 * @return a {@link List} of strings with the class header, attributes, constructors and methods
	 * @throws ClassNotFoundException
	 */
	public static List<String> getAll(Class cl, boolean onlyPublic, String seperator,boolean loadExtends){
		ArrayList<String> list = new ArrayList<String>();
		list.add(getClassHeader(cl));
		list.addAll(getAttributes(cl, onlyPublic, seperator, loadExtends));
		list.addAll(getConstructors(cl, onlyPublic, seperator, loadExtends));
		list.addAll(getMethods(cl, onlyPublic, seperator, loadExtends));
		return list;
	}
	
	/**
	 * Returns class header, attributes, constructors and methods.
	 * @param className the classname as String
	 * @param onlyPublic set true to get only the public methods
	 * @param seperator the seperator of the output strings
	 * @param loadExtends set true to load all which was extended
	 * @return a {@link List} of strings with the class header, attributes, constructors and methods
	 * @throws ClassNotFoundException
	 */
	public static List<String> getAll(String className, boolean onlyPublic, String seperator,boolean loadExtends) throws ClassNotFoundException{
		ArrayList<String> list = new ArrayList<String>();
		list.add(getClassHeader(className));
		list.addAll(getAttributes(className, onlyPublic, seperator,loadExtends));
		list.addAll(getConstructors(className, onlyPublic, seperator, loadExtends));
		list.addAll(getMethods(className, onlyPublic, seperator, loadExtends));
		return list;
	}
	
	/**
	 * Returns all constructors of a class.
	 * @param cl the class to analyse
	 * @param seperator the seperator of the output strings
	 * @param onlyPublic set true to get only the public methods
	 * @param loadExtends set true to load all constructors which were extended
	 * @return a {@link List} with constructors
	 */
	public static List<String> getConstructors(Class cl, boolean onlyPublic, String seperator, boolean loadExtends){
		ArrayList<String> list  = new ArrayList<String>();
		Constructor[] constructors;
		
		
		if(loadExtends){
			constructors = cl.getConstructors();
		}
		else
		{
			constructors= cl.getDeclaredConstructors();
		}
		
		for(Constructor c : constructors){
			if(onlyPublic){
				if(Modifier.isPublic(c.getModifiers())){
					list.add(prepareConstructorString(c,seperator));
				}
			}
			else
				list.add(prepareConstructorString(c, seperator));
		}
		
		return list;
	}
	
	private static String prepareConstructorString(Constructor c, String seperator){
		StringBuffer buffer = new StringBuffer();
		buffer.append(Modifier.toString(c.getModifiers()));
		buffer.append(seperator);
		buffer.append(c.getName());
		buffer.append("(");
		Class[] types = c.getParameterTypes();
		for(Class t : types){
			buffer.append(t.getName());
			buffer.append(", ");
		}
		if(types.length>0){
			buffer.delete(buffer.length()-2, buffer.length());
		}
		buffer.append(")");
		buffer.append(seperator);
		Class[] exceptions = c.getExceptionTypes();
		if(exceptions.length>0){
			buffer.append("throws ");
			for(int i=0; i<exceptions.length;i++){
				buffer.append(exceptions[i].getName());
				buffer.append(", ");
			}
			buffer.delete(buffer.length()-2, buffer.length());
		}
		return buffer.toString();
	}
	
	/**
	 * Returns all constructors of a class.
	 * @param className the classname as String
	 * @param seperator the seperator of the output strings
	 * @param onlyPublic set true to get only the public constructors
	 * @param loadExtends set true to load all constructors which were extended
	 * @return a {@link List} of constructors of strings
	 * @throws ClassNotFoundException
	 */
	public static List<String> getConstructors(String className, boolean onlyPublic, String seperator, boolean loadExtends) throws ClassNotFoundException{
		Class cl = Class.forName(className);
		return getConstructors(cl, onlyPublic, seperator, loadExtends);
	}
	
	/**
	 * Returns all attributes of a class.
	 * @param cl the class to analyse
	 * @param seperator the seperator of the output strings
	 * @param onlyPublic set true to get only the public attributes
	 * @param loadExtends set true to load all attributes which were extended
	 * @return a {@link List} of attributes of strings
	 */
	public static List<String> getAttributes(Class cl, boolean onlyPublic, String seperator, boolean loadExtends){
		ArrayList<String> list  = new ArrayList<String>();
		Field[] fields;
		if(loadExtends){
			fields = cl.getFields();
		}
		else
		{
			fields = cl.getDeclaredFields();
		}
		
		
		for(Field f : fields){
			if(onlyPublic){
				if(Modifier.isPublic(f.getModifiers())){
					list.add(prepareFieldString(f,seperator));
				}
			}
			else
				list.add(prepareFieldString(f, seperator));
		}
		return list;
	}
	
	private static String prepareFieldString(Field f, String seperator){
		StringBuffer buffer = new StringBuffer();
		buffer.append(Modifier.toString(f.getModifiers()));
		buffer.append(seperator);
		buffer.append(f.getType().getName());
		buffer.append(seperator);
		buffer.append(f.getName());
		return buffer.toString();
	}
	/**
	 * Returns all attributes of a class.
	 * @param className the classname as String
	 * @param seperator the seperator of the output strings
	 * @param onlyPublic set true to get only the public attributes
	 * @param loadExtends set true to load all attributes which were extended
	 * @return a {@link List} of attributes of strings
	 * @throws ClassNotFoundException
	 */
	public static List<String> getAttributes(String className, boolean onlyPublic, String seperator,boolean loadExtends) throws ClassNotFoundException{
		Class cl = Class.forName(className);
		return getAttributes(cl, onlyPublic, seperator, loadExtends);
	}
	
	/**
	 * Returns all methods of the class as an {@link List} with strings.
	 * @param cl the class to analyse
	 * @param seperator the seperator of the output strings
	 * @param onlyPublic set true to get only the public methods
	 * @param loadExtends set true to load all methods which were extended
	 * @return a {@link List} of methods of strings
	 */
	public static List<String> getMethods(Class cl, boolean onlyPublic, String seperator, boolean loadExtends){
		ArrayList<String> list  = new ArrayList<String>();
		
		Method[] methods;
		if(loadExtends){
			methods = cl.getMethods();
		}
		else
		{
			methods = cl.getDeclaredMethods();
		}
		
		
		for(Method m : methods){
			if(onlyPublic){
				if(Modifier.isPublic(m.getModifiers())){
					list.add(prepareMethodString(m,seperator));
				}
			}
			else
				list.add(prepareMethodString(m,seperator));
		}
		return list;
	}
	
	
	private static String prepareMethodString(Method m, String seperator){
		StringBuffer buffer = new StringBuffer();
		buffer.append(Modifier.toString(m.getModifiers()));
		buffer.append(seperator);
		buffer.append(m.getReturnType().getName()); 
		buffer.append(seperator);
		buffer.append(m.getName());
		buffer.append("(");
		Class[] types = m.getParameterTypes();
		for(Class type : types){
			buffer.append(type.getName());
			buffer.append(", ");
		}
		if(types.length>0){
			buffer.delete(buffer.length()-2, buffer.length());
		}
		buffer.append(")");
		buffer.append(seperator);
		Class[] exceptions = m.getExceptionTypes();
		if(exceptions.length>0){
			buffer.append("throws ");
			for(int i=0; i<exceptions.length;i++){
				buffer.append(exceptions[i].getName());
				buffer.append(", ");
			}
			buffer.delete(buffer.length()-2, buffer.length());
		}
		return buffer.toString();
	}
	
	
	/**
	 * Returns all methods of the class as an {@link List} with strings.
	 * @param className the classname as String
	 * @param onlyPublic set true to get only the public methods
	 * @param seperator the seperator of the output strings
	 * @param loadExtends set true to load all methods which were extended
	 * @return a {@link List} of methods of strings
	 * @throws ClassNotFoundException
	 */
	public static List<String> getMethods(String className,boolean onlyPublic, String seperator,boolean loadExtends) throws ClassNotFoundException{
		Class cl = Class.forName(className);
		return getMethods(cl,onlyPublic, seperator, loadExtends);
	}
	
	/**
	 * Returns the header of a class.<br>
	 * Example:<br>
	 * <i>public class myClass extends AbstractClass implements MyInterface</i>
	 * @param className the filename of the class
	 * @return returns a header of a class
	 * @throws ClassNotFoundException
	 */
	public static String getClassHeader(String className) throws ClassNotFoundException{
		Class cl = Class.forName(className);
		return getClassHeader(cl);
	}
	
	/**
	 * Returns the header of a class.<br>
	 * Example:<br>
	 * <i>public class myClass extends AbstractClass implements MyInterface</i>
	 * @param cl the classe to analyse
	 * @return returns a header of a class
	 */
	public static String getClassHeader(Class cl){
		StringBuffer buffer = new StringBuffer();
		buffer.append(Modifier.toString(cl.getModifiers()));
		buffer.append(" class ");
		buffer.append(cl.getName());
		Class superclass = cl.getSuperclass();
		if(superclass!= null && superclass != Object.class){
			buffer.append(" extends ");
			buffer.append(superclass.getName());
		}
		
		Class[] interfaces = cl.getInterfaces();
		
		if(interfaces.length >0){
			buffer.append(" implements ");
		}
		
		for(Class c : interfaces){
			buffer.append(c.getName());
			buffer.append(", ");
		}
		if(interfaces.length >0){
			buffer.delete(buffer.length()-2, buffer.length());
		}
		
		return buffer.toString();
	}
	

	/**
	 * Returs the package of an java file (if there is set one). <br>
	 * Example:<br>
	 * de.slfw.util
	 * @param f the file
	 * @return returns the package 
	 * @throws IOException
	 */
	public static String getPackageNameFromJavaFile(File f) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line;
		boolean goOn = true;
		boolean readEveryLine = false;
		String cache = "";
		
		while((line=br.readLine())!=null && goOn){
			if(line.indexOf("package") != -1 || readEveryLine){
				if(line.indexOf("package") != -1){
					cache = line.substring("package".length());
				}
				else
				{
					cache = cache + line;
				}
				
				if(cache.indexOf(";") != -1){
					goOn = false;
				}
				else
				{
					readEveryLine = true;
				}
				
			}
		}

		cache = StringUtil.remove(cache, ';');
		cache = StringUtil.remove(cache, ' ');
		return cache;
	}
	
	/**
	 * A dummy print for the methods.
	 * @param methods the return of the getMethods
	 */
	public static void printMethods(List<String> methods){
		dummyPrint("Methods", methods);
	}
	
	/**
	 * A dummy print for the attributes
	 * @param attributes
	 */
	public static void printAttributes(List<String> attributes){
		dummyPrint("Attributes", attributes);
	}
	
	/**
	 * A dummy print for the constructors
	 * @param constructors
	 */
	public static void printConstructors(List<String> constructors){
		dummyPrint("Constructors", constructors);
	}
	
	
	/**
	 * A dummy {@link List}  printer with a head line.
	 * @param name
	 * @param values
	 */
	public static void dummyPrint(String name, List<String> values){
		System.out.println(name);
		for(String s:values){
			System.out.println(s);
		}
	}
	
}
