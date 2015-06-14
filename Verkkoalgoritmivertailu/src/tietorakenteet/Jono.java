package tietorakenteet;

import verkko.Solmu;

/**
 * Jono on Solmu olioita tallettava taulukkopohjainen jono, joka kasvaa automaattisesti.
 */
public class Jono {

    private Solmu[] solmut;
    private int alku;
    private int loppu;
    private int koko;

    /**
     * Konstruktorissa luodaan taulukko Solmu-olioita varten
     *
     * @param koko
     */
    public Jono(int koko) {
        solmut = new Solmu[koko];
        loppu = 0;
        alku = 0;
        this.koko = koko;
    }

    /**
     * Metodi kertoo onko jono tyhjä.
     *
     * @return Boolean-arvo riippuen siitä onko jono tyhjä.
     */
    public boolean empty() {
        return loppu == alku;
    }

    /**
     * Metodi kertoo onko jono täysi.
     *
     * @return Boolean-arvo riippuen siitä onko jono täysi.
     */
    public boolean full() {
        return loppu == koko;
    }

    /**
     * Metodilla lisätään uusi Solmu jonoon.
     *
     * @param s Lisättävä solmu.
     */
    public void enqueue(Solmu s) {
        if (!full()) {
            solmut[loppu] = s;
            loppu++;
        } else {
            grow();
            enqueue(s);
        }
    }

    /**
     * Metodilla poistetaan ensimmäisenä lisätty alkio.
     *
     * @return Poistettu solmu.
     */
    public Solmu dequeue() {
        if (!empty()) {
            Solmu s = solmut[alku];
            alku++;
            return s;
        }
        return null;
    }

    /**
     * Metodi kasvattaa jonon kaksinkertaiseksi.
     */
    private void grow() {
        Solmu[] isompiKopio = new Solmu[solmut.length * 2];
        int a = 0;
        for (int i = alku; i < loppu; i++) {
            isompiKopio[a] = solmut[i];
            a++;
        }
        alku = 0;
        loppu = a;
        koko = solmut.length * 2;
        solmut = isompiKopio;
    }

    public int getKoko() {
        return koko;
    }

}
