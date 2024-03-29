package Aereoporto.ZonaPartenze;

import Aereoporto.Common.ZonaAeroporto;
import Persona.Turista;
import TorreDiControllo.Viaggio;

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
            Gate g = new Gate(v, turistiPericolosi);
            g.start();
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
