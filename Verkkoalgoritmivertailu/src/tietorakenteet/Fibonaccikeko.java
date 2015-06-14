package tietorakenteet;

import verkko.Solmu;

/**
 * Luokka toteuttaa Solmu-olioita tallettavan Fibonaccikeon.
 */
public class Fibonaccikeko implements Minimikeko {

    private FibonaccikekoSolmu min;
    private int koko;

    public Fibonaccikeko() {
        min = null;
        koko = 0;
    }

    @Override
    public boolean empty() {
        return min == null;
    }

    /**
     * Insert-operaatiossa luodaan uusi FibonacciKekoSolmu ja asetetaan se
     * juurilistaan.
     *
     * @param s Fibonaccikekoon lisättävä solmu.
     */
    @Override
    public void insert(Solmu s) {
        if (s.getSolmuKeossa() == null) {
            FibonaccikekoSolmu uusi = new FibonaccikekoSolmu(s);
            s.setSolmuKeossa(uusi);
            uusi.setLeft(uusi);
            uusi.setRight(uusi);
            if (min == null) {
                min = uusi;
            } else {
                min = yhdistaKaksiListaa(uusi, min);
            }
            koko++;
        }
    }

    @Override
    public Solmu min() {
        return min.getSolmu();
    }

    /**
     * Metodi yhdistää kaksi FibonaccikekoSolmuja sisältävää linkitettyä listaa
     * ja palauttaa viitteen näistä pienempään.
     *
     * @param e Ensimmäinen yhdistettävä FibonacciKekoSolmu.
     * @param t Toinen yhdistettävä FibonacciKekoSolmu.
     * @return Viite avainarvoltaan pienempään parametrina annettuun
     * FibonacciKekoSolmu-olioon.
     */
    private FibonaccikekoSolmu yhdistaKaksiListaa(FibonaccikekoSolmu e, FibonaccikekoSolmu t) {
        if (e == null && t == null) {
            return null;
        } else if (e == null) {
            return t;
        } else if (t == null) {
            return e;
        }
        FibonaccikekoSolmu eOikea = e.getRight();

        e.setRight(t.getRight());
        e.getRight().setLeft(e);
        t.setRight(eOikea);
        t.getRight().setLeft(t);

        if (e.getAvainArvo() < t.getAvainArvo()) {
            return e;
        } else {
            return t;
        }
    }

    /**
     * Palauttaa viitteen pienimpään FibonacciKekoSolmuun.
     *
     * @return Viite keossa olevaan pienimpään FibonacciKekoSolmuun.
     */
    protected FibonaccikekoSolmu getMin() {
        return min;
    }

    /**
     * Metodi poistaa ja palauttaa pienimmän alkion keossa.
     *
     * @return Pienimmän avainarvon solmu, joka poistettiin keosta.
     */
    @Override
    public Solmu delMin() {
        FibonaccikekoSolmu s = min;
        if (s.getChild() != null) {
            FibonaccikekoSolmu l = s.getChild();
            while (l.getRight() != l) {
                FibonaccikekoSolmu n = l.getRight();
                l.setParent(null);
                l.getRight().setLeft(l.getLeft());
                l.getLeft().setRight(l.getRight());
                l.setLeft(l);
                l.setRight(l);
                yhdistaKaksiListaa(l, min);
                l = n;
            }
            l.setParent(null);
            yhdistaKaksiListaa(l, min);
        }
        s.getLeft().setRight(s.getRight());
        s.getRight().setLeft(s.getLeft());
        if (s.getRight() == s) {
            min = null;
        } else {
            min = min.getRight();
            vakauta();
        }
        koko--;
        return s.getSolmu();
    }

