package Persona;

import Aereo.Aereo;

import java.util.Random;

public class Pilota extends Persona{
    private Aereo a;
    private TorreControllo tC;
    private Pista pista;
    private boolean deveAtterare; // true --> aereo atterra // false --> aereo decolla // non so se si fa  // non so se serva
    private Parcheggio parcheggio;
    private int ritardoArrivo;

    public Pilota(Aereo a, TorreControllo tC, boolean deveAtterare, int id)
    {
        setName(id+"");
        this.a = a;
        this.tC = tC;
        pista = null;
        parcheggio = null;
        this.deveAtterare = deveAtterare;
        ritardoArrivo = 0;
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

            boolean generatoRitardo = false;
            while(!verficaCondMeteo())
            {
                if (verficaCondMeteo())
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

            if(!deveAtterare) // decolla
            {
                a.inVolo = true;
                System.out.println("Il pilota " + getName() + " ha fatto decollare l'aereo " + a.nome + " dalla pista " + p.getId());
                break;
            }
            else // atterra
            {
                a.inVolo = false;
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

                a.setParcheggio(parcheggio);
                System.out.println("Il pilota " + getName() + " ha parcheggiato l'aereo sul parcheggio " + parcheggio.getId());

                break;
            }
        }
    }

    public boolean verficaCondMeteo()
    {
        String meteoAttuale = tC.dimmiMeteoAttuale();
        daiDatiScatolaNera(meteoAttuale);

        for(String meteoProibiti : tC.getCondMeteoProibite())
        {
            if (meteoAttuale == meteoProibiti)
            {
                return false;
            }
        }

        return true;
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
