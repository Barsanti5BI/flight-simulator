package TorreDiControllo;

import Aereo.Aereo;
import Persona.Pilota;
public class Viaggio
{
    Boolean finito;
    public Pilota p;
    public Aereo a;
    private String destinazione;
    private int numGate;
    public Viaggio(String d, Aereo aereo,Pilota pilota, int numGate)
    {
        p = pilota;
        a = aereo;
        destinazione = d;
        finito = false;
        this.numGate = numGate;
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
    public Aereo getAereo()
    {
        return a;
    }
}