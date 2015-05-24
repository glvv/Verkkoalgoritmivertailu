package io;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BittikarttaTest {
    
    public BittikarttaTest() {
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

    @Test
    public void luoKenttaLuoKentanJossaOikeaMerkki() {
        char[][] kentta = Bittikartta.luoKentta("images/testikuva.bmp");
        assertEquals('.', kentta[0][0]);
    }
    
    @Test
    public void luoKenttaLuoKentanJossaOikeaMerkki2() {
        char[][] kentta = Bittikartta.luoKentta("images/testikuva.bmp");
        assertEquals('#', kentta[0][2]);
    }
    
    @Test
    public void luoKenttaLuoKentanJossaOikeaMerkki3() {
        char[][] kentta = Bittikartta.luoKentta("images/testikuva.bmp");
        assertEquals('~', kentta[0][9]);
    }
    
}