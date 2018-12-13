package de.slfw.security;

import java.security.NoSuchAlgorithmException;
import java.util.Random;
/**
 * 
 * <p>todo docu</p>
 *
 * @author Alex S.
 * 
 *
 */
public class SerialGenerartor {
	
	private static char[] alph = new char[36];
	
	
	public SerialGenerartor() {
		int startNumber = (int)'0';
		int startAlph = (int)'A';
		
		int index = 0;
		for(int i=0;i<10; i++, index++){
			alph[index] = (char)(startNumber+i);
		}
		for(int i=0;i<26; i++, index++){
			alph[index] = (char)(startAlph+i);
		}
	}
	
	
	public static String generateSerial(int numberOfChars){
		StringBuffer serial = new StringBuffer();
		Random rnd = new Random();
		
		for(int i=0; i<numberOfChars; i++){
			serial.append(alph[rnd.nextInt(36)]);
		}
		return serial.toString();		
	}
	
	
	
	public static String genearteEncryptedSerial(String serial){
		try {
			Encrypter e = new Encrypter("MD5");
			return e.letzzzEncrypt(serial);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
