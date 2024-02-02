package Aereo;
import  Utils.Coda;
import  java.util.Random;

public class Entrata extends Thread{
    private Coda<F_Turista> entranti;
    private Coda<F_Turista> salitoant;
    private Coda<F_Turista> salitopost;
    int i=0;
    private int timer;
    private TheBackrooms tb;



    public Entrata()
    {
        entranti=new Coda<F_Turista>();
        salitoant=new Coda<F_Turista>();
        salitopost=new Coda<F_Turista>();
        i=entranti.size();
        tb=new TheBackrooms();

    }
  /*  public void TakeTime(int timer)
     {
         this.timer=timer;
     }
*/
    //divide in 2 file

    public void run()
    {
        tb.start();
        try{//&& timer>0
            while(!entranti.isEmpty() ) {
               // System.out.println("(EN) mancano " + entranti.size());
                //System.out.println("(EN)  I Turisti stanno entrando...");
                if( entranti.size() % 2==0)
                {   F_Turista ft=entranti.pop();
                    if(!Backroom(ft))
                    {
                        salitoant.push(ft);
                        Thread.sleep(1);
                    }
                }
                if(  entranti.size() % 2==1)
                {
                    F_Turista ft=entranti.pop();
                    if(!Backroom(ft)) {
                        salitopost.push(entranti.pop());
                        Thread.sleep(1);
                    }

                }
                //this.sleep(10);
            }
            System.out.println("(EN)    Sono entrati tutti i turisti sono entrati nell'aereo.");
            sleep(1000);
        }
        catch (Exception e){};
    }

    // prende la coda di persone in entrata
    public void DareEntranti(Coda<F_Turista> e)
    {
        while (!e.isEmpty()){
            F_Turista a = e.pop();
            entranti.push(a);
        }
    }

    public Coda<F_Turista> GetsalitiDavanti() {return  salitoant;}
    public Coda<F_Turista> GetsalitiDietro()
    {
        return  salitopost;
    }
    public int Getnperson()
    {
        return i;
    }
    public int GetNpersonedavanti(){return salitoant.size();}
    public int GetNpersoneDietro(){ return salitopost.size();}
    public Coda<F_Turista> GetEntranti(){ return entranti;}

    public Boolean Backroom(F_Turista t)
    {
        Random r = new Random();
        int k= r.nextInt(10000);
        Random ra = new Random();
        int ka= ra.nextInt(5);
        boolean b=false;

        if( k==1 )
        {
            System.out.print("(en)    Passeggero ");System.out.print(t.Get_id());System.out.println(" è finito nel livello "+ka+" dei Backrooms");
            b=true;
            tb.DareMortu(t);
        }
        return b;
    }
}

