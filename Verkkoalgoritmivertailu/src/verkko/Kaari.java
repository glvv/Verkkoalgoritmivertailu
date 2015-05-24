package verkko;

/**
 * Kaari kuvaa verkon kaarta, se yhdistää kaksi solmua ja sillä on jokin paino.
 */
public class Kaari {

    private final Solmu kohdeSolmu;
    private int paino;

    /**
     * Konstruktorissa asetetaan kaaren kohdesolmu ja sen paino.
     *
     * @param kohdeSolmu Kaaren kohdesolmu.
     * @param paino Kaaren paino.
     */
    public Kaari(Solmu kohdeSolmu, int paino) {
        this.kohdeSolmu = kohdeSolmu;
        this.paino = paino;
    }

    /**
     * Palauttaa kaaren kohdesolmun.
     *
     * @return Kaaren kohdesolmu.
     */
    public Solmu getKohdeSolmu() {
        return kohdeSolmu;
    }

    /**
     * Palauttaa kaaren painon.
     *
     * @return Kaaren paino.
     */
    public int getPaino() {
        return paino;
    }

    public void setPaino(int paino) {
        this.paino = paino;
    }
    
}
