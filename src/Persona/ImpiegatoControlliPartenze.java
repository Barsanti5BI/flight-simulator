package Persona;
import Utils.Coda;

import java.util.List;

public class ImpiegatoControlliPartenze extends Persona{
    private Coda<Bagaglio> codaScanner;
    private Coda<Turista> codaTurista;
    private List<Oggetto> oggettiProibiti;
    public ImpiegatoControlliPartenze(Documento doc, Coda<Bagaglio> codaScanner, Coda<Turista> codaTurista, List<Oggetto> oggettiProibiti){
        super(doc);
        this.codaScanner = codaScanner;
        this.codaTurista = codaTurista;
        this.oggettiProibiti = oggettiProibiti; // lista fornita dall'aereoporto
    }
    public void run(){
        while(true)
        {
            if (codaScanner != null)
            {
                // controllore dei bagagli sospetti
                Bagaglio b = codaScanner.pop();
                System.out.println("Attenzione turista " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " è sospetto e viene controllato");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                String controllo = ControlloApprofondito(b.getOggettiContenuti());
                System.out.println("Bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " è bloccato poichè contiene: " + controllo);
                System.out.println("In attesa del proprietario...");

                while(!b.getRitirato())
                {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                // cercare proprietario nella lista dei passeggeri che hanno completato i controlli
            }
            else if (codaTurista != null)
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
