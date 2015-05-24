package keot;

import verkko.Solmu;

/**
 * Rajapinta, joka määrittelee minimikeon toiminnallisuuden.
 */
public interface Minimikeko {

    public void insert(Solmu u);

    public void decreaseKey(Solmu e);

    public Solmu min();

    public Solmu delMin();

    public boolean empty();

    public void clear();

    public int size();

}
