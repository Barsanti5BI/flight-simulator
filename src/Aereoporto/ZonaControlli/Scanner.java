package Aereoporto.ZonaControlli;

import Persona.Bagaglio;
import Utils.Coda;

import java.util.ArrayList;

public class Scanner  extends Thread{
   //persona che controlla i bagagli sul nastro per controllare se sono presenti oggetti pericolosi
   Coda<Bagaglio> codaBagagli;
   ArrayList<Bagaglio> bagagliControllati;
   public Scanner(){
      this.codaBagagli = new Coda<>();
      this.bagagliControllati = new ArrayList<>();
   }
    public void entraInCoda(Bagaglio bagaglio){
        codaBagagli.push(bagaglio);
    }
    public void run(){

    }
}
