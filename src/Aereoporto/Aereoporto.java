package Aereoporto;

import Aereoporto.ZonaArrivi.ZonaArrivi;
import Aereoporto.ZonaCheckIn.NastroTrasportatore;
import Aereoporto.ZonaCheckIn.ZonaCheckIn;
import Aereoporto.ZonaControlli.ZonaControlli;
import Aereoporto.ZonaControlli.ZonaControlliBagagliStiva;
import Aereoporto.ZonaEntrata.ZonaEntrata;
import Aereoporto.ZonaNegozi.ZonaNegozi;
import Aereoporto.ZonaPartenze.ZonaPartenze;
import Persona.Turista;

public class Aereoporto {
    ZonaEntrata zonaEntrata;
    ZonaCheckIn zonaCheckIn;
    ZonaControlli zonaControlli;
    ZonaControlliBagagliStiva zonaControlliBagagliStiva;
    ZonaNegozi zonaNegozi;
    ZonaPartenze zonaPartenze;
    ZonaArrivi zonaArrivi;
    NastroTrasportatore nastroTrasportatore;

    public Aereoporto() {
        nastroTrasportatore = new NastroTrasportatore();
        zonaEntrata = new ZonaEntrata();
        zonaCheckIn = new ZonaCheckIn(nastroTrasportatore);
        zonaControlli = new ZonaControlli();
        zonaControlliBagagliStiva = new ZonaControlliBagagliStiva(nastroTrasportatore);
        zonaNegozi = new ZonaNegozi();
        zonaPartenze = new ZonaPartenze();
        zonaArrivi = new ZonaArrivi();

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
        zonaArrivi.impostaZonaSuccessiva(zonaEntrata);
    }

}
