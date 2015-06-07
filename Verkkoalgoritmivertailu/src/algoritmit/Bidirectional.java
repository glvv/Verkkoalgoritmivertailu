package algoritmit;

import tietorakenteet.Jono;
import verkko.Kaari;
import verkko.Solmu;
import verkko.Verkko;

public class Bidirectional {

    public void haeLyhinPolku(Verkko verkko, int alkuX, int alkuY, int loppuX, int loppuY) {
        boolean[][] alustaKaydyt = new boolean[verkko.haeLeveys()][verkko.haePituus()];
        Jono alku = new Jono(10);
        Jono loppu = new Jono(10);
        
        Solmu alkuSolmu = verkko.haeSolmu(alkuX, alkuY);
        alkuSolmu.setMinimiEtaisyys(0);
        alkuSolmu.setKasitelty(true);
        alku.enqueue(alkuSolmu);
        
        Solmu loppuSolmu = verkko.haeSolmu(loppuX, loppuY);
        loppuSolmu.setMinimiEtaisyys(0);
        loppuSolmu.setKasitelty(true);
        loppu.enqueue(loppuSolmu);

        while (!alku.empty() && !loppu.empty()) {
            Solmu u = alku.dequeue();
            alustaKaydyt[u.getX()][u.getY()] = true;
            for (Kaari v : u.getKaaret()) {
                Solmu k = v.getKohdeSolmu();
                if (!k.isKasitelty()) {
                    k.setKasitelty(true);
                    k.setMinimiEtaisyys(u.getMinimiEtaisyys() + 1);
                    k.setEdellinen(u);
                    alku.enqueue(k);
                }
            }
            Solmu u2 = loppu.dequeue();
            for (Kaari v : u2.getKaaret()) {
                Solmu k = v.getKohdeSolmu();
                if (alustaKaydyt[k.getX()][k.getY()]) {
                    yhdistaPolku(u2, k);
                    return;
                } else if (!k.isKasitelty()) {
                    k.setKasitelty(true);
                    k.setMinimiEtaisyys(u2.getMinimiEtaisyys() + 1);
                    k.setEdellinen(u2);
                    loppu.enqueue(k);
                }
            }
        }
    }

    private void yhdistaPolku(Solmu u, Solmu k) {
        Solmu a = u;
        Solmu edellinen = k;
        int etaisyys = k.getMinimiEtaisyys() + 1;
        while (a.getEdellinen() != null) {
            Solmu e = a.getEdellinen();
            a.setEdellinen(edellinen);
            a.setMinimiEtaisyys(etaisyys);
            etaisyys++;
            edellinen = a;
            a = e;
        }
        a.setEdellinen(edellinen);
        a.setMinimiEtaisyys(etaisyys);
    }
}
