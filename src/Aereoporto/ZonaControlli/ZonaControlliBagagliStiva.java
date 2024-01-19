package Aereoporto.ZonaControlli;

import Aereoporto.Common.ListaOggetti;
import Aereoporto.Common.ZonaAeroporto;
import Aereoporto.ZonaCheckIn.NastroTrasportatore;
import Persona.ImpiegatoControlliStiva;

public class ZonaControlliBagagliStiva extends ZonaAeroporto {
      NastroTrasportatore nastroTrasportatore;
      Scanner scanner;
      ImpiegatoControlliStiva controllore;

      public  ZonaControlliBagagliStiva(NastroTrasportatore n, Scanner s){
            nastroTrasportatore = n;
            scanner = s;
            controllore = new ImpiegatoControlliStiva(s, ListaOggetti.getOggettiPericolosi());
            controllore.start();
      }
      public ImpiegatoControlliStiva getControllore(){
            return controllore;
      }
}
