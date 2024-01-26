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

    private Dictionary<Aereo,String> viaggi;


    public F_TorreControllo(String aereoporto, LinkedList<Gate> list_gate){

       AereiInArrivo = new Coda<>();
       AereiInPartenza= new Coda<>();
       ListaGate = list_gate;
       nomeAerereoporto = aereoporto;

    }

    public void run(){

        while(!viaggi.isEmpty()){


        }
    }

    public void faiPartire(){

    }

    public void setAereoGate(){
        Aereo a = AereiInArrivo.pop();
        for (Gate g:ListaGate ) {
            g.openGate(a,viaggi.get(a));   }
    }


    public void setAereoporti(Dictionary<String,F_TorreControllo> Ap){
        Aereoporti=Ap;
    }
    public void setViaggi(Dictionary<Aereo,String> v){
        viaggi = v;
    }






    public String GetDestinazione(Aereo a) {
        return viaggi.get(a);
    }







}
