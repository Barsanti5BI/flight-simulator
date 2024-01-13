package Persona;

import javax.print.Doc;

public class Manutentore extends Persona{

    public Aereo a;
    public Manutentore(Aereo a){
        this.a = a;
    }
    public void run(){

    }
    public void ControlloStatiApparecchiature(){
        if(a.percentualeApp == 100){
            System.out.println("Sistemi OK");
        }
        else{
            System.out.println("Sistemi KO");
            a.pecentualeApp = 100;
        }
    }
    public void ControlloCarburante(){
        if (a.livelloCarburante ==  100) {
            System.out.println("Il veicolo ha ancora carburante.");
        } else {
            System.out.println("Il livello di carburante è basso. Rifornire!");
            a.livelloCarburante = 100;
        }
    }
    public void ControlloTurbine(){
        if (a.percentualeTurbine == 100) {
            System.out.println("La turbina è buoni condizioni.");
        } else {
            System.out.println("La turbina è rotta.");
            a.percentualeTurbine = 100;
        }
    }
}
