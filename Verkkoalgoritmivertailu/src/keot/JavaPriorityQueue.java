package keot;

import java.util.PriorityQueue;

public class JavaPriorityQueue<E> implements Minimikeko<Object>{

    private final PriorityQueue<E> keko;

    public JavaPriorityQueue() {
        this.keko = new PriorityQueue<>();
    }
    
    @Override
    public void insert(Object u) {
        
    }

    @Override
    public E min() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E delMin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
