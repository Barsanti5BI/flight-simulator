package Persona;

import Aereoporto.ZonaCheckIn.Banco;
import Aereoporto.ZonaCheckIn.NastroTrasportatore;

public class ImpiegatoCheckIn extends Persona{
    public Banco banco;
    public NastroTrasportatore nT;
    public ImpiegatoCheckIn(Banco banco, NastroTrasportatore nT){
        this.banco = banco;
        this.nT = nT;
    }
    public void run() {
        while(true)
        {
            if (!banco.getCodaTuristi().isEmpty())
            {
                eseguiCheckIn();
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

    public void eseguiCheckIn(){
        Turista t = banco.GetCodaTuristi().pop();
        t.GetBagaglio().setEtichetta(banco.generaEtichetta(t));
        t.devePoggiareBagagliAlCheckIn = false;
        t.setCartaImbarco(banco.generaCartaImbarco(t));
        t.devePrendereBiglietto = false;

        Bagaglio b = t.GetBagaglio();

        if(b.getDaStiva()){
            nT.aggiungiBagaglio(b, banco.getIndice());
        }

        nT.codaBagagli.push(t.GetBagaglio());
        t.deveFareCheckIn = false;
    }
}
