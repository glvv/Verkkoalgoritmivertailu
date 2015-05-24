package verkko;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SolmuTest {

    private Solmu s;
    private Solmu s2;

    public SolmuTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        s = new Solmu(0, 0);
        s2 = new Solmu(5, 5);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void luodullaSolmullaOnOikeaXKoordinaatti() {
        assertEquals(0, s.getX());
    }

    @Test
    public void luodullaSolmullaOnOikeaYKoordinaatti() {
        assertEquals(0, s.getY());
    }

    @Test
    public void luodullaSolmullaOnOikeaXKoordinaatti2() {
        assertEquals(5, s2.getX());
    }

    @Test
    public void luodullaSolmullaOnOikeaYKoordinaatti2() {
        assertEquals(5, s2.getY());
    }

    @Test
    public void luodunSolmunMinimietaisyysAlkuSolmuunOnAareton() {
        assertEquals(1000000000, s.getMinimiEtaisyys());
    }

    @Test
    public void luodunSolmunEtaisyysKohdeSolmuunOn0() {
        assertEquals(0, s.getEtaisyysLoppusolmuun());
    }

    @Test
    public void kaksiSolmuaJoillaEriKoordinaatitEivatOleSamat() {
        assertFalse(s.equals(s2));
    }

    @Test
    public void kaksiSolmuaJoillaSamatKoordinaatitOvatSamat() {
        Solmu s3 = new Solmu(0, 0);
        assertTrue(s.equals(s3));
    }

    @Test
    public void toinenSolmuOnJarjestyksessaToistaSuurempiJosMinimietaisyysOnSuurempi() {
        s.setMinimiEtaisyys(5);
        int vertailu = s.compareTo(s2);
        assertTrue(vertailu < 0);
    }

    @Test
    public void toinenSolmuOnJarjestyksessaToistaPienempiJosMinimietaisyysOnPienempi() {
        s2.setMinimiEtaisyys(1000);
        int vertailu = s.compareTo(s2);
        assertTrue(vertailu > 0);
    }
    
    @Test
    public void toinenSolmuOnJarjestyksessaToistaPienempiJosMinimietaisyysPlusEtaisyysLoppuunOnPienempi() {
        s2.setMinimiEtaisyys(1);
        s2.setEtaisyysLoppusolmuun(6);
        s.setMinimiEtaisyys(3);
        int vertailu = s.compareTo(s2);
        assertTrue(vertailu < 0);
    }

}
