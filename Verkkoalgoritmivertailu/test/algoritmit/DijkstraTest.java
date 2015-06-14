package algoritmit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tietorakenteet.Binaarikeko;
import tietorakenteet.Fibonaccikeko;
import verkko.Solmu;
import verkko.Verkko;

public class DijkstraTest {

    private Dijkstra dijkstra;
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

    public DijkstraTest() {
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
        dijkstra = new Dijkstra(new Binaarikeko(kentta[0].length * kentta.length));
        dijkstra.haeLyhinPolku(verkko, 0, 0, 13, 13);
    }

    @After
    public void tearDown() {
    }

    private Solmu[] loysaaKunLoysaysTarpeellinen() {
        Solmu kohde = new Solmu(1, 1);
        kohde.setMinimiEtaisyys(100);
        Solmu alku = new Solmu(2, 2);
        alku.setMinimiEtaisyys(5);
        dijkstra.relax(alku, kohde, 10);
        Solmu[] solmut = {alku, kohde};
        return solmut;
    }

    private Solmu[] eiLoysataKunLoysaysEiOleTarpeellinen() {
        Solmu lahto = new Solmu(0, 0);
        Solmu kohde = new Solmu(1, 1);
        kohde.setEdellinen(lahto);
        kohde.setMinimiEtaisyys(1);
        Solmu alku = new Solmu(2, 2);
        alku.setMinimiEtaisyys(5);
        dijkstra.relax(alku, kohde, 10);
        Solmu[] solmut = {alku, kohde, lahto};
        return solmut;
    }

    @Test
    public void loysaaMuuttaaPolunJosReittiOnLyhyempi() {
        Solmu[] solmut = loysaaKunLoysaysTarpeellinen();
        assertEquals(solmut[0], solmut[1].getEdellinen());
    }

    @Test
    public void loysaaMuuttaaEtaisyydenJosReittiOnLyhyempi() {
        Solmu[] solmut = loysaaKunLoysaysTarpeellinen();
        assertEquals(15, solmut[1].getMinimiEtaisyys());
    }

    @Test
    public void loysaaEiMuutaPolunReittiaJosUusiReittiOnPidempi() {
        Solmu[] solmut = eiLoysataKunLoysaysEiOleTarpeellinen();
        assertEquals(solmut[2], solmut[1].getEdellinen());
    }

    @Test
    public void loysaaEiMuutaEtaisyyttaJosReittiOnPidempi() {
        Solmu[] solmut = eiLoysataKunLoysaysEiOleTarpeellinen();
        assertEquals(1, solmut[1].getMinimiEtaisyys());
    }

    @Test
    public void haeLyhimmatPolutHakeeOikeanPituisenLyhimmanPolun() {
        assertEquals(30, verkko.haeSolmu(13, 13).getMinimiEtaisyys());
    }

    @Test
    public void haeLyhimmatPolutHakeeOikeanPituisenReitinAariarvolla() {
        assertEquals(0, verkko.haeSolmu(0, 0).getMinimiEtaisyys());
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
