package Persona;

import Aereoporto.ZonaControlli.Scanner;

import java.util.ArrayList;
import java.util.List;

public class ImpiegatoControlliStiva extends Persona{
    private Scanner s;
    private ArrayList<String> oggettiProibiti;

    public ImpiegatoControlliStiva(Scanner s, ArrayList<String> oggettiProibiti){
        this.s = s;
        this.oggettiProibiti = oggettiProibiti;
    }

    public void run(){
        while(true)
        {
            if (s != null)
            {
                if (!s.getCodaBagagliPericolosi().isEmpty())
                {
                    Bagaglio b = s.getCodaBagagliPericolosi().pop();

                    String controllo = ControlloApprofondito(b.getOggettiContenuti());

                    if(controllo == "")
                    {
                        System.out.println("Il bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " è sicuro, non contiene nessun oggetto proibito");
                        s.getCodaBagagliControllati().push(b);
                    }
                    else
                    {
                        System.out.println("Il bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " è bloccato poichè contiene: " + controllo);
                    }

                    // manca da ricercare il turista
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
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
                // cercare proprietario nella lista dei passeggeri che hanno completato i controlli
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
