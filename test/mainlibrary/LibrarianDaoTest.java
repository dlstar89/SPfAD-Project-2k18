/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainlibrary;

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
public class LibrarianDaoTest {
    
    public LibrarianDaoTest() {
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
     * Test of validate method, of class LibrarianDao.
     */
    @Test
    public void testValidate() {
        System.out.println("Librarian Logged In");
        String name = "Zero";
        String password = "9876";
        boolean expResult = true;
        boolean result = LibrarianDao.validate(name, password);
        assertEquals(expResult, result);
    }    
}
