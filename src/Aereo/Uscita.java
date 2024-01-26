package Aereo;
import  Utils.Coda;

public class Uscita extends Thread{
    private Coda<F_Turista> uscenti;
    private Coda<F_Turista> uscentiAnt;
    private Coda<F_Turista> uscentiPost;
    private Coda<F_Turista> usciti;

    int i=0;

    public Uscita(Aereo ap)
    {
        uscenti=new Coda<F_Turista>();
        uscentiPost=new Coda<F_Turista>();
        uscentiAnt = new Coda<F_Turista>();
        usciti=new Coda<F_Turista>();
        int i = uscenti.size();
    }
//divide in 2 che forma unica coda all'uscita
    public void run()
    {
        try{
            System.out.println("I Turisti Stanno uscendo...");
            while(!uscenti.isEmpty())
            {
             if (uscenti.size() % 2==0)
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
          while (!uscentiAnt.isEmpty() && !uscentiPost.isEmpty())
          {
              usciti.push(uscentiAnt.pop());
              usciti.push(uscentiPost.pop());
          }
        }
        catch (Exception e){};
        System.out.println("I Turisti sono usciti.");
    }

    public Coda<F_Turista> GetUscitiDavanti() {return uscentiAnt;}
    public Coda<F_Turista> GetUscitiDietro()
    {
        return  uscentiPost;
    }
    public  Coda<F_Turista>  GetUsciti(){ return  usciti;}
    public int Getnpersoneuscita()
    {
        return i;
    }
    public int GetNuscitidavanti(){return uscentiAnt.size();}
    public int GetNuscitieDietro(){ return uscentiPost.size();}

}
