package domain;

public class Solmu implements Comparable<Solmu> {
    private Kaari[] kaaret;
    private final int x;
    private final int y;
    private int minimiEtaisyys;
    private Solmu edellinen;
    private boolean kasitelty;

    public Solmu(int x, int y) {
        this.kaaret = null;
        this.x = x;
        this.y = y;
        this.minimiEtaisyys = 1000000000;
        this.edellinen = null;
        this.kasitelty = false;
    }
    
    @Override
    public int compareTo(Solmu o) {
        return this.minimiEtaisyys - o.minimiEtaisyys;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.x;
        hash = 59 * hash + this.y;
        return hash;
    }

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

    public Kaari[] getKaaret() {
        return kaaret;
    }

    public void setKaaret(Kaari[] kaaret) {
        this.kaaret = kaaret;
    }

    public void setMinimiEtaisyys(int minimiEtaisyys) {
        this.minimiEtaisyys = minimiEtaisyys;
    }

    public int getMinimiEtaisyys() {
        return minimiEtaisyys;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setEdellinen(Solmu edellinen) {
        this.edellinen = edellinen;
    }

    public Solmu getEdellinen() {
        return edellinen;
    }

    public boolean isKasitelty() {
        return kasitelty;
    }

    public void setKasitelty(boolean kasitelty) {
        this.kasitelty = kasitelty;
    }
    
}