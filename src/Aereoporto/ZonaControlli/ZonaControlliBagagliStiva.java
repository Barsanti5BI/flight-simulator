package Aereoporto.ZonaControlli;

import Aereo.Aereo;
import Aereoporto.Common.ListaOggetti;
import Aereoporto.Common.ZonaAeroporto;
import Aereoporto.ZonaCheckIn.NastroTrasportatore;
import Persona.ImpiegatoControlliStiva;
import Persona.Turista;

import java.util.ArrayList;
import java.util.Random;

public class ZonaControlliBagagliStiva extends ZonaAeroporto {
      NastroTrasportatore nastroTrasportatore;
      Scanner scanner;
      ImpiegatoControlliStiva controllore;
      Random rand;
      ArrayList<Turista> turistiPericolosi; // turisti da bloccare al gate per il bagaglio in stiva,

      public  ZonaControlliBagagliStiva(NastroTrasportatore n, ArrayList<Aereo> lista_aerei){
            rand = new Random();
            turistiPericolosi = new ArrayList<>();
            nastroTrasportatore = n;
            scanner = new Scanner();
            n.setScanner(scanner);
            controllore = new ImpiegatoControlliStiva(scanner, ListaOggetti.getOggettiPericolosi(), lista_aerei,1, turistiPericolosi);
            controllore.start();
            scanner.start();
      }
      public ImpiegatoControlliStiva getControllore(){
            return controllore;
      }
}
