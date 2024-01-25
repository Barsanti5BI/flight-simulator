package Aereoporto.ZonaCheckIn;

import Aereoporto.Common.ZonaAeroporto;
import Aereoporto.ZonaControlli.ZonaControlliBagagliStiva;
import Persona.ImpiegatoCheckIn;
import TorreDiControllo.Viaggio;

import java.util.ArrayList;
import java.util.Random;

public class ZonaCheckIn extends ZonaAeroporto {
    Banco banco;
    ImpiegatoCheckIn impiegato;
    NastroTrasportatore nastroTrasportatore;

    Random rand;

    public ZonaCheckIn(NastroTrasportatore nastro, ArrayList<Viaggio> viaggi) {
        rand = new Random();
        nastroTrasportatore = nastro;
        banco = new Banco(nastroTrasportatore, 1,viaggi);
        impiegato = new ImpiegatoCheckIn(banco, nastroTrasportatore, 1);
        banco.impiegatoCheckIn = impiegato;
        impiegato.start();
    }

    public Banco getBanco() {
        return banco;
    }
}
