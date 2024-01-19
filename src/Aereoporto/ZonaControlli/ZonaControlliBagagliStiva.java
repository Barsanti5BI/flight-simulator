package Aereoporto.ZonaControlli;

import Aereo.Aereo;
import Aereoporto.Common.ListaOggetti;
import Aereoporto.Common.ZonaAeroporto;
import Aereoporto.ZonaCheckIn.NastroTrasportatore;
import Persona.ImpiegatoControlliStiva;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class ZonaControlliBagagliStiva extends ZonaAeroporto {
      NastroTrasportatore nastroTrasportatore;
      Scanner scanner;
      ImpiegatoControlliStiva controllore;

      Random rand;

      public  ZonaControlliBagagliStiva(NastroTrasportatore n, Scanner s, ArrayList<Aereo> lista_aerei){
            rand = new Random();
            nastroTrasportatore = n;
            scanner = s;
            controllore = new ImpiegatoControlliStiva(s, ListaOggetti.getOggettiPericolosi(), lista_aerei ,rand.nextInt(0, 1000));
            controllore.start();
      }
      public ImpiegatoControlliStiva getControllore(){
            return controllore;
      }
}
