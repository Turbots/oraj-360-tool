package be.ordina.threesixty.person.business;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by stevedezitter on 22/04/15.
 */
public class PasswordHasherTest {

    private static final String SALT="PguEGz7ALBCOZU+2SQ6yEg==";
    private static final String HASHED_PASSWORD="Byb+wtbXYlXrhPBhtrRYnBBQhpVEby+25wu7CU49Eek=";

    private PasswordHasher passwordHasher;
    private Charset charset;
    private BASE64Decoder decoder;

    @Before
    public void init() {
        passwordHasher = new PasswordHasher();
        charset = Charset.forName("UTF-16");
        decoder = new BASE64Decoder();
    }

    @Test
    public void passwordHashWithFixedSaltMatches() throws IOException{
        String password = "mypass";

        String hashedPassword = passwordHasher.generateBase64HashedPasswordForPasswordAndSalt(password.getBytes(charset), decoder.decodeBuffer(SALT));

        assertThat(HASHED_PASSWORD, equalTo(hashedPassword));
    }

}
