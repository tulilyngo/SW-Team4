package Kahoot.Tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import Kahoot.CreateAccountData;
import Kahoot.DatabaseFile;

public class CreateAccountDataTest {

	private CreateAccountData cad;
	private DatabaseFile db; 
	 
	@Before
	  public void setUp() throws Exception 
	  {
	   
		CreateAccountData cad = new CreateAccountData(null, null);

	  }
	
	@Test //Non-Null Object
	  public void testNonNull()
	  {
	    Object obj = cad.getUsername();
	    assertNotNull("Username returns null", obj);
	    
	    Object objP = cad.getPassword();
	    assertNotNull("Password returns null", objP);
	    
	    Object objP2 = cad.getPassword2nd();
	    assertNotNull("Password 2nd returns null", objP2);
	  }
	
	@Test
	public void testsetUsername(String username)
	{
		CreateAccountData cad = new CreateAccountData(username, null);
		cad.setUsername("testUser");
		assertEquals("testUser", cad.getUsername());
	}
	
	@Test
	public void testsetPassword(String password)
	{
		CreateAccountData cad = new CreateAccountData(null, password);
		cad.setPassword("hello123");
		assertEquals("hello123", cad.getPassword());
	}
	
	@Test
	public void testsetPassword2nd(String password2nd)
	{
		CreateAccountData cad = new CreateAccountData(null, password2nd);
		cad.setPassword2nd("hello123");
		assertEquals("hello123", cad.getPassword());
	}
	

}
