package Aereoporto.ZonaPartenze;

import Persona.Turista;
import TorreDiControllo.Viaggio;
import Utils.Coda;

import java.util.ArrayList;

public class Gate extends Thread {
    Viaggio viaggio;
    ArrayList<Turista> turistiPericolosi;
    Coda<Turista> codaTuristi;

    public Gate(Viaggio viaggio, ArrayList<Turista> turistiPericolosi)
    {
        this.viaggio = viaggio;
        this.turistiPericolosi = turistiPericolosi;
        codaTuristi = new Coda<>();
    }

    public void run()
    {
        while(true)
        {
            Turista t = codaTuristi.pop();

            boolean pericoloso = false;
            for(Turista tPericoloso : turistiPericolosi)
            {
                if (t == tPericoloso)
                {
                    pericoloso = true;
                    break;
                }
            }

            synchronized (this)
            {
                t.passatoControlliGate = true;
                t.pericolosoAlGate = pericoloso;
                this.notify();
            }
        }
    }

    public Viaggio getViaggio() {
        return viaggio;
    }

    public Coda<Turista> getCodaTuristi()
    {
        return codaTuristi;
    }
}
