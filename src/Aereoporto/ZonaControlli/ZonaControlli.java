package Aereoporto.ZonaControlli;

import Aereoporto.Common.ZonaAeroporto;
import Persona.Turista;
import java.util.ArrayList;

public class ZonaControlli extends ZonaAeroporto {
   ArrayList<Settore> settori;

   public ZonaControlli() {
      settori = new ArrayList<>();
      configuraZona();
   }

   public void configuraZona(){
      for (int i = 0; i < 3; i++){
         settori.add(new Settore(i));
      }
   }

   public Settore getSettore(int indice) {
      return settori.get(indice);
   }
}
