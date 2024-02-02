package Aereo;
import java.awt.*;
import java.io.FileWriter;
import java.time.ZonedDateTime;
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
        ultimaPosizione = ZonedDateTime.now();
        logPosizione.put(ultimaPosizione,"0");


    }
    public void run(){
        try{
            while(percBatteria > 0 && a.Get_Stato_Aereo() && !pericolo){
                String pos = logPosizione.get(ultimaPosizione);
                Thread.sleep(500);
                InserisciPosizione();
                System.out.println("Dentro Ciclo");
                if(pos.equals(logPosizione.get(ultimaPosizione))){
                    nAllerte++;
                }else{
                    nAllerte = 0;
                }
                if(nAllerte > 5){
                    Attiva();
                }
                this.sleep(100);
            }
            EstraiLogs();
        }catch(Exception ex){
            System.out.println("CIAOO");

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

    public void EstraiLogs(){
        try{
            FileWriter fw1 = new FileWriter("ScatolaNera-LogsPosizione.txt");
            String logsPos = "";
            for(ZonedDateTime key : logPosizione.keySet()){
                logsPos = logsPos +"Data E Ora: "+ key + " Posizione Registrata: " + logPosizione.get(key) + "\n";
            }
            fw1.write(logsPos);
            fw1.close();
            FileWriter fw2 = new FileWriter("ScatolaNera-LogsComunicazioni.txt");
            logsPos = "";
            for(ZonedDateTime key : logComunicazioni.keySet()){
                logsPos = logsPos +"Data E Ora: "+ key + " Posizione Registrata: " + logComunicazioni.get(key) + "\n";
            }
            fw2.write(logsPos);
            fw2.close();
            System.out.println("(SN) - ESTRATTI I LOGS");
        }catch(Exception ex){

        }


    }


}
