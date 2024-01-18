package Persona;

<<<<<<< Updated upstream
import Aereoporto.ZonaControlli.Scanner;

import java.util.ArrayList;
import java.util.List;

=======
import Aereoporto.ZonaCheckIn.NastroTrasportatore;
import java.util.List;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
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
=======
                // controllore dei bagagli da stiva sospetti

                Bagaglio b = nT.codaBagagli.pop();

                String controllo = ControlloApprofondito(b.getOggettiContenuti());

                Turista proprietario = b.getProprietario();

                if(controllo == null)
                {
                    System.out.println("Il bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " è sicuro, non contiene nessun oggetto proibito");
                    nT.bagagliCaricati.push(b);
                    System.out.println("Il proprietario " + b.getProprietario().getDoc().getNome() + " del bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " non nescessita di ulteriori controlli");
                }
                else
                {
                    System.out.println("Il bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " è bloccato poichè contiene: " + controllo);
                    System.out.println("Il proprietario " + b.getProprietario().getDoc().getNome() + " verrà portato all'interrogatorio e perquisito");
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
            for(String o1 : oggettiProibiti)
=======
            for(Oggetto o1 : ogg)
>>>>>>> Stashed changes
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
