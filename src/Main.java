import Aereo.Aereo;
import Aereoporto.ZonaCheckIn.NastroTrasportatore;
import Persona.*;
import Utils.Coda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Aereo.AereoPasseggeri;

public class Main {
   public static void main(String[] args) {

      LinkedList<Aereo> lista_aerei = new LinkedList<Aereo>();

      for (int i = 0; i <= 9; i++){
         AereoPasseggeri a = new AereoPasseggeri(i);
         lista_aerei.add((Aereo) a);
      }

      ImpiegatoControlliStiva impiegatoControlliStiva =  new ImpiegatoControlliStiva(new NastroTrasportatore(), lista_aerei);
   }
}