package Aereoporto.ZonaCheckIn;

import Aereoporto.Common.ZonaAeroporto;
import Aereoporto.ZonaControlli.ZonaControlliBagagliStiva;
import Persona.ImpiegatoCheckIn;
import TorreDiControllo.Viaggio;

import java.util.ArrayList;

public class ZonaCheckIn extends ZonaAeroporto {
    Banco banco;
    ImpiegatoCheckIn impiegato;
    NastroTrasportatore nastroTrasportatore;

    public ZonaCheckIn(NastroTrasportatore nastro, ArrayList<Viaggio> viaggi) {
        nastroTrasportatore = nastro;
        banco = new Banco(nastroTrasportatore, 1,viaggi);
        impiegato = new ImpiegatoCheckIn(banco, nastroTrasportatore);
        banco.impiegatoCheckIn = impiegato;
        impiegato.start();
    }

    public Banco getBanco() {
        return banco;
    }
}
