package Aereoporto.ZonaControlli;

import Aereoporto.Common.ListaOggetti;
import Persona.ImpiegatoControlliPartenze;
import Persona.Oggetto;
import Persona.Turista;
import Utils.Coda;

import java.util.ArrayList;

public class MetalDetector extends Thread {
   Coda<Turista> codaTuristiAttesa;
   Coda<Turista> codaTuristiPericolosi;
   Coda<Turista> codaTuristiBuoni;
   ImpiegatoControlliPartenze impiegato;

    public MetalDetector(){
        codaTuristiAttesa = new Coda<>();
        impiegato = new ImpiegatoControlliPartenze(null, codaTuristiPericolosi, ListaOggetti.getOggettiPericolosi());
    }

   public void run() {
       while(true) {
           // TODO: immetto tempo random per il controllo del passeggero tramite la guardia
           try {
               Thread.sleep(1000);
               Turista turista = codaTuristiAttesa.pop();
               boolean nonPericoloso = controllaTurista(turista);
               if (nonPericoloso) {
                   System.out.println("Il turista " + turista.getDoc().getCognome() + " " + turista.getDoc().getNome() + " è in regola, può procedere");
                  codaTuristiBuoni.push(turista);
               } else {
                   System.out.println("Il turista " + turista.getDoc().getCognome() + " " + turista.getDoc().getNome() + " è pericoloso, viene arrestato");
                   turista.controlloMetalDetectorSospetto = true;
                   codaTuristiPericolosi.push(turista);
               }
               synchronized (turista) {
                   turista.notify();
               }
               turista.deveFareControlliAlMetalDetector = false;
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
