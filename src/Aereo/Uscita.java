package Aereo;
import  Utils.Coda;

public class Uscita extends Thread{
    private Coda<F_Turista> uscenti;
    private Coda<F_Turista> uscentiAnt;
    private Coda<F_Turista> uscentiPost;
    private Coda<F_Turista> usciti;
    private Aereo a;
    private boolean aereo_arrivato;
    int i=0;

    public Uscita(Aereo ap)
    {
        uscenti=new Coda<F_Turista>();
        uscentiPost=new Coda<F_Turista>();
        uscentiAnt = new Coda<F_Turista>();
        usciti=new Coda<F_Turista>();
        int i = uscenti.size();
        a = ap;
        aereo_arrivato = false;
    }
//divide in 2 che forma unica coda all'uscita
    public void run()
    {
        try{
            while(true){
                while(!aereo_arrivato){
                    this.sleep(10);
                }
                Riempi_Usciti();
                System.out.println("(US) I Turisti stanno uscendo...");
                while(aereo_arrivato && uscenti.size() > 0)
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
                while (!uscentiAnt.isEmpty()) {
                    usciti.push(uscentiAnt.pop());
                }
                while(!uscentiPost.isEmpty()){
                    usciti.push(uscentiPost.pop());
                }
                //System.out.println("(US) "+ usciti.size());
                System.out.println("(US) I Turisti sono usciti.");
                aereo_arrivato = false;
                this.sleep(100);
            }
        }catch (Exception e){}
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
    public void Riempi_Usciti(){uscenti = a.FaiScendere();}
    public void Set_Stato_Uscita(boolean stato){
        aereo_arrivato = stato;
    }

}
