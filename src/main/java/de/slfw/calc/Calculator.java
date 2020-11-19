package de.slfw.calc;

/**
 * 
 * <p>Wandelt Zahlen in verschiedene Zahlensysteme um.</p>
 *
 * @author Alex S.
 */
public class Calculator {


  /**
   * 
   * <p>Wandelt Dezimalzahlen in Binärzahlen um. </p>
   * @param dezimal Die übergebene Dezimalzahllzahl
   * @return Gibt die Binärzahl als String zurück
   */
	public static String deziToBin(long dezimal)
	{
		String bin = "";
		while(dezimal>0)
		{
			bin = dezimal%2 + bin ;
			dezimal = dezimal / 2;
		}
	    return bin;
	
	}

  
	/**
   * 
   * <p>Wandelt Binärzahlen in Dezimalzahlen um.</p>
   * @param bin �bergebene Binährzahl
   * @return Gibt die Dezimalzahl zurück
	 */
	public static long binToDezi(String bin)
	{
		long dezimal= 0;
		for(int i=0; i<bin.length(); i++)
		{
			dezimal = dezimal * 2 + Long.parseLong(bin.substring(i,i+1));  //wandelt binär zu Dezimalzahl
		}
		return dezimal;
	}


	
}
