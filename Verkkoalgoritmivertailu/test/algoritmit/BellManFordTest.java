package algoritmit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import verkko.Verkko;

public class BellManFordTest {

    private BellmanFord bellmanford;
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

    public BellManFordTest() {
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
        bellmanford = new BellmanFord();
        bellmanford.haeLyhimmatPolut(verkko, 0, 0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void haeLyhimmatPolutHakeeOikeanPituisenLyhimmanPolun() {
        assertEquals(30, verkko.haeSolmu(13, 13).getMinimiEtaisyys());
    }

    @Test
    public void haeLyhimmatPolutHakeeOikeanPituisenLyhimmanPolun2() {
        assertEquals(7, verkko.haeSolmu(0, 5).getMinimiEtaisyys());
    }

    @Test
    public void haeLyhimmatPolutPalauttaaTrueJosVerkossaEiOleNegatiivisiaKaaria() {
        assertEquals(true, bellmanford.haeLyhimmatPolut(verkko, 0, 0));
    }

    @Test
    public void haeLyhimmatPolutPalauttaaFalseJosVerkossaOnNegatiivinenSykli() {
        verkko.haeSolmu(3, 3).getKaaret().get(0).setPaino(-1);
        verkko.haeSolmu(4, 3).getKaaret().get(1).setPaino(-1);
        assertEquals(false, bellmanford.haeLyhimmatPolut(verkko, 0, 0));
    }

    @Test
    public void haeLyhimmatPolutLuoPolkuunOikeanSolmun1() {
        assertEquals(verkko.haeSolmu(12, 13), verkko.haeSolmu(13, 13).getEdellinen());
    }

}
