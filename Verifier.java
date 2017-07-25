import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import java.security.Security;

public class Verifier {
	public static void main(
	        String[]    args)
	        throws Exception
	    {
	       /* Security.addProvider(new BouncyCastleProvider()); */   
	        byte[]           recv = new byte[] { (byte)0xbe, (byte)0xef }; 
	        //Cipher	         cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	        SecureRandom     random = new SecureRandom();
	        
	        // create the keys
	        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
	        
	        generator.initialize(512, random);

	        KeyPair          pair = generator.generateKeyPair();
	        Key              pubKey = pair.getPublic();
	        Key              privKey = pair.getPrivate();
	        
	        // decrypt the digest
	    }
}
