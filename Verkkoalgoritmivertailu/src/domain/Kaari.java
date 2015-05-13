package domain;

public class Kaari {
    private final Solmu kohdeSolmu;
    private final int paino;

    public Kaari(Solmu kohdeSolmu, int paino) {
        this.kohdeSolmu = kohdeSolmu;
        this.paino = paino;
    }

    public Solmu getKohdeSolmu() {
        return kohdeSolmu;
    }

    public int getPaino() {
        return paino;
    }
    
}