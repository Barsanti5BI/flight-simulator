package Aereo;
import  Utils.Coda;
import  java.util.Random;

public class Entrata extends Thread{
    private Coda<F_Turista> entranti;
    private Coda<F_Turista> salitoant;
    private Coda<F_Turista> salitopost;
    int i=0;
    private TheBackrooms tb;

    public Entrata()
    {
        entranti=new Coda<F_Turista>();
        salitoant=new Coda<F_Turista>();
        salitopost=new Coda<F_Turista>();
        i=entranti.size();
        tb=new TheBackrooms();
    }

    public void run()
    {
        //tb.start();
        try{
            while(!entranti.isEmpty() ) {
                //System.out.println("(EN) mancano " + entranti.size());
                //System.out.println("(EN)  I Turisti stanno entrando...");
                System.out.println("entrati nel metodo" + entranti.size());
                if( entranti.size() % 2==0)
                {
                    //salitoant.push(entranti.pop());
                    F_Turista ft=entranti.pop();
                    salitoant.push(entranti.pop());
                    Thread.sleep(10);
//                    F_Turista ft = entranti.pop();
//                    if(!Backroom(ft))
//                    {
//                        salitoant.push(ft);
//                        Thread.sleep(100);
//                    }
                }
                if(  entranti.size() % 2==1)
                {
                {   F_Turista ft=entranti.pop();
                    if(!Backroom(ft))
                    {
                        salitoant.push(ft);
                        //Thread.sleep(1);

                    }
                   /* Thread.sleep(100);
                    Thread.sleep(10);*/
                }
                
                Thread.sleep(100);
            }
            System.out.println("(EN)    Sono entrati tutti i turisti sono entrati nell'aereo.");
        }
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
        System.out.println("entranti" + entranti.size());
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
        int k = r.nextInt(10000);
        Random ra = new Random();
        int ka= ra.nextInt(5);
        boolean b=false;

        if( k==1 )
        {
            System.out.print("(en)    Passeggero ");
            System.out.print(t.Get_id());
            System.out.println(" è finito nel livello "+ka+" dei Backrooms");
            b=true;
            tb.DareMorto(t);
        }
        return b;
    }
}

