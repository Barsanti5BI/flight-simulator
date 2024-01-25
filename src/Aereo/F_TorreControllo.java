package Aereo;
import  Utils.Coda;
import javax.management.DynamicMBean;
import java.util.Dictionary;
import java.util.HashMap;

public class F_TorreControllo {

    private  Coda<Aereo> AereiInPartenza;
    private  Coda<Aereo> AereiInArrivo;

    private Dictionary<String,F_TorreControllo> Aereoporti;

    private Dictionary<Aereo,String> viaggi;

    public F_TorreControllo(Dictionary<String,F_TorreControllo> Aereoporti, Dictionary<Aereo,String> v){
        this.Aereoporti =Aereoporti;
        this.viaggi = v;
    }

    public String GetDestinazione(Aereo a) {
        return viaggi.get(a);
    }



}
