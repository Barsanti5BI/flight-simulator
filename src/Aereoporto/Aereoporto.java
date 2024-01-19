package Aereoporto;

import Aereo.Aereo;
import Aereoporto.ZonaArrivi.ZonaArrivi;
import Aereoporto.ZonaCheckIn.NastroTrasportatore;
import Aereoporto.ZonaCheckIn.ZonaCheckIn;
import Aereoporto.ZonaControlli.ZonaControlli;
import Aereoporto.ZonaControlli.ZonaControlliBagagliStiva;
import Aereoporto.ZonaEntrata.ZonaEntrata;
import Aereoporto.ZonaNegozi.ZonaNegozi;
import Aereoporto.ZonaPartenze.ZonaPartenze;
import Persona.ImpiegatoControlliStiva;
import Persona.Turista;
import TorreDiControllo.Viaggio;

import java.util.ArrayList;
import java.util.LinkedList;

public class Aereoporto {
    ZonaEntrata zonaEntrata;
    ZonaCheckIn zonaCheckIn;
    ZonaControlli zonaControlli;
    ZonaControlliBagagliStiva zonaControlliBagagliStiva;
    ZonaNegozi zonaNegozi;
    ZonaPartenze zonaPartenze;
    NastroTrasportatore nastroTrasportatore;
    ZonaArrivi zonaArrivi;
    public Aereoporto(ArrayList<Viaggio> viaggi, ArrayList<Aereo> lista_aerei) {

        zonaEntrata = new ZonaEntrata(lista_aerei);
        zonaCheckIn = new ZonaCheckIn(nastroTrasportatore,viaggi);
        zonaControlli = new ZonaControlli();
        nastroTrasportatore = new NastroTrasportatore();
        zonaControlliBagagliStiva = new ZonaControlliBagagliStiva(nastroTrasportatore,nastroTrasportatore.getScanner(), lista_aerei);
        zonaNegozi = new ZonaNegozi();
        zonaPartenze = new ZonaPartenze(viaggi, new ImpiegatoControlliStiva(null, null, null, 69420));
        //zonaArrivi = new ZonaArrivi();
        configuraZone();
    }

    public void configuraZone() {
        // N.B. l'entrata non ha nessuna zona precedente
        zonaEntrata.impostaZonaSuccessiva(zonaCheckIn);

        zonaCheckIn.impostaZonaPrecedente(zonaEntrata);
        zonaCheckIn.impostaZonaSuccessiva(zonaControlli);

        zonaControlli.impostaZonaPrecedente(zonaCheckIn);
        zonaControlli.impostaZonaSuccessiva(zonaNegozi);

        zonaNegozi.impostaZonaPrecedente(zonaControlli);
        zonaNegozi.impostaZonaSuccessiva(zonaPartenze);

        zonaPartenze.impostaZonaPrecedente(zonaNegozi);
        // TODO: imposta zona successiva come uscita alla pista

        // TODO: imposta zona precedente come entrata dalla pista
    }
    public NastroTrasportatore getNastroTrasportatore() {
        return nastroTrasportatore;
    }

}
