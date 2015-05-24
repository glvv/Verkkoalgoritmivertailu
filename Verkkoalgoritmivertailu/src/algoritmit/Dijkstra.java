package algoritmit;

import verkko.Kaari;
import verkko.Solmu;
import verkko.Verkko;
import keot.Minimikeko;

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
        keko.clear();
        verkko.haeSolmu(alkuX, alkuY).setMinimiEtaisyys(0);
        for (int i = 0; i < verkko.haeLeveys(); i++) {
            for (int j = 0; j < verkko.haePituus(); j++) {
                keko.insert(verkko.haeSolmu(i, j));
            }
        }
        while (!keko.empty()) {
            Solmu u = keko.delMin();
            u.setKasitelty(true);
            for (Kaari v : u.getKaaret()) {
                if (v == null) {
                    break;
                }
                loysaa(u, v.getKohdeSolmu(), v.getPaino());
                if (!(v.getKohdeSolmu().isKasitelty())) {
                    keko.decreaseKey(v.getKohdeSolmu());
                }
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
    protected void loysaa(Solmu alkuSolmu, Solmu kohdeSolmu, int paino) {
        int etaisyys = alkuSolmu.getMinimiEtaisyys() + paino;
        if (kohdeSolmu.getMinimiEtaisyys() > etaisyys) {
            kohdeSolmu.setMinimiEtaisyys(etaisyys);
            kohdeSolmu.setEdellinen(alkuSolmu);
        }
    }
}