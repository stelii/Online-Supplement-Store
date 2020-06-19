package proiect.fis.store.model;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class HashPasswordTest extends ApplicationTest {

    @Test
    public void encrypt() {
        HashPassword hashPassword = new HashPassword();
        assertNotNull(hashPassword);
        assertNotEquals("testPassword", HashPassword.encrypt("testPassword"));
        String hashedPassword = HashPassword.encrypt("testPassword2");
        assertEquals(hashedPassword, HashPassword.encrypt("testPassword2"));
        assertNotEquals("failTest", HashPassword.encrypt("Password"));
    }
}