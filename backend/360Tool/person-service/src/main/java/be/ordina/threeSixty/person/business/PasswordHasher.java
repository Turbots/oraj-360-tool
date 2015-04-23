package be.ordina.threeSixty.person.business;

import org.apache.commons.logging.Log;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.annotation.PostConstruct;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.logging.Logger;

/**
 * Created by stevedezitter on 22/04/15.
 */
@Component
public class PasswordHasher {

    private static final int HASH_ITERATIONS=1000;
    private BASE64Encoder encoder;

    public PasswordHasher() {
        encoder = new BASE64Encoder();
    }

    public byte[] generateRandomSalt() {
        byte[] salt = new byte[16];

        SecureRandom random = new SecureRandom();

        random.nextBytes(salt);

        return salt;
    }

    public byte[] getBytesForPassword(String password) {
        Charset charset = Charset.forName("UTF-16");
        return password.getBytes(charset);
    }

    public byte[] getBytesForCharArrayPassword(char[] password) {
        byte[] passwordBytes = new byte[password.length*2];
        Charset charset = Charset.forName("UTF-16");

        CharBuffer charBuffer = CharBuffer.wrap(password);
        ByteBuffer byteBuffer = charset.encode(charBuffer);

        byteBuffer.get(passwordBytes);

        return passwordBytes;
    }

    public String generateBase64HashedPasswordForPasswordAndSalt(byte[] password, byte[] salt) {
        try {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256","BC");

            messageDigest.reset();
            messageDigest.update(salt);

            byte[] hashedPassword = messageDigest.digest(password);

            for(int i=0; i<HASH_ITERATIONS; i++) {
                messageDigest.reset();
                hashedPassword = messageDigest.digest(hashedPassword);
            }

            return encoder.encode(hashedPassword);
        }catch(NoSuchAlgorithmException |
                NoSuchProviderException cryptoException) {
            System.out.println("Error hashing password");
            return null;
        }
    }

    public String generateBase64SaltForBytes(byte[] salt) {
        return encoder.encode(salt);
    }

}
