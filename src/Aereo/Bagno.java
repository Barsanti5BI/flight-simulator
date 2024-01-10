

package Aereo;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bagno extends Thread{
    private  Coda c;

    private Lock l;


    public Bagno(Coda a)
    {
        l= new ReentrantLock();
        c=a;

    }

    public Coda getC() {
        return c;
    }

    public synchronized void Bisogno(int i)
    {
        try
        {
            l.lock();

//serve nome del turista

            System.out.println("il bagno è occupato da "+c.pop());
            Thread.sleep(2000);




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


