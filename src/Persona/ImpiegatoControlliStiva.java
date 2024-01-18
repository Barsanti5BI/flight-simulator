package Persona;

import Aereoporto.ZonaCheckIn.NastroTrasportatore;

import java.util.List;

public class ImpiegatoControlliStiva extends Persona{
    private NastroTrasportatore nT;

    public ImpiegatoControlliStiva(NastroTrasportatore n){
        nT = n;
    }
    public void run(){
        while(true)
        {
            if (nT != null)
            {
                if (!nT.codaBagagli.isEmpty())
                {
                    Bagaglio b = nT.getCodaScanner().pop();

                    String controllo = ControlloApprofondito(b.getOggettiContenuti());

                    if(controllo == "")
                    {
                        System.out.println("Il bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " è sicuro, non contiene nessun oggetto proibito");
                        nT.getBagagliSicuri().push(b);
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
            for(Oggetto o1 : oggettiProibiti)
            {
                if (o.getNome() == o1.getNome())
                {
                    oggettiTrovati += " " + o.getNome();
                }
            }
        }

        return oggettiTrovati;
    }
}
