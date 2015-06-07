package algoritmit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import verkko.Solmu;
import verkko.Verkko;

public class BFSTest {

    private BFS bfs;
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
    
    public BFSTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.bfs = new BFS();
        this.verkko = new Verkko(kentta, false, false);
        bfs.haeLyhinPolku(verkko, 0, 0, 11, 14);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void haeLyhinPolkuLoytaaOikeanMittaisenLyhimmanPolun() {
        Solmu s = verkko.haeSolmu(11, 14);
        assertEquals(67, s.getMinimiEtaisyys());
    }
    
    @Test
    public void haeLyhinPolkuLuoPolunOikein() {
        Solmu s = verkko.haeSolmu(11, 14);
        assertEquals(verkko.haeSolmu(11, 13), s.getEdellinen());
    }
    
    @Test
    public void haeLyhinPolkuLoytaaOikeanMittaisenLyhimmanPolun2() {
        Solmu s = verkko.haeSolmu(6, 8);
        assertEquals(32, s.getMinimiEtaisyys());
    }
    
}