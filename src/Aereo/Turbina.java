package Aereo;

import java.util.Random;

public class Turbina extends Thread{

    public Aereo a;
    public int id;
    private Random r;
    public double percRiparazione;
    public Boolean funzionante;
    private int random;
    Turbina(Aereo a, int id){
        r = new Random();
        this.a = a;
        funzionante = false;
        percRiparazione = 100;
        this.id = id;
    }
    public void run(){
        try{
            while(funzionante && a.einvolo){
                percRiparazione -= 0.5;
                random = r.nextInt(100);
                if(percRiparazione == 0){
                    Disabilita();
                }else if(percRiparazione < 75 && random > 75){
                    Disabilita();
                }else if(percRiparazione < 50 && random > 50){
                    Disabilita();
                }else if(percRiparazione < 25 && random > 25){
                    Disabilita();
                }
                Thread.sleep(500);
            }
        }catch(Exception ex) {

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
        this.start();
    }
    public double Get_Stato_Riparazione(){
        return this.percRiparazione;
    }
    public boolean Get_Stato_Turbina(){return this.funzionante;}

}
