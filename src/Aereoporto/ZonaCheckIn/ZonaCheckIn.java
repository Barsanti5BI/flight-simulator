package Aereoporto.ZonaCheckIn;

import Aereoporto.Common.ZonaAeroporto;
import Aereoporto.ZonaControlli.ZonaControlliBagagliStiva;
import Persona.ImpiegatoCheckIn;

public class ZonaCheckIn extends ZonaAeroporto {
    public ZonaControlliBagagliStiva zonaControlliBagagliStiva;

    Banco banco;
    ImpiegatoCheckIn impiegato;
    NastroTrasportatore nastroTrasportatore;

    public ZonaCheckIn(NastroTrasportatore nastro) {
        nastroTrasportatore = nastro;
        banco = new Banco(nastroTrasportatore, 1);
        impiegato = new ImpiegatoCheckIn(banco, nastroTrasportatore);
        banco.impiegatoCheckIn = impiegato;
    }

    public Banco getBanco() {
        return banco;
    }

   // tabellone
}
