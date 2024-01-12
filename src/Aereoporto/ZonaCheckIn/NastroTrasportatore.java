package Aereoporto.ZonaCheckIn;

import Persona.Bagaglio;
import Utils.Coda;

public class NastroTrasportatore {
   //Lista di bagagli che sono stati caricati sul nastro trasportatore iseriti a indice in base alla posizione
   //del banco di check-in da cui sono stati caricati
   Coda<Bagaglio> bagagliCaricati;
   public void  CaricaBagaglio(Bagaglio b){
        bagagliCaricati.push(b);
   }
    public Bagaglio ScaricaBagaglio(){
         return bagagliCaricati.pop();
    }
}
