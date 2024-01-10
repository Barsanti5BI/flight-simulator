import Persona.Documento;
import Persona.ImpiegatoControlliPartenze;
import Persona.Oggetto;
import Persona.Turista;
import Utils.Coda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
   public static void main(String[] args) {
      System.out.println("Airport Simulation");

      //Aereoporto aereoporto = new Aereoporto();
      Documento d = new Documento("","","", LocalDate.of(1,1,1), "", "", "", LocalDate.of(1,1,1));
      Coda<Turista> codaTuristi = new Coda<>();
      Oggetto o = new Oggetto("Mela");
      Oggetto o1 = new Oggetto("Banana");
      List<Oggetto> lNormale = new ArrayList<>();
      List<Oggetto> lproibito = new ArrayList<>();
      lproibito.add(o1);
      lNormale.add(o);
      Turista t = new Turista(d, null, null, lproibito);
      Turista t1 = new Turista(d, null, null, lNormale);
      codaTuristi.push(t);
      codaTuristi.push(t1);
      ImpiegatoControlliPartenze i = new ImpiegatoControlliPartenze(d,null, codaTuristi, lproibito);

      i.start();

      // TODO: generare turisti e farli entrare in aeroporto
   }
}