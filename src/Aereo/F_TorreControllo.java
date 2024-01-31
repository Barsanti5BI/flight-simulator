package Aereo;
import  Utils.Coda;
import javax.management.DynamicMBean;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;

public class F_TorreControllo extends Thread{
    private String nomeAeroporto;
    private  Coda<Aereo> AereiInPartenza;
    private  Coda<Aereo> AereiInArrivo;
    private LinkedList<Gate> ListaGate;
    private Dictionary<String,F_TorreControllo> Aereoporti;
    private Dictionary<Aereo, String> viaggi;


    public F_TorreControllo(String aereoporto, LinkedList<Gate> list_gate){

       AereiInArrivo = new Coda<>();
       AereiInPartenza= new Coda<>();
       ListaGate = list_gate;
       nomeAeroporto = aereoporto;

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
            if(g.Get_Aereo() != null){
            ;
            if(g.Get_Aereo().Prepara_Aereo()){
                while(g.Get_Aereo().Get_Posizione() <100){
                    try{
                        sleep(10);;
                    }catch (Exception e){ }
                }
                Aereoporti.get(viaggi.get(g.Get_Aereo().Get_AP_Destinazione())).AereiInArrivo.push(g.Get_Aereo());

            }
        }
        }

    }

    public void setAereoGate(){
        if( !AereiInArrivo.isEmpty())
        {
            Aereo a = AereiInArrivo.pop();

            for (Gate g:ListaGate ) {
                if(!g.Get_Gate_Aperto()) {
                setDestinazione(a);
                g.openGate(a,viaggi.get(a));
            }
            }
        }
    }

    public void Crea_Viaggi(String dest,Aereo a){
        viaggi.put(a,dest);
    }

    public void Set_Aereoporti(Dictionary<String,F_TorreControllo> Ap){
        Aereoporti=Ap;
    }
    public void Set_Viaggi(Dictionary<Aereo,String> v){
        viaggi = v;
    }
    public void setDestinazione(Aereo a) {
        a.Set_AP_Destinazione(viaggi.get(a));
    }







}
