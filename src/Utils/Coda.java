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

    public synchronized T pop(){
        try{
            while(list.isEmpty()) {
                wait();
            }
        } catch(InterruptedException ex) {}
        return list.removeFirst();
    }


    public boolean isEmpty() {
        return list.isEmpty();
    }

    public synchronized void push(T element){
        list.addLast(element);
        notify();
    }
}

