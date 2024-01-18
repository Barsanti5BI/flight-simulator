package Aereoporto.ZonaPartenze;

import Aereo.Gate;
import Aereoporto.Common.ZonaAeroporto;

import java.util.ArrayList;

public class ZonaPartenze extends ZonaAeroporto {

    ArrayList<Gate> listaGate;

    public ZonaPartenze()
    {
        this.listaGate = new ArrayList<>();
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
