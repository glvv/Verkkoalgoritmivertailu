package tietorakenteet;

import verkko.Kaari;

/**
 * Lista on taulukkopohjainen tietorakennne, johon voi lisätä kaaria. Kaaret
 * menevät taulukkoon lisäämisjärjestyksessä. Listaan lisääminen ei onnistu, jos
 * taulukko on täynnä.
 *
 */
public class Lista {

    private final Kaari[] taulukko;
    private int indeksi;

    /**
     * Konstruktorissa määritetään taulukon koko.
     *
     * @param koko Listan koko.
     */
    public Lista(int koko) {
        taulukko = new Kaari[koko];
        indeksi = 0;
    }

    public int getIndeksi() {
        return indeksi;
    }

    /**
     * Metodilla lisätään taulukkoon solmu.
     *
     * @param e Lisättävä kaari.
     */
    public void add(Kaari e) {
        if (indeksi != taulukko.length) {
            taulukko[indeksi] = e;
            indeksi++;
        }
    }

    /**
     * Palauttaa listan sisältämän taulukon.
     *
     * @return Listan sisältämä taulukko.
     */
    public Kaari[] haeTaulukko() {
        return taulukko;
    }

}
