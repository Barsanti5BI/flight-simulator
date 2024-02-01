
import Aereo.*;
import Aereoporto.*;
import TorreDiControllo.*;
import Persona.*;
import java.util.ArrayList;


public class Main {
   public static void main(String[] args) {
      System.out.println("Benvenuti, l'aereoporto è aperto!");
      ArrayList<Viaggio> viaggi = new ArrayList<Viaggio>();

      ArrayList<Aereo> lista_aerei = new ArrayList<Aereo>();
      ArrayList<Pista> piste = new ArrayList<Pista>();
      ArrayList<Parcheggio> parcheggioGate = new ArrayList<Parcheggio>();
      ArrayList<Parcheggio> parcheggioEmergenza = new ArrayList<Parcheggio>();
      ArrayList<Hangar> hangar = new ArrayList<Hangar>();
      TorreControllo tC = new TorreControllo(viaggi,piste,parcheggioGate,parcheggioEmergenza,hangar,parcheggioGate.size(),parcheggioEmergenza.size(),piste.size(),hangar.size());

      for (int i = 0; i <= 9; i++){
         AereoPasseggeri a = new AereoPasseggeri(i,tC);
         lista_aerei.add((Aereo) a);
      }
      for (int i = 0; i <= 9; i++){
         Viaggio v = new Viaggio("numero: "+ i,lista_aerei.get(i),null, i);
         viaggi.add(v);
      }

      Aereoporto aereoporto = new Aereoporto(viaggi, lista_aerei);
   }
}