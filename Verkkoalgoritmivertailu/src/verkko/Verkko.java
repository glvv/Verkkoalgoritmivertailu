package verkko;

import tietorakenteet.Lista;

/**
 * Verkko kuvaa syötteenä annettua char-taulukkoa verkkona, jota algoritmit
 * pystyvät käsittelemään.
 */
public class Verkko {

    private final Solmu[][] solmut;
    private final boolean diagonaalitSallittu;
    private final boolean seinienLapiSaaLiikkua;
    private int kaartenMaara;
    private final int solmujenMaara;

    /**
     * Konstruktorissa annetaan kenttä kaksiulotteisena char-taulukkona, joka
     * muunnetaan verkoksi.
     *
     * @param kentta Muunnettava kaksiulotteinen char-taulukko.
     * @param diagonaalitSallittu Saako verkossa kulkea diagonaalisesti.
     * @param seinienLapiSaaLiikkua Saako seinien lapi liikkua. Jos seinien läpi
     * ei saa liikkua, niin seiniin vieviä kaaria ei luoda.
     */
    public Verkko(char[][] kentta, boolean diagonaalitSallittu, boolean seinienLapiSaaLiikkua) {
        this.diagonaalitSallittu = diagonaalitSallittu;
        this.seinienLapiSaaLiikkua = seinienLapiSaaLiikkua;
        solmut = new Solmu[kentta.length][kentta[0].length];
        this.kaartenMaara = 0;
        this.solmujenMaara = kentta.length * kentta[0].length;
        for (int i = 0; i < kentta.length; i++) {
            for (int j = 0; j < kentta[0].length; j++) {
                solmut[i][j] = new Solmu(i, j);
            }
        }
        for (int i = 0; i < kentta.length; i++) {
            for (int j = 0; j < kentta[0].length; j++) {
                haeKaaret(i, j, kentta);
            }
        }
        
    }

    /**
     * Metodi hakee solmun vierussolmut ja yhdistää ne oikeanpainoisilla
     * kaarilla.
     *
     * @param x Solmun x-koordinaatti.
     * @param y Solmun y-koordinaatti.
     * @param kentta Syötteenä annettu kaksiulotteinen char-taulukko, josta
     * kaarten painot luetaan.
     */
    private void haeKaaret(int x, int y, char[][] kentta) {
        Lista<Kaari> kaaret = new Lista<>(8);
        haeKaari(x + 1, y, kentta, kaaret);
        haeKaari(x - 1, y, kentta, kaaret);
        haeKaari(x, y + 1, kentta, kaaret);
        haeKaari(x, y - 1, kentta, kaaret);
        if (diagonaalitSallittu) {
            haeKaari(x + 1, y + 1, kentta, kaaret);
            haeKaari(x - 1, y + 1, kentta, kaaret);
            haeKaari(x + 1, y - 1, kentta, kaaret);
            haeKaari(x - 1, y - 1, kentta, kaaret);
        }
        solmut[x][y].setKaaret(kaaret);
    }

    /**
     * Metodi palauttaa yhteen ruutuun vievän kaaren, jos se on olemassa.
     *
     * @param x Ruudun x-koordinaatti.
     * @param y Ruudun y-koordinaatti.
     * @param kentta Konstruktorissa annettu kaksiulotteinen char-taulukko,
     * josta kaaren paino saadaan.
     * @param kaaret Lista, johon kaaret lisätään.
     */
    private void haeKaari(int x, int y, char[][] kentta, Lista kaaret) {
        if (!(x < 0 || x >= kentta.length || y < 0 || y >= kentta[0].length)) {
            if ((seinienLapiSaaLiikkua || kentta[x][y] != '#')) {
                kaaret.add(new Kaari(solmut[x][y], haePaino(kentta, x, y)));
                kaartenMaara++;
            }
        }
    }

    /**
     * Metodi, joka muuttaa char-merkit kaaren painoksi.
     *
     * @param kentta Konstruktorissa annettu kaksiulotteinen char-taulukko,
     * josta merkit luetaan.
     * @param x X-koordinaatti, josta kaaren paino luetaan,
     * @param y Y-koordinaatti, josta kaaren paino luetaan.
     * @return Kaaren paino.
     */
    private int haePaino(char[][] kentta, int x, int y) {
        char c = kentta[x][y];
        if (c == '.') {
            return 1;
        }
        if (c == '#') {
            return 100;
        }
        if (c == '¤') {
            return 3;
        }
        if (c == '=') {
            return 4;
        }
        if (c == '&') {
            return 12;
        }
        if (c == '~') {
            return 20;
        } else {
            return 8;
        }
    }

    /**
     * Palauttaa verkon solmut kaksiulotteisessa taulukossa.
     *
     * @return Verkon solmut kaksiulotteisessa taulukossa.
     */
    public Solmu[][] haeSolmut() {
        return solmut;
    }

    public int haeLeveys() {
        return solmut.length;
    }

    public int haePituus() {
        return solmut[0].length;
    }

    /**
     * Palauttaa solmun koordinaateissa x ja y.
     *
     * @param x Solmun x-koordinaatti
     * @param y Solmun y-koordinaatti
     * @return Solmu parametreina annetuissa koordinaateissa
     */
    public Solmu haeSolmu(int x, int y) {
        return solmut[x][y];
    }

    public int haeKaartenMaara() {
        return kaartenMaara;
    }

    public int haeSolmujenMaara() {
        return solmujenMaara;
    }
    
}
