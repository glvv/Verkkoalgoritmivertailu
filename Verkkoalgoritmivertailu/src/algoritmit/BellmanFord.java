package algoritmit;

import verkko.Kaari;
import verkko.Solmu;
import verkko.Verkko;

/**
 * Luokka tarjoaa toiminnallisuuden lyhimpien polkujen etsintään Bellman-Ford
 * -algoritmilla.
 */
public class BellmanFord {

    /**
     * Metodi hakee lyhimmät polut annetussa verkossa alkusolmusta kaikkiin
     * solmuihin Bellman-Fordin algoritmilla.
     *
     * @param g Verkko, josta polut etsitään.
     * @param alkuSolmuX Alkusolmun X-koordinaatti.
     * @param alkuSolmuY Alkusolmun Y-koordinaatti.
     * @return Bellman-Ford-algoritmi palauttaa false, jos verkossa on
     * negatiivisia syklejä.
     */
    public boolean haeLyhimmatPolut(Verkko g, int alkuSolmuX, int alkuSolmuY) {
        g.haeSolmu(alkuSolmuX, alkuSolmuY).setMinimiEtaisyys(0);
        for (int i = 0; i < (g.haeLeveys() * g.haePituus() - 1); i++) {
            loysaaKaaret(g);
        }
        return onkoNegatiivinenSykli(g);
    }

    /**
     * Metodi suorittaa löysäämisoperaation kaikille verkon kaarille.
     *
     * @param g Verkko, jota käsitellään.
     */
    private void loysaaKaaret(Verkko g) {
        for (int x = 0; x < g.haeLeveys(); x++) {
            for (int y = 0; y < g.haePituus(); y++) {
                Solmu s = g.haeSolmu(x, y);
                for (Kaari v : s.getKaaret()) {
                    if (v == null) {
                        break;
                    }
                    loysaa(s, v.getKohdeSolmu(), v.getPaino());
                }
            }
        }
    }

    /**
     * Metodi tarkistaa onko verkossa vielä jännitteitä jäljellä lyhimpien
     * polkujen etsinnän jälkeen, jos on, niin verkossa on negatiivinen sykli.
     *
     * @param g Verkko, jota tutkitaan.
     * @return False, jos verkossa on negatiivinen sykli, True muuten.
     */
    private boolean onkoNegatiivinenSykli(Verkko g) {
        for (int x = 0; x < g.haeLeveys(); x++) {
            for (int y = 0; y < g.haePituus(); y++) {
                Solmu s = g.haeSolmu(x, y);
                for (Kaari v : s.getKaaret()) {
                    if (v == null) {
                        break;
                    }
                    if (v.getKohdeSolmu().getMinimiEtaisyys() > s.getMinimiEtaisyys() + v.getPaino()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Löysäämisoperaatio, jossa verrataan onko alkusolmun kautta kulkeminen
     * lyhyempi reitti kohdesolmuun, kuin sinne suoraan meneminen.
     *
     * @param alkuSolmu Solmu, jonka kautta kuljetaan.
     * @param kohdeSolmu Solmu, johon kulkevaa reittiä mitataan.
     * @param paino Solmuja yhdistävän kaaren paino.
     */
    private void loysaa(Solmu alkuSolmu, Solmu kohdeSolmu, int paino) {
        int etaisyys = alkuSolmu.getMinimiEtaisyys() + paino;
        if (kohdeSolmu.getMinimiEtaisyys() > etaisyys) {
            kohdeSolmu.setMinimiEtaisyys(etaisyys);
            kohdeSolmu.setEdellinen(alkuSolmu);
        }
    }

}
