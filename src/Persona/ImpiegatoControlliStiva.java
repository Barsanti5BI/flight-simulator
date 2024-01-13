package Persona;

import Aereoporto.ZonaCheckIn.NastroTrasportatore;

public class ImpiegatoControlliStiva extends Persona{
    NastroTrasportatore nastroTrasportatore;
    public ImpiegatoControlliStiva(NastroTrasportatore n){
        nastroTrasportatore = n;
    }
    public void run(){
        while(true)
        {
            if (CodaScanner != null)
            {
                // controllore dei bagagli da stiva sospetti

                Bagaglio b = CodaScanner.pop();

                String controllo = ControlloApprofondito(b.getOggettiContenuti());

                if(controllo == null)
                {
                    System.out.println("Il bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " è sicuro, non contiene nessun oggetto proibito");
                    BagagliSicuri.push(b);
                }
                else
                {
                    System.out.println("Il bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " è bloccato poichè contiene: " + controllo);
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
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
