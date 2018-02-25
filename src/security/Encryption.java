package security;

import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;

public class Encryption {

	private static final String ENCRYPTION_ALGORITHM="AES";
	private static String generatedKey="";

	/**
	 * Generate key
	 * @param nbCar number of chars of the key to generate
	 * @return the generated key
	 */
	public static String generateKey(int nbCar) 
	{
		String chars="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String key ="";
		for(int i=0;i<nbCar;i++) 
		{
			int c = (int) Math.floor(Math.random()*62);
			key+=chars.charAt(c);
		}
		return key;
	}

	/**
	 * Encrypt data with AES Algorithm and random generated key
	 * @param s data to encrypt
	 * @return encrypted password
	 */
	public static String encrypt(String s) 
	{
		try {
			Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
			generatedKey = generateKey(16);
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(generatedKey.getBytes(StandardCharsets.UTF_8), ENCRYPTION_ALGORITHM));
			return Base64.encodeBase64URLSafeString(cipher.doFinal(s.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s; 
	}

	/**
	 * Encrypt data with AES Algorithm and key
	 * @param password password to encrypt
	 * @param key the key to encrypt password
	 * @return encrypted password
	 */
	public static String encrypt(String password,String key) 
	{
		try {
			Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ENCRYPTION_ALGORITHM));
			return Base64.encodeBase64URLSafeString(cipher.doFinal(password.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password; 
	}

	/**
	 * Gets the generated key
	 * @return the generated key
	 */
	public static String getGeneratedKey() {
		return generatedKey;
	}

}
