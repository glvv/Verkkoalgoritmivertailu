package verkko;

/**
 * Solmu kuvaa verkon solmua.
 */
public class Solmu implements Comparable<Solmu> {

    private Kaari[] kaaret;
    private final int x;
    private final int y;
    private int minimiEtaisyysAlkusolmusta;
    private int etaisyysLoppusolmuun;
    private Solmu edellinen;
    private boolean kasitelty;

    /**
     * Konstruktorissa alustetaan solmun komponentit.
     *
     * @param x Solmun x-koordinaatti.
     * @param y Solmun y-koordinaatti.
     */
    public Solmu(int x, int y) {
        this.kaaret = null;
        this.x = x;
        this.y = y;
        this.minimiEtaisyysAlkusolmusta = 1000000000;
        this.etaisyysLoppusolmuun = 0;
        this.edellinen = null;
        this.kasitelty = false;
    }

    /**
     * Metodi solmujen vertaamiseen.
     *
     * @param o Verrattava solmu.
     * @return Metodi palauttaa negatiivisen arvon, jos tämä solmu on pienempi,
     * positiivisen arvon jos tämä solmu on suurempi. Metodi palauttaa nollan,
     * jos solmut ovat yhtäsuuret.
     */
    @Override
    public int compareTo(Solmu o) {
        return (this.minimiEtaisyysAlkusolmusta + this.etaisyysLoppusolmuun) - (o.minimiEtaisyysAlkusolmusta + o.etaisyysLoppusolmuun);
    }

    /**
     * Metodi palauttaa solmun hajautuskoodin.
     *
     * @return Solmun hajautuskoodi.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.x;
        hash = 59 * hash + this.y;
        return hash;
    }

    /**
     * Metodi vertaa solmua parametrina annettuun objektiin ja palauttaa true,
     * jos ne ovat samat.
     *
     * @param obj Verrattava objekti.
     * @return Onko parametrina annettu objekti sama kuin tämä solmu.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Solmu other = (Solmu) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }

    /**
     * Metodi palauttaa solmusta lähtevät kaaret.
     *
     * @return Solmusta lähtevät kaaret.
     */
    public Kaari[] getKaaret() {
        return kaaret;
    }

    /**
     * Metodilla asetetaan solmuun siitä lähtevät kaaret.
     *
     * @param kaaret Solmuun asetettavat kaaret.
     */
    public void setKaaret(Kaari[] kaaret) {
        this.kaaret = kaaret;
    }

    /**
     * Metodilla asetetaan minimietäisyys alkusolmuun.
     *
     * @param minimiEtaisyys Asetettava minimietäisyys.
     */
    public void setMinimiEtaisyys(int minimiEtaisyys) {
        this.minimiEtaisyysAlkusolmusta = minimiEtaisyys;
    }

    /**
     * Metodilla haetaan nykyinen etäisyys alkusolmuun.
     *
     * @return Solmun etäisyys alkusolmusta.
     */
    public int getMinimiEtaisyys() {
        return minimiEtaisyysAlkusolmusta;
    }

    /**
     * Metodi palauttaa Solmun x-koordinaatin.
     *
     * @return Solmun x-koordinaatti.
     */
    public int getX() {
        return x;
    }

    /**
     * Metodi palauttaa Solmun y-koordinaatin.
     *
     * @return Solmun y-koordinaatti.
     */
    public int getY() {
        return y;
    }

    /**
     * Metodilla asetetaan edellinen solmu lyhimmässä polussa tähän solmuun.
     *
     * @param edellinen Edellinen solmu lyhimmässä polussa tähän solmuun.
     */
    public void setEdellinen(Solmu edellinen) {
        this.edellinen = edellinen;
    }

    /**
     * Metodi palauttaa edellisen solmum lyhimmässä polussa alkusolmusta tähän
     * solmuun.
     *
     * @return Edellinen solmu lyhimmässä polussa alkusolmusta tähän solmuun.
     */
    public Solmu getEdellinen() {
        return edellinen;
    }

    /**
     * Palauttaa totuusarvon riippuen siitä, onko solmu käsitelty vai ei.
     *
     * @return Totuusarvo riippuen siitä onko solmu käsitelty vai ei.
     */
    public boolean isKasitelty() {
        return kasitelty;
    }

    /**
     * Metodilla asetetaan solmu läpikäydyksi tai läpikäymättömäksi.
     *
     * @param kasitelty
     */
    public void setKasitelty(boolean kasitelty) {
        this.kasitelty = kasitelty;
    }

    public void setEtaisyysLoppusolmuun(int etaisyysLoppusolmuun) {
        this.etaisyysLoppusolmuun = etaisyysLoppusolmuun;
    }

    public int getEtaisyysLoppusolmuun() {
        return etaisyysLoppusolmuun;
    }

}
