package Aereo;
import  Utils.Coda;
import javax.management.DynamicMBean;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;

public class F_TorreControllo extends Thread{
    private String nomeAerereoporto;
    private  Coda<Aereo> AereiInPartenza;
    private  Coda<Aereo> AereiInArrivo;
    private LinkedList<Gate> ListaGate;
    private Dictionary<String,F_TorreControllo> Aereoporti;
    private Dictionary<Aereo, String> viaggi;


    public F_TorreControllo(String aereoporto, LinkedList<Gate> list_gate){

       AereiInArrivo = new Coda<>();
       AereiInPartenza= new Coda<>();
       ListaGate = list_gate;
       nomeAerereoporto = aereoporto;

    }

    public void run(){
        // La torre di controllo o il gate
        // prima di dare "l'ok"
        // dovrebbe chiamare il metodo per riempire la stiva
        while(!viaggi.isEmpty()){
            setAereoGate();
            faiPartire();
        }
    }

    public void faiPartire(){
        for (Gate g: ListaGate) {
            g.aereo.Prepara_Aereo();
            if(g.aereo.aereo_pronto){


                while(g.aereo.posizione <100){
                    try{
                        sleep(20);;
                    }catch (Exception e){ }

                    Aereoporti.get(viaggi.get(g.aereo.destinazione)).AereiInArrivo.push(g.aereo);
                }


            }
        }

    }

    public void setAereoGate(){
        Aereo a = AereiInArrivo.pop();
        for (Gate g:ListaGate ) {
            if(!g.GateAperto) {
            setDestinazione(a);
            g.openGate(a,viaggi.get(a));
            }
        }
    }

    public void Crea_Viaggi(){

    }

    public void Set_Aereoporti(Dictionary<String,F_TorreControllo> Ap){
        Aereoporti=Ap;
    }
    public void Set_Viaggi(Dictionary<Aereo,String> v){
        viaggi = v;
    }
    public void setDestinazione(Aereo a) {
        a.destinazione = viaggi.get(a);
    }







}
