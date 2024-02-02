//ATTENZIONE ALLA CODA VUOTA CHE CREAZIONE OGGETTO NON CREA
package Aereo;

import Utils.Coda;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bagno extends Thread{
    private  Coda<F_Turista> c;
    private  Coda<F_Turista> finio;
    private Lock l;
    private F_Turista t;
    public void run()
    {
        //quando pos<1->posizione < 85 fa andare in bagno
        while(pos!=1)
        {
            for (int i = 0 ; i<c.size();i++)
            {
                Bisogno(i);
            }
        }
    }

    //Turista in coda entra in bagno
    public void DareBagno(Coda<F_Turista> a)
    {

        while (a.size()>0){

            F_Turista e = a.pop();
            c.push(e);
        }
    }


    //Turista si mette in coda
    public void DareBisogno(F_Turista e)
    {
        c.push(e);
    }

    public Bagno()
    {
        c = new Coda<F_Turista>();
        l= new ReentrantLock();
    }

    public Coda<F_Turista> getCoda() {
        return c;
    }

    //Turista occupa il bagno per un periodo di tempo e poi lo rilascia
    public synchronized void Bisogno(int i)
    {

        try
        {
            l.lock();
            t=c.pop();
            System.out.print("(BG)   Il bagno è occupato da " + t.Get_id() + ".");
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

    public Coda<F_Turista> finito()
    {
       return finio;
    }

    private int pos=0;
    public void setpos()
    {
     pos=1;
    }

}


