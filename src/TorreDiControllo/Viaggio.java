package TorreDiControllo;
import Aereo.*;
import Persona.*;
import Aereoporto.*;

import java.util.Timer;
import java.util.TimerTask;
public class Viaggio
{
    Boolean finito;
    public Pilota p;
    public Aereo a;
    private String destinazione;
    //partenza o arrivo, true = partenza, false = arrivo
    private Boolean pa;
    private int numGate;
    public Viaggio(String d, Aereo aereo,Pilota pilota, boolean partenzaArrivo, int nG)
    {
        p = pilota;
        a = aereo;
        destinazione = d;
        finito = false;
        pa = partenzaArrivo;
        numGate = nG;
    }

    public String DammiDestinzazione()
    {
        return destinazione;
    }

    public int GetNumGate()
    {
        return numGate;
    }
    public Boolean DimmiSeFinito()
    {
        return finito;
    }


}