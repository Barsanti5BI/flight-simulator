package Aereoporto.ZonaPartenze;

import Aereo.Gate;
import Aereo.Turista;
import Aereoporto.Common.ZonaAeroporto;
import TorreDiControllo.Viaggio;
import Utils.Coda;

import java.util.ArrayList;
import java.util.List;

public class ZonaPartenze extends ZonaAeroporto {

    ArrayList<Gate> listaGate;

    public ZonaPartenze(ArrayList<Viaggio> viaggi)
    {
        this.listaGate = new ArrayList<>();
        int i = 0;
        for (Viaggio v : viaggi)
        {
            i++;
            Gate g = new Gate("Gate: " + i,new Coda<Turista>(),v.getAereo().nome);
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
