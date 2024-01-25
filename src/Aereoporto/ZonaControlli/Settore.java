package Aereoporto.ZonaControlli;

import Persona.Turista;
import Utils.Coda;

public class Settore {
   MetalDetector metalDetector;
   Scanner scanner;
   String name;

   public Settore(int numero){
      metalDetector = new MetalDetector();
      scanner = new Scanner();
      name = "Settore " + numero;
   }

    public Scanner getScannerBagagali() {
        return scanner;
    }

    public MetalDetector getMetalDetector() {
       return metalDetector;
    }
}
