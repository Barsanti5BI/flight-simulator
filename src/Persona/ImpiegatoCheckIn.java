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
    }

    public void EseguiCheckIn(){
        Turista t = banco.GetCodaTuristi.pop();
        t.GetBagaglio().setEtichetta(banco.generaEtichetta());
        t.setCartaImbarco(banco.generaCartaImbarco(t));

        Bagaglio b = t.GetBagaglio();

        if(b.getDaStiva()){
            nT.aggiungiBagaglio(b, banco.getIndice());
        }
    }
}
