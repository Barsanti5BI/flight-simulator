package Aereo;

public class Entrata extends Thread{

    private Coda<Turista> entranti;


    private Coda<Turista> salito;

    int i=0;

    public Entrata(Coda<Turista> e)
    {
        entranti=new Coda<Turista>();


        this.entranti=e;


        salito=new Coda<Turista>();
        i=entranti.size();
    }
    public void run()
    {
        try{
            while(entranti.size>0)
            {
                salito.add(entranti.pop);
                thread.sleep(1);
            }

        }
        catch (exception e){};


    }


    public Coda<Turista> Getsaliti()
    {
    return  salito;
    }
    public int Getnperson()
    {
        return i;
    }

}

