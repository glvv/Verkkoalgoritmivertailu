package tietorakenteet;

import verkko.Solmu;

/**
 * Fibonaccikekosolmu on fibonaccikekoon talletettava solmu, joka sisältää
 * viitteen Solmu-olioon.
 */
public class FibonaccikekoSolmu implements KekoSolmu {

    private FibonaccikekoSolmu left;
    private FibonaccikekoSolmu right;
    private FibonaccikekoSolmu parent;
    private FibonaccikekoSolmu child;
    private int avainArvo;
    private final Solmu solmu;
    private int degree;
    private boolean marked;
    private boolean kasitelty;

    public FibonaccikekoSolmu(Solmu s) {
        degree = 0;
        marked = false;
        solmu = s;
        parent = null;
        child = null;
        avainArvo = s.getMinimiEtaisyys() + s.getEtaisyysLoppusolmuun();
    }

    public void setLeft(FibonaccikekoSolmu left) {
        this.left = left;
    }

    public void setRight(FibonaccikekoSolmu right) {
        this.right = right;
    }

    public FibonaccikekoSolmu getLeft() {
        return left;
    }

    public FibonaccikekoSolmu getRight() {
        return right;
    }

    public int getAvainArvo() {
        return avainArvo;
    }

    public Solmu getSolmu() {
        return solmu;
    }

    public FibonaccikekoSolmu getChild() {
        return child;
    }

    public FibonaccikekoSolmu getParent() {
        return parent;
    }

    public void setParent(FibonaccikekoSolmu parent) {
        this.parent = parent;
    }

    public int getDegree() {
        return degree;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    /**
     * Kasvattaa degree-arvoa yhdelllä.
     */
    public void kasvata() {
        degree++;
    }

    /**
     * Pienentää degree-arvoa yhdellä.
     */
    public void pienenna() {
        degree--;
    }

    public void setChild(FibonaccikekoSolmu child) {
        this.child = child;
    }

    public void setKasitelty(boolean kasitelty) {
        this.kasitelty = kasitelty;
    }

    public boolean isKasitelty() {
        return kasitelty;
    }

    public void setAvainArvo(int avainArvo) {
        this.avainArvo = avainArvo;
    }

    public boolean isMarked() {
        return marked;
    }

}
