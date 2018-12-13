package de.slfw.security;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * <p>Encryts Strings</p>
 * 
 * @author Alex S.
 *
 *
 */
public class Encrypter {
	
		
	private MessageDigest md;
	/**
	 * 
	 * @param encrypter - Algorithmn as String (Bsp: MD5)
	 * @throws NoSuchAlgorithmException
	 */
	public Encrypter(String encrypter) throws NoSuchAlgorithmException{
		md = MessageDigest.getInstance( encrypter);
	}
	
	/**
	 * 
	 * @param value - The String to encrypt
	 * @return encrypted String
	 */
	public String letzzzEncrypt(String value){
		String pw = "";
		byte[] digest = md.digest( value.getBytes() );
		for (int i=0; i<digest.length; i++){
			pw = pw + Integer.toHexString( digest[i] & 0xFF);
		}
		return pw;
	}
	
	
}
