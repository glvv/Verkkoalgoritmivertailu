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
        testaaPainollinenVerkko(11, new Fibonaccikeko(), false, 10);
    }

    /**
     * Metodilla voidaan testata Dijkstra ja A*-algoritmien toimintaa
     * painollisissa verkoissa. Metodi toistaa testit usemman kerran ja tulostaa
     * keskiarvot. Ensimmäinen luku on Dijkstran algoritmin suoritusaika ja
     * toinen on A*-algoritmin suoritusaika.
     *
     * @param testikuva Testikuvan indeksi testikuvataulukossa.
     * @param keko Keko, jota Dijkstra ja A* -algoritmit käyttävät.
     * @param luoRatkaisut Piirretäänkö algoritmien löytämät ratkaisut.
     * @param toistokertoja Kuinka monta kertaa testit toistetaan.
     */
    public static void testaaPainollinenVerkko(int testikuva, Minimikeko keko, boolean luoRatkaisut, int toistokertoja) {
        long[] tulokset = new long[2];
        for (int i = 0; i < toistokertoja; i++) {
            String kuva = testiPainollisetKentat[testikuva];
            keko.clear();
            tulokset[0] += testaaKentta(kuva, new Dijkstra(keko), luoRatkaisut, true);
            keko.clear();
            tulokset[1] += testaaKentta(kuva, new AStar(keko), luoRatkaisut, true);
        }
        for (int i = 0; i < 2; i++) {
            tulokset[i] = tulokset[i] / toistokertoja;
            System.out.println(tulokset[i]);
        }
        System.out.println();
    }

    /**
     * Metodi testaa polunetsintää painottomassa verkossa Dijkstra, A*, BFS ja
     * Bidirectional -algoritmeilla. Metodi toistaa testit usemman kerran ja
     * tulostaa keskiarvot. Ensimmäinen luku on Dijkstran algoritmin
     * suoritusaika, toinen on A*-algoritmin suoritusaika, kolmas on
     * BFS-algoritmin suoritusaika ja neljäs on Bidirectional-algoritmin
     * suoritusaika.
     *
     * @param testikuva Testikuvan indeksi testikuvataulukossa.
     * @param keko Keko, jota Dijkstra ja A* -algoritmit käyttävät.
     * @param luoRatkaisut Luodaanko kuvat, joihin ratkaisut ja läpikäydyt
     * solmut ovat piirretty.
     * @param toistoKertoja Kuinka monta kertaa testit suoritetaan.
     */
    public static void testaaPainotonVerkko(int testikuva, Minimikeko keko, boolean luoRatkaisut, int toistoKertoja) {
        long[] tulokset = new long[4];
        for (int i = 0; i < toistoKertoja; i++) {
            String kuva = testiLabyrintit[testikuva];
            keko.clear();
            tulokset[0] += testaaKentta(kuva, new Dijkstra(keko), luoRatkaisut, false);
            keko.clear();
            tulokset[1] += testaaKentta(kuva, new AStar(keko), luoRatkaisut, false);
            tulokset[2] += testaaKentta(kuva, new BFS(), luoRatkaisut, false);
            tulokset[3] += testaaKentta(kuva, new Bidirectional(), luoRatkaisut, false);
        }
        for (int i = 0; i < 4; i++) {
            tulokset[i] = tulokset[i] / toistoKertoja;
            System.out.println(tulokset[i]);
        }
        System.out.println("");
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
            kirjoitaRatkaisu(verkko, "images/ratkaisut/" + algoritmi.algoritminNimi() + "Ratkaisu.bmp", kentta, leveys - 1, pituus - 1);
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

    /**
     * Painottomien verkkojen testikuvien tiedostopolut.
     */
    private static final String[] testiLabyrintit = {
        //Vaikeat labyrintit 0 - 10.
        "images/labyrintit/labyrintti100100.bmp",
        "images/labyrintit/labyrintti251251.bmp",
        "images/labyrintit/labyrintti300300.bmp",
        "images/labyrintit/labyrintti251502.bmp",
        "images/labyrintit/labyrintti500500.bmp",
        "images/labyrintit/labyrintti5001000.bmp",
        "images/labyrintit/labyrintti10001000.bmp",
        "images/labyrintit/labyrintti10002000.bmp",
        "images/labyrintit/labyrintti20002000.bmp",
        "images/labyrintit/labyrintti20004000.bmp",
        "images/labyrintit/labyrintti40004000.bmp",
        //Helpot labyrintit 11 - 21.
        "images/helpotlabyrintit/helppolabyrintti100100.bmp",
        "images/helpotlabyrintit/helppolabyrintti200100.bmp",
        "images/helpotlabyrintit/helppolabyrintti200200.bmp",
        "images/helpotlabyrintit/helppolabyrintti400200.bmp",
        "images/helpotlabyrintit/helppolabyrintti400400.bmp",
        "images/helpotlabyrintit/helppolabyrintti800400.bmp",
        "images/helpotlabyrintit/helppolabyrintti800800.bmp",
        "images/helpotlabyrintit/helppolabyrintti1600800.bmp",
        "images/helpotlabyrintit/helppolabyrintti16001600.bmp",
        "images/helpotlabyrintit/helppolabyrintti32001600.bmp",
        "images/helpotlabyrintit/helppolabyrintti32003200.bmp",};

    /**
     * Painollisten kenttien tiedostopolut.
     */
    private static final String[] testiPainollisetKentat = {
        //Vaikeat painolliset kentät 0 - 10.
        "images/painollisetverkot/PAINOLLINENLABYRINTTI100100.bmp",
        "images/painollisetverkot/PAINOLLINENLABYRINTTI200100.bmp",
        "images/painollisetverkot/PAINOLLINENLABYRINTTI200200.bmp",
        "images/painollisetverkot/PAINOLLINENLABYRINTTI200400.bmp",
        "images/painollisetverkot/PAINOLLINENLABYRINTTI400400.bmp",
        "images/painollisetverkot/PAINOLLINENLABYRINTTI400800.bmp",
        "images/painollisetverkot/PAINOLLINENLABYRINTTI800800.bmp",
        "images/painollisetverkot/PAINOLLINENLABYRINTTI1600800.bmp",
        "images/painollisetverkot/PAINOLLINENLABYRINTTI16001600.bmp",
        "images/painollisetverkot/PAINOLLINENLABYRINTTI32001600.bmp",
        "images/painollisetverkot/PAINOLLINENLABYRINTTI32003200.bmp",
        //Helpot painolliset kentät 11 - 21.
        "images/helpotpainolliset/helppopainollinen100100.bmp",
        "images/helpotpainolliset/helppopainollinen200100.bmp",
        "images/helpotpainolliset/helppopainollinen200200.bmp",
        "images/helpotpainolliset/helppopainollinen400200.bmp",
        "images/helpotpainolliset/helppopainollinen400400.bmp",
        "images/helpotpainolliset/helppopainollinen800400.bmp",
        "images/helpotpainolliset/helppopainollinen800800.bmp",
        "images/helpotpainolliset/helppopainollinen1600800.bmp",
        "images/helpotpainolliset/helppopainollinen16001600.bmp",
        "images/helpotpainolliset/helppopainollinen32001600.bmp",
        "images/helpotpainolliset/helppopainollinen32003200.bmp",};

}
