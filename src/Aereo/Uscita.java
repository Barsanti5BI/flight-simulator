package Aereo;
import  Utils.Coda;

public class Uscita extends Thread{

    private Coda<Turista> uscenti;


    private Coda<Turista> uscentiAnt;
    private Coda<Turista> uscentiPost;

    private Coda<Turista> usciti;
    int i=0;


    public Uscita(AereoPasseggeri ap)
    {
        uscenti=new Coda<Turista>();
        uscentiPost=new Coda<Turista>();
        uscentiAnt = new Coda<Turista>();
        uscenti=ap.FaiScendere();
        usciti=new Coda<Turista>();
        int i=uscenti.size();
    }

    public void run()
    {
        try{
            while(uscenti.size()>0&& uscenti.size() % 2==0)
            {
               uscentiAnt.push(uscenti.pop());
                Thread.sleep(1);
            }
            while(uscenti.size()>0&& uscenti.size() % 2==1)
            {
                uscentiPost.push(uscenti.pop());
                Thread.sleep(1);
            }

          while (uscentiAnt.size()>0 && uscentiPost.size()>0)
          {
              usciti.push(uscentiAnt.pop());
              usciti.push(uscentiPost.pop());
          }
        }
        catch (Exception e){};


    }

    public Coda<Turista> GetUscitiDavanti() {return uscentiAnt;}
    public Coda<Turista> GetUscitiDietro()
    {
        return  uscentiPost;
    }
    public  Coda<Turista>  GetUsciti(){ return  usciti;}
    public int Getnperson()
    {
        return i;
    }
    public int GetNuscitidavanti(){return uscentiAnt.size();}
    public int GetNuscitieDietro(){ return uscentiPost.size();}

}
