
import algoritmit.Dijkstra;
import domain.Solmu;
import domain.Verkko;
import java.util.Random;
import keot.JavaPriorityQueue;
import keot.Minimikeko;

public class Main {

    public static void main(String[] args) {
        JavaPriorityQueue<Solmu> keko = new JavaPriorityQueue<>();
        testaaDijkstra(100, 100, 0, 0, 99, 99, keko);
    }

    private static void tulosta(char[][] kentta) {
        for (char[] c : kentta) {
            for (char v : c) {
                System.out.print(v);
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public static void testaaDijkstra(int leveys, int korkeus, int alkuSolmuX, int alkuSolmuY, int kohdeSolmuX, int kohdeSolmuY, Minimikeko keko) {
        char[][] kentta = arvoKentta(leveys, korkeus);
        tulosta(kentta);
        
        Verkko verkko = new Verkko(kentta);
        Dijkstra dijkstra = new Dijkstra(keko);
        dijkstra.haeLyhimmatPolut(alkuSolmuX, alkuSolmuY, verkko);
        
        Solmu kohdeSolmu = verkko.haeSolmut()[kohdeSolmuX][kohdeSolmuY];
        
        kentta[kohdeSolmuX][kohdeSolmuY] = '@';
        while (kohdeSolmu.getEdellinen() != null) {
            kohdeSolmu = kohdeSolmu.getEdellinen();
            kentta[kohdeSolmu.getX()][kohdeSolmu.getY()] = '@';
        }
        tulosta(kentta);
    }
    
    public static char[][] arvoKentta(int leveys, int korkeus) {
        char[][] kentta = new char[leveys][korkeus];
        Random random = new Random();
        for (int a = 0; a < leveys; a++) {
            for (int i = 0; i < korkeus; i++) {
                int e = random.nextInt(4);
                if (e == 0) {
                    kentta[a][i] = ' ';
                } else if (e == 1) {
                    kentta[a][i] = '#';
                } else if (e == 2) {
                    kentta[a][i] = '¤';
                } else if (e == 3) {
                    kentta[a][i] = '%';
                }
            }
        }
        return kentta;
    }

}
