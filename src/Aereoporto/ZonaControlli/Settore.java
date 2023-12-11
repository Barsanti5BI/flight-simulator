package Aereoporto.ZonaControlli;

import Aereoporto.Common.ZonaAeroporto;
import Persona.Turista;
import Utils.Coda;

public class Settore {
   ZonaControlli zonaControlli;
   MetalDetector metalDetector;
   Scanner scanner;
   String name;
   Coda<Turista> codaTuristi;

   // TODO: aggiungere campi per il personale di controllo

   public Settore(int numero, ZonaControlli zonaDiAppartenenza){
      metalDetector = new MetalDetector();
      scanner = new Scanner();
      name = "Settore " + numero;
   }

    public void run(){
        while(true){
            try{
                // TODO: immettere un tempo di intervallo tra un controllo e l'altro
                Thread.sleep(1000);
                Turista t = codaTuristi.pop();
                // TODO: il turista poggia i bagagli sul nastro trasportatore dello scanner
                scanner.mettiSuNastroTrasportatore(t);
                metalDetector.controllaTurista(t);
                // TODO: anche il personale controlla il turista di persona
                // TODO: una volta terminato il controllo, il turista riprende i bagagli dal nastro trasportatore
                //  se sono passati tutti

                // il turista può procedere verso la zona successiva
                zonaControlli.entraInZonaSuccessiva(t);
            }
            catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }
}
