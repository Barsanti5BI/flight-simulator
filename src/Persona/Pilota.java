package Persona;
import TorreDiControllo.TorreControllo;
import TorreDiControllo.Parcheggio;
import TorreDiControllo.Pista;


import Aereo.Aereo;

import java.util.Random;

public class Pilota extends Thread{
    private Aereo a;
    private TorreControllo tC;
    private Pista pista;
    private boolean deveAtterare; // true --> aereo atterra // false --> aereo decolla // non so se si fa  // non so se serva
    private Parcheggio parcheggio;

    public Pilota(Aereo a, TorreControllo tC, boolean deveAtterare, int id)
    {
        setName(id+"");
        this.a = a;
        this.tC = tC;
        pista = null;
        parcheggio = null;
        this.deveAtterare = deveAtterare;
    }

    public void run(){
        while(true)
        {
            tC.getCodaPilotiRichiestePista().push(this);
            System.out.println("Il pilota " + getName() + " sta comunicando con la torre");

            synchronized (tC)
            {
                while(pista != null)
                {
                    tC.wait();
                }
            }

            pista.SetAereo(a);
            a.setPista(pista);

            boolean generatoRitardo = false;
            while(!tC.getCondizioniMeteoAttuali())
            {
                if (tC.getCondizioniMeteoAttuali())
                {
                    System.out.println("Che bel tempo! Non ci sarà nessun ritardo.");
                    break;
                }
                else
                {
                    if (!generatoRitardo && !deveAtterare)
                    {
                        System.out.println("Pilota:\"Che brutto tempo! Mi sa che ci sarà un ritardo!\"");
                        generatoRitardo = true;
                        a.setRitardo(generatoRitardo);
                        daiDatiScatolaNera("Ritardo " + ritardo);
                        inviaComuncazioneTC("Ritardo " + ritardo);
                    }

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(!deveAtterare) // decolla
            {
                parcheggio.aereoInPartenza();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                a.inVolo = true;
                pista.setAereo(null);
                a.setPista(null);

                System.out.println("Il pilota " + getName() + " ha fatto decollare l'aereo " + a.nome + " dalla pista " + p.getId());
                break;
            }
            else // atterra
            {
                a.inVolo = false;
                pista.setAereo(null);
                a.setPista(null);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Il pilota " + getName() + " ha fatto atterare l'aereo " + a.nome + " sulla pista " + p.getId());

                tC.getCodaPilotiRichiesteParcheggio().push(this);
                System.out.println("Il pilota " + getName() + " sta comunicando con la torre");

                synchronized (tC)
                {
                    while(parcheggio != null)
                    {
                        tC.wait();
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                a.setParcheggio(parcheggio);
                parcheggio.aereoArrivato(aereo);
                System.out.println("Il pilota " + getName() + " ha parcheggiato l'aereo sul parcheggio " + parcheggio.getId());

                break;
            }
        }
    }

    public void setPista(Pista p)
    {
        this.pista = p;
        daiDatiScatolaNera("Pista" + p.getId());
    }

    public void daiDatiScatolaNera(String comunicazione)
    {
        a.getScatolaNera().InserisciComunicazione(comunicazione);
    }

    public void inviaComuncazioneTC(String comunicazione)
    {
        tC.invioCom(comunicazione);
    }

    public void setParcheggio(Parcheggio p)
    {
        this.parcheggio = p;
        daiDatiScatolaNera("Parcheggio" + p.getId());
    }
}
