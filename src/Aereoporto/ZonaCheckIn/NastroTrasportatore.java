package Aereoporto.ZonaCheckIn;

import Persona.Bagaglio;
import Utils.Coda;

public class NastroTrasportatore {
   //Lista di bagagli che sono stati caricati sul nastro trasportatore iseriti a indice in base alla posizione
   //del banco di check-in da cui sono stati caricati

    public Coda<Bagaglio> codaBagagli;
    public Coda<Bagaglio> bagagliCaricati;
    public NastroTrasportatore(){
       codaBagagli = new Coda<>();
       bagagliCaricati = new Coda<>();
    }

    public void aggiungiBagaglio(Bagaglio b, Object indice) {
    }
}
