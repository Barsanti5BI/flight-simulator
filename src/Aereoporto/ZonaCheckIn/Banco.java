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
        this.numeroBanco = numeroBanco;
        this.viaggi = viaggi;
    }

    public CartaImbarco generaCartaImbarco(Turista p,Viaggio viaggio, String idBagalio){
        // aggiungere i dati sui voli
        return new CartaImbarco(p.getDoc().getNome(), p.getDoc().getCognome(),LocalDate.now(), viaggio, idBagalio, Math.random() > 0.5);
    }

    public Etichetta generaEtichetta(Turista t, Viaggio viaggio, String idBagaglio){
        return new Etichetta(viaggio.getAereo().Get_ID(), idBagaglio);
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
