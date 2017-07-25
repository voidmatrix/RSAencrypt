
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import java.security.Security;
import java.util.Scanner;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * 
 */
public class Sender
{
    public static void main()throws Exception{
    	//Scanner in = new Scanner(System.in);
    	//System.out.println("Please input plaintext:");
		//BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		//String input = buf.readLine();
    	String          input = "This is a message";
        MessageDigest   hash = MessageDigest.getInstance("SHA1");
        
        System.out.println("input : " + input);
        
        hash.update(Utils.toByteArray(input));
        String digest = Utils.toHex(hash.digest());
        System.out.println("digest : " + digest);
        RSA(digest);
        
    }
	public static void RSA(
        String    input)
        throws Exception
    {
       /* Security.addProvider(new BouncyCastleProvider()); */   
        //byte[]           input = new byte[] { (byte)0xbe, (byte)0xef }; 
		
        Cipher	         cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        SecureRandom     random = new SecureRandom();
        byte[] in = input.getBytes();
        // create the keys
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        
        generator.initialize(512, random);

        KeyPair          pair = generator.generateKeyPair();
        Key              pubKey = pair.getPublic();
        Key              privKey = pair.getPrivate();

        //System.out.println("input : " + Utils.toHex(in));
        
        // encryption step
        
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);

        byte[] cipherText = cipher.doFinal(in);

        System.out.println("cipher: " + Utils.toHex(cipherText));
        
        // decryption step

        cipher.init(Cipher.DECRYPT_MODE, privKey);

        byte[] plainText = cipher.doFinal(cipherText);
        
        System.out.println("plain : " + Utils.toHex(plainText));
    }
	/*public static void digest(
            String    args)
            throws Exception
        {
            String 		input="";
            MessageDigest   hash = MessageDigest.getInstance("SHA1");
            
            System.out.println("input : " + input);
            
            hash.update(Utils.toByteArray(input));
            
            System.out.println("digest : " + Utils.toHex(hash.digest()));


        } */ 

}