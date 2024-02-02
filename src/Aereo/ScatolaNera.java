package Aereo;
import java.awt.*;
import java.io.FileWriter;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;

public class ScatolaNera extends Thread{
    Aereo a;
    private Color colore = Color.orange;
    private HashMap<ZonedDateTime,LinkedList<String>> logStatiTurbine;
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
        logStatiTurbine = new HashMap<>();
        ultimaPosizione = ZonedDateTime.now();
        logPosizione.put(ultimaPosizione,"0");


    }
    public void run(){
        try{
            while(percBatteria > 0 && a.Get_Stato_Aereo() && !pericolo){
                String pos = logPosizione.get(ultimaPosizione);
                Thread.sleep(500);
                InserisciPosizione();
                InserisciStatiTurbine(a.Get_Turbine());
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

        }
    }


    public void Ricarica(){
        percBatteria = 100;
    }

    //Attiva segnale di pericolo della scatola nera
    public void Attiva(){
        pericolo = true;
    }

    public void InserisciStatiTurbine(ArrayList<Turbina> turbine){
        ultimaComunicazione = ZonedDateTime.now();
        LinkedList<String> statiTurbine = new LinkedList<String>();
        statiTurbine.add(turbine.get(0).Get_Stato_Thread());
        statiTurbine.add(turbine.get(1).Get_Stato_Thread());
        statiTurbine.add(turbine.get(2).Get_Stato_Thread());
        statiTurbine.add(turbine.get(3).Get_Stato_Thread());
        logStatiTurbine.put(ultimaComunicazione,statiTurbine);
    }

    public void InserisciPosizione(){
        ultimaPosizione = ZonedDateTime.now();
        logPosizione.put(ultimaPosizione,""+a.Get_Posizione());
        //System.out.println("ScatolaNera " + logPosizione.get(ultimaPosizione));
    }

    public HashMap<ZonedDateTime,String> Get_Log_Posizione(){
        return logPosizione;
    }
    public HashMap<ZonedDateTime,LinkedList<String>> Get_Log_StatiTurbine(){
        return logStatiTurbine;
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
            FileWriter fw2 = new FileWriter("ScatolaNera-LogsStatiTurbine.txt");
            String logsStati = "";
            for(ZonedDateTime key : logStatiTurbine.keySet()){
                logsStati = logsStati +"Data E Ora: "+ key + " Stati Registrati: ";
                int i = 1;
                        for(String stato : logStatiTurbine.get(key)) {
                            logsStati = logsStati + " | Turbina N: " + i + " Stato Registrato: " + stato;
                                    i++;
                        }
                        logsStati = logsStati + " | \n\n";
                        i = 1;
            }
            fw2.write(logsStati);
            fw2.close();
            System.out.println("(SN) - ESTRATTI I LOGS");
        }catch(Exception ex){

        }


    }


}
