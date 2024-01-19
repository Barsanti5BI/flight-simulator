package Persona;

import Aereoporto.ZonaCheckIn.Banco;
import Aereoporto.ZonaCheckIn.NastroTrasportatore;

public class ImpiegatoCheckIn extends Persona{
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
        turista.getBagaglio().setEtichetta(banco.generaEtichetta(turista));
        turista.setCartaImbarco(banco.generaCartaImbarco(turista));

        Bagaglio bagaglio = turista.getBagaglio();

        if(bagaglio.getDaStiva()){
            System.out.println("Il bagaglio " + bagaglio.getEtichetta().getIdRiconoscimentoBagaglio() + " è da stiva, verrà caricato sul nastro trasportatore");
            nastroTrasportatore.aggiungiBagaglio(bagaglio, banco.getIndice());
            turista.setBagaglio(null);
        }

//        nastroTrasportatore.codaBagagli.push(turista.getBagaglio());
        turista.deveFareCheckIn = false;
        turista.notify();
    }
}
