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
        codaTuristiPericolosi = new Coda<>();
        codaTuristiBuoni = new Coda<>();
        impiegato = new ImpiegatoControlliPartenze(null,null, codaTuristiPericolosi, ListaOggetti.getOggettiPericolosi(), rand.nextInt(0, 1000));
        impiegato.start();
    }

   public void run() {
       while(true) {
           // TODO: immetto tempo random per il controllo del passeggero tramite la guardia
           try {
               Turista turista = codaTuristiAttesa.pop();
               boolean nonPericoloso = controllaTurista(turista);
               Thread.sleep(rand.nextInt(0, 1001));
              if (nonPericoloso) {
                 codaTuristiBuoni.push(turista);
                 System.out.println("Il turista " + turista.getName() + " ha superato il metal detector");
              } else {
                 codaTuristiPericolosi.push(turista);
                  System.out.println("Il metal detector suona per il turista " + turista.getName());
                 turista.controlloMetalDetectorSospetto =true;
              }
               synchronized (this) {
                 turista.deveFareControlliAlMetalDetector = false;
                 notify();
               }

           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }
   }

   // ritorna true se il turista è stato controllato senza problemi
   public boolean controllaTurista(Turista turista) {
      System.out.println("metal detector sta controllando Il turista " + turista.getName());
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
