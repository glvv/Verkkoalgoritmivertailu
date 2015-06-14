package vertailu;

import algoritmit.*;
import io.Bittikartta;
import tietorakenteet.*;
import verkko.Solmu;
import verkko.Verkko;

/**
 * Luokka tarjoaa toiminnallisuuden Polunetsintäalgoritmien suorituskyvyn
 * testaamiseen.
 */
public class Suorituskykytesti {

    public static void main(String[] args) {
        testaaLabyrintti(9, new Fibonaccikeko(), false, 10);
    }

    /**
     * Metodi testaa labyrintin Dijkstra, A*, BFS ja Bidirectional
     * -algoritmeilla.
     *
     * @param testikuva Testikuvan indeksi testikuvataulukossa.
     * @param keko Keko, jota Dijkstra ja A* -algoritmit käyttävät.
     * @param luoRatkaisut Luodaanko kuvat, joihin ratkaisut ja läpikäydyt
     * solmut ovat piirretty.
     */
    public static void testaaLabyrintti(int testikuva, Minimikeko keko, boolean luoRatkaisut, int toistoKertoja) {
        long[] tulokset = new long[2];
        for (int i = 0; i < toistoKertoja; i++) {
            String kuva = testiLabyrintit[testikuva];
            keko.clear();
            tulokset[0] += testaaKentta(kuva, new Dijkstra(keko), luoRatkaisut, false);
            keko.clear();
            tulokset[1] += testaaKentta(kuva, new AStar(keko), luoRatkaisut, false);
//            tulokset[2] += testaaKentta(kuva, new BFS(), luoRatkaisut, false);
//            tulokset[3] += testaaKentta(kuva, new Bidirectional(), luoRatkaisut, false);
        }
        for (int i = 0; i < 2; i++) {
            tulokset[i] = tulokset[i] / toistoKertoja;
            System.out.println(tulokset[i]);
        }
    }

    /**
     * Metodi testaa polunetsintäalgoritmin suorituskykyä -algoritmien
     * suorituskykyä mustavalkoisella labyrintilla, joka on painoton verkko.
     * Metodi luo labyrintin rakaisun images-kansioon. Ratkaisuissa polku on
     * väritetty punaisella, käydyt solmut harmaalla. Metodi palauttaa
     * polunetsintään kuluneen ajan.
     *
     * @param kuvaTiedostoPolku Kuvan tiedostopolku.
     * @param algoritmi Algoritmi, jolla polunetsintä suoritetaan.
     * @param luoRatkaisu Luodaanko uusi kuva, johon ratkaisu on piirretty.
     * @param luoKaaretMustiinRuutuihin Luodaanko kaaret mustiin ruutuihin. Kun
     * arvoksi asetetaan false ja kuva on mustavalkoinen saadaan aikaiseksi
     * painoton verkko.
     * @return Haun kesto.
     */
    public static long testaaKentta(String kuvaTiedostoPolku, PolunetsintaAlgoritmi algoritmi, boolean luoRatkaisu, boolean luoKaaretMustiinRuutuihin) {
        char[][] kentta = Bittikartta.luoKentta(kuvaTiedostoPolku);
        int leveys = kentta.length;
        int pituus = kentta[0].length;

        Verkko verkko = new Verkko(kentta, false, luoKaaretMustiinRuutuihin);
        
        System.gc();
        long a = System.currentTimeMillis();
        algoritmi.haeLyhinPolku(verkko, 0, 0, leveys - 1, pituus - 1);
        long b = System.currentTimeMillis();
        long tulos = b - a;

        if (luoRatkaisu) {
            kirjoitaRatkaisu(verkko, "images/" + algoritmi.algoritminNimi() + "Ratkaisu.bmp", kentta, leveys - 1, pituus - 1);
        }
        return tulos;
    }

