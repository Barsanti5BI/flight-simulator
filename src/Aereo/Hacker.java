package Aereo;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Hacker extends Thread{ //Feature Borsato Marco
    private ScatolaNera sn;
    private HashMap<ZonedDateTime,String> logPosRubati;
    private HashMap<ZonedDateTime, LinkedList<String>> logComRubati;
    private Random r;
    private boolean hackerato;
    Hacker(ScatolaNera sn){
        this.sn = sn;
        r = new Random();
        hackerato = false;
        logPosRubati = new HashMap<>();
        logComRubati = new HashMap<>();
    }
    public void run(){
        try{
            while(!hackerato){
                if(r.nextInt(1000) == 200){
                    Thread.sleep(5000);
                    hackerato = true;
                    HashMap<ZonedDateTime,String> logtemp = sn.Get_Log_Posizione();
                    for ( ZonedDateTime key : sn.Get_Log_Posizione().keySet() ) {
                        logPosRubati.put(key,logtemp.get(key));
                        sn.Get_Log_Posizione().replace(key,"///CANCELLATO///");
                    }
                    HashMap<ZonedDateTime,LinkedList<String>> logtemp1 = sn.Get_Log_StatiTurbine();
                    LinkedList<String> cancellati = new LinkedList<String>();
                    for(int i = 0;i<4;i++){
                        cancellati.add("///CANCELLATO///");
                    }
                    for ( ZonedDateTime key : sn.Get_Log_StatiTurbine().keySet() ) {
                        logComRubati.put(key,logtemp1.get(key));

                        sn.Get_Log_StatiTurbine().replace(key,cancellati);
                    }
                    System.out.println("(HACKER)    HACKERATI GODO EZ");
                }
                Thread.sleep(500);
            }
        }catch (Exception ex){

        }

    }
}
