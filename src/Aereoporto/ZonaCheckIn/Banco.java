package Aereoporto.ZonaCheckIn;

import Persona.Etichetta;
import Persona.ImpiegatoCheckIn;
import Persona.Turista;
import TorreDiControllo.Viaggio;
import Utils.Coda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Banco {
    int numeroBanco;
    Coda<Turista> codaTuristi;
    NastroTrasportatore nastroTrasportatore;
    ImpiegatoCheckIn impiegatoCheckIn;
    ArrayList<Viaggio> viaggi;
    Random rand;

    public Banco(NastroTrasportatore n, int numeroBanco, ArrayList<Viaggio> viaggi){
        rand = new Random();
        codaTuristi = new Coda<>();
        nastroTrasportatore = n;
        impiegatoCheckIn = new ImpiegatoCheckIn(this,nastroTrasportatore, rand.nextInt(0, 1000));
        impiegatoCheckIn.start();
        this.numeroBanco = numeroBanco;
        this.viaggi = viaggi;
    }

    public CartaImbarco generaCartaImbarco(Turista p,Viaggio viaggio){
        // aggiungere i dati sui voli
        return new CartaImbarco(p.getDoc().getNome(), p.getDoc().getCognome(), viaggio.GetNumGate(), LocalDate.now(), viaggio.getAereo().getName(), p.getBagaglio().getEtichetta().getIdRiconoscimentoBagaglio(), Math.random() > 0.5);
    }
    public Etichetta generaEtichetta(Turista t,Viaggio viaggio){
        return new Etichetta(viaggio.getAereo().Get_ID(),t.getBagaglio().getEtichetta().getIdRiconoscimentoBagaglio());
    }
    public Coda<Turista> getCodaTuristi() {
        return codaTuristi;
    }
    public ArrayList<Viaggio> getViaggi(){
        return viaggi;
    }
    public int getIndice() {
        return  numeroBanco;
    }

    public ImpiegatoCheckIn getImpiegatoCheckIn()
    {
        return impiegatoCheckIn;
    }
}
