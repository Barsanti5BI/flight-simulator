package Aereo;

import java.util.Random;

public class Turbina extends Thread{
    private Aereo a;
    public int id;
    private Random r;
    public double percRiparazione;
    public Boolean funzionante;
    private int random;

    public Turbina(Aereo a, int id){
        r = new Random();
        this.a = a;
        funzionante = false;
        percRiparazione = 100;
        this.id = id;
    }
    public void run(){
        try{
            while(funzionante && a.Get_Stato_Aereo()){
                percRiparazione -= 0.5;
                random = r.nextInt(100);
                if(percRiparazione == 0){
                    Disabilita();
                    System.out.println("(TRB) Liggi 1");
                }else if(percRiparazione < 75 && random > 75){
                    Disabilita();
                    System.out.println("(TRB) Liggi 2");
                }else if(percRiparazione < 50 && random > 50){
                    Disabilita();
                    System.out.println("(TRB) Liggi 3");
                }else if(percRiparazione < 25 && random > 25){
                    Disabilita();
                    System.out.println("(TRB) Liggi troia");
                }
                Thread.sleep(5000);
            }
        }catch(Exception ex) {

        }
    }
    public void Ripara(){
        percRiparazione = 100;
        System.out.println("(TRB)  Turbina " + id +  " dell'aereo " + a.Get_ID() + " riparata.");
    }
    public void Disabilita() {
        funzionante = false;
        System.out.println("(TRB)  Turbina " + id + " dell'aereo " + a.Get_ID() + " disattivata.");
    }
    public void Attiva(){
        funzionante = true;
        System.out.println("(TRB)  Turbina " + this.id + " dell'aereo " + a.Get_ID() + " è in funzione.");
    }
    public double Get_Stato_Riparazione(){
        return this.percRiparazione;
    }
    public boolean Get_Stato_Turbina(){return this.funzionante;}

}
