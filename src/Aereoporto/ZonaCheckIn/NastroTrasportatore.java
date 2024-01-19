package Aereoporto.ZonaCheckIn;

import Persona.Bagaglio;
import Utils.Coda;
import Aereoporto.ZonaControlli.Scanner;

public class NastroTrasportatore extends Thread{

    public Coda<Bagaglio> codaBagagli;
    public Coda<Bagaglio> bagagliCaricati;
    Scanner scanner;
    public NastroTrasportatore(){
       codaBagagli = new Coda<>();
       bagagliCaricati = new Coda<>();
       this.scanner = new Scanner();
    }

    public void aggiungiBagaglio(Bagaglio b) {
         codaBagagli.push(b);
    }
    public void run(){
       while(true) {
           Bagaglio b = codaBagagli.pop();
           scanner.getCodaBagagli().push(b);
           try {
               Thread.sleep(5);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }
    }

   public Scanner getScanner() {
       return scanner;
   }
}
