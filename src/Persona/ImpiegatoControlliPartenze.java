package Persona;
import Utils.Coda;

import java.util.ArrayList;
import java.util.List;

public class ImpiegatoControlliPartenze extends Persona{
    private Coda<Bagaglio> codaScanner;
    private Coda<Turista> codaTurista;
    private ArrayList<String> oggettiProibiti;
    public ImpiegatoControlliPartenze(Coda<Bagaglio> codaScanner, Coda<Turista> codaTurista, ArrayList<String> oggettiProibiti){
        this.codaScanner = codaScanner;
        this.codaTurista = codaTurista;
        this.oggettiProibiti = oggettiProibiti; // lista fornita dall'aereoporto
    }
    public void run(){
        while(true)
        {
            if (codaScanner != null)
            {
<<<<<<< Updated upstream
                if (!codaScanner.isEmpty())
                {
                    // controllore dei bagagli sospetti
                    Bagaglio b = codaScanner.pop();
                    System.out.println("Attenzione bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " è sospetto e viene controllato");
=======
                // controllore dei bagagli sospetti
                Bagaglio b = codaScanner.pop();

                System.out.println("Attenzione turista " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " è sospetto e viene controllato");
>>>>>>> Stashed changes

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    String controllo = ControlloApprofondito(b.getOggettiContenuti());
                    System.out.println("Bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " è bloccato poichè contiene: " + controllo);

                    // manca da ricercare il turista
                }
                else
                {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

<<<<<<< Updated upstream
=======
                Turista t = b.getProprietario();
                System.out.println("Il turista " + t.getName() +  ", proprietario del bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + ", verrà perquisito e portato all'interrogatorio");
                // cercare proprietario nella lista dei passeggeri che hanno completato i controlli
>>>>>>> Stashed changes
            }
            else if (codaTurista != null)
            {
                if (!codaTurista.isEmpty())
                {
                    // controllore delle persone sospette
                    Turista t = codaTurista.pop();
                    System.out.println("Attenzione turista " + t.getName() + " è sospetto e viene controllato");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    String controllo = ControlloApprofondito(t.GetListaOggetti());
                    System.out.println("Turista " + t.getName() + " è arrestato poichè in possesso di: " + controllo);
                    t.perquisizioneTerminata = true;
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
