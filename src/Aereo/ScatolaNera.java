package Aereo;
import java.awt.*;
import java.time.ZonedDateTime;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;

public class ScatolaNera extends Thread{
    Aereo a;
    private Color colore = Color.orange;
    private HashMap<ZonedDateTime,String> logComunicazioni;
    private HashMap<ZonedDateTime,String> logPosizione;
    private ZonedDateTime ultimaComunicazione;
    private ZonedDateTime ultimaPosizione;
    public double percBatteria;
    private boolean pericolo;
    private int nAllerte;

    public ScatolaNera(Aereo a){
        this.a = a;
        nAllerte = 0;
        percBatteria = 100;
        pericolo = false;
        logPosizione = new HashMap<>();
        logComunicazioni = new HashMap<>();

    }
    public void run(){
        try{
            while(percBatteria > 0 && a.Get_Stato_Aereo()){
                if(pericolo){
                    percBatteria -= 0.5;
                    System.out.println("(SN)  ALLARME - BEEEP - BEEEP !");
                    Thread.sleep(1000);
                }else{
                    String pos = logPosizione.get(ultimaPosizione);
                    Thread.sleep(500);
                    InserisciPosizione();
                    if(pos == logPosizione.get(ultimaPosizione)){
                        nAllerte++;
                    }else{
                        nAllerte = 0;
                    }
                    if(nAllerte > 5){
                        Attiva();
                    }
                }
                this.sleep(100);
            }
        }catch(Exception ex){

        }
    }


    public void Ricarica(){
        percBatteria = 100;
    }

    //Attiva segnale di pericolo della scatola nera
    public void Attiva(){
        pericolo = true;
    }

    public void InserisciComunicazione(String comunicazione){
        ultimaComunicazione = ZonedDateTime.now();
        logComunicazioni.put(ultimaComunicazione,comunicazione);
    }

    public void InserisciPosizione(){
        ultimaPosizione = ZonedDateTime.now();
        logPosizione.put(ultimaPosizione,""+a.Get_Posizione());
        //System.out.println("ScatolaNera " + logPosizione.get(ultimaPosizione));
    }

    public HashMap<ZonedDateTime,String> Get_Log_Posizione(){
        return logPosizione;
    }
    public HashMap<ZonedDateTime,String> Get_Log_Comunicazioni(){
        return logComunicazioni;
    }
    public ZonedDateTime Get_Ultima_Comunicazione() {return this.ultimaComunicazione;}
    public ZonedDateTime Get_Ultima_Posizione() {return this.ultimaPosizione;}


}
