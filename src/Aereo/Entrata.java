package Aereo;
import  Utils.Coda;

public class Entrata extends Thread{
    private Coda<F_Turista> entranti;
    private Coda<F_Turista> salitoant;
    private Coda<F_Turista> salitopost;
    int i=0;
    private int timer;



    public Entrata()
    {
        entranti=new Coda<F_Turista>();
        salitoant=new Coda<F_Turista>();
        salitopost=new Coda<F_Turista>();
        i=entranti.size();

    }
  /*  public void TakeTime(int timer)
     {
         this.timer=timer;
     }
*/
    //divide in 2 file

    public void run()
    {
        try{//&& timer>0
            while(!entranti.isEmpty() ) {
                System.out.println("(EN) " + entranti.size());
                System.out.println("(EN)  I Turisti stanno entrando...");
                if( entranti.size() % 2==0)
                {
                    salitoant.push(entranti.pop());
                    Thread.sleep(10);
                }
                if(  entranti.size() % 2==1)
                {
                    salitopost.push(entranti.pop());
                    Thread.sleep(10);
                }
                this.sleep(10);
            }
            System.out.println("(EN)    I Turisti sono entrati.");
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

}

