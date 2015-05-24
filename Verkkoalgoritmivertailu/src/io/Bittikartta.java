package io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Luokka, joka muuttaa tietyillä väreillä tehtyjä kuvia kaksiulotteisiksi
 * char-taulukoiksi ja takaisin.
 */
public class Bittikartta {

    //RGB-koodeja
    private static final int suo = -8355840;
    private static final int valkoinen = -1;
    private static final int vesi = -16711681;
    private static final int maasto = -8372224;
    private static final int punainen = - 8388608;
    private static final int musta = -16777216;
    private static final int ratkaisuPunainen = - 4388608;

    /**
     * Metodi hakee annetusta tiedostopolusta tiedoston ja luo siitä
     * char-taulukon, jossa eri värit vastaavat eri merkkejä.
     *
     * @param tiedosto Tiedostopolku, josta kuva haetaan.
     * @return Kaksiulotteinen char-taulukko, johon kuva on tallennettu eri
     * merkeillä.
     */
    public static char[][] luoKentta(String tiedosto) {
        BufferedImage kuva = haeKuva(tiedosto);
        int leveys = kuva.getWidth();
        int korkeus = kuva.getHeight();
        char[][] kentta = new char[leveys][korkeus];
        for (int x = 0; x < leveys; x++) {
            for (int y = 0; y < korkeus; y++) {
                asetaRuutu(x, y, kentta, kuva);
            }
        }
        return kentta;
    }

    /**
     * Metodi palauttaa tiedostopolussa olevan kuvan BufferedImage-oliona.
     *
     * @param tiedosto Tiedostopolku, josta kuva haetaan.
     * @return Haettu kuva BufferedImage-oliona.
     */
    private static BufferedImage haeKuva(String tiedosto) {
        BufferedImage kuva = null;
        try {
            kuva = ImageIO.read(new File(tiedosto));
        } catch (Exception x) {

        }
        return kuva;
    }

    /**
     * Metodi hakee BufferedImage-kuvasta koordinaateilla pikselin ja asettaa
     * sen väriä vastaavan merkin char-taulukkoon annettuihin koordinaatteihin.
     *
     * @param x X-koordinaatti.
     * @param y Y-koordinaatti.
     * @param kentta Kaksiulotteinen char-taulukko, johon pikseliä kuvaava
     * merkki talletetaan.
     * @param kuva BufferedImage-tyyppinen kuva, josta väri haetaan.
     */
    private static void asetaRuutu(int x, int y, char[][] kentta, BufferedImage kuva) {
        int pikseli = kuva.getRGB(x, y);
        if (pikseli == valkoinen) {
            kentta[x][y] = '.';
        } else if (pikseli == suo) {
            kentta[x][y] = '&';
        } else if (pikseli == vesi) {
            kentta[x][y] = '~';
        } else if (pikseli == musta) {
            kentta[x][y] = '#';
        } else if (pikseli == punainen) {
            kentta[x][y] = '¤';
        } else if (pikseli == maasto) {
            kentta[x][y] = '=';
        }
    }

    /**
     * Metodi muuttaa kaksiulotteisen char-taulukon bmp-kuvaksi annettuun
     * tiedostopolkuun.
     *
     * @param ratkaisu Kaksiulotteinen char-taulukko, josta kuvan pikselien
     * varit luetaan.
     * @param tiedostoPolku Tiedostopolku, johon kuva kirjoitetaan.
     */
    public static void kirjoitaKuva(char[][] ratkaisu, String tiedostoPolku) {
        try {
            BufferedImage ratkaistuKuva = new BufferedImage(ratkaisu.length, ratkaisu[0].length, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < ratkaisu.length; x++) {
                for (int y = 0; y < ratkaisu[0].length; y++) {
                    asetaPikseli(ratkaisu, ratkaistuKuva, x, y);
                }
            }
            ImageIO.write(ratkaistuKuva, "bmp", new File(tiedostoPolku));
        } catch (IOException ex) {

        }
    }

    /**
     * Metodi hakee kaksiulotteisesta char-taulukosta koordinaateilla merkin ja
     * asettaa BufferedImage-kuvaan näihin koordinaatteihin merkkiä vastaavan
     * värin.
     *
     * @param ratkaisu char-taulukko, josta merkki haetaan.
     * @param ratkaistuKuva BufferedImage-johon väri asetetaan.
     * @param x X-koordinaatti.
     * @param y Y-Koordinaatti;
     */
    private static void asetaPikseli(char[][] ratkaisu, BufferedImage ratkaistuKuva, int x, int y) {
        char c = ratkaisu[x][y];
        if (c == '.') {
            ratkaistuKuva.setRGB(x, y, valkoinen);
        } else if (c == '&') {
            ratkaistuKuva.setRGB(x, y, suo);
        } else if (c == '~') {
            ratkaistuKuva.setRGB(x, y, vesi);
        } else if (c == '#') {
            ratkaistuKuva.setRGB(x, y, musta);
        } else if (c == '¤') {
            ratkaistuKuva.setRGB(x, y, punainen);
        } else if (c == '=') {
            ratkaistuKuva.setRGB(x, y, maasto);
        } else if (c == '@') {
            ratkaistuKuva.setRGB(x, y, ratkaisuPunainen);
        }
    }
}
