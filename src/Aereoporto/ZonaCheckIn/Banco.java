package Aereoporto.ZonaCheckIn;

import Persona.Turista;
import Persona.Bagaglio;
import  Persona.Etichetta;
import Persona.ImpiegatoCheckIn;
import Utils.Coda;

import java.time.LocalDate;

public class Banco extends Thread{
    Coda<Turista> codaTuristi;
    NastroTrasportatore nastroTrasportatore;

    ImpiegatoCheckIn impiegatoCheckIn;
    //persona che sta al banco
    //coda di persone che aspettano di essere servite
    //nastro traspotaore
    //bagagli che vengono sul nastro trasportatore
    public  Banco(NastroTrasportatore n){
        codaTuristi = new Coda<>();
        nastroTrasportatore = n;
    }
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);

                impiegatoCheckIn.eseguiCheckIn();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public CartaImbarco generaCartaImbarco(Turista p) {
        // aggiungere i dati sui voli
        return new CartaImbarco(p.getDoc().getNome(), p.getDoc().getCognome(), 0, LocalDate.now(), "", p.GetBagaglio().getEtichetta().getIdRiconoscimentoBagaglio(), false);
    }
    public Etichetta generaEtichetta(Turista t){
        return new Etichetta("Codice Veicolo",t.GetBagaglio().getEtichetta().getIdRiconoscimentoBagaglio());
    }

    public Coda<Turista> GetCodaTuristi() {
        return codaTuristi;
    }

    public Object getIndice() {
    }
}
