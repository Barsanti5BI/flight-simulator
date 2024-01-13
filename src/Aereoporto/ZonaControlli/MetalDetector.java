package Aereoporto.ZonaControlli;

import Persona.ImpiegatoControlliPartenze;
import Persona.Oggetto;
import Persona.Turista;
import Utils.Coda;

import java.util.ArrayList;

public class MetalDetector extends Thread {
   Coda<Turista> codaTuristiAttesa;
   Coda<Turista> codaTuristiPericolosi;
   Coda<Turista> codaTuristiBuoni;
    public MetalDetector(){
        codaTuristiAttesa = new Coda<>();
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
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }
   }

   // ritorna true se il turista è stato controllato senza problemi
   public boolean controllaTurista(Turista turista) {
      ArrayList<String> oggettiPericolosi = new ArrayList<>();
        for (Oggetto oggetto : turista.GetListaOggetti()) {
             if (oggettiPericolosi.contains(oggetto.getNome())) {
                return false;
             }
        }
        return true;
   }

}
