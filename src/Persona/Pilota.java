package Persona;

import Aereo.Aereo;
import TorreDiControllo.Parcheggio;
import TorreDiControllo.Pista;
import TorreDiControllo.TorreControllo;

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
                    try {
                        tC.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            pista.SetAereo(a);
            a.setPista(pista);

            //boolean generatoRitardo = false;
            while(!tC.DimmiMeteo())
            {
                if (tC.DimmiMeteo())
                {
                    System.out.println("Che bel tempo! Non ci sarà nessun ritardo.");
                    break;
                }
                else
                {
//                    if (!generatoRitardo && !deveAtterare)
//                    {
//                        System.out.println("Pilota:\"Che brutto tempo! Mi sa che ci sarà un ritardo!\"");
//                        generatoRitardo = true;
//                        a.setRitardo(generatoRitardo);
//                        daiDatiScatolaNera("Ritardo " + generatoRitardo);
//                        inviaComuncazioneTC("Ritardo " + generatoRitardo);
//                    }

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
                parcheggio.AereoInPartenza();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                a.start();
                pista.SetAereo(null);
                a.setPista(null);

                System.out.println("Il pilota " + getName() + " ha fatto decollare l'aereo " + a.Get_ID() + " dalla pista " + pista.getId());
                break;
            }
            else // atterra
            {
                a.start();
                pista.SetAereo(null);
                a.setPista(null);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("Il pilota " + getName() + " ha fatto atterare l'aereo " + a.Get_ID() + " sulla pista " + pista.getId());

                tC.getCodaPilotiRichiesteParcheggio().push(this);
                System.out.println("Il pilota " + getName() + " sta comunicando con la torre");

                synchronized (tC)
                {
                    while(parcheggio != null)
                    {
                        try {
                            tC.wait();
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

                a.setParcheggio(parcheggio);
                parcheggio.aereoArrivato(a);
                System.out.println("Il pilota " + getName() + " ha parcheggiato l'aereo sul parcheggio " + parcheggio.GetId());

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
        a.Get_Scatola_Nera().InserisciComunicazione(comunicazione);
    }

    public void setParcheggio(Parcheggio p)
    {
        this.parcheggio = p;
        daiDatiScatolaNera("Parcheggio" + p.GetId());
    }
}
