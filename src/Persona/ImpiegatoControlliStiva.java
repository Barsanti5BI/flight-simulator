package Persona;

import Aereo.Aereo;
import Aereoporto.ZonaControlli.Scanner;

import java.util.ArrayList;
import java.util.List;

public class ImpiegatoControlliStiva extends Thread{
    private Scanner s;
    private ArrayList<String> oggettiProibiti;
    private ArrayList<Turista> turistiPericolosi;
    private ArrayList<Aereo> listaDiAerei;


    public ImpiegatoControlliStiva(Scanner s, ArrayList<String> oggettiProibiti, ArrayList<Aereo> listaDiAerei, int id){
        this.s = s;
        this.oggettiProibiti = oggettiProibiti;
        this.listaDiAerei = listaDiAerei;
        setName(id+"");
    }

    public void run(){
        while(true)
        {
            if (s != null)
            {
                // controllore dei bagagli da stiva sospetti

                Bagaglio bPericoloso = s.getCodaBagagliPericolosi().pop();
                String controllo = ControlloApprofondito(bPericoloso.getOggettiContenuti());
                Turista proprietario = bPericoloso.getProprietario();

                if(controllo == "")
                {
                    System.out.println("Il bagaglio " + bPericoloso.getEtichetta().getIdRiconoscimentoBagaglio() + " è sicuro, non contiene nessun oggetto proibito");
                    s.getCodaBagagliControllati().push(bPericoloso);
                    System.out.println("Il proprietario " + bPericoloso.getProprietario().getDoc().getNome() + " del bagaglio " + bPericoloso.getEtichetta().getIdRiconoscimentoBagaglio() + " non nescessita di ulteriori controlli");
                }
                else
                {
                    System.out.println("Il bagaglio " + bPericoloso.getEtichetta().getIdRiconoscimentoBagaglio() + " è bloccato poichè contiene: " + controllo);
                    System.out.println("Il proprietario " + bPericoloso.getProprietario().getDoc().getNome() + " verrà bloccato al gate");
                    turistiPericolosi.add(proprietario);
                }

                Bagaglio bSicuro = s.getCodaBagagliControllati().pop();
                Aereo aGiusto = null;
                String aId = aGiusto.Get_ID() + "";

                for(Aereo a : listaDiAerei)
                {
                    if (bSicuro.getEtichetta().getCodiceVeivolo() == aId)
                    {
                        aGiusto = a;
                        break;
                    }
                }

                aGiusto.Get_Stiva().Aggiungi_Bagaglio_Stiva(bSicuro);
            }
        }
    }

    public String ControlloApprofondito(List<Oggetto> ogg)
    {
        String oggettiTrovati = "";

        for(Oggetto o : ogg)
        {
            for(String o1 : oggettiProibiti)
            {
                if (o.getNome() == o1)
                {
                    oggettiTrovati += " " + o.getNome();
                }
            }
        }

        return oggettiTrovati;
    }

    public ArrayList<Turista> getTuristiPericolosi()
    {
        return turistiPericolosi;
    }
}
