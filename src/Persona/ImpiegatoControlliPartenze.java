package Persona;
import Utils.Coda;

import java.util.ArrayList;
import java.util.List;

public class ImpiegatoControlliPartenze extends Thread{
    private Coda<Bagaglio> codaScanner;
    private Coda<Turista> codaTurista;
    private ArrayList<String> oggettiProibiti;
    public ImpiegatoControlliPartenze(Coda<Bagaglio> codaScanner, Coda<Turista> codaTurista, ArrayList<String> oggettiProibiti, int id){
        this.codaScanner = codaScanner;
        this.codaTurista = codaTurista;
        this.oggettiProibiti = oggettiProibiti; // lista fornita dall'aereoporto
        setName(id+"");
    }
    public void run(){
        while(true)
        {
            if (codaScanner != null)
            {
                if (!codaScanner.isEmpty())
                {
                    // controllore dei bagagli sospetti
                    Bagaglio b = codaScanner.pop();
                    System.out.println("Attenzione bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " è sospetto e viene controllato");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    String controllo = ControlloApprofondito(b.getOggettiContenuti());
                    System.out.println("Bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " è bloccato poichè contiene: " + controllo);
                    b.getProprietario().bagaglioSospetto = true;
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
                    t.notify();
                }
                else
                {
                    System.out.println("Impiegato controlli partenze: \"Sto aspettando\"");
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
