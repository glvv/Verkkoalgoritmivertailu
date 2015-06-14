package algoritmit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tietorakenteet.Binaarikeko;
import tietorakenteet.Fibonaccikeko;
import verkko.Verkko;

public class AStarTest {

    private AStar astar;
    private static final char[][] kentta = {
        {'.', '.', '.', '=', '=', '.', '.', '¤', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '¤', '.', '.', '.', '.', '#', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '¤', '.', '~', '.', '.', '#', '.'},
        {'.', '.', '.', '=', '=', '.', '.', '¤', '~', '~', '.', '.', '#', '.'},
        {'.', '.', '.', '=', '=', '.', '.', '¤', '~', '~', '.', '.', '#', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '¤', '~', '~', '.', '.', '#', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '¤', '~', '~', '.', '.', '#', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '¤', '~', '.', '.', '.', '#', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '~', '~', '.', '.', '#', '#', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '~', '~', '.', '.', '#', '#', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '~', '~', '.', '.', '#', '#', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '~', '#', '#', '#', '#', '#', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '~', '~', '#', '#', '#', '#', '.'},
        {'.', '.', '.', '.', '.', '#', '#', '#', '#', '#', '#', '#', '#', '.'},};//Lyhin reitti solmusta (0,0) solmuun (13,13) 30.
    private Verkko verkko;

    public AStarTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        verkko = new Verkko(kentta, false, true);
        astar = new AStar(new Binaarikeko(kentta[0].length * kentta.length));
        astar.haeLyhinPolku(verkko, 0, 0, 13, 13);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void haeLyhinPolkuHakeeOikeanPituisenPolun() {
        assertEquals(30, verkko.haeSolmu(13, 13).getMinimiEtaisyys());
    }
    
    @Test
    public void haeLyhimmatPolutLisaaPolkuunOikeanSolmun1() {
        assertEquals(verkko.haeSolmu(12, 13), verkko.haeSolmu(13, 13).getEdellinen());
    }
    
    @Test
    public void haeLyhimmatPolutLisaaPolkuunOikeanSolmun2() {
        assertEquals(verkko.haeSolmu(1, 2), verkko.haeSolmu(1, 3).getEdellinen());
    }
    
}
