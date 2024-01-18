package Persona;

import Aereo.Aereo;

public class Manutentore{
    public Aereo a;

    public Manutentore(Aereo a){
        this.a = a;
    }

    public void Manutenzione()
    {
        ControlloStatiApparecchiature();
        ControlloCarburante();
        ControlloTurbine();
    }

    public void ControlloStatiApparecchiature(){
        if(a.getPercentualeApp() == 100){
            System.out.println("Sistemi OK");
        }
        else{
            System.out.println("Sistemi KO");
            a.setPecentualeApp(100);
        }
    }
    public void ControlloCarburante(){
        if (a.getLivelloCarburante() ==  100) {
            System.out.println("Il veicolo ha ancora carburante.");
        } else {
            System.out.println("Il livello di carburante è basso. Rifornire!");
            a.setLivelloCarburante(100);
        }
    }
    public void ControlloTurbine(){
        if (a.getPercentualeTurbine() == 100) {
            System.out.println("La turbina è buoni condizioni.");
        } else {
            System.out.println("La turbina è rotta.");
            a.setPercentualeTurbine(100);
        }
    }
}
