package algoritmit;

import verkko.Kaari;
import verkko.Solmu;
import verkko.Verkko;
import tietorakenteet.Minimikeko;

/**
 * Luokka, joka tarjoaa polkujen hakemisen Dijkstran algoritmilla.
 */
public class Dijkstra implements PolunetsintaAlgoritmi {

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
     * Metodi hakee lyhimmän polun Dijkstran algoritmilla syötteenä annetulle
     * verkolle alkusolmusta loppusolmuun. Alkusolmu ja loppusolmu annetaan sen
     * x- ja y -koordinaatteina.
     *
     * @param alkuX Alkusolmun x-koordinaatti.
     * @param alkuY Alkusolmun y-koordinaatti.
     * @param loppuX Loppusolmun x-koordinaatti.
     * @param loppuY Loppusolmun y-koordinaatti.
     * @param verkko Verkko, jossa haku tehdään.
     */
    @Override
    public void haeLyhinPolku(Verkko verkko, int alkuX, int alkuY, int loppuX, int loppuY) {
        verkko.haeSolmu(alkuX, alkuY).setMinimiEtaisyys(0);
        for (int i = 0; i < verkko.haeLeveys(); i++) {
            for (int j = 0; j < verkko.haePituus(); j++) {
                Solmu s = verkko.haeSolmu(i, j);
                keko.insert(s);
            }
        }
        while (!verkko.haeSolmu(loppuX, loppuY).isKasitelty()) {
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
    
    @Override
    public String tulos(long tulosMillisekunteina) {
        return "Dijkstran algoritmilla polunetsintä kesti " + tulosMillisekunteina + " millisekuntia.";
    }

    @Override
    public String algoritminNimi() {
        return "Dijkstra";
    }
    
    
}
