package tietorakenteet;

import java.lang.reflect.Array;

public class Lista<E> {

    private E[] taulukko;
    private int indeksi;

    public Lista(Class<E> c, int koko) {
        taulukko = (E[]) Array.newInstance(c, koko);
        indeksi = 0;
    }
    
    public void add(E e) {
        if (indeksi != taulukko.length) {
            taulukko[indeksi] = e;
            indeksi++;
        }
    }

    public E[] haeTaulukko() {
        return taulukko;
    }
            
}