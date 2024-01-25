package Persona;

import Aereoporto.ZonaCheckIn.Banco;
import Aereoporto.ZonaCheckIn.NastroTrasportatore;
import TorreDiControllo.Viaggio;

public class ImpiegatoCheckIn extends Thread{
    public Banco banco;
    public NastroTrasportatore nastroTrasportatore;

    public ImpiegatoCheckIn(Banco banco, NastroTrasportatore nT, int id){
        setName("ImpiegatoCheckIn" + id);
        this.banco = banco;
        this.nastroTrasportatore = nT;
    }

    public void run() {
        try {
            while(true) {
                System.out.println("Impiegato del banco del check-in pronto");
                Turista t = banco.getCodaTuristi().pop();
                System.out.println("L'impiegato check-in " + getName() + " sta servendo il turista " + t.getName());
                Thread.sleep(1000);
                eseguiCheckIn(t);
                System.out.println("L'impiegato check-in " + getName() + " ha servito il turista " + t.getName());
            }
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void eseguiCheckIn(Turista turista) {
        Viaggio vGiusto = null;

        for(Viaggio v : banco.getViaggi())
        {
            if (v.getAereo().Get_ID() == turista.getCodAereo())
            {
                vGiusto = v;
                break;
            }
        }
        if (vGiusto == null)
        {
            synchronized (this) {
                System.out.println("Viaggio non trovato");
                turista.deveFareCheckIn = false;
                notify();
                return;
            }
        }
        turista.getBagaglio().setEtichetta(banco.generaEtichetta(turista, vGiusto));
        turista.setCartaImbarco(banco.generaCartaImbarco(turista, vGiusto));


        Bagaglio bagaglio = turista.getBagaglio();

        if(bagaglio.getDaStiva()){
            System.out.println("Il bagaglio " + bagaglio.getEtichetta().getIdRiconoscimentoBagaglio() + " è da stiva, verrà caricato sul nastro trasportatore");
            nastroTrasportatore.aggiungiBagaglio(bagaglio);
            turista.setBagaglio(null);
        }

        synchronized (banco) {
            turista.deveFareCheckIn = false;
            banco.notify();
        }
    }
}
