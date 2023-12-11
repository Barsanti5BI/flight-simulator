package Aereoporto.ZonaControlli;

import Persona.Bagaglio;
import Persona.Turista;
import Utils.Coda;

import java.util.ArrayList;

public class Scanner extends Thread{
   // TODO: persona che controlla i bagagli sul nastro per controllare se sono presenti oggetti pericolosi
   Coda<Bagaglio> codaBagagli;
   ArrayList<Bagaglio> bagagliControllati;

   public Scanner(){
      this.codaBagagli = new Coda<>();
      this.bagagliControllati = new ArrayList<>();
   }

    public void entraInCoda(Bagaglio bagaglio){
        codaBagagli.push(bagaglio);
    }

    public void run(){
        // TODO: controllo a intervalli i bagagli dalla coda
        // TODO: mostro risultato al personale che decide se far passare il turista o meno
    }

    public void mettiSuNastroTrasportatore(Turista t) {
       // TODO: implementare in base al modo in cui i bagagli sono associati al turista
    }
}
