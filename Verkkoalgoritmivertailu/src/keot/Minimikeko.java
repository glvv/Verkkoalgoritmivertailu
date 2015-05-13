package keot;

public interface Minimikeko<E> {
    
    public void insert(E u);
    public void decreaseKey(E e);
    public E min();
    public E delMin();
    public boolean empty();
    public void clear();
    public int size();
    
}