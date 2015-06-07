package algoritmit;

import tietorakenteet.KekoSolmu;
import tietorakenteet.Minimikeko;
import verkko.Kaari;
import verkko.Solmu;
import verkko.Verkko;

/**
 * Luokka tarjoaa toiminnallisuuden lyhimpien polkujen etsintään
 * A*-algoritmilla.
 *
 */
public class AStar {

    private Minimikeko keko;

    /**
     * Konstruktorissa annetaan parametrina minimikeko, jota A*-algoritmi
     * käyttää.
     *
     * @param keko käytettävä minimikeko.
     */
    public AStar(Minimikeko keko) {
        this.keko = keko;
    }

    /**
     * Metodi hakee lyhimmän polun A*-algoritmilla annetussa verkossa
     * alkusolmusta loppusolmuun.
     *
     * @param g Verkko, jossa haku tehdään.
     * @param alkuX Ensimmäisen solmun x-koordinaatti.
     * @param alkuY Ensimmäisen solmun y-koordinaatti.
     * @param loppuX Toisen solmun x-koordinaatti.
     * @param loppuY Toisen solmun y-koordinaatti.
     */
    public void haeLyhinPolku(Verkko g, int alkuX, int alkuY, int loppuX, int loppuY) {
        g.haeSolmu(alkuX, alkuY).setMinimiEtaisyys(0);
        for (int x = 0; x < g.haeLeveys(); x++) {
            for (int y = 0; y < g.haePituus(); y++) {
                Solmu s = g.haeSolmu(x, y);
                s.setEtaisyysLoppusolmuun(arvioiEtaisyysLoppuun(x, y, loppuX, loppuY));
                keko.insert(s);
            }
        }
        while (!(g.haeSolmu(loppuX, loppuY).isKasitelty())) {
            Solmu s = keko.delMin();
            s.setKasitelty(true);
            for (Kaari e : s.getKaaret()) {
                relax(s, e.getKohdeSolmu(), e.getPaino());
                Solmu kohdeSolmu = e.getKohdeSolmu();
                keko.decreaseKey(kohdeSolmu);
            }
        }
    }

    /**
     * Metodi laskee manhattan-etaisyyden kahden solmun välillä.
     *
     * @param x Ensimmäisen solmun x-koordinaatti.
     * @param y Ensimmaisen solmun y-koordinaatti.
     * @param loppuX Toisen solmun x-koordinaatti.
     * @param loppuY Toisen solmun y-koordinaatti.
     * @return
     */
    private int arvioiEtaisyysLoppuun(int x, int y, int loppuX, int loppuY) {
        return Math.abs(loppuX - x) + Math.abs(loppuY - y);
    }

    /**
     * A* -algoritmin relax-operaatio.
     *
     * @param alkuSolmu Kaaren alkusolmu.
     * @param kohdeSolmu Kaaren päätesolmu.
     * @param paino Kaaren paino.
     */
    private void relax(Solmu alkuSolmu, Solmu kohdeSolmu, int paino) {
        int etaisyys = alkuSolmu.getMinimiEtaisyys() + paino;
        if (kohdeSolmu.getMinimiEtaisyys() > etaisyys) {
            kohdeSolmu.setMinimiEtaisyys(etaisyys);
            kohdeSolmu.setEdellinen(alkuSolmu);
        }
    }

}
