package TorreDiControllo;
import Aereo.*;
import Persona.*;
import Aereoporto.*;

public class Navetta {
    public Gate gate;
    public Aereo aereo;
    public Coda<Turista> codaTuristi;

    public Navetta(Gate g, Aereo a)
    {
        this.gate = g;
        this.aereo = a;
        this.codaTuristi = new Coda<Turista>();
    }


    public void navettaAndata()
    {
        //prendo la coda dei turisti dal gate
        codaTuristi = gate.GetCodaTurista();
        System.out.println("I turisti sono entrati nella navetta");

        //la navetta porta i turisti all'aereo
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return;
        }

        //prendo la coda dei turisti e la metto nell'entrata dell'aereo
        aereo.getEntrata().DareEntranti(codaTuristi);
        System.out.println("I turisti sono usciti dalla navetta");
    }

    public void navettaRitorno(){
        //prendo la coda dei turisti dall'uscita dell'aereo
        codaTuristi = aereo.getUscita().Getusciti();
        System.out.println("I turisti sono entrati nella navetta");

        //la navetta porta i turisti all'aereoporto
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return;
        }

        System.out.println("I turisti sono usciti dall'aeroporto");
    }
}