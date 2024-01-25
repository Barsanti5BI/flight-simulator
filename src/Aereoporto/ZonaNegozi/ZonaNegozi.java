package Aereoporto.ZonaNegozi;

import Aereoporto.Common.ZonaAeroporto;
import Persona.Prodotto;

import java.util.ArrayList;

public class ZonaNegozi extends ZonaAeroporto {
   //negozi
    ArrayList<Negozio> listaNegozi;

    public ZonaNegozi()
    {
       ArrayList<Prodotto> prodotti = new ArrayList<>();
         prodotti.add(new Prodotto("Pasta", 1));
            prodotti.add(new Prodotto("Pomodori", 2));
            prodotti.add(new Prodotto("Cipolle", 3));
            prodotti.add(new Prodotto("Insalata", 4));
            prodotti.add(new Prodotto("Coca Cola", 5));

        listaNegozi = new ArrayList<>();
        listaNegozi.add(new Negozio("IPER", "Supermercato", prodotti, 1));
        listaNegozi.add(new Negozio("H&M", "Abbigliamento", prodotti, 2));
    }

    public ArrayList<Negozio> getListaNegozi()
    {
        return listaNegozi;
    }
}
