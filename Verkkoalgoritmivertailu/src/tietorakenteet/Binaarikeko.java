package tietorakenteet;

import verkko.Solmu;

/**
 * Binaarikeko, johon voi tallettaa viitteitä Solmu-olioihin.
 */
public class Binaarikeko implements Minimikeko {

    private BinaariKekoSolmu[] solmut;
    protected int koko;

    /**
     * Konstruktorissa luodaan KekoSolmu-taulukko, joka asetetaan
     * oliomuuttujaan. Binaarikeon koko on aluksi 0.
     *
     * @param koko Binaarikeon koko.
     */
    public Binaarikeko(int koko) {
        this.solmut = new BinaariKekoSolmu[koko];
        this.koko = 0;
    }

    /**
     * Metodi palauttaa indeksin i parent-solmun indeksin.
     *
     * @param i Indeksi, jonka parent solmun indeksi haetaan.
     * @return parentsolmun indeksi keossa.
     */
    private int parent(int i) {
        return i / 2;
    }

    /**
     * Metodi palauttaa indeksin i vasemman oksan indeksin.
     *
     * @param i Indeksi, jonka vasen oksa haetaan.
     * @return Vasemman oksan indeksi keossa.
     */
    private int left(int i) {
        return 2 * i;
    }

    /**
     * Metodi palauttaa indeksin i oikean oksan indeksin.
     *
     * @param i Indeksi, jonka oikea oksa haetaan.
     * @return Oikean oksan indeksi keossa.
     */
    private int right(int i) {
        return 2 * i + 1;
    }

    /**
     * Metodi siirtää kekosolmua keossa alaspäin kekoehdon mukaisesti.
     *
     * @param i Indeksi, jossa olevaa kekosolmua siirretään alaspäin.
     */
    private void heapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest;
        if (r <= koko) {
            if (solmut[l].compareTo(solmut[r]) < 0) {
                smallest = l;
            } else {
                smallest = r;
            }
            if (solmut[i].compareTo(solmut[smallest]) > 0) {
                vaihda(i, smallest);
                heapify(smallest);
            }
        } else if (l == koko && solmut[i].compareTo(solmut[l]) > 0) {
            vaihda(i, l);
        }
    }

    /**
     * Metodi kertoo onko keko tyhjä.
     *
     * @return Boolean-arvo riippuen siitä onko keosa alkioita vai ei.
     */
    @Override
    public boolean empty() {
        return this.koko == 0;
    }

    /**
     * Metodilla lisätään kekoon Solmu. Kekoon talletetaan KekoSolmu-olio, jossa
     * on viite talletettuun Solmu-olioon.
     *
     * @param u Lisättävä solmu.
     */
    @Override
    public void insert(Solmu u) {
        BinaariKekoSolmu uusi = new BinaariKekoSolmu(u);
        u.setSolmuKeossa(uusi);

        int i = koko;
        while (i > 0 && solmut[parent(i)].compareTo(uusi) > 0) {
            solmut[i] = solmut[parent(i)];
            solmut[i].setIndeksiKeossa(i);
            i = parent(i);
        }
        solmut[i] = uusi;
        uusi.setIndeksiKeossa(i);
        koko++;
    }

    /**
     * Metodilla päivitetään Solmun järjestystä keossa ylöspäin.
     *
     * @param s Solmu, jonka järjestystä päivitetään.
     */
    @Override
    public void decreaseKey(Solmu s) {
        int uusiArvo = s.getEtaisyysLoppusolmuun() + s.getMinimiEtaisyys();
        BinaariKekoSolmu k = (BinaariKekoSolmu) s.getSolmuKeossa();
        if (uusiArvo < k.getAvainArvo()) {
            k.setAvainArvo(uusiArvo);
            int i = k.getIndeksiKeossa();
            while (i > 0 && solmut[parent(i)].compareTo(solmut[i]) > 0) {
                vaihda(i, parent(i));
                i = parent(i);
            }
        }
    }

    /**
     * Metodi vaihtaa indekseissä i ja i2 olevat kekosolmut keskenään.
     *
     * @param i
     * @param i2
     */
    private void vaihda(int i, int i2) {
        BinaariKekoSolmu c = solmut[i];
        BinaariKekoSolmu p = solmut[i2];

        solmut[i2] = c;
        c.setIndeksiKeossa(i2);

        solmut[i] = p;
        p.setIndeksiKeossa(i);
    }

    /**
     * Palauttaa pienimmän Solmun keossa.
     *
     * @return Pienin Solmu keossa.
     */
    @Override
    public Solmu min() {
        return solmut[0].getSolmu();
    }

    /**
     * Palauttaa ja poistaa pienimmän Solmun keossa.
     *
     * @return Pienin Solmu keossa.
     */
    @Override
    public Solmu delMin() {
        Solmu min = solmut[0].getSolmu();
        solmut[0] = solmut[koko - 1];
        koko--;
        heapify(0);
        return min;
    }

    /**
     * Metodi tyhjentää keon.
     */
    @Override
    public void clear() {
        solmut = new BinaariKekoSolmu[solmut.length];
        koko = 0;
    }

    /**
     * Palauttaa keon koon.
     *
     * @return Keon koko.
     */
    @Override
    public int size() {
        return koko;
    }

    /**
     * Metodi palauttaa keon kekosolmut.
     *
     * @return Keossa olevat kekosolmut.
     */
    protected BinaariKekoSolmu[] getSolmut() {
        return solmut;
    }

}
