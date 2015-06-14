/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import verkko.Solmu;

public class FibonaccikekoSolmuTest {
    
    private FibonaccikekoSolmu fks;
    private Solmu s;
    
    public FibonaccikekoSolmuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        s = new Solmu(5, 5);
        s.setMinimiEtaisyys(4);
        s.setEtaisyysLoppusolmuun(54);
        this.fks = new FibonaccikekoSolmu(s);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void luodunFibonacciKekoSolmunAvainArvoOnOikein() {
        assertEquals(58, fks.getAvainArvo());
    }
    
    @Test
    public void luodunFibonaccikekoSolmunChildViiteOnNull() {
        assertEquals(null, fks.getChild());
    }
    
    @Test
    public void luodunFibonaccikekoSolmunParentViiteOnNull() {
        assertEquals(null, fks.getParent());
    }
    
    @Test
    public void luodunFibonaccikekoSolmunLeftViiteOnNull() {
        assertEquals(null, fks.getLeft());
    }
    
    @Test
    public void luodunFibonaccikekoSolmunRightViiteOnNull() {
        assertEquals(null, fks.getRight());
    }
    
    @Test
    public void luodullaFibonacciKekoSolmullaOnViiteSolmuun() {
        assertEquals(s, fks.getSolmu());
    }

}
