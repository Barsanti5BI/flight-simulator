package Aereoporto.ZonaControlli;
import Persona.Bagaglio;
import Utils.Coda;

import java.util.ArrayList;
public class Scanner extends Thread{
   // TODO: persona che controlla i bagagli sul nastro per controllare se sono presenti oggetti pericolosi
   Coda<Bagaglio> codaBagagli;
   Coda<Bagaglio> codaBagagliPericolosi;
   Coda<Bagaglio> codabagagliControllati;

   public Scanner(){
      this.codaBagagli = new Coda<>();
      this.codaBagagliPericolosi = new Coda<>();
   }
    public void run(){
        while(true) {
            try {
                // TODO: controllo a intervalli random i bagagli dalla coda
                Thread.sleep(1000);
                Bagaglio bagaglio = codaBagagli.pop();
                boolean nonPericoloso = scannerizzaBagaglio(bagaglio);
                if (nonPericoloso) {
                    codabagagliControllati.push(bagaglio);
                } else {
                     codaBagagliPericolosi.push(bagaglio);
                }
                // TODO: mostro risultato al personale che decide se far passare il turista o meno
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // ritorna true se il bagaglio è stato controllato e non contiene oggetti pericolosi
    public boolean scannerizzaBagaglio(Bagaglio bagaglio) {
       ArrayList<String> oggettiPericolosi = new ArrayList<>();
       oggettiPericolosi.add("Coltello");
       oggettiPericolosi.add("Pistola");
       oggettiPericolosi.add("Bastone");
       for (String oggetto : bagaglio.getOggettiContenuti()) {
           if (oggettiPericolosi.contains(oggetto)) {
               return false;
           }
       }
        return true;
    }
}
