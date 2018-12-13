package de.slfw.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>Some String Utils </p>
 * @author Alex S.
 *
 *
 */
public class StringUtil {
	
	/**
	 * 
	 * <p>Erweiterung der normalen isNumeric Funktion um die Erkennung von Zahlen,
	 * die ein + oder - davor stehen haben.</p>
	 * @param string
	 * @return true - false
	 */
	  public static boolean isNumeric(String string){
	    if(string!=null){
	    	String cache = string;
	    	
	    	if((string.charAt(0)== '-' || string.charAt(0)== '+')){
	    		cache = cache.substring(1);
	    	}

			try {
				Double.parseDouble(cache);
				return true;
			} catch (NumberFormatException e) {}
	    }
	    return false;
	  }
	  
	  /**
	   * Removes all chars from a string if they are like the spezified char.
	   * @param targetString the string to check
	   * @param removeChar the char to remove
	   * @return the filtered string
	   */
	  public static String remove(String targetString, char removeChar){
		  StringBuffer returnString = new StringBuffer();
		  char cache;
		  for(int i=0; i<targetString.length(); i++){
			  cache = targetString.charAt(i);
			  if(cache!=removeChar){
				  returnString.append(cache);
			  }
		  }
		  return returnString.toString();		  
	  }
	  
	  /**
	   * Retunrs all tokens of a String wich were seperated by an seperator. This methods was implemented,
	   * because the StringTokenizer from java.util dosn't support empty Strings -->" ".
	   * @param string the string to analyse
	   * @param seperator the seperator to seperate the string
	   * @return a list with the tokens. 
	   */
	  public static List<String> seperate(String string, String seperator){
		  ArrayList<String> strings = new ArrayList<String>();
		  int index;
		  while((index=string.indexOf(seperator))!= -1){
			  strings.add(string.substring(0, index));
			  string = string.substring(index +1);
		  }
		  strings.add(string);
		  return strings;		  
	  }
	  
	  
}
