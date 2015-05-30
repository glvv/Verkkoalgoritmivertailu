package algoritmit;

import verkko.Kaari;
import verkko.Solmu;
import verkko.Verkko;
import tietorakenteet.Minimikeko;

/**
 * Luokka, joka tarjoaa polkujen hakemisen Dijkstran algoritmilla.
 */
public class Dijkstra {

    private final Minimikeko keko;

    /**
     * Konstruktorissa annetaan parametrina minimikeko, jota algoritmi käyttää.
     *
     * @param keko Minimikeko-rajapinnan toteuttama olio, jota algoritmi
     * käyttää.
     */
    public Dijkstra(Minimikeko keko) {
        this.keko = keko;
    }

    /**
     * Metodi hakee lyhimmät polut Dijkstran algoritmilla syötteenä annetulle
     * verkolle lähtien annetusta aloitussolmusta. Alkusolmu annetaan sen x- ja
     * y -koordinaatteina.
     *
     * @param alkuX Alkusolmun x-koordinaatti.
     * @param alkuY Alkusolmun y-koordinaatti.
     * @param verkko Verkko, josta algoritmi hakee lyhimmät polut.
     */
    public void haeLyhimmatPolut(int alkuX, int alkuY, Verkko verkko) {
        verkko.haeSolmu(alkuX, alkuY).setMinimiEtaisyys(0);
        for (int i = 0; i < verkko.haeLeveys(); i++) {
            for (int j = 0; j < verkko.haePituus(); j++) {
                Solmu s = verkko.haeSolmu(i, j);
                keko.insert(s);
            }
        }
        while (!keko.empty()) {
            Solmu u = keko.delMin();
            u.setKasitelty(true);
            for (Kaari v : u.getKaaret()) {
                relax(u, v.getKohdeSolmu(), v.getPaino());
                Solmu kohdeSolmu = v.getKohdeSolmu();
                keko.decreaseKey(kohdeSolmu);
            }
        }
    }

    /**
     * Dijkstran algoritmin relax-operaatio.
     *
     * @param alkuSolmu Kaaren alkusolmu.
     * @param kohdeSolmu Kaaren päätesolmu.
     * @param paino Kaaren paino.
     */
    protected void relax(Solmu alkuSolmu, Solmu kohdeSolmu, int paino) {
        int etaisyys = alkuSolmu.getMinimiEtaisyys() + paino;
        if (kohdeSolmu.getMinimiEtaisyys() > etaisyys) {
            kohdeSolmu.setMinimiEtaisyys(etaisyys);
            kohdeSolmu.setEdellinen(alkuSolmu);
        }
    }
}
