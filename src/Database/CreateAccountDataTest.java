package Database;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateAccountDataTest {
    private CreateAccountData cad;
    private Database db;

    @Before
    public void setUp() throws Exception
    {
        CreateAccountData cad = new CreateAccountData(null, null, null);
    }

    @Test //Non-Null Object
    public void testNonNull()
    {
        Object obj = cad.getUsername();
        assertNotNull(obj, "Username returns null");

        Object objP = cad.getPassword();
        assertNotNull(objP, "Password returns null");

        Object objP2 = cad.getPassword2nd();
        assertNotNull(objP2, "Password 2nd returns null");
    }

    @Test
    public void testsetUsername(String username)
    {
        CreateAccountData cad = new CreateAccountData(username, null, null);
        cad.setUsername("testUser");
        assertEquals("testUser", cad.getUsername());
    }

    @Test
    public void testsetPassword(String password)
    {
        CreateAccountData cad = new CreateAccountData(null, password, null);
        cad.setPassword("hello123");
        assertEquals("hello123", cad.getPassword());
    }

    @Test
    public void testsetPassword2nd(String password2nd)
    {
        CreateAccountData cad = new CreateAccountData(null, password2nd, null);
        cad.setPassword2nd("hello123");
        assertEquals("hello123", cad.getPassword());
    }
}