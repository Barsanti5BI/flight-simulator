package Aereo;
import  Utils.Coda;


public class Entrata extends Thread{

    private Coda<F_Turista> entranti;

  

    private Coda<F_Turista> salitoant;
    private Coda<F_Turista> salitopost;

    int i=0;

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
    public void run()
    {
        try{
            System.out.println("Stanno entrando");
            while(!entranti.isEmpty())
            {

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

