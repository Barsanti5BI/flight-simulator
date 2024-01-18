package Aereoporto.ZonaCheckIn;

import Persona.Etichetta;
import Persona.ImpiegatoCheckIn;
import Persona.Turista;
import Utils.Coda;

import java.time.LocalDate;

public class Banco {
    int numeroBanco;
    Coda<Turista> codaTuristi;
    NastroTrasportatore nastroTrasportatore;
    ImpiegatoCheckIn impiegatoCheckIn;

    public Banco(NastroTrasportatore n, int numeroBanco){
        codaTuristi = new Coda<>();
        nastroTrasportatore = n;
        impiegatoCheckIn.start();
        this.numeroBanco = numeroBanco;
    }

    public CartaImbarco generaCartaImbarco(Turista p) {
        // aggiungere i dati sui voli
        return new CartaImbarco(p.getDoc().getNome(), p.getDoc().getCognome(), 0, LocalDate.now(), "", p.getBagaglio().getEtichetta().getIdRiconoscimentoBagaglio(), false);
    }
    public Etichetta generaEtichetta(Turista t){
        return new Etichetta("Codice Veicolo",t.getBagaglio().getEtichetta().getIdRiconoscimentoBagaglio());
    }

    public Coda<Turista> getCodaTuristi() {
        return codaTuristi;
    }

    public int getIndice() {
        return  numeroBanco;
    }

    public ImpiegatoCheckIn getImpiegatoCheckIn()
    {
        return impiegatoCheckIn;
    }
}
