package tietorakenteet;

import java.util.Iterator;
import verkko.Kaari;

/**
 * Lista on taulukkopohjainen tietorakennne, johon voi lisätä kaaria. Kaaret
 * menevät taulukkoon lisäämisjärjestyksessä. Listaan lisääminen ei onnistu, jos
 * taulukko on täynnä.
 *
 */
public class Lista implements Iterable<Kaari> {

    private final Kaari[] kaaret;
    private int indeksi;

    /**
     * Konstruktorissa määritetään taulukon koko.
     *
     * @param koko Listan koko.
     */
    public Lista(int koko) {
        kaaret = new Kaari[koko];
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
        if (indeksi != kaaret.length) {
            kaaret[indeksi] = e;
            indeksi++;
        }
    }

    /**
     * Palauttaa listan sisältämän taulukon.
     *
     * @return Listan sisältämä taulukko.
     */
    public Kaari[] haeTaulukko() {
        return kaaret;
    }

    @Override
    public Iterator<Kaari> iterator() {
        return new ListaIterator(indeksi, kaaret);
    }
    
    public Kaari get(int indeksi) {
        return kaaret[indeksi];
    }

}
