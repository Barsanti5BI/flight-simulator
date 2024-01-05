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

    public void run(){
        while(true) {
            try {
                // TODO: controllo a intervalli random i bagagli dalla coda
                Thread.sleep(1000);
                Bagaglio bagaglio = codaBagagli.pop();
                boolean nonPericoloso = scannerizzaBagaglio(bagaglio);
                if (nonPericoloso) {
                    bagagliControllati.add(bagaglio);
                } else {

                }
                // TODO: mostro risultato al personale che decide se far passare il turista o meno
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void mettiSuNastroTrasportatore(Bagaglio bagaglio) {
       // TODO: implementare in base al modo in cui i bagagli sono associati al turista
        codaBagagli.push(bagaglio);
    }

    public synchronized ArrayList<Bagaglio> prendiDaNastroTrasportatore(ArrayList<String> ids) {
        ArrayList<Bagaglio> bagagli = new ArrayList<>();
        for (String id : ids) {
            for (Bagaglio bagaglio : bagagliControllati) {
                if (bagaglio.id.equals(id)) {
                    bagagli.add(bagaglio);
                    bagagliControllati.remove(bagaglio);
                }
            }
        }
        return bagagli;
    }

    // ritorna true se il bagaglio è stato controllato e non contiene oggetti pericolosi
    public boolean scannerizzaBagaglio(Bagaglio bagaglio) {
       // TODO: implementare
        return true;
    }
}
