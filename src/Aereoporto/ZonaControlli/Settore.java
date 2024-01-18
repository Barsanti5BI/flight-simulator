package Aereoporto.ZonaControlli;

import Persona.Turista;
import Utils.Coda;

public class Settore {
   MetalDetector metalDetector;
   Scanner scanner;
   String name;

   // TODO: aggiungere campi per il personale di controllo

   public Settore(int numero){
      metalDetector = new MetalDetector();
      scanner = new Scanner();
      name = "Settore " + numero;
   }

//    public void run() {
//        while(true){
//            try{
//                // TODO: immettere un tempo di intervallo random tra un controllo e l'altro
//                Thread.sleep(1000);
//                Turista turista = codaTuristi.pop();
//
//                turista.devePoggiareBagagliAiControlli = true;
//                turista.deveFareControlliAlMetalDetector = true;
//            }
//            catch (InterruptedException ex){
//                ex.printStackTrace();
//            }
//        }
//    }

    public Scanner getScannerBagagali() {
        return scanner;
    }

    public MetalDetector getMetalDetector() {
       return metalDetector;
    }
}
