
import Aereo.Aereo;
import Aereoporto.ZonaCheckIn.NastroTrasportatore;
import Persona.*;
import TorreDiControllo.Viaggio;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import Aereoporto.Aereoporto;

import Aereo.AereoPasseggeri;

public class Main {
   public static void main(String[] args) {
      System.out.println("Hello world!");
      ArrayList<Viaggio> viaggi = new ArrayList<Viaggio>();
      Aereoporto aereoporto = new Aereoporto(viaggi);
      LinkedList<Aereo> lista_aerei = new LinkedList<Aereo>();

      for (int i = 0; i <= 9; i++){
         AereoPasseggeri a = new AereoPasseggeri(i);
         lista_aerei.add((Aereo) a);
      }
   }
}