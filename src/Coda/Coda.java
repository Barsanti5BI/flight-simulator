package Coda;

import java.util.LinkedList;
public class Coda<T> {
   private LinkedList<T> list;
   public Coda() {
      list = new LinkedList<T>();
   }

   public synchronized T pop() {
      try {
         while (list.isEmpty()) {
            wait();
         }
      } catch(InterruptedException ex) {}
      return list.removeFirst(); // estrazione primo elemento
   }

   public synchronized void push(T element) {
      list.addLast(element); // inserimento in testa
      notify();
   }

   public int size() {
      return list.size();
   }

   public boolean isEmpty() {
      return list.isEmpty();
   }
}
