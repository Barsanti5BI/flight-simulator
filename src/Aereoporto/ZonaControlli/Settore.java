package Aereoporto.ZonaControlli;

import Persona.Turista;
import Utils.Coda;

public class Settore {
   MetalDetector metalDetector;
   Scanner scanner;
   String name;
   Coda<Turista> codaTuristi;
   public Settore(int numero){
      metalDetector = new MetalDetector();
      scanner = new Scanner();
      name = "Settore " + numero;
   }
}
