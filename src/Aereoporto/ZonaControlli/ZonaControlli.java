package Aereoporto.ZonaControlli;

import Aereoporto.Common.ZonaAeroporto;
import Persona.Turista;
import Utils.Coda;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ZonaControlli extends ZonaAeroporto {
   ArrayList<Settore> settori;


   public ZonaControlli() {
      settori = new ArrayList<>();
      configuraZona();
   }
   public void configuraZona(){
      for (int i = 0; i < 1; i++){
         settori.add(new Settore(i));
      }
   }
   public void smistaTuristi(Turista t) {
      Settore s = settori.get(0);
      for (Settore set : settori){
         if (s.codaTuristi.size() > set.codaTuristi.size()){
            s = set;
         }
      }
      s.codaTuristi.push(t);
   }
   public void run(){
         while(true){
            try{
               Turista t = turistiEntranti.pop();
               smistaTuristi(t);
               Thread.sleep(10);
            }
            catch (InterruptedException ex){
               ex.printStackTrace();
            }
         }
   }

}