    /**
     * Metodi luo kopion annetusta kaksiulotteisesta char-taulukosta, kirjoittaa
     * siihen läpikäydyt solmut ja algoritmin löytän ratkaisun. Char-taulukko
     * piirretään kuvaksi annetun nimiseen tiedostopolkuun.
     *
     * @param verkko Verkko, josta käydyt solmut ja ratkaisu haetaan.
     * @param tiedosto Tiedostopolku, johon ratkaisu kirjoitetaan.
     * @param kentta Kenttä, josta labyrintti kopioidaan.
     * @param loppuX Kohdesolmun x-koordinaatti.
     * @param loppuY Kohdesolmun y-koordinaatti.
     */
    private static void kirjoitaRatkaisu(Verkko verkko, String tiedosto, char[][] kentta, int loppuX, int loppuY) {
        char[][] ratkaisu = kopioiCharTaulukko(kentta);
        haeKaydytSolmut(ratkaisu, verkko);
        haeRatkaisu(verkko.haeSolmu(loppuX, loppuY), ratkaisu);
        Bittikartta.kirjoitaKuva(ratkaisu, tiedosto);
    }

    /**
     * Metodi seuraa kohdesolmusta lähtevää polkua ja kirjoittaa sen annettuun
     * taulukkoon.
     *
     * @param s Kohdesolmu, josta lähtevä polku kirjoitetaan.
     * @param ratkaisu Kaksiulotteinen char-taulukko, johon ratkaisu
     * kirjoitetaan.
     */
    private static void haeRatkaisu(Solmu s, char[][] ratkaisu) {
        ratkaisu[s.getX()][s.getY()] = '@';
        while (s.getEdellinen() != null) {
            s = s.getEdellinen();
            ratkaisu[s.getX()][s.getY()] = '@';
        }
    }

    /**
     * Metodi kay annetun verkon läpi ja asettaa char-taulukkoon huutomerkin
     * kaikkien solmujen, jotka algoritmi on merkinnyt läpikäydyksi, kohdalle.
     *
     * @param ratkaisu Kaksiulotteinen char-taulukko, johon läpikäydyt solmut
     * kirjoitetaan.
     * @param verkko Verkko, josta läpikäydyt solmut haetaan.
     */
    private static void haeKaydytSolmut(char[][] ratkaisu, Verkko verkko) {
        int leveys = verkko.haeLeveys();
        int pituus = verkko.haePituus();
        int kaytyjenSolmujenMaara = 0;
        for (int x = 0; x < leveys; x++) {
            for (int y = 0; y < pituus; y++) {
                if (verkko.haeSolmu(x, y).isKasitelty()) {
                    ratkaisu[x][y] = '!';
                    kaytyjenSolmujenMaara++;
                }
            }
        }
        System.out.println("Algoritmi merkitsee läpikäydyksi " + kaytyjenSolmujenMaara + " solmua.");
    }

    /**
     * Metodi palauttaa kopion annetusta char[][] -taulukosta.
     *
     * @param kopioitava char[][]-taulukko, josta kopio tehdään.
     * @return Kopio parametrina annetusta taulukosta.
     */
    private static char[][] kopioiCharTaulukko(char[][] kopioitava) {
        char[][] kopio = new char[kopioitava.length][kopioitava[0].length];
        for (int x = 0; x < kopioitava.length; x++) {
            for (int y = 0; y < kopioitava[0].length; y++) {
                kopio[x][y] = kopioitava[x][y];
            }
        }
        return kopio;
    }

    private static final String[] testiLabyrintit = {
        "images/labyrintti100100.bmp",
        "images/labyrintti251251.bmp",
        "images/labyrintti300300.bmp",
        "images/labyrintti251502.bmp",
        "images/labyrintti500500.bmp",
        "images/labyrintti5001000.bmp",
        "images/labyrintti10001000.bmp",
        "images/labyrintti10002000.bmp",
        "images/labyrintti20002000.bmp",
        "images/labyrintti20004000.bmp",
        "images/labyrintti40004000.bmp",};
}
