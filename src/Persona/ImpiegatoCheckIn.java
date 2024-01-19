package Persona;

import Aereoporto.ZonaCheckIn.Banco;
import Aereoporto.ZonaCheckIn.NastroTrasportatore;
import TorreDiControllo.Viaggio;

public class ImpiegatoCheckIn extends Thread{
    public Banco banco;
    public NastroTrasportatore nastroTrasportatore;

    public ImpiegatoCheckIn(Banco banco, NastroTrasportatore nT, int id){
        setName(id+"");
        this.banco = banco;
        this.nastroTrasportatore = nT;
    }

    public void run() {
        while(true)
        {
            if (!banco.getCodaTuristi().isEmpty())
            {
                Turista t = banco.getCodaTuristi().pop();
                System.out.println("L'impiegato check-in " + getName() + " sta servendo il turista " + t.getName());
                eseguiCheckIn(t);
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

    public synchronized void eseguiCheckIn(Turista turista) {

        Viaggio vGiusto = null;

        for(Viaggio v : banco.getViaggi())
        {
            if (v.getAereo().Get_ID() == turista.getCodAereo())
            {
                vGiusto = v;
                break;
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

        turista.deveFareCheckIn = false;
        turista.notify();
    }
}
