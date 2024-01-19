package Aereoporto.ZonaNegozi;

import Aereoporto.Common.ZonaAeroporto;
import Persona.Prodotto;

import java.util.ArrayList;

public class ZonaNegozi extends ZonaAeroporto {
   //negozi
    ArrayList<Negozio> listaNegozi;

    public ZonaNegozi()
    {
        listaNegozi = new ArrayList<>();
        listaNegozi.add(new Negozio("IPER", "Supermercato", new ArrayList<Prodotto>(), 1));
        listaNegozi.add(new Negozio("H&M", "Abbigliamento", new ArrayList<Prodotto>(), 2));
    }

    public ArrayList<Negozio> getListaNegozi()
    {
        return listaNegozi;
    }
}
