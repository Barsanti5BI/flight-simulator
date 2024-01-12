package Aereo;

public class Entrata extends Thread{

    private Coda<Turista> entranti;


    private Coda<Turista> salitoant;
    private Coda<Turista> salitopost;

    int i=0;

    public Entrata(Coda<Turista> e)
    {
        entranti=new Coda<Turista>();


        this.entranti=e;


        salitoant=new Coda<Turista>();
        salitopost=new Coda<Turista>();
        i=entranti.size();
    }
    public void run()
    {
        try{
            while(entranti.size>0&& entranti.size % 2==0)
            {
                salitoant.add(entranti.pop);
                thread.sleep(1);
            }
            while(entranti.size>0&& entranti.size % 2==1)
            {
                salitopost.add(entranti.pop);
                thread.sleep(1);
            }
        }
        catch (exception e){};


    }


    public Coda<Turista> GetsalitiDavanti() {return  salitoant;}
    public Coda<Turista> GetsalitiDietro()
    {
        return  salitopost;
    }
    public int Getnperson()
    {
        return i;
    }
    public int GetNpersonedavanti(){return salitoant.size();}
    public int GetNpersoneDietro(){return; salitopost.size();}

}

