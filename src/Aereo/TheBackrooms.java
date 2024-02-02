package Aereo;
//feature Luo
import Utils.Coda;


import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TheBackrooms extends Thread{
    private Coda<F_Turista> deadmeat;


    // mangiato da un entity
    // annegato
    //


    public void DareMorti(Coda<F_Turista> e)
    {
        while (e.size()>0){

            F_Turista a = e.pop();
            deadmeat.push(a);
        }


    }
    public void DareMortu(F_Turista e)
    {

        deadmeat.push(e);



    }

    public TheBackrooms()
    {

        deadmeat=new Coda<F_Turista>();


    }

    private void sopravive(F_Turista t)
    {
        //sopravive al livello

        Random r = new Random();
        int k= r.nextInt(100);
        if(k<5)
        {

            deadmeat.push(t);
        }
        else {

            DYTD(t);
        }

    }
    private void Backing(F_Turista t)
    {
        //incontro

        try {
            Random r = new Random();
            int k = r.nextInt(200);

            if (k < 14) {
                Random ra = new Random();
                int ka = ra.nextInt(deadmeat.size());

                try {
                    deadmeat.GetAtPos(ka).ded.lock();
                    Random kda = new Random();
                    int ratio = kda.nextInt(400);
                    if (ratio < 100) {
                        System.out.print("(tb)   " ); System.out.print(t.Get_id()); System.out.print(" has been slain by " ); System.out.println( deadmeat.GetAtPos(ka).Get_id());
                        deadmeat.GetAtPos(ka).ded.unlock();
                    } else if (ratio < 200) {
                        System.out.print("(tb)   " );  System.out.print(t.Get_id()); System.out.print( " has commit unalive with " ); System.out.println( deadmeat.GetAtPos(ka).Get_id());
                        deadmeat.Eliminated(ka);
                    } else if (ratio < 300) {
                        System.out.print("(tb)   " ); System.out.print(t.Get_id()); System.out.print( " has  slain  "  );System.out.println( deadmeat.GetAtPos(ka).Get_id());
                        deadmeat.Eliminated(ka);
                        deadmeat.push(t);
                    } else {
                        System.out.print("(tb)   " );System.out.print(t.Get_id());  System.out.print( " has become friends with " ); System.out.println( deadmeat.GetAtPos(ka).Get_id());
                        deadmeat.GetAtPos(ka).ded.unlock();
                        deadmeat.push(t);
                    }

                } catch (Exception e) {
                }
                ;
            } else {
                deadmeat.push(t);
            }
        } catch (Exception e){}
    }

    private void DYTD(F_Turista t)
    {

        Random r = new Random();
        int k= r.nextInt(10);
        Coda<String> deathmsg = new Coda<String>();
        deathmsg.push( " stato mangiato da un entity ");
        deathmsg.push(   "     annegato");
        deathmsg.push(   "     esploso");
        deathmsg.push(   "    morto  attacco cardiaco");
        deathmsg.push(  "     arrosto");
        deathmsg.push( "     diventato uno di noi");
        deathmsg.push(  "     diventato una mummia");
        deathmsg.push(  "     stato fulminanto");
        deathmsg.push(  "     impalato");
        deathmsg.push(  "    morto di colpo di frutta che non puoi nominare");

        System.out.print("(tb)    ");  System.out.print(t.Get_id());System.out.print(" è ");System.out.println( deathmsg.GetAtPos(k));



    }
    public void run()
    {
        try {
            while (deadmeat.size()==0)
            {
                Thread.sleep(1000);
            }

            while (deadmeat.size()>0)
            {
                F_Turista t=deadmeat.pop();
                Random r = new Random();
                int k= r.nextInt(2);
                if(deadmeat.size()==0)
                {
                    sopravive(t);
                }
                else if(k==0){
                    Backing(t);

                }
                else{
                    sopravive(t);
                    Thread.sleep(1000);

                }
            }


        }
        catch(Exception e){}
    }



}