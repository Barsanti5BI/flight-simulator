package Utils;

import java.util.LinkedList;

public class Coda<T> {
    private LinkedList<T> list;

    public int size(){
        return list.size();
    }

    public Coda(){
        list = new LinkedList<T>();
    }

    public T pop(){
        try{
            while(list.isEmpty())
                Thread.sleep(10);
        } catch(InterruptedException ex) {}
        return this.estrai();
    }

    private synchronized T estrai() {
        return list.removeFirst(); }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public synchronized void push(T element){
        list.addLast(element);
    }
}

