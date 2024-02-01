package Aereo;
import  Utils.Coda;

public class Entrata extends Thread{
    private Coda<F_Turista> entranti;
    private Coda<F_Turista> salitoant;
    private Coda<F_Turista> salitopost;
    int i=0;

    // prende la coda di persone in entrata
    public void DareEntranti(Coda<F_Turista> e)
    {
        while (!e.isEmpty()){
          F_Turista a = e.pop();
          entranti.push(a);
        }
    }

    public Entrata()
    {
        entranti=new Coda<F_Turista>();
        salitoant=new Coda<F_Turista>();
        salitopost=new Coda<F_Turista>();
        i=entranti.size();
    }

    //divide in 2 file

    public void run()
    {
        try{
            while(!entranti.isEmpty()) {
                System.out.println("(EN)    I Turisti stanno entrando...");
                if( entranti.size() % 2==0)
                {
                    salitoant.push(entranti.pop());
                    Thread.sleep(1);
                }
                if(  entranti.size() % 2==1)
                {
                    salitopost.push(entranti.pop());
                    Thread.sleep(1);
                }
            }
            System.out.println("(EN)    I Turisti sono entrati.");
        }
        catch (Exception e){};
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

}

