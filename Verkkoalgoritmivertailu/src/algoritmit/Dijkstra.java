package algoritmit;

import domain.Kaari;
import domain.Solmu;
import domain.Verkko;
import keot.Minimikeko;

public class Dijkstra {
    
    private final Minimikeko<Solmu> keko;

    public Dijkstra(Minimikeko<Solmu> keko) {
        this.keko = keko;
    }
    
    public void haeLyhimmatPolut(int alkuX, int alkuY, Verkko verkko) {
        Solmu[][] solmut = verkko.haeSolmut();
        solmut[alkuX][alkuY].setMinimiEtaisyys(0);
        for (int i = 0; i < solmut.length; i++) {
            for (int j = 0; j < solmut[0].length; j++) {
                keko.insert(solmut[i][j]);
            }
        }
        while(!keko.empty()) {
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
    
    private void loysaa(Solmu alkuSolmu, Solmu kohdeSolmu, int paino) {
        int etaisyys = alkuSolmu.getMinimiEtaisyys() + paino;
        if (kohdeSolmu.getMinimiEtaisyys() > etaisyys) {
            kohdeSolmu.setMinimiEtaisyys(etaisyys);
            kohdeSolmu.setEdellinen(alkuSolmu);
        }
    }
    
}
