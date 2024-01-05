package Aereoporto.ZonaControlli;

import Persona.Turista;
import Utils.Coda;

public class MetalDetector extends Thread {
   Coda<Turista> codaTuristi;
   //persona che controlla i passeggeri con il metal detector

    public MetalDetector(){
        codaTuristi = new Coda<>();
    }

   public void run() {
       while(true) {
           // TODO: immetto tempo random per il controllo del passeggero tramite la guardia
           try {
               Thread.sleep(1000);
               Turista turista = codaTuristi.pop();
               boolean nonPericoloso = controllaTurista(turista);
               if (nonPericoloso) {

               } else {

               }
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }
   }

   // ritorna true se il turista è stato controllato senza problemi
   public boolean controllaTurista(Turista turista) {
       try {
           Thread.sleep(100);
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
       // TODO: implementare logiche di controllo con possibili feature particolari
       return true;
   }
}
