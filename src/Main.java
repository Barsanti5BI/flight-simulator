import Aereo.Aereo;
import Aereo.F_Turista;
import Utils.Coda;

import java.util.LinkedList;
//AGGIUNGERE PRINTS NEI METODI/THREADS
public class Main {
   public static void main(String[] args) {
      LinkedList<String> aereoporti = new LinkedList<String>();
      aereoporti.add("New York Airport");
      aereoporti.add("Tokyo Airport");
      aereoporti.add("Aereoporto Milano");
      aereoporti.add("Aereoporto Messina");

      LinkedList<Aereo> list_aerei = new LinkedList<Aereo>();
      for(int i = 0; i < 4; i++){
         Aereo a = new Aereo(i);
         list_aerei.add(a);
      }

      Coda<F_Turista> coda_turisti = new Coda<F_Turista>();
      int k = 0;
      int id_tur = 0;
      for(Aereo a : list_aerei){
         String dest = aereoporti.get(k);
         for(int i = 1; i <= 4; i++){
            for(int j = 1; i <= 10; i++){
               if(id_tur % 2 == 0){
                  F_Turista turista = new F_Turista(id_tur, a.Get_ID(), i, j, true, dest);
                  coda_turisti.push(turista);
               }
               else{
                  F_Turista turista = new F_Turista(id_tur, a.Get_ID(), i, j, false, dest);
                  coda_turisti.push(turista);
               }
               id_tur++;
            }
         }
         k++;
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