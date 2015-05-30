package tietorakenteet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import verkko.Solmu;

public class BinaarikekoTest {

    private Solmu[] solmut;
    private Binaarikeko keko;

    public BinaarikekoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        solmut = luoSolmut();
        keko = new Binaarikeko(5);
    }

    @After
    public void tearDown() {

    }

    private Solmu[] luoSolmut() {
        Solmu s0 = new Solmu(1, 1);
        s0.setMinimiEtaisyys(5);
        Solmu s1 = new Solmu(1, 1);
        s1.setMinimiEtaisyys(4);
        Solmu s2 = new Solmu(1, 1);
        s2.setMinimiEtaisyys(3);
        Solmu s3 = new Solmu(1, 1);
        s3.setMinimiEtaisyys(2);
        Solmu s4 = new Solmu(1, 1);
        s4.setMinimiEtaisyys(1);

        Solmu[] s = {s0, s1, s2, s3, s4};
        return s;
    }

    @Test
    public void luodunBinaariKeonKokoOn0() {
        assertEquals(0, keko.koko);
    }

    @Test
    public void lisayksenJalkeenKokoOn1() {
        keko.insert(solmut[0]);
        assertEquals(1, keko.koko);
    }

    @Test
    public void lisayksenJalkeenAlkioSaadaanDelMinOperaatiolla() {
        keko.insert(solmut[0]);
        Solmu s = keko.delMin();
        assertEquals(solmut[0], s);
    }

    @Test
    public void lisayksenJalkeenKekoSolmunIndeksiOnOikein() {
        keko.insert(solmut[0]);
        assertEquals(0, solmut[0].getSolmuKeossa().getIndeksiKeossa());
    }
    
    private void lisaaSolmujaKekoon(int i) {
        for (int a = 0; a < i; a++) {
            keko.insert(solmut[a]);
        }
    }
    
    private void lisaaKekoSolmujaKekoonKaanteisessaJarjestyksessa(int i) {
        for (int a = i - 1; a >= 0; a--) {
            keko.insert(solmut[a]);
        }
    }
    
    @Test
    public void kahdenAlkionLisaamisenJalkeenJarjestysOnOikein() {
        lisaaSolmujaKekoon(2);
        assertEquals(solmut[1], keko.delMin());
    }
    
    @Test
    public void lisatessaPieninPysyyEnsimmaisena() {
        keko.insert(solmut[4]);
        lisaaSolmujaKekoon(4);
        assertEquals(solmut[4], keko.delMin());
    }
    
    @Test
    public void kahdenSolmunLisaamisenJalkeenJalkimmaisenIndeksiOnOikeinKunJarjestystaMuutetaan() {
        lisaaSolmujaKekoon(2);
        assertEquals(0, solmut[1].getSolmuKeossa().getIndeksiKeossa());
    }
    
    @Test
    public void kahdenAlkionLisaamisenJalkeenEnsimmaisenIndeksiOnOikeinKunJarjestystaMuutetaan() {
        lisaaSolmujaKekoon(2);
        assertEquals(1, solmut[0].getSolmuKeossa().getIndeksiKeossa());
    }
    
    @Test
    public void kahdenSolmunLisaamisenJalkeenJalkimmaisenIndeksiOnOikeinKunJarjestystaEiMuuteta() {
        keko.insert(solmut[1]);
        keko.insert(solmut[0]);
        assertEquals(0, solmut[1].getSolmuKeossa().getIndeksiKeossa());
    }
    
    @Test
    public void kahdenSolmunLisaamisenJalkeenEnsimmaisenIndeksiOnOikeinKunJarjestystaEiMuuteta() {
        keko.insert(solmut[1]);
        keko.insert(solmut[0]);
        assertEquals(1, solmut[0].getSolmuKeossa().getIndeksiKeossa());
    }
    
    @Test
    public void viidenSolmunLisaamisenJalkeenViimeisenIndeksiOnOikein() {
        lisaaSolmujaKekoon(5);
        assertEquals(0, solmut[4].getSolmuKeossa().getIndeksiKeossa());
    }
    
    @Test
    public void viidenSolmunLisaamisenJalkeenDelMinPalauttaaPienimman() {
        lisaaSolmujaKekoon(5);
        assertEquals(solmut[4], keko.delMin());
    }
    
    private boolean tarkistaIndeksit(KekoSolmu[] kekosolmut) {
        for (int i = 0; i < kekosolmut.length; i++) {
            if (kekosolmut[i].getIndeksiKeossa() != i) {
                return false;
            }
        }
        return true; 
    }
    
    @Test
    public void viidenSolmunLisaamisenJalkeenIndeksitOvatOikein() {
        lisaaSolmujaKekoon(5);
        assertTrue(tarkistaIndeksit(keko.getSolmut()));
    }
    
    @Test
    public void viidenSolmunLisaamisessaKaanteisessaJarjestyksessaIndeksitOvatOikein() {
        lisaaKekoSolmujaKekoonKaanteisessaJarjestyksessa(5);
        assertTrue(tarkistaIndeksit(keko.getSolmut()));
    }
    
    private void muutaMinimietaisyyttaKeossa(int uusiEtaisyys, int alkioidenMaara) {
        lisaaSolmujaKekoon(alkioidenMaara);
        solmut[0].setMinimiEtaisyys(uusiEtaisyys);
        keko.decreaseKey(solmut[0]);
    }
    
    @Test
    public void decreaseKeyOperaationJalkeenJarjestysOnOikeinKunAlkiotOvatEnsinEpajarjestyksessa() {
        muutaMinimietaisyyttaKeossa(1, 2);
        assertEquals(solmut[0], keko.min());
    }
    
    @Test
    public void decreaseKeyOperaationJalkeenJarjestysOnOikeinKunAlkiotOvatEnsinJarjestyksessa() {
        muutaMinimietaisyyttaKeossa(5, 2);
        assertEquals(solmut[1], keko.min());
    }
    
    @Test
    public void decreaseKeyOperaationJalkeenIndeksitOvatKekoSolmuissaOikeinKunJarjestystaOnMuutettu() {
        muutaMinimietaisyyttaKeossa(1, 2);
        assertEquals(0, keko.getSolmut()[0].getIndeksiKeossa());
    }
    
    @Test
    public void decreaseKeyOperaationJalkeenIndeksitOvatKekoSolmuissaOikeinKunJarjestystaEiOleMuutettu() {
        muutaMinimietaisyyttaKeossa(5, 2);
        assertEquals(1, solmut[0].getSolmuKeossa().getIndeksiKeossa());
    }
    
    @Test
    public void delMinOperaationJalkeenKokoVaheneeYhdella() {
        lisaaSolmujaKekoon(5);
        keko.delMin();
        assertEquals(4, keko.koko);
    }
    
    @Test
    public void delMinOperaationJalkeenToiseksiPieninAlkioOnIndeksissa0() {
        lisaaSolmujaKekoon(5);
        keko.delMin();
        assertEquals(solmut[3], keko.min());
    }
    
    @Test
    public void delMinOperaationJalkeenToiseksiPienimmanAlkionIndeksiPaivittyy() {
        lisaaSolmujaKekoon(5);
        keko.delMin();
        assertEquals(0, solmut[3].getSolmuKeossa().getIndeksiKeossa());
    }
    
}
