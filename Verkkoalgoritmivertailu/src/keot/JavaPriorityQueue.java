package keot;

import java.util.PriorityQueue;

public class JavaPriorityQueue<E> implements Minimikeko<E>{

    private final PriorityQueue<E> keko;

    public JavaPriorityQueue() {
        this.keko = new PriorityQueue<>();
    }

    @Override
    public void insert(E e) {
        keko.add(e);
    }

    @Override
    public E min() {
        return keko.peek();
    }

    @Override
    public E delMin() {
        return keko.poll();
    }

    @Override
    public void decreaseKey(E e) {
        keko.remove(e);
        keko.add(e);
    }

    @Override
    public boolean empty() {
        return keko.isEmpty();
    }

    @Override
    public void clear() {
        keko.clear();
    }

    @Override
    public int size() {
        return keko.size();
    }
    
}
