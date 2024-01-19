package Aereoporto.ZonaCheckIn;

import Persona.Bagaglio;
import Utils.Coda;
import Aereoporto.ZonaControlli.Scanner;

public class NastroTrasportatore extends Thread

    public Coda<Bagaglio> codaBagagli;
    public Coda<Bagaglio> bagagliCaricati;
    Scanner scanner;
    public NastroTrasportatore(Scanner scanner){
       codaBagagli = new Coda<>();
       bagagliCaricati = new Coda<>();
       this.scanner = scanner;
    }

    public void aggiungiBagaglio(Bagaglio b, Object indice) {
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
}
