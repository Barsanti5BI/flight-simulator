package Persona;

import Aereo.Aereo;

public class Pilota extends Persona{
    public Aereo a;
    public TorreControllo tC;
    public Pista p;

    public Pilota(Aereo a, TorreControllo tC){
        this.a = a;
        this.tC = tC;
        p = null;
    }

    public void run(){

        // metodo per far partire l'aereo
            // chiedo meteo
            // chiedo pista
            // accendo l'aereo
            // metto l'aereo dentro pista

        // metodo blackbox

        // metodo ritardo
            // genero random un ritardo in caso di mal tempo
            // ve lo invio

        while(true)
        {
            if(comunicaConTorre())
            {
                a.InVolo = true;
                break;
            }
        }
    }

    public boolean comunicaConTorre()
    {
        //mi metto in wait sulla torreDiControllo
        // mi da ok
        // mi dai pista
        

        return true;
    }

    public void daiInfo(String azione)
    {
        a.InsersciDati(azione);
    }
}
