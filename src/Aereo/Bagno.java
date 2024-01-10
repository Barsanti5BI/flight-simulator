

package Aereo;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class Bagno extends Thread{
    private  Coda<Turista> c;

    private Lock l;


    public Bagno(Coda<Turista> a)
    {
        l= new ReentrantLock();
        c=a;

    }

    public Coda<Turista> getC() {
        return c;
    }

    public synchronized void Bisogno(int i)
    {
        try
        {
            l.lock();

//serve nome del turista

            System.out.println("il bagno è occupato da "+c.pop());
            random r = new random();
            int k= r.nextInt(5000);

            Thread.sleep(k);

        }
        catch (InterruptedException e) {}

        finally {
            l.unlock();
        }

    }
    public void run()
    {
        //try{
        for (int i = 0 ; i<c.size();i++)
        {
            Bisogno(i);
        }




    }
}


