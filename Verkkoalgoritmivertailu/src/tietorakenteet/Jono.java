package tietorakenteet;

import verkko.Solmu;

/**
 * Jono on Solmu-olioita tallettava taulukkopohjainen jono.
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
        int l1 = loppu + 1;
        if (l1 == koko) {
            l1 = 0;
        }
        return l1 == alku;
    }

    /**
     * Metodilla lisätään uusi Solmu jonoon.
     *
     * @param s Lisättävä solmu.
     * @return Onnistuiko lisäys.
     */
    public boolean enqueue(Solmu s) {
        if (!full()) {
            solmut[loppu] = s;
            loppu++;
            if (loppu == koko) {
                loppu = 0;
            }
            return true;
        }
        return false;
    }

    /**
     * Metodilla poistetaan ensimmäisenä lisätty alkio.
     *
     * @return Poistettu solmu.
     */
    public Solmu dequeue() {
        Solmu s = solmut[alku];
        alku++;
        if (alku == koko) {
            alku = 0;
        }
        return s;
    }

}
