import Aereo.Aereo;

import java.util.LinkedList;

public class Main {
   public static void main(String[] args) {
      LinkedList<Aereo> list_aerei = new LinkedList<Aereo>();
      for(int i = 0; i < 2; i++){
         AereoPasseggeri a = new AereoPasseggeri(i);
         list_aerei.add(a);
      }
      //Creare Aerei
      //Creare Turisti con i controllo già fatti al gate
      //2 possibilità per trasporto Gate -> Aereo
      //1 Teletrasportare turisti dentro aereos
      //2 creare navette che trasportano fino ad aereo
      //"Creare" ok della torre di controllo per permettere all'aereo di partire
      //Fare partire aereo
   }
}