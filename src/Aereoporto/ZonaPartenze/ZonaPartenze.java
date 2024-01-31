package Aereoporto.ZonaPartenze;

import Aereo.Gate;
import Aereoporto.Common.ZonaAeroporto;
import Persona.Turista;
import TorreDiControllo.Viaggio;
import Utils.Coda;

import java.util.ArrayList;

public class ZonaPartenze extends ZonaAeroporto {
    ArrayList<Gate> listaGate;

    public ZonaPartenze(ArrayList<Viaggio> viaggi, ArrayList<Turista> turistiPericolosi)
    {
        this.listaGate = new ArrayList<>();
        int i = 0;
        for (Viaggio v : viaggi)
        {
            i++;
            Gate g = new Gate(i, new Coda<Turista>(), v.DammiDestinzazione(), turistiPericolosi,i);
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
