package Aereoporto.ZonaControlli;

import Aereoporto.Common.ListaOggetti;
import Persona.ImpiegatoControlliPartenze;

public class Settore {
   MetalDetector metalDetector;
   Scanner scanner;
   String name;
   ImpiegatoControlliPartenze impiegato;

   public Settore(int numero){
      metalDetector = new MetalDetector();
      scanner = new Scanner();
      impiegato = new ImpiegatoControlliPartenze(scanner.getCodaBagagliPericolosi(),scanner.getCodaBagagliControllati(), null, ListaOggetti.getOggettiPericolosi(),(int)(Math.random()*1000));
      name = "Settore " + numero;

      scanner.start();
      impiegato.start();
      metalDetector.start();
   }

    public Scanner getScannerBagagali() {
        return scanner;
    }

    public MetalDetector getMetalDetector() {
       return metalDetector;
    }
}
