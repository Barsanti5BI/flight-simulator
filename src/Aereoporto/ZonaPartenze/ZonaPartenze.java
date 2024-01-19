package Aereoporto.ZonaPartenze;
import Persona.ImpiegatoControlliStiva;
import Persona.Turista;
import Aereo.Gate;
import Aereoporto.Common.ZonaAeroporto;
import TorreDiControllo.Viaggio;
import Utils.Coda;

import java.util.ArrayList;
import java.util.List;

public class ZonaPartenze extends ZonaAeroporto {
    ArrayList<Gate> listaGate;

    public ZonaPartenze(ArrayList<Viaggio> viaggi, ImpiegatoControlliStiva impiegatoControlliStiva)
    {
        this.listaGate = new ArrayList<>();
        int i = 0;
        for (Viaggio v : viaggi)
        {
            i++;
            Gate g = new Gate(i, new Coda<Turista>(), v.DammiDestinzazione(), impiegatoControlliStiva);
            listaGate.add(g);
        }
    }

    public void setListaGate(ArrayList<Gate> listaGate)
    {
        this.listaGate = listaGate;
    }

    public ArrayList<Gate> getListaGate()
    {
        return listaGate;
    }
}
