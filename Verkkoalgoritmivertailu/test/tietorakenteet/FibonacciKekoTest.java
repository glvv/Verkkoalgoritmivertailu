package tietorakenteet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import verkko.Solmu;

public class FibonacciKekoTest {

    private Fibonaccikeko keko;
    private Solmu[] solmut;

    public FibonacciKekoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.keko = new Fibonaccikeko();
        this.solmut = luoSolmuja(10);
    }
    
    @After
    public void tearDown() {
    }
    
    private Solmu[] luoSolmuja(int maara) {
        Solmu[] s = new Solmu[maara];
        for (int i = 0; i < maara; i++) {
            s[i] = new Solmu(i, i);
            s[i].setMinimiEtaisyys(i);
        }
        return s;
    }

    private void lisaaSolmujaKekoon(int maara) {
        for (int i = 0; i < maara; i++) {
            keko.insert(solmut[i]);
        }
    }
    
    @Test
    public void kahdenInsertOperaationJuuriListassaMinAlkionViitteetOvatOikein() {
        lisaaSolmujaKekoon(2);
        FibonaccikekoSolmu min = keko.getMin();
        assertEquals(solmut[1].getSolmuKeossa(), min.getLeft());
        assertEquals(solmut[1].getSolmuKeossa(), min.getRight());
    }
    
    @Test
    public void kahdenInsertOperaationJuuriListassaToisenAlkionViitteetOvatOikein() {
        lisaaSolmujaKekoon(2);
        FibonaccikekoSolmu min = keko.getMin();
        FibonaccikekoSolmu toinen = (FibonaccikekoSolmu) solmut[1].getSolmuKeossa();
        assertEquals(min, toinen.getLeft());
        assertEquals(min, toinen.getRight());
    }
    
    @Test
    public void yhdenAlkionLisaamisenJalkeenLisattyAlkioSaadaanDelMinOperaatiolla() {
        keko.insert(solmut[0]);
        assertEquals(solmut[0], keko.delMin());
    }
    
    @Test
    public void yhdenDelMinOperaationJalkeenDelMinPalauttaaOikeanSolmun() {
        lisaaSolmujaKekoon(5);
        keko.delMin();
        assertEquals(solmut[1], keko.delMin());
    }
    
    @Test
    public void kahdenDelMinOperaationJalkeenDelMinPalauttaaOikeanArvon() {
        lisaaSolmujaKekoon(5);
        keko.delMin();
        keko.delMin();
        assertEquals(solmut[2], keko.delMin());
    }
    
    @Test
    public void decreaseKeyOperaationJalkeenAlkioSaadaanMinOperaatiollaJosSeOnUusiPienin() {
        lisaaSolmujaKekoon(10);
        solmut[9].setMinimiEtaisyys(-1);
        keko.decreaseKey(solmut[9]);
        assertEquals(solmut[9], keko.delMin());
    }
    
    @Test
    public void decreaseKeyOperaationJalkeenVanhaMinOnEnnallaanJosSeEdelleenOnPienin() {
        lisaaSolmujaKekoon(10);
        solmut[9].setMinimiEtaisyys(6);
        assertEquals(solmut[0],keko.delMin());
    }
    

    
}