    /**
     * Vakauttamisoperaatiossa juurilistan solmuja asetetaan toistensa lapsiksi
     * siten, että juurilistassa ei ole kahta solmua, joilla on sama
     * degree-arvo.
     */
    private void vakauta() {
        FibonaccikekoSolmu[] a = new FibonaccikekoSolmu[laskeTarvittavaKoko()];
        Lista<FibonaccikekoSolmu> l = new Lista<>(10);
        FibonaccikekoSolmu w = min;
        do {
            w = w.getRight();
            l.add(w);
        } while (min != w);
        for (FibonaccikekoSolmu q : l) {
            FibonaccikekoSolmu x = q;
            int d = x.getDegree();
            while (a[d] != null) {
                FibonaccikekoSolmu y = a[d];
                if (x.getAvainArvo() > y.getAvainArvo()) {
                    FibonaccikekoSolmu c = x;
                    x = y;
                    y = c;
                }
                asetaLapseksi(y, x);
                a[d] = null;
                d++;
            }
            a[d] = x;
        }
        min = null;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) {
                a[i].setLeft(a[i]);
                a[i].setRight(a[i]);
                if (min == null) {
                    min = a[i];
                } else {
                    min = yhdistaKaksiListaa(a[i], min);
                }
            }
        }
    }

    /**
     * Fibonaccikeon juurilistassa on korkeintaan koon fiikantaisen logaritmin
     * verran eri degree-arvon omaavia Fibonaccikekosolmuja. Metodi laskee keon
     * koon fiikantaisen logaritmin.
     *
     * @return Keon koon fiinkantainen logaritmi.
     */
    private int laskeTarvittavaKoko() {
        double k = 1.61803398875;
        double c = k;
        int a = 2;
        while (c < koko) {
            c = c * k;
            a++;
        }
        return a;
    }

    /**
     * Metodi asettaa y-Fibonaccikekosolmun x-FibonaccikekoSolmun lapseksi.
     *
     * @param y FibonaccikekoSolmu, joka lisätään x-FibonaccikekoSolmmun
     * lapseksi.
     * @param x FibonaccikekoSolmu, jonka lapseksi y-FibonaccikekoSolmu
     * lisätään.
     */
    private void asetaLapseksi(FibonaccikekoSolmu y, FibonaccikekoSolmu x) {
        y.getLeft().setRight(y.getRight());
        y.getRight().setLeft(y.getLeft());
        y.setLeft(y);
        y.setRight(y);

        if (x.getChild() != null) {
            yhdistaKaksiListaa(y, x.getChild());
        } else {
            x.setChild(y);
        }
        x.kasvata();
        y.setParent(x);
        y.setMarked(false);
    }

    /**
     * Metodi päivittää annettua Solmu-oliota vastaavan FibonacciKekoSolmun
     * järjestystä keossa, jos tarpeellista.
     *
     * @param s Solmu, jonka järjestystä muutetaan.
     */
    @Override
    public void decreaseKey(Solmu s) {
        int uusiArvo = s.getMinimiEtaisyys() + s.getEtaisyysLoppusolmuun();
        FibonaccikekoSolmu x = (FibonaccikekoSolmu) s.getSolmuKeossa();
        if (uusiArvo < x.getAvainArvo()) {
            x.setAvainArvo(uusiArvo);
            FibonaccikekoSolmu y = x.getParent();
            if (y != null && x.getAvainArvo() < y.getAvainArvo()) {
                leikkaa(x);
            }
            if (x.getAvainArvo() < min.getAvainArvo()) {
                min = x;
            }
        }
    }

    /**
     * Metodi leikkaa x-FibonaccikekoSolmun vanhemmastaan ja lisää sen
     * juurilistaan.
     *
     * @param x FibonaccikekoSolmu, joka leikataan vanhemmastaan.
     */
    private void leikkaa(FibonaccikekoSolmu x) {
        FibonaccikekoSolmu y = x.getParent();
        x.setMarked(false);
        if (y == null) {
            return;
        }
        if (x.getLeft() != x) {
            x.getLeft().setRight(x.getRight());
            x.getRight().setLeft(x.getLeft());
        }
        if (y.getChild() == x) {
            if (x.getLeft() != x) {
                y.setChild(x.getLeft());
            } else {
                y.setChild(null);
            }
        }
        y.pienenna();
        x.setLeft(x);
        x.setRight(x);
        yhdistaKaksiListaa(x, min);
        if (y.isMarked()) {
            leikkaa(x.getParent());
        } else {
            y.setMarked(true);
        }
        x.setParent(null);
    }

    /**
     * Metodi asettaa min-viiteeseen arvon null ja kooksi arvon 0.
     */
    @Override
    public void clear() {
        min = null;
        koko = 0;
    }

    @Override
    public int size() {
        return koko;
    }
}
