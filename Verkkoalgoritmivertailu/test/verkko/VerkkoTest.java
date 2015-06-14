package verkko;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tietorakenteet.Lista;

public class VerkkoTest {

    private static final char[][] kentta = new char[][]{
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '#', '.', '.', '.', '.', '.', '.', '#', '#'},
        {'.', '#', '.', '.', '.', '.', '.', '.', '#', '.'},
        {'.', '#', '#', '#', '#', '#', '#', '#', '#', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}};

    private Verkko verkko;

    public VerkkoTest() {
        
    }

    @BeforeClass
    public static void setUpClass() {
        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.verkko = new Verkko(kentta, false, true);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void luodussaVerkossaOnOikeaSolmu1() {
        assertEquals(new Solmu(0, 0), verkko.haeSolmu(0, 0));
    }

    @Test
    public void luodussaVerkossaOnOikeaSolmu2() {
        assertEquals(new Solmu(4, 4), verkko.haeSolmu(4, 4));
    }

    private Kaari haeKaari(int alkuX, int alkuY, int kohdeX, int kohdeY) {
        Solmu kohde = verkko.haeSolmu(kohdeX, kohdeY);
        Solmu alku = verkko.haeSolmu(alkuX, alkuY);
        Kaari e = null;
        for (Kaari k : alku.getKaaret()) {
            if (k.getKohdeSolmu() == kohde) {
                e = k;
                break;
            }
        }
        return e;
    }

    @Test
    public void luodussaVerkossaOnKaarenPainotOikein1() {
        Kaari e = haeKaari(0, 1, 1, 1);
        assertEquals(100, e.getPaino());
    }

    @Test
    public void luodussaVerkossaOnKaarenPainotOikein2() {
        Kaari e = haeKaari(0, 0, 0, 1);
        assertEquals(1, e.getPaino());
    }

    private int laskeKaaret(int solmuX, int solmuY) {
        Solmu s = verkko.haeSolmu(solmuX, solmuY);
        int kaaria = 0;
        for (Kaari e : s.getKaaret()) {
            kaaria++;
        }
        return kaaria;
    }

    @Test
    public void luodunVerkonSolmuissaOnOikeaMaaraKaaria1() {
        assertEquals(2, laskeKaaret(0, 0));
    }

    @Test
    public void luodunVerkonSolmuissaOnOikeaMaaraKaaria2() {
        assertEquals(4, laskeKaaret(1, 1));
    }

    @Test
    public void luodunVerkonSolmuissaOnOikeaMaaraKaaria3() {
        assertEquals(3, laskeKaaret(0, 1));
    }

    private boolean contains(Lista<Kaari> kaaret, Solmu haettava) {
        for (Kaari kaari : kaaret) {
            if (kaari.getKohdeSolmu().equals(haettava)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void luodunVerkonSolmussaOnOikeatKaaret() {
        Solmu s = verkko.haeSolmu(3, 3);
        assertEquals(true, contains(s.getKaaret(), new Solmu(3, 2)));
        assertEquals(true, contains(s.getKaaret(), new Solmu(3, 4)));
        assertEquals(true, contains(s.getKaaret(), new Solmu(2, 3)));
        assertEquals(true, contains(s.getKaaret(), new Solmu(4, 3)));
    }

    @Test
    public void luodunVerkonSolmussaOikeanPainoisetKaaret() {
        Solmu s = verkko.haeSolmu(3, 3);
        assertEquals(1, s.getKaaret().get(0).getPaino());
        assertEquals(1, s.getKaaret().get(1).getPaino());
        assertEquals(100, s.getKaaret().get(2).getPaino());
        assertEquals(100, s.getKaaret().get(3).getPaino());
    }

    @Test
    public void diagonaalisetSolmutLuodaanOikein() {
        Verkko d = new Verkko(kentta, true, true);
        Solmu s = d.haeSolmu(3, 3);
        assertTrue(contains(s.getKaaret(), new Solmu(4, 4)));
        assertTrue(contains(s.getKaaret(), new Solmu(2, 2)));
        assertTrue(contains(s.getKaaret(), new Solmu(2, 4)));
        assertTrue(contains(s.getKaaret(), new Solmu(4, 2)));
    }
    
    @Test
    public void josSeinienLapiEiSaaLiikkuaNiinNiihinJohtaviaKaariaEiLuoda() {
        Verkko w = new Verkko(kentta, false, false);
        Solmu s = w.haeSolmu(1, 0);
        assertFalse(contains(s.getKaaret(), new Solmu(1, 1)));
    }
    
    @Test
    public void luodussaVerkossaKaartenMaaraOnOikein() {
        assertEquals(170, verkko.haeKaartenMaara());
    }
    
    @Test
    public void luodussaVerkossaSolmujenMaaraOnOikein() {
        assertEquals(50, verkko.haeSolmujenMaara());
    }
    
    @Test
    public void kunSeiniinEiLuodaKaariaKaartenMaaraOnOikein() {
        Verkko g = new Verkko(kentta, false, false);
        assertEquals(119, g.haeKaartenMaara());
    }
    
    @Test
    public void kunDiagonaalitOnSallittuNiinKaartenMaaraOnOikein() {
        Verkko g = new Verkko(kentta, true, true);
        assertEquals(314, g.haeKaartenMaara());
    }

}
