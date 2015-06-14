package tietorakenteet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import verkko.Kaari;
import verkko.Solmu;

public class ListaTest {
    
    private Kaari s;
    private Lista<Kaari> lista;
    
    public ListaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        lista = new Lista<>(3);
        s = new Kaari(new Solmu(5, 5), 2);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void luodullaListallaOnOikeanPituinenTaulukko() {
        assertEquals(3, lista.haeTaulukko().length);
    }
    
    @Test
    public void luodullaListanIndeksiOn0() {
        assertEquals(0, lista.getIndeksi());
    }
    
    @Test
    public void lisaamisenJalkeenLisattyAlkioOnOikeassaPaikassa() {
        lisaaKaaria(1);
        Kaari e = (Kaari) lista.haeTaulukko()[0];
        assertEquals(s, e);
    }
    
    @Test
    public void lisaamisenJalkeenIndeksiOnOikein() {
        lisaaKaaria(1);
        assertEquals(1, lista.getIndeksi());
    }
    
    private void lisaaKaaria(int maara) {
        for (int i = 0; i < maara; i++) {
            lista.add(s);
        }
    }
    
    @Test
    public void listanVoiLisataTayteen() {
        lisaaKaaria(3);
        assertEquals(3, lista.getIndeksi());
    }
    
    @Test
    public void kunListaOnTaynnaSeKasvaaKaksinKertaiseksiKunSiihenLisataan() {
        lisaaKaaria(4);
        assertEquals(6, lista.koko());
    }
    
}
