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

    public Pilota(Aereo a, TorreControllo tC, boolean deveAtterare)
    {
        this.a = a;
        this.tC = tC;
        pista = null;
        parcheggio = null;
        this.deveAtterare = deveAtterare;
        ritardoArrivo = 0;
    }

    public void run(){

        // metodo per far partire l'aereo
            // chiedo meteo
            // chiedo pista
            // accendo l'aereo
            // metto l'aereo dentro pista

        // metodo blackbox

        // metodo ritardo
            // genero random un ritardo in caso di mal tempo
            // ve lo invio

        while(true)
        {
            tC.getCodaPilotiRichiestePista().push(this);

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
                    break;
                }
                else
                {
                    if (!generatoRitardo && !deveAtterare)
                    {
                        Random r = new Random();
                        ritardoArrivo = r.nextInt(100,1001);
                        daiDatiScatolaNera("Ritardo " + ritardo);
                        inviaComuncazioneTC("Ritardo " + ritardo);
                        generatoRitardo = true;
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
            }
            else // atterra
            {
                try {
                    Thread.sleep(ritardoArrivo);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                a.inVolo = false;

                tC.getCodaPilotiRichiesteParcheggio().push(this);
                synchronized (tC)
                {
                    while(parcheggio != null)
                    {
                        tC.wait();
                    }
                }
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
