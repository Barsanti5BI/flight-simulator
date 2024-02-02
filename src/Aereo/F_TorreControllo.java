package Aereo;
import  Utils.Coda;
import javax.management.DynamicMBean;
import java.util.*;

public class F_TorreControllo extends Thread{
    private String nomeAeroporto;
    private  Coda<Aereo> AereiInPartenza;
    private  Coda<Aereo> AereiInArrivo;
    private LinkedList<Gate> ListaGate;
    private HashMap<String,F_TorreControllo> Aereoporti;
    private HashMap<String, String> viaggi;


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
            setAereoGate();
            faiPartire();
            try {
                this.sleep(1000);
            }catch (Exception e){}

        }
    }

    public void faiPartire(){
        for (Gate g: ListaGate) {
            if(g.Get_Gate_Aperto()){
            ;
            if(g.Get_Aereo().Prepara_Aereo()){
                while(g.Get_Aereo().Get_Posizione() <100){
                    try{
                        sleep(10);;
                    }catch (Exception e){ }
                }
                g.Get_Aereo().SetPosizione();
                //Aereoporti.get(viaggi.get(g.Get_Aereo())).AereiInArrivo.push(g.Get_Aereo());
                try{
                    this.sleep(5000);
                }catch (Exception e){}
                Aereoporti.get(g.Get_Aereo().Get_AP_Destinazione()).AereiInArrivo.push(g.Get_Aereo());
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
                    g.openGate(a,viaggi.get(a.Get_ID()));
                    System.out.println("(TRC)  Aereo " + a.Get_ID() + " associato al gate " + g.Get_Id()+".");
                }
            }
        }
    }

    public void Add_Aereo(Aereo a){
        this.AereiInArrivo.push(a);
    }
    public void Crea_Viaggi(String dest,Aereo a){
        viaggi.put(a.Get_ID(),dest);
    }
    public void Set_Aereoporti(HashMap<String,F_TorreControllo> Ap){
        Aereoporti=Ap;
    }
    public void Set_Viaggi(HashMap<String ,String> v){
        viaggi = v;
    }
    public void setDestinazione(Aereo a) {
        a.Set_AP_Destinazione(viaggi.get(a.Get_ID()));
    }







}
