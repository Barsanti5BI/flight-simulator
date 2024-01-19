import Aereoporto.Aereoporto;
import Aereoporto.Common.ListaOggetti;
import Persona.Documento;
import Persona.ImpiegatoControlliPartenze;
import Persona.Oggetto;
import Persona.Turista;
import Utils.Coda;
import java.time.LocalDate;
import TorreDiControllo.*;
import Aereo.*;
import java.util.ArrayList;

public class Main {

   public static void main(String[] args) {
      System.out.println("Airport Simulation");
      ArrayList<Viaggio> viaggi = new ArrayList<>();
      Aereoporto aereoporto = new Aereoporto(viaggi);
   }
}
