package Aereoporto.ZonaCheckIn;

import Aereoporto.ZonaCheckIn.CartaImbarco;
import Persona.Persona;
import Persona.Turista;
import Persona.ImpiegatoCheckIn;
import Utils.Coda;

import java.time.LocalDate;

public class Banco extends Thread{
    Coda<Turista> codaTuristi;

    ImpiegatoCheckIn impiegatoCheckIn;
    //persona che sta al banco
    //coda di persone che aspettano di essere servite
    //nastro traspotaore
    //bagagli che vengono sul nastro trasportatore

    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
                Turista t = codaTuristi.pop();
                t.setCartaImbarco(generaCartaImbarco(t));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public CartaImbarco generaCartaImbarco(Turista p) {
        // aggiungere i dati sui voli
        return new CartaImbarco(p.getDoc().getNome(), p.getDoc().getCognome(), 0, LocalDate.now(), "", p.getDestinazione(), p.GetBagaglio().getEtichetta().getIdRiconoscimentoBagaglio());
    }


}
