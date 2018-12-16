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
public class UtilHashingTest {
    
    public UtilHashingTest() {
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
     * Test of getNextSalt method, of class UtilHashing.
     */
    @Test
    public void testValidHashedPassword() {
        System.out.println("TestHashedPassword");
        
        String password = "Pass1234*";
        String salt = UtilHashing.getNextSalt();
        String hashedPassword = UtilHashing.saltedHash(password, salt);
        
        boolean result = UtilHashing.identicalHash(password, hashedPassword, salt);
        assertEquals(true, result);
    }    
}
