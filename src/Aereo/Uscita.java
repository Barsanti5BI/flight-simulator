package Aereo;
import  Utils.Coda;

public class Uscita extends Thread{

    private Coda<F_Turista> uscenti;


    private Coda<F_Turista> uscentiAnt;
    private Coda<F_Turista> uscentiPost;

    private Coda<F_Turista> usciti;
    int i=0;

    public Uscita(AereoPasseggeri ap)
    {
        uscenti=new Coda<F_Turista>();
        uscentiPost=new Coda<F_Turista>();
        uscentiAnt = new Coda<F_Turista>();
        uscenti=ap.FaiScendere();
        usciti=new Coda<F_Turista>();
        int i=uscenti.size();
    }

    public void run()
    {
        try{
            while(uscenti.size()>0)
            {


             if ( uscenti.size() % 2==0)
             {
               uscentiAnt.push(uscenti.pop());
                Thread.sleep(1);
             }
             if( uscenti.size() % 2==1)
             {
                uscentiPost.push(uscenti.pop());
                Thread.sleep(1);
             }
            }
          while (uscentiAnt.size()>0 && uscentiPost.size()>0)
          {
              usciti.push(uscentiAnt.pop());
              usciti.push(uscentiPost.pop());
          }
        }
        catch (Exception e){};


    }

    public Coda<F_Turista> GetUscitiDavanti() {return uscentiAnt;}
    public Coda<F_Turista> GetUscitiDietro()
    {
        return  uscentiPost;
    }
    public  Coda<F_Turista>  GetUsciti(){ return  usciti;}
    public int Getnperson()
    {
        return i;
    }
    public int GetNuscitidavanti(){return uscentiAnt.size();}
    public int GetNuscitieDietro(){ return uscentiPost.size();}

}
