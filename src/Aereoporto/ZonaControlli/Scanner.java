package Aereoporto.ZonaControlli;
import Aereoporto.Common.ListaOggetti;
import Persona.Bagaglio;
import Persona.ImpiegatoControlliPartenze;
import Persona.Oggetto;
import Utils.Coda;

import java.util.ArrayList;
import java.util.Random;

public class Scanner extends Thread{
   // TODO: persona che controlla i bagagli sul nastro per controllare se sono presenti oggetti pericolosi
   Coda<Bagaglio> codaBagagli;
   Coda<Bagaglio> codaBagagliPericolosi;
   Coda<Bagaglio> codabagagliControllati;
   ImpiegatoControlliPartenze impiegato;

   public Scanner(Coda<Bagaglio> codaBagagli){
      this.codaBagagli = codaBagagli;
      this.codaBagagliPericolosi = new Coda<>();
      impiegato = new ImpiegatoControlliPartenze(codaBagagliPericolosi,codabagagliControllati, null, ListaOggetti.getOggettiPericolosi(),(int)(Math.random()*1000));
   }

   public Scanner(){
        this.codaBagagli = new Coda<>();
        this.codaBagagliPericolosi = new Coda<>();
        codabagagliControllati = new Coda<>();
         impiegato = new ImpiegatoControlliPartenze(codaBagagliPericolosi,codabagagliControllati, null, ListaOggetti.getOggettiPericolosi(),(int)(Math.random()*1000));
   }

    public void run(){
        Random rand = new Random();

        while(true) {
            try {
                // TODO: controllo a intervalli random i bagagli dalla coda
                Thread.sleep(rand.nextInt(0, 1001));
                Bagaglio bagaglio = codaBagagli.pop();
                boolean nonPericoloso = scannerizzaBagaglio(bagaglio);
                if (nonPericoloso) {
                    codabagagliControllati.push(bagaglio);
                } else {
                     codaBagagliPericolosi.push(bagaglio);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // ritorna true se il bagaglio è stato controllato e non contiene oggetti pericolosi
    public boolean scannerizzaBagaglio(Bagaglio bagaglio) {
       ArrayList<String> oggettiPericolosi = ListaOggetti.getOggettiPericolosi();
       for (Oggetto oggetto : bagaglio.getOggettiContenuti()) {
           if (oggettiPericolosi.contains(oggetto.getNome())) {
               return false;
           }
       }
        return true;
    }

    public Coda<Bagaglio> getCodaBagagli() {
       return codaBagagli;
    }

    public Coda<Bagaglio> getCodaBagagliControllati() {
       return codabagagliControllati;
    }

    public Coda<Bagaglio> getCodaBagagliPericolosi()
    {
        return codaBagagliPericolosi;
    }
}
