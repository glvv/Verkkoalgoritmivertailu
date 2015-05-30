
package tietorakenteet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import verkko.Solmu;


public class JonoTest {
    
    private Jono jono;
    private Solmu[] solmut; 
    
    public JonoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.jono = new Jono(5);
        this.solmut = luoSolmut(5);
    }
    
    private Solmu[] luoSolmut(int koko) {
        Solmu[] s = new Solmu[koko];
        for (int i = 0; i < koko; i++) {
            s[i] = new Solmu(i, i);
        }
        return s;
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void luotuJonoOnTyhja() {
        assertTrue(jono.empty());
    }
    
    @Test
    public void kunJonoonOnLisatty1AlkioJonoEiOleTyhjä() {
        lisaaAlkioitaJonoon(1);
        assertFalse(jono.empty());
    }
    
    private void lisaaAlkioitaJonoon(int alkioita) {
        for (int i = 0; i < alkioita; i++) {
            jono.enqueue(solmut[i]);
        }
    }
    
    @Test
    public void alkiodenLisaamisenJalkeenEnsimmaiseksiLisattySaadaanDequeueOperaatiolla() {
        lisaaAlkioitaJonoon(2);
        assertEquals(solmut[0], jono.dequeue());
    }
    
    @Test
    public void alkioidenLisaamisenJalkeenToiseksiLisattySaadaanToisenaUlos() {
        lisaaAlkioitaJonoon(2);
        jono.dequeue();
        assertEquals(solmut[1], jono.dequeue());
    }
    
    @Test
    public void fullPalauttaaTrueKunJonossaOnSenKoonVerranAlkioita() {
        lisaaAlkioitaJonoon(5);
        assertTrue(jono.full());
    }
    
    @Test
    public void kunTayteenJonoonTehdaanDequeueOperaatioNiinJonoEiOleTaysi() {
        lisaaAlkioitaJonoon(5);
        jono.dequeue();
        assertFalse(jono.full());
    }
    
    @Test
    public void kunTayteenJonoonTehdaanDequeueOperaatioNiinLisaaminenOnnistuu() {
        lisaaAlkioitaJonoon(5);
        jono.dequeue();
        assertTrue(jono.enqueue(solmut[0]));
    }
    
    @Test
    public void tayteenJonoonLisaaminenEiOnnistu() {
        lisaaAlkioitaJonoon(5);
        assertFalse(jono.enqueue(solmut[2]));
    }

}