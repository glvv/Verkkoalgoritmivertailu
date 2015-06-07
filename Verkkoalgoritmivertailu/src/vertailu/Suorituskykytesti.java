package vertailu;

import algoritmit.*;
import io.Bittikartta;
import tietorakenteet.Binaarikeko;
import verkko.Solmu;
import verkko.Verkko;

public class Suorituskykytesti {

    public static void main(String[] args) {
        testaaLabyrintti("labyrintti100100");
        testaaLabyrintti("labyrintti250250");
        testaaLabyrintti("labyrintti300300");
        testaaLabyrintti("labyrintti250500");
        testaaLabyrintti("labyrintti500500");
        testaaLabyrintti("labyrintti5001000");
        testaaLabyrintti("labyrintti10001000");
        testaaLabyrintti("labyrintti10002000");
        testaaLabyrintti("labyrintti20002000");
        testaaLabyrintti("labyrintti20004000");
        testaaLabyrintti("labyrintti40004000");
    }

    /**
     * Metodi testaa Dijkstra, A*, Bellman-Ford, BFS ja Bidirectional
     * algoritmien suorituskykyä mustavalkoisella labyrintilla. Metodi luo eri
     * algoritmien ratkaisut samaan kansioon. Ratkaisuissa polku on väritetty
     * punaisella, käydyt solmut harmaalla. Metodi on tarkoitettu painottomien
     * verkkojen testaamiseen.
     *
     * @param kuvaTiedosto bmp-muotoisen kuvatiedoston nimi images-kansiossa
     * ilman tiedostopäätettä.
     */
    public static void testaaLabyrintti(String kuvaTiedosto) {
        char[][] kentta = Bittikartta.luoKentta("images/" + kuvaTiedosto + ".bmp");
        int leveys = kentta.length;
        int pituus = kentta[0].length;

        Binaarikeko keko = new Binaarikeko(leveys * pituus);
        Dijkstra dijkstra = new Dijkstra(keko);

        Verkko verkko = new Verkko(kentta, false, false);
        System.out.println("Verkossa on " + verkko.haeSolmujenMaara() + " solmua ja " + verkko.haeKaartenMaara() + " kaarta.");

        long a = System.currentTimeMillis();
        dijkstra.haeLyhimmatPolut(0, 0, leveys - 1, pituus - 1, verkko);
        long b = System.currentTimeMillis();
        long dijkstranTulos = b - a;
        keko.clear();
        System.out.println("Dijkstran algoritmilla reitinhaku kesti " + dijkstranTulos + " millisekuntia.");
        kirjoitaRatkaisu(verkko, "images/" + kuvaTiedosto + "DijkstraRatkaisu.bmp", kentta, leveys - 1, pituus - 1);

        verkko = new Verkko(kentta, false, false);
        AStar astar = new AStar(keko);
        a = System.currentTimeMillis();
        astar.haeLyhinPolku(verkko, 0, 0, leveys - 1, pituus - 1);
        b = System.currentTimeMillis();
        long aStarTulos = b - a;
        keko.clear();
        System.out.println("A*-algoritmilla reitinhaku kesti " + aStarTulos + " millisekuntia.");
        kirjoitaRatkaisu(verkko, "images/" + kuvaTiedosto + "AStarRatkaisu.bmp", kentta, leveys - 1, pituus - 1);

        verkko = new Verkko(kentta, false, false);
        BFS bfs = new BFS();
        a = System.currentTimeMillis();
        bfs.haeLyhinPolku(verkko, 0, 0, leveys - 1, pituus - 1);
        b = System.currentTimeMillis();
        long bfsTulos = b - a;
        System.out.println("Leveyssuuntaisella läpikäynnillä reitinhaku kesti " + bfsTulos + " millisekuntia.");
        kirjoitaRatkaisu(verkko, "images/" + kuvaTiedosto + "BFSRatkaisu.bmp", kentta, leveys - 1, pituus - 1);

        verkko = new Verkko(kentta, false, false);
        Bidirectional bd = new Bidirectional();
        a = System.currentTimeMillis();
        bd.haeLyhinPolku(verkko, 0, 0, leveys - 1, pituus - 1);
        b = System.currentTimeMillis();
        long bdTulos = b - a;
        System.out.println("Bidirectional-algoritmilla reitinhaku kesti " + bdTulos + " millisekuntia.");
        kirjoitaRatkaisu(verkko, "images/" + kuvaTiedosto + "BiDirectionalRatkaisu.bmp", kentta, leveys - 1, pituus - 1);

//        verkko = new Verkko(kentta, false, false);
//        BellmanFord bmf = new BellmanFord();
//        a = System.currentTimeMillis();
//        bmf.haeLyhimmatPolut(verkko, 0, 0);
//        b = System.currentTimeMillis();
//        long bmfTulos = b - a;
//        System.out.println("Bellman-Fordin algoritmilla reitinhaku kesti " + bmfTulos + " millisekuntia.");
//        kirjoitaRatkaisu(verkko, "images/" + kuvaTiedosto + "BMFRatkaisu.bmp", kentta, leveys - 1, pituus - 1);
        System.out.println();
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
     * kaikkien solmujen, jotka algoritmi on läpikäynyt, kohdalle. Bellman-Ford
     * algoritmi ei merkitse solmuja.
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
}
