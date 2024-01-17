package Aereoporto.ZonaCheckIn;

import Aereoporto.Common.ZonaAeroporto;
import Aereoporto.ZonaControlli.ZonaControlliBagagliStiva;
import Persona.ImpiegatoCheckIn;

public class ZonaCheckIn extends ZonaAeroporto {
    public ZonaControlliBagagliStiva zonaControlliBagagliStiva;

    Banco b1;
    ImpiegatoCheckIn ib1;
    NastroTrasportatore nastroTrasportatore;

    public ZonaCheckIn(NastroTrasportatore nastro) {
        b1 = new Banco();
        nastroTrasportatore = nastro;
        ib1 = new ImpiegatoCheckIn(b1, nastroTrasportatore);

        b1.impiegatoCheckIn = ib1;
    }

   //tabellone
   //nastro trasportatore
   //tanti banchi
   //tanti clienti
    //tanti addetti
    //nastro trasportatore
}
