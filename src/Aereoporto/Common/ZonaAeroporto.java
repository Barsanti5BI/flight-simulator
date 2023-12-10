package Aereoporto.Common;

import Persona.Turista;
import Utils.Coda;
import org.jetbrains.annotations.Nullable;

public class ZonaAeroporto extends Thread {
     protected @Nullable ZonaAeroporto zonaSuccessiva;
     protected @Nullable ZonaAeroporto zonaPrecedente;
     protected Coda<Turista> turistiEntranti;
     protected Coda<Turista> turistiUscenti;

     public ZonaAeroporto() {
        turistiEntranti = new Coda<>();
        turistiUscenti = new Coda<>();
     }

     public void entraInZona(Turista turista) {
         turistiEntranti.push(turista);
     }

     protected void entraInZonaSuccessiva(Turista turista) {
         if(zonaSuccessiva == null) {
             throw new RuntimeException("Non è possibile entrare in una zona successiva se non è stata impostata");
         }

         zonaSuccessiva.turistiEntranti.push(turista);
     }
    protected void entraInZonaSuccessiva(Turista turista, Boolean inUscita) {
         if(zonaSuccessiva == null) {
             throw new RuntimeException("Non è possibile entrare in una zona successiva se non è stata impostata");
         }

         if(inUscita) {
             zonaSuccessiva.turistiUscenti.push(turista);
         } else {
             zonaSuccessiva.turistiEntranti.push(turista);
         }
     }
     protected void entraInZonaPrecedente(Turista turista) {
         if(zonaPrecedente == null) {
             throw new RuntimeException("Non è possibile entrare in una zona precedente se non è stata impostata");
         }

         zonaPrecedente.turistiEntranti.push(turista);
     }
     protected void entraInZonaPrecedente(Turista turista, Boolean inUscita) {
         if(zonaPrecedente == null) {
             throw new RuntimeException("Non è possibile entrare in una zona precedente se non è stata impostata");
         }

         if(inUscita) {
             zonaPrecedente.turistiUscenti.push(turista);
         } else {
             zonaPrecedente.turistiEntranti.push(turista);
         }
     }

     public void impostaZonaSuccessiva(ZonaAeroporto zonaSuccessiva) {
         this.zonaSuccessiva = zonaSuccessiva;
     }
     public void impostaZonaPrecedente(ZonaAeroporto zonaPrecedente) {
        this.zonaPrecedente = zonaPrecedente;
     }
}
