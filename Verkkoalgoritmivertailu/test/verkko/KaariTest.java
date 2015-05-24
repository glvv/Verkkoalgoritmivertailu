/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author raot
 */
public class KaariTest {

    private Kaari kaari;

    public KaariTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.kaari = new Kaari(new Solmu(0, 0), 5);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void luodullaKaarellaOnOikeaSolmu() {
        assertEquals(new Solmu(0, 0), kaari.getKohdeSolmu());
    }

    @Test
    public void luodullaKaarellaOnOikeaPaino() {
        assertEquals(5, kaari.getPaino());
    }
}
