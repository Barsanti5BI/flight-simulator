package Persona;

import javax.print.Doc;

public class Manutentore extends Persona{
    int livelloCarburante;
    boolean inFunzione;
    boolean stato;
    public Manutentore(Documento doc){
        super(doc);
    }
    public void run(){

    }
    public void ControlloStatiApparecchiature(){
        if(stato){
            System.out.println("Sistemi OK");
        }
        else{
            System.out.println("Sistemi KO");
        }
    }
    public void ControlloCarburante(){
        if (livelloCarburante > 0) {
            System.out.println("Il veicolo ha ancora carburante.");
        } else {
            System.out.println("Il livello di carburante è basso. Rifornire!");
        }
    }
    public void ControlloTurbine(){
        if (inFunzione) {
            System.out.println("La turbina è in funzione.");
        } else {
            System.out.println("La turbina è spenta.");
        }
    }
}
