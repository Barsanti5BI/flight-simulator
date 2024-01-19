
//ATTENZIONE ALLA CODA VUOTA CHE CREAZIONE OGGETTO NON CREA
package Aereo;

import Utils.Coda;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class Bagno extends Thread{
    private  Coda<Turista> c;
    private  Coda<Turista> finio;
    private Lock l;


    public void DareBagno(Coda<Turista> a)
    {

        while (a.size()>0){

            Turista e = a.pop();
            c.push(e);
        }
    }


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

    public synchronized void Bisogno(int i)
    {
        Turista t=new Turista("prova","prova","prova","911",1);
        try
        {
            l.lock();

//serve nome del turista
            t=c.pop();
            System.out.println("il bagno è occupato da "+t.Nome);
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


