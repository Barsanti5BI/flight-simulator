package Aereoporto.ZonaCheckIn;

import Aereoporto.ZonaCheckIn.CartaImbarco;
import Persona.Persona;
import java.time.LocalDate;

public class Banco {
   //persona che sta al banco
   //coda di persone che aspettano di essere servite
   //nastro traspotaore
   //bagagli che vengono sul nastro trasportatore

    public CartaImbarco generaCartaImbarco(Persona p) {

        // aggiungere i dati sui voli
        return new CartaImbarco(p.getDoc().getNome(), p.getDoc().getCognome(), 0, LocalDate.now(), "", "", "");
    }
}
