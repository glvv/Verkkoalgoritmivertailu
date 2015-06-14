package algoritmit;

import verkko.Verkko;

/**
 * Rajapinta määrittelee polunetsintä algoritmin toiminnallisuuden.
 */
public interface PolunetsintaAlgoritmi {

    public void haeLyhinPolku(Verkko g, int alkuX, int alkuY, int loppuX, int loppuY);

    public String tulos(long tulosMillisekunteina);

    public String algoritminNimi();

}
