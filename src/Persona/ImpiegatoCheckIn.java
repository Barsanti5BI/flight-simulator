package Persona;

import Aereoporto.ZonaCheckIn.Banco;
import Aereoporto.ZonaCheckIn.NastroTrasportatore;
import TorreDiControllo.Viaggio;

import java.util.Random;

public class ImpiegatoCheckIn extends Thread{
    public Banco banco;
    public NastroTrasportatore nastroTrasportatore;
    private Random rand;

    public ImpiegatoCheckIn(Banco banco, NastroTrasportatore nT, int id){
        setName("ImpiegatoCheckIn" + id);
        this.banco = banco;
        this.nastroTrasportatore = nT;
        rand = new Random();
    }

    public void run() {
        try {
            while(true) {
                System.out.println("Impiegato del banco del check-in pronto");
                Turista t = banco.getCodaTuristi().pop();
                System.out.println("L'impiegato check-in " + getName() + " sta servendo il turista " + t.getName());
                Thread.sleep(rand.nextInt(0, 1001));
                eseguiCheckIn(t);
                System.out.println("L'impiegato check-in " + getName() + " ha servito il turista " + t.getName());
            }
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void eseguiCheckIn(Turista turista) {
        Viaggio viaggioGiusto = null;

        for(Viaggio v : banco.getViaggi())
        {
            if (v == turista.getViaggio())
            {
                viaggioGiusto = v;
                break;
            }
        }
        if (viaggioGiusto == null)
        {
            synchronized (this) {
                System.out.println("Viaggio non trovato");
                turista.deveFareCheckIn = false;
                notify();
                return;
            }
        }

        String randomIdBagaglio = (int)(Math.random() * 10000000) + "";
        turista.getBagaglio().setEtichetta(banco.generaEtichetta(turista, viaggioGiusto, randomIdBagaglio));
        turista.setCartaImbarco(banco.generaCartaImbarco(turista, viaggioGiusto, randomIdBagaglio));


        Bagaglio bagaglio = turista.getBagaglio();

        if(bagaglio.getDaStiva()){
            System.out.println("Il bagaglio di" + bagaglio.getProprietario().getName() + " è da stiva, verrà caricato sul nastro trasportatore");
            nastroTrasportatore.aggiungiBagaglio(bagaglio);
            turista.setBagaglio(null);
        }

        synchronized (banco) {
            turista.deveFareCheckIn = false;
            banco.notify();
        }
    }
}
