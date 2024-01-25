package Aereo;
import  Utils.Coda;
import javax.management.DynamicMBean;
import java.util.Dictionary;
import java.util.HashMap;

public class F_TorreControllo extends Thread{

    private String nomeAerereoporto;
    private  Coda<Aereo> AereiInPartenza;
    private  Coda<Aereo> AereiInArrivo;

    private Dictionary<String,F_TorreControllo> Aereoporti;

    private Dictionary<Aereo,String> viaggi;

    public F_TorreControllo(String nome){
       nomeAerereoporto= nome;
    }

    public void setAereoporti(Dictionary<String,F_TorreControllo> Ap){
        Aereoporti=Ap;
    }
    public void AddViaggio(Dictionary<Aereo,String> v){
        viaggi = v;
    }


    public String GetDestinazione(Aereo a) {
        return viaggi.get(a);
    }







}
