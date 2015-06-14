package tietorakenteet;

import java.util.Iterator;

/**
 * Iteraattori Lista-luokan olioiden iterointia varten.
 *
 * @param <E> Minkä tyyppisiä alkioita iteroidaan.
 */
public class ListaIterator<E> implements Iterator<E> {

    private int alkioita;
    private int indeksi;
    private final E[] taulukko;

    public ListaIterator(int koko, E[] kaaret) {
        this.alkioita = koko;
        this.indeksi = 0;
        this.taulukko = kaaret;
    }

    @Override
    public boolean hasNext() {
        return indeksi < alkioita;
    }

    @Override
    public E next() {
        E k = taulukko[indeksi];
        indeksi++;
        return k;
    }

}
