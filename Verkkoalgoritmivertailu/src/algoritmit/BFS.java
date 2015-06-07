package algoritmit;

import tietorakenteet.Jono;
import verkko.Kaari;
import verkko.Solmu;
import verkko.Verkko;

/**
 * Luokka tarjoaa toiminnallisuuden lyhimmän polun etsimiseen painottomassa
 * verkossa leveysssuuntaisella läpikäynnillä.
 */
public class BFS {

    /**
     * Metodi hakee lyhimmän polun alkusolmusta loppusolmuun leveyssuutaisella läpikäynnillä.
     *
     * @param verkko Verkko, jossa haku tehdään.
     * @param alkuX Alkusolmun x-koordinaatti.
     * @param alkuY Alkusolmun y-koordinaatti.
     * @param loppuX Loppusolmun x-koordinaatti.
     * @param loppuY Loppusolmun y-koordinaatti.
     */
    public void haeLyhinPolku(Verkko verkko, int alkuX, int alkuY, int loppuX, int loppuY) {
        Solmu alkuSolmu = verkko.haeSolmu(alkuX, alkuY);
        alkuSolmu.setMinimiEtaisyys(0);
        alkuSolmu.setKasitelty(true);

        Jono jono = new Jono(10);
        jono.enqueue(alkuSolmu);
        
        while (!verkko.haeSolmu(loppuX, loppuY).isKasitelty()) {
            Solmu u = jono.dequeue();
            for (Kaari v : u.getKaaret()) {
                Solmu k = v.getKohdeSolmu();
                if (!k.isKasitelty()) {
                    k.setKasitelty(true);
                    k.setMinimiEtaisyys(u.getMinimiEtaisyys() + 1);
                    k.setEdellinen(u);
                    jono.enqueue(k);
                }
            }
        }
    }
}
