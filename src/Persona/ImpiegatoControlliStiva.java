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
                // controllore dei bagagli da stiva sospetti

                Bagaglio b = s.getCodaBagagliPericolosi().pop();

                String controllo = ControlloApprofondito(b.getOggettiContenuti());

                Turista proprietario = b.getProprietario();

                if(controllo == null)
                {
                    System.out.println("Il bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " è sicuro, non contiene nessun oggetto proibito");
                    s.getCodaBagagliControllati().push(b);
                    System.out.println("Il proprietario " + b.getProprietario().getDoc().getNome() + " del bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " non nescessita di ulteriori controlli");
                }
                else
                {
                    System.out.println("Il bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " è bloccato poichè contiene: " + controllo);
                    System.out.println("Il proprietario " + b.getProprietario().getDoc().getNome() + " verrà portato all'interrogatorio e perquisito");

                    // da gestire domani blocco del turista
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
