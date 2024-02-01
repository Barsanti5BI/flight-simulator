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

       ListaGate.get(0).start();

    }

    public void run(){
        // La torre di controllo o il gate
        // prima di dare "l'ok"
        // dovrebbe chiamare il metodo per riempire la stiva
        while(!viaggi.isEmpty()){
            System.out.println("(TRC)  DIO");
            setAereoGate();
            System.out.println("(TRC)  NEGRO");
            faiPartire();
            System.out.println("(TRC)  LEBBROSO");
            try {
                this.sleep(100);
            }catch (Exception e){}

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
                g.Get_Aereo().SetPosizione(nomeAeroporto);
                Aereoporti.get(viaggi.get(g.Get_Aereo().Get_AP_Destinazione())).AereiInArrivo.push(g.Get_Aereo());

                //qui da qualche parte dovrebbe essere settato l'aereoporto attuale dell'aereo
                //che permette all'aereo di uscire dal ciclo del volo e quindi di atterrare
            }
        }
        }

    }

    public void setAereoGate(){
        if( !AereiInArrivo.isEmpty())
        {
            for (Gate g:ListaGate ) {
                if(!g.Get_Gate_Aperto()) {
                    Aereo a = AereiInArrivo.pop();
                    setDestinazione(a);
                    g.openGate(a,viaggi.get(a));
                    System.out.println("(TRC)  Aereo " + a.Get_ID() + " associato al gate " + g.Get_Id()+".");
                }
            }
        }
    }

    public void Add_Aereo(Aereo a){
        this.AereiInArrivo.push(a);
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
