package tietorakenteet;

import java.util.Iterator;

/**
 * Lista on järjestyksessä täyttyvä taulukko, joka kasvaa automaattisesti.
 *
 * @param <E> Tyyppiparametri, joka kertoo millaisia olioita Listaan
 * talletetaan.
 */
public class Lista<E> implements Iterable<E> {

    private Object[] taulukko;
    private int indeksi;

    /**
     * Konstruktorissa määritetään taulukon koko.
     *
     * @param koko Listan koko.
     */
    public Lista(int koko) {
        taulukko = new Object[koko];
        indeksi = 0;
    }

    public int getIndeksi() {
        return indeksi;
    }

    /**
     * Metodilla lisätään taulukkoon alkio.
     *
     * @param e Lisättävä alkio.
     */
    public void add(E e) {
        if (indeksi != taulukko.length) {
            taulukko[indeksi] = e;
            indeksi++;
        } else {
            kasvataKaksinkertaiseksi();
            add(e);
        }
    }

    /**
     * Palauttaa listan sisältämän taulukon.
     *
     * @return Listan sisältämä taulukko.
     */
    public Object[] haeTaulukko() {
        return (E[]) taulukko;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListaIterator(indeksi, taulukko);
    }

    /**
     * Metodi palauttaa alkion annetussa indeksissä.
     *
     * @param indeksi Indeksi, josta alkio haetaan.
     * @return Alkio annetussa indeksissä.
     */
    public E get(int indeksi) {
        return (E) taulukko[indeksi];
    }

    /**
     * Metodi kasvattaa Listan koon kaksinkertaiseksi.
     */
    private void kasvataKaksinkertaiseksi() {
        Object[] uusiTaulukko = new Object[taulukko.length * 2];
        for (int i = 0; i < taulukko.length; i++) {
            uusiTaulukko[i] = taulukko[i];
        }
        taulukko = uusiTaulukko;
    }
    
    public int koko() {
        return taulukko.length;
    }

}
