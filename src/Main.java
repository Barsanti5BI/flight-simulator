import Aereo.Aereo;
import Aereoporto.ZonaCheckIn.NastroTrasportatore;
import Persona.*;
import Utils.Coda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
   public static void main(String[] args) {
      System.out.println("Airport Simulation");

      LinkedList<Aereo> lista_aerei = new LinkedList<Aereo>();

      for (int i = 0; i <= 9; i++){
         lista_aerei.add(new Aereo(i) {
         });
      }

      ImpiegatoControlliStiva giano =  new ImpiegatoControlliStiva(new NastroTrasportatore(), lista_aerei);

      // TODO: generare turisti e farli entrare in aeroporto
   }
}