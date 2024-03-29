package Aereo;

import Utils.Coda;
import Persona.Turista;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class Bagno extends Thread{
    private  Coda<Turista> c;
    private  Coda<Turista> finio;
    private Lock l;

    private Turista t;


    //Turista in coda entra in bagno
    public void DareBagno(Coda<Turista> a)
    {

        while (a.size()>0){

            Turista e = a.pop();
            c.push(e);
        }
    }


    //Turista si mette in coda
    public void DareBisogno(Turista e)
    {
        c.push(e);
    }

    public Bagno()
    {
        c = new Coda<Turista>();
        l= new ReentrantLock();
    }

    public Coda<Turista> getC() {
        return c;
    }

    //Turista occupa il bagno per un periodo di tempo e poi lo rilascia
    public synchronized void Bisogno(int i)
    {

        try
        {
            l.lock();
            t=c.pop();
            System.out.print("il bagno è occupato da ");
            System.out.println(t.getName());
            Random r = new Random();
            int k= r.nextInt(5000);

            Thread.sleep(k);

        }
        catch (InterruptedException e) {}

        finally {
            l.unlock();
                    finio.push(t);
        }


    }

    public Turista finito()
    {
       return finio.pop();
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


