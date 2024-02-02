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
                percRiparazione -= 1;
                random = r.nextInt(0, 100);
                if(percRiparazione == 0){
                    Disabilita();
                    a.Get_Serbatoio().Set_Consumo_Carburante(a.Get_Serbatoio().Get_Consumo_Carburante()+2);
                    System.out.println("(TRB)  Consumo del carburante aumentato a " + a.Get_Serbatoio().Get_Consumo_Carburante()+".");
                }else if(percRiparazione < 75 && random > 75){
                    Disabilita();
                    a.Get_Serbatoio().Set_Consumo_Carburante(a.Get_Serbatoio().Get_Consumo_Carburante()+2);
                    System.out.println("(TRB)  Consumo del carburante aumentato a " + a.Get_Serbatoio().Get_Consumo_Carburante()+".");
                }else if(percRiparazione < 50 && random > 50){
                    Disabilita();
                    a.Get_Serbatoio().Set_Consumo_Carburante(a.Get_Serbatoio().Get_Consumo_Carburante()+2);
                    System.out.println("(TRB)  Consumo del carburante aumentato a " + a.Get_Serbatoio().Get_Consumo_Carburante()+".");
                }else if(percRiparazione < 25 && random > 25){
                    Disabilita();
                    a.Get_Serbatoio().Set_Consumo_Carburante(a.Get_Serbatoio().Get_Consumo_Carburante()+2);
                    System.out.println("(TRB)  Consumo del carburante aumentato a " + a.Get_Serbatoio().Get_Consumo_Carburante()+".");
                }
                Thread.sleep(3500);
            }
        }catch(Exception ex) {}
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
        System.out.println("(TRB)  Turbina " + id + " dell'aereo " + a.Get_ID() + " è in funzione.");
    }
    public double Get_Stato_Riparazione(){
        return this.percRiparazione;
    }
    public boolean Get_Stato_Turbina(){return this.funzionante;}

}
