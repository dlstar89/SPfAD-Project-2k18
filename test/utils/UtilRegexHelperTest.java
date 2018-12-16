/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dstarcenko
 */
public class UtilRegexHelperTest {
    
    public UtilRegexHelperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validateEmail method, of class UtilRegexHelper.
     */
    @Test
    public void testValidateEmail() {
        System.out.println("ValidateEmail");
        String email = "email@mail.com";
        boolean expResult = true;
        boolean result = UtilRegexHelper.validateEmail(email);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testInvalidateEmail() {
        System.out.println("InvalidateEmail");
        String email = "email@gmail.c";
        boolean expResult = false;
        boolean result = UtilRegexHelper.validateEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of validUsername method, of class UtilRegexHelper.
     */
    @Test
    public void testValidUsername() {
        System.out.println("ValidUsername");
        String username = "user1";
        boolean expResult = true;
        boolean result = UtilRegexHelper.validUsername(username);
        assertEquals(expResult, result);
    }
    @Test
    public void testInvalidUsername() {
        System.out.println("InvalidUsername");
        String username = "Ac2";
        boolean expResult = false;
        boolean result = UtilRegexHelper.validUsername(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of validPassword method, of class UtilRegexHelper.
     */
    @Test
    public void testValidPassword() {
        System.out.println("ValidPassword");
        String password = "Pas1234*";
        boolean expResult = true;
        boolean result = UtilRegexHelper.validPassword(password);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testInvalidPassword() {
        System.out.println("InvalidPassword");
        String password = "Pas12*";
        boolean expResult = false;
        boolean result = UtilRegexHelper.validPassword(password);
        assertEquals(expResult, result);
    }
    
}
