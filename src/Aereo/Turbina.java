package Aereo;

import java.util.Random;

public class Turbina extends Thread{

    public Aereo a;
    public int id;
    private Random r;
    public double percRiparazione;
    public Boolean funzionante;
    Turbina(Aereo a){
        r = new Random();
    }
    public void run(){
        while(funzionante && a.inVolo){
            percRiparazione -= 0.5;
            if(percRiparazione == 0){
                Disabilita();
            }else if(percRiparazione < 50 ){

            }
        }
    }

    public void Ripara(){
        percRiparazione = 100;
    }
    public void Disabilita() {
        funzionante = false;
        System.out.println("Turbina " + id + "disattivata!");
    }
    public void Attiva(){
        funzionante = true;
    }

}
