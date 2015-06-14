package tietorakenteet;

import verkko.Solmu;

/**
 * Luokka, jonka avulla Solmu-olioita talletetaan kekoon.
 */
public class BinaariKekoSolmu implements Comparable<BinaariKekoSolmu>, KekoSolmu {

    private final Solmu solmu;
    private int avainArvo;
    private int indeksiKeossa;

    /**
     * Konstruktorissa luodaan uusi KekoSolmu, parametrina annettuun solmuun
     * tallennetaan viite ja avainarvo lasketaan.
     *
     * @param s Solmu, johon KekoSOlmu liittyy.
     */
    public BinaariKekoSolmu(Solmu s) {
        this.solmu = s;
        this.avainArvo = s.getMinimiEtaisyys() + s.getEtaisyysLoppusolmuun();
    }

    public int getAvainArvo() {
        return avainArvo;
    }

    public void setAvainArvo(int avainArvo) {
        this.avainArvo = avainArvo;
    }

    public Solmu getSolmu() {
        return solmu;
    }

    @Override
    public int compareTo(BinaariKekoSolmu o) {
        return this.avainArvo - o.avainArvo;
    }

    public int getIndeksiKeossa() {
        return indeksiKeossa;
    }

    public void setIndeksiKeossa(int indeksiKeossa) {
        this.indeksiKeossa = indeksiKeossa;
    }

}
