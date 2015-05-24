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
    private Lista lista;
    
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
        lista = new Lista(3);
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
        lista.add(s);
        Kaari e = lista.haeTaulukko()[0];
        assertEquals(s, e);
    }
    
    @Test
    public void lisaamisenJalkeenIndeksiOnOikein() {
        lista.add(s);
        assertEquals(1, lista.getIndeksi());
    }
    
    @Test
    public void listanVoiLisataTayteen() {
        lista.add(s);
        lista.add(s);
        lista.add(s);
        assertEquals(3, lista.getIndeksi());
    }
    
}
