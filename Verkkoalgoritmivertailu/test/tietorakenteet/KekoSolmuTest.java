package tietorakenteet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import verkko.Solmu;


public class KekoSolmuTest {
    
    private BinaariKekoSolmu kekosolmu;
    private BinaariKekoSolmu kekosolmu2;
    
    public KekoSolmuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Solmu s = new Solmu(3, 3);
        s.setMinimiEtaisyys(6);
        kekosolmu = new BinaariKekoSolmu(s);
        kekosolmu2 = new BinaariKekoSolmu(new Solmu(2, 2));
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void luodunKekoSolmunAvainArvoOnOikein() {
        assertEquals(6, kekosolmu.getAvainArvo());
    }
    
    @Test
    public void kahdenKekoSolmuVertailuPalauttaaNegatiivisenLuvunJosVertailtavaAlkioOnSuurempi() {
        assertTrue(kekosolmu.compareTo(kekosolmu2) < 0);
    }
    
    @Test
    public void kahdenKekoSolmunVertailuPalauttaaPositiivisenLuvunJosVertailtavaAlkioOnPienempi() {
        assertTrue(kekosolmu2.compareTo(kekosolmu) > 0);
    }
    
    @Test
    public void kahdenKekoSolmunVertailuPalauttaaNollanVertailtavatAlkiotOvatYhtaSuuret() {
        assertTrue(kekosolmu.compareTo(kekosolmu) == 0);
    }
    
    @Test
    public void luodullaKekoSolmullaOnViiteAnnettuunSolmuun() {
        assertEquals(new Solmu(3, 3), kekosolmu.getSolmu());
    }
    
}
