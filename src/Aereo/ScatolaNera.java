package Aereo;

import java.time.ZonedDateTime;
import java.util.Dictionary;
import java.util.Enumeration;

public class ScatolaNera extends Thread{

    Aereo a;
    private Dictionary<ZonedDateTime,String> logComunicazioni;
    private Dictionary<ZonedDateTime,String> logPosizione;
    private ZonedDateTime ultimaComunicazione;
    private ZonedDateTime ultimaPosizione;
    private double percBatteria;
    private boolean pericolo;
    private int nAllerte;

    ScatolaNera(Aereo a){
        this.a = a;
        nAllerte = 0;
        percBatteria = 100;
        pericolo = false;
        logComunicazioni = new Dictionary<ZonedDateTime, String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public Enumeration<ZonedDateTime> keys() {
                return null;
            }

            @Override
            public Enumeration<String> elements() {
                return null;
            }

            @Override
            public String get(Object key) {
                return null;
            }

            @Override
            public String put(ZonedDateTime key, String value) {
                return null;
            }

            @Override
            public String remove(Object key) {
                return null;
            }

        };
        logPosizione = new Dictionary<ZonedDateTime, String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public Enumeration<ZonedDateTime> keys() {
                return null;
            }

            @Override
            public Enumeration<String> elements() {
                return null;
            }

            @Override
            public String get(Object key) {
                return null;
            }

            @Override
            public String put(ZonedDateTime key, String value) {
                return null;
            }

            @Override
            public String remove(Object key) {
                return null;
            }
        };

    }
    public void run(){
        try{
            while(percBatteria > 0){
                if(pericolo){
                    percBatteria -= 0.5;
                    System.out.println("ALLARME - BEEEP - BEEEP");
                    Thread.sleep(1000);
                }else{
                    String pos = logPosizione.get(ultimaPosizione);
                    Thread.sleep(500);
                    InserisciPosizione();
                    if(pos == logPosizione.get(ultimaPosizione)){
                        nAllerte++;
                    }
                    if(nAllerte > 5){
                        Attiva();
                    }

                }

            }
        }catch(Exception ex){

        }
    }

    public void Ricarica(){
        percBatteria = 100;
    }
    public void Attiva(){
        pericolo = true;
    }
    public void InserisciComunicazione(String comunicazione){
        ultimaComunicazione = ZonedDateTime.now();
        logComunicazioni.put(ultimaComunicazione,comunicazione);
    }

    public void InserisciPosizione(){
        ultimaPosizione = ZonedDateTime.now();
        logPosizione.put(ultimaPosizione,""+a.posizione);
    }

}
