package de.slfw.io;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * <p>Terminal</p>
 * <p>Reads different data from the Terminal.</p>
 *
 * @author Alex S.</a>
 * @version $Revision $
 */
//Terminal makes it easy to read different Data from the Terminal. 

public class Terminal {
  /**
   * 
   * <p>Liest das erste Zeichen eines String oder eines Chararrays ein und gibt es als
   * einzelnes char zurück.</p>
   * @return Gibt ein Char zurück
   * @throws IOException
   */
	public static char readChar() throws IOException	{
		return readString().charAt(0); //liest das erste zeichen des Strings
	}
	/**
   * 
   * <p>Wandelt den eingegebenen String  in eine Double um.</p>
   * @return Gibt eine Double zurück
   * @throws IOException
	 */
	public static double readDouble() throws IOException	{
		return Double.parseDouble(readString());
	}

  /**
   * 
   * <p>Wandelt den eingegebenen String in eine Float um.</p>
   * @return Gibt eine Float zurück
   * @throws IOException
   */
	public static float readFload() throws IOException	{		
		return Float.parseFloat(readString());
	}
	
  /**
   * 
   * <p>Wandelt den eingegebenen String in eine Int um.</p>
   * @return Gibt eine Int zurück
   * @throws NumberFormatException 
   * @throws IOException
   */	
	public static int readInt() throws NumberFormatException, IOException	{
		return Integer.parseInt(readString());
	}
  
  /**
   * 
   * <p>Wandelt den eingegebenen String in eine Long um.</p>
   * @return Gibt eine Long zurück
   * @throws NumberFormatException 
   * @throws IOException
   */ 
	public static long readLong() throws NumberFormatException, IOException	{
		return Long.parseLong(readString());
	}
	
  
  /**
   * 
   * <p>Wandelt den eingegebenen String in eine Short um.</p>
   * @return Gibt eine Short zurück
   * @throws NumberFormatException 
   * @throws IOException
   */
	public static short readShort() throws NumberFormatException, IOException	{
		return Short.parseShort(readString());
	}

	/**
   * 
   * <p>Wandelt den eingegebene in einen String um.</p>
   * @return Gibt einen String zurück
   * @throws IOException
	 */
	public static String readString() throws IOException 	{
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader buf = new BufferedReader(reader);
		return buf.readLine();
	}
	
}
