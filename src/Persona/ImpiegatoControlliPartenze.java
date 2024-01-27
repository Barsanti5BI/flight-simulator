package Persona;

import Utils.Coda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImpiegatoControlliPartenze extends Thread{
    private Coda<Bagaglio> codaBagagliSospetti;
    private Coda<Bagaglio> codaBagagliBuoni;
    private Coda<Turista> codaTurista;
    private ArrayList<String> oggettiProibiti;
    private Random rand;
    public ImpiegatoControlliPartenze(Coda<Bagaglio> codaBagagliPericolosi,Coda<Bagaglio> codaBagagliBuoni, Coda<Turista> codaTurista, ArrayList<String> oggettiProibiti, int id){
        this.codaBagagliSospetti = codaBagagliPericolosi;
        this.codaTurista = codaTurista;
        this.codaBagagliBuoni = codaBagagliBuoni;
        this.oggettiProibiti = oggettiProibiti; // lista fornita dall'aereoporto
        setName(id+"");
        rand = new Random();
        this.start();
    }
    public void run(){
        System.out.println("Impiegato controlli partenze: \"Sto aspettando\"");
        while(true)
        {
            if (codaBagagliSospetti != null)
            {
                if (!codaBagagliSospetti.isEmpty())
                {
                    // controllore dei bagagli sospetti
                    Bagaglio b = codaBagagliSospetti.pop();
                    System.out.println("ImpiegatoControlli dice: Attenzione bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " di turista " + b.getProprietario().getName() + " è sospetto e viene controllato");

                    try {
                        Thread.sleep(rand.nextInt(0,1001));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    String controllo = ControlloApprofondito(b.getOggettiContenuti());
                    System.out.println("Bagaglio " + b.getEtichetta().getIdRiconoscimentoBagaglio() + " di turista " + b.getProprietario().getName() +  " è bloccato poichè contiene: " + controllo);
                    b.getProprietario().bagaglioSospetto = true;
                    synchronized (codaBagagliBuoni){
                        codaBagagliBuoni.notify();
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
            }
            else if (codaTurista != null)
            {
                if (!codaTurista.isEmpty())
                {
                    // controllore delle persone sospette
                    Turista t = codaTurista.pop();
                    System.out.println("Attenzione turista " + t.getName() + " è sospetto e viene controllato");

                    try {
                        Thread.sleep(rand.nextInt(0, 1001));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    String controllo = ControlloApprofondito(t.GetListaOggetti());
                    System.out.println("Turista " + t.getName() + " è arrestato poichè in possesso di: " + controllo);
                    t.criminale =true;
                    t.perquisizioneTerminata = true;
                    synchronized (codaTurista) {
                        codaTurista.notify();
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
