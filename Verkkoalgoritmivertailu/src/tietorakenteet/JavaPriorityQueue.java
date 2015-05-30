package tietorakenteet;

import java.util.PriorityQueue;
import verkko.Solmu;

/**
 * Luokka käärii sisäänsä Javan PriorityQueue-olion.
 *
 */
public class JavaPriorityQueue implements Minimikeko {

    private final PriorityQueue<KekoSolmu> keko;

    /**
     * Konstruktorissa luodaan uusi PriorityQueue-olio, joka talletetaan
     * oliomuuttujaan.
     */
    public JavaPriorityQueue() {
        this.keko = new PriorityQueue<>();
    }

    /**
     * Metodilla lisätään Solmu kekoon. Solmu lisätään KekoSolmu-oliona, joka
     * sisältää viitteen lisättäävään solmuun.
     *
     * @param e Lisättävä Solmu.
     */
    @Override
    public void insert(Solmu e) {
        KekoSolmu uusi = new KekoSolmu(e);
        e.setSolmuKeossa(uusi);
        keko.add(uusi);
    }

    /**
     * Palauttaa pienimmän alkion keossa.
     *
     * @return Pienin alkio keossa.
     */
    @Override
    public Solmu min() {
        return keko.peek().getSolmu();
    }

    /**
     * Palauttaa ja poistaa pienimmän alkion.
     *
     * @return Pienin alkio keossa.
     */
    @Override
    public Solmu delMin() {
        return keko.poll().getSolmu();
    }

    /**
     * DecreaseKey on toteutettu siten, että ensin poistetaan alkio ja se
     * lisätään sinne uudestaan.
     *
     * @param vanha Solmu, jota päivitetään.
     */
    @Override
    public void decreaseKey(Solmu vanha) {
        int uusiArvo = vanha.getEtaisyysLoppusolmuun() + vanha.getMinimiEtaisyys();
        KekoSolmu ks = vanha.getSolmuKeossa();
        if (keko.contains(ks)) {
            keko.remove(ks);
            ks.setAvainArvo(uusiArvo);
            keko.add(ks);
        }
    }

    /**
     * Metodi keroo onko keko tyhjä.
     *
     * @return Boolean-arvo riippuen siitä, onko keko tyhjä vai ei.
     */
    @Override
    public boolean empty() {
        return keko.isEmpty();
    }

    /**
     * Metodi tyhjentää keon.
     */
    @Override
    public void clear() {
        keko.clear();
    }

    /**
     * Metodin palauttaa keon koon.
     *
     * @return Keon koko.
     */
    @Override
    public int size() {
        return keko.size();
    }

}
