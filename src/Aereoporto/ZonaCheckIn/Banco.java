package Aereoporto.ZonaCheckIn;

import Persona.Etichetta;
import Persona.ImpiegatoCheckIn;
import Persona.Turista;
import TorreDiControllo.Viaggio;
import Utils.Coda;

import java.time.LocalDate;
import java.util.ArrayList;

public class Banco {
    int numeroBanco;
    Coda<Turista> codaTuristi;
    NastroTrasportatore nastroTrasportatore;
    ImpiegatoCheckIn impiegatoCheckIn;
    ArrayList<Viaggio> viaggi;

    public Banco(NastroTrasportatore n, int numeroBanco, ArrayList<Viaggio> viaggi){
        codaTuristi = new Coda<>();
        nastroTrasportatore = n;
        impiegatoCheckIn = new ImpiegatoCheckIn(this,nastroTrasportatore);
        impiegatoCheckIn.start();
        this.numeroBanco = numeroBanco;
        this.viaggi = viaggi;
    }

    public CartaImbarco generaCartaImbarco(Turista p,Viaggio viaggio){
        // aggiungere i dati sui voli
        return new CartaImbarco(p.getDoc().getNome(), p.getDoc().getCognome(), viaggio.GetNumGate(), LocalDate.now(), viaggio.Get_Aereo().getName(), p.getBagaglio().getEtichetta().getIdRiconoscimentoBagaglio(), Math.random() > 0.5);
    }
    public Etichetta generaEtichetta(Turista t,Viaggio viaggio){
        return new Etichetta(viaggio.Get_Aereo().Get_ID(),t.getBagaglio().getEtichetta().getIdRiconoscimentoBagaglio());
    }
    public Coda<Turista> getCodaTuristi() {
        return codaTuristi;
    }
    public Viaggio getViaggio(){
        return viaggi.get((int)Math.random()*viaggi.size());
    }
    public int getIndice() {
        return  numeroBanco;
    }

    public ImpiegatoCheckIn getImpiegatoCheckIn()
    {
        return impiegatoCheckIn;
    }
}
