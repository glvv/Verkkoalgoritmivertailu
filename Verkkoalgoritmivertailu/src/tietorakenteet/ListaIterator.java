package tietorakenteet;

import java.util.Iterator;
import verkko.Kaari;

public class ListaIterator implements Iterator<Kaari> {

    private int koko;
    private int indeksi;
    private Kaari[] kaaret;
    
    public ListaIterator(int koko, Kaari[] kaaret) {
        this.koko = koko;
        this.indeksi = 0;
        this.kaaret = kaaret;
    }
    
    @Override
    public boolean hasNext() {
        return indeksi < koko;
    }

    @Override
    public Kaari next() {
        Kaari k = kaaret[indeksi];
        indeksi++;
        return k;
    }
    
}