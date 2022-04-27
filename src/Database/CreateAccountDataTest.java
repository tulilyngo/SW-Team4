package Database;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateAccountDataTest {
    private CreateAccountData cad;

    @Before
    public void setUp() {
        cad = new CreateAccountData("test", "test", "test");
    }

    @Test //Non-Null Object
    public void testNonNull()
    {
        String obj = cad.getUsername();
        assertEquals("test", obj);

        obj = cad.getPassword();
        assertEquals("test", obj);

        obj = cad.getPassword2nd();
        assertEquals("test", obj);
    }

    @Test
    public void testSetUsername()
    {
        cad.setUsername("testUser");
        assertEquals("testUser", cad.getUsername());
    }

    @Test
    public void testSetPassword()
    {
        cad.setPassword("hello123");
        assertEquals("hello123", cad.getPassword());
    }

    @Test
    public void testSetPassword2nd()
    {
        cad.setPassword2nd("hello123");
        assertEquals("hello123", cad.getPassword2nd());
    }
}