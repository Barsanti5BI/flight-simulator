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


    public ImpiegatoControlliStiva(Scanner s, ArrayList<String> oggettiProibiti, ArrayList<Aereo> listaDiAerei, int id, ArrayList<Turista> turistiPericolosi){
        this.s = s;
        this.oggettiProibiti = oggettiProibiti;
        this.listaDiAerei = listaDiAerei;
        this.turistiPericolosi = turistiPericolosi;
        setName(id+"");
    }

    public void run(){
        System.out.println("Impiegato controlli stiva: \"Sto aspettando\"");
        while(true)
        {
            if (s != null)
            {
                // controllore dei bagagli da stiva sospetti
                if (!s.getCodaBagagliPericolosi().isEmpty()) {
                    Bagaglio bPericoloso = s.getCodaBagagliPericolosi().pop();
                    String controllo = ControlloApprofondito(bPericoloso.getOggettiContenuti());

                    if (controllo == "") {
                        System.out.println("Il bagaglio " + bPericoloso.getEtichetta().getIdRiconoscimentoBagaglio() + " è sicuro, non contiene nessun oggetto proibito");
                        s.getCodaBagagliControllati().push(bPericoloso);
                    } else {
                        System.out.println("ImpiegatoControlliStiva: Il bagaglio " + bPericoloso.getEtichetta().getIdRiconoscimentoBagaglio() + " è bloccato poichè contiene: " + controllo);
                        System.out.println("Il proprietario " + bPericoloso.getProprietario().getDoc().getNome() + " verrà bloccato al gate");
                        Turista proprietario = bPericoloso.getProprietario();
                        turistiPericolosi.add(proprietario);
                    }
                }
                else
                {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (!s.getCodaBagagliControllati().isEmpty())
                {
                    Bagaglio bSicuro = s.getCodaBagagliControllati().pop();
                    Aereo aGiusto = null;

                    for (Aereo a : listaDiAerei) {
                        if (bSicuro.getEtichetta().getCodiceVeivolo() == a.Get_ID()) {
                            aGiusto = a;
                            break;
                        }
                    }

                    aGiusto.Get_Stiva().Aggiungi_Bagaglio_Stiva(bSicuro);
                    System.out.println("Il bagaglio " + bSicuro.getEtichetta().getIdRiconoscimentoBagaglio() + " è stato caricato sull'aereo " + aGiusto.Get_ID());
                }
                else
                {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
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
}
