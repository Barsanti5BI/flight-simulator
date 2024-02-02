package Aereo;
//feature Luo
import Utils.Coda;


import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TheBackrooms extends Thread{
    private Coda<F_Turista> codaMorti;

    public void DareMorti(Coda<F_Turista> e)
    {
        while (e.size()>0){
            F_Turista a = e.pop();
            codaMorti.push(a);
        }
    }
    public void DareMorto(F_Turista e)
    {
        codaMorti.push(e);
    }

    public TheBackrooms()
    {

        codaMorti=new Coda<F_Turista>();


    }

    private void sopravive(F_Turista t)
    {
        //sopravive al livello

        Random r = new Random();
        int k= r.nextInt(100);
        if(k<5)
        {

            codaMorti.push(t);
        }
        else {

            Morte(t);
        }

    }
    private void Incontro(F_Turista t)
    {
        //incontro

        try {
            Random r = new Random();
            int k = r.nextInt(200);

            if (k < 14) {
                Random ra = new Random();
                int ka = ra.nextInt(codaMorti.size());

                try {
                    codaMorti.GetAtPos(ka).dead.lock();
                    Random kda = new Random();
                    int ratio = kda.nextInt(400);
                    if (ratio < 100) {
                        System.out.print("(tb)   " ); System.out.print(t.Get_id()); System.out.print(" è stato ucciso da " ); System.out.println( codaMorti.GetAtPos(ka).Get_id());
                        codaMorti.GetAtPos(ka).dead.unlock();
                    } else if (ratio < 200) {
                        System.out.print("(tb)   " );  System.out.print(t.Get_id()); System.out.print( " si sono doppi suicidati con " ); System.out.println(codaMorti.GetAtPos(ka).Get_id());
                        codaMorti.Eliminated(ka);
                    } else if (ratio < 300) {
                        System.out.print("(tb)   " ); System.out.print(t.Get_id()); System.out.print( " ha ucciso "  );System.out.println( codaMorti.GetAtPos(ka).Get_id());
                        codaMorti.Eliminated(ka);
                        codaMorti.push(t);
                    } else {
                        System.out.print("(tb)   " );System.out.print(t.Get_id());  System.out.print( " è diventato amico di " ); System.out.println( codaMorti.GetAtPos(ka).Get_id());
                        codaMorti.GetAtPos(ka).dead.unlock();
                        codaMorti.push(t);
                    }

                } catch (Exception e) {
                }
                ;
            } else {
                codaMorti.push(t);
            }
        } catch (Exception e){}
    }
//metodi di morte
    private void Morte(F_Turista t)
    {

        Random r = new Random();
        int k= r.nextInt(10);
        Coda<String> deathmsg = new Coda<String>();
        deathmsg.push( "     stato mangiato da un entity ");
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
            while (codaMorti.size()==0)
            {
                Thread.sleep(1000);
            }

            while (codaMorti.size()>0)
            {
                F_Turista t=codaMorti.pop();
                Random r = new Random();
                int k= r.nextInt(2);
                if(codaMorti.size()==0)
                {
                    sopravive(t);
                }
                else if(k==0){
                    Incontro(t);

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