package keot;

import java.util.PriorityQueue;
import verkko.Solmu;

/**
 * Luokka käärii sisäänsä Javan PriorityQueue-olion.
 *
 */
public class JavaPriorityQueue implements Minimikeko {

    private final PriorityQueue<Solmu> keko;

    /**
     * Konstruktorissa luodaan uusi PriorityQueue-olio, joka talletetaan
     * oliomuuttujaan.
     */
    public JavaPriorityQueue() {
        this.keko = new PriorityQueue<>();
    }

    @Override
    public void insert(Solmu e) {
        keko.add(e);
    }

    @Override
    public Solmu min() {
        return keko.peek();
    }

    @Override
    public Solmu delMin() {
        return keko.poll();
    }

    /**
     * DecreaseKey on toteutettu siten, että ensin poistetaan alkio ja se
     * lisätään sinne uudestaan. Metodia ei tule kutsua, jos alkioita ei ole
     * keossa.
     *
     * @param e Alkio, jonka järjestystä muutetaan.
     */
    @Override
    public void decreaseKey(Solmu e) {
        keko.remove(e);
        keko.add(e);
    }

    @Override
    public boolean empty() {
        return keko.isEmpty();
    }

    @Override
    public void clear() {
        keko.clear();
    }

    @Override
    public int size() {
        return keko.size();
    }

}
