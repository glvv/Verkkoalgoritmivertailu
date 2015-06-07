package algoritmit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import verkko.Solmu;
import verkko.Verkko;

public class BidirectionalTest {
    
    private Bidirectional bd;
    private char[][] kentta
         = {{'.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '.', '#', '.', '.'},
            {'.', '.', '.', '#', '#', '.', '#', '.', '.', '.', '.', '.', '#', '.', '.'},
            {'.', '.', '.', '#', '.', '.', '#', '.', '#', '#', '#', '#', '#', '.', '.'},
            {'.', '.', '.', '#', '.', '.', '#', '.', '.', '.', '.', '.', '#', '.', '.'},
            {'.', '.', '.', '#', '.', '#', '#', '#', '#', '#', '#', '.', '#', '#', '.'},
            {'.', '.', '.', '#', '.', '.', '#', '.', '.', '.', '.', '.', '#', '.', '.'},
            {'#', '#', '.', '#', '.', '.', '#', '.', '#', '#', '#', '#', '#', '.', '.'},
            {'.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '.', '#', '.', '.', '.'},
            {'.', '.', '.', '#', '.', '.', '#', '.', '.', '.', '.', '#', '.', '#', '.'},
            {'.', '.', '#', '#', '.', '#', '#', '#', '#', '#', '.', '#', '.', '#', '#'},
            {'.', '.', '#', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.'}};
    private Verkko verkko;
    
    public BidirectionalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.bd = new Bidirectional();
        this.verkko = new Verkko(kentta, false, false);
        bd.haeLyhinPolku(verkko, 0, 0, 11, 14);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void haeLyhinPolkuLoytaaLyhimmanPolun() {
        assertEquals(67, verkko.haeSolmu(11, 14).getMinimiEtaisyys());
    }
    
    private Solmu haePolunPaassaOlevaSolmu(Solmu s) {
        while (s.getEdellinen() != null) {
            s = s.getEdellinen();
        }
        return s;
    }
    
    @Test
    public void haeLyhinPolkuLuoPolunOikeinViimeiseenSolmuun() {
        assertEquals(verkko.haeSolmu(11, 13), verkko.haeSolmu(11, 14).getEdellinen());
    }
    
    @Test
    public void haeLyhinPolkuLuoPolunJokaJohtaaAloitusSolmuun() {
        assertEquals(verkko.haeSolmu(0, 0), haePolunPaassaOlevaSolmu(verkko.haeSolmu(11, 14)));
    }
    
}
