package Aereoporto.ZonaControlli;

import Aereoporto.Common.ZonaAeroporto;
import Aereoporto.ZonaCheckIn.NastroTrasportatore;
import Persona.Documento;
import Persona.ImpiegatoControlliPartenze;
import Persona.ImpiegatoControlliStiva;

public class ZonaControlliBagagliStiva extends ZonaAeroporto {
      NastroTrasportatore nastroTrasportatore;
      ImpiegatoControlliStiva controllore;
      public  ZonaControlliBagagliStiva(NastroTrasportatore n){
            nastroTrasportatore = n;
            controllore = new ImpiegatoControlliStiva(n);
      }
}
