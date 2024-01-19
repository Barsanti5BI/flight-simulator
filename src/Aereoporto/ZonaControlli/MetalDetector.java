package Aereoporto.ZonaControlli;

import Aereoporto.Common.ListaOggetti;
import Persona.ImpiegatoControlliPartenze;
import Persona.Oggetto;
import Persona.Turista;
import Utils.Coda;

import java.util.ArrayList;
import java.util.Random;

public class MetalDetector extends Thread {
   Coda<Turista> codaTuristiAttesa;
   Coda<Turista> codaTuristiPericolosi;
   Coda<Turista> codaTuristiBuoni;
   ImpiegatoControlliPartenze impiegato;

   Random rand;

    public MetalDetector(){
        rand = new Random();
        codaTuristiAttesa = new Coda<>();
        impiegato = new ImpiegatoControlliPartenze(null, codaTuristiPericolosi, ListaOggetti.getOggettiPericolosi(), rand.nextInt(0, 1000));
    }

   public void run() {
       while(true) {
           // TODO: immetto tempo random per il controllo del passeggero tramite la guardia
           try {
               Thread.sleep(1000);
               Turista turista = codaTuristiAttesa.pop();
               boolean nonPericoloso = controllaTurista(turista);
               if (nonPericoloso) {
                  codaTuristiBuoni.push(turista);
               } else {
                  codaTuristiPericolosi.push(turista);
               }
               synchronized (turista) {
                   turista.notify();
               }
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }
   }

   // ritorna true se il turista è stato controllato senza problemi
   public boolean controllaTurista(Turista turista) {
      ArrayList<String> oggettiPericolosi = ListaOggetti.getOggettiPericolosi();
      for (Oggetto oggetto : turista.GetListaOggetti()) {
          if (oggettiPericolosi.contains(oggetto.getNome())) {
              return false;
          }
      }
      return true;
   }

   public Coda<Turista> getCodaTuristiAttesa() {
        return codaTuristiAttesa;
   }

    public ImpiegatoControlliPartenze getImpiegatoControlli() {
        return impiegato;
    }
}
