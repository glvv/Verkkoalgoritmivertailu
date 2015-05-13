package domain;

import tietorakenteet.Lista;

public class Verkko {

    private Solmu[][] solmut;

    public Verkko(char[][] kentta) {
        solmut = new Solmu[kentta.length][kentta[0].length];
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

    private void haeKaaret(int i, int j, char[][] kentta) {
        Lista<Kaari> kaaret = new Lista<>(Kaari.class, 4);
        haeKaari(i + 1, j, kentta, kaaret);
        haeKaari(i - 1, j, kentta, kaaret);
        haeKaari(i, j + 1, kentta, kaaret);
        haeKaari(i, j - 1, kentta, kaaret);
        solmut[i][j].setKaaret(kaaret.haeTaulukko());
    }

    private void haeKaari(int i, int j, char[][] kentta, Lista<Kaari> kaaret) {
        if (!(i < 0 || i >= kentta.length || j < 0 || j >= kentta[0].length)) {
            kaaret.add(new Kaari(solmut[i][j], haePaino(kentta, i, j)));
        }
    }

    private int haePaino(char[][] kentta, int i, int j) {
        char c = kentta[i][j];
        if (c == '.') {
            return 1;
        }
        if (c == '#') {
            return 100;
        }
        if (c == '¤') {
            return 60;
        } else {
            return 8;
        }
    }
    
    public Solmu[][] haeSolmut() {
        return solmut;
    }
}
