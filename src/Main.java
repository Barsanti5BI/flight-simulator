import Persona.Documento;
import Persona.ImpiegatoControlliPartenze;
import Persona.Oggetto;
import Persona.Turista;
import Utils.Coda;
import java.time.LocalDate;
import TorreDiControllo.*;
import Aereo.*;
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
      ImpiegatoControlliPartenze ImpiegatoControlliPartenze = new ImpiegatoControlliPartenze( codaTuristi,lproibito);

      ImpiegatoControlliPartenze.start();

      // TODO: generare turisti e farli entrare in aeroporto

      // Creazione di oggetti necessari come esempio

      int parcheggiGateCount = 10;  // Sostituisci con il valore corretto
      int parcheggiEmergenzaCount = 5;  // Sostituisci con il valore corretto
      int pisteCount = 3;  // Sostituisci con il valore corretto
      int hangarCount = 5;  // Sostituisci con il valore corretto

      //Creazione Viaggi e Aerei
      List<Viaggio> viaggi = new ArrayList<>();
      List<Aereo> aerei = new ArrayList<>();
      for (int i = 0; i < 9; i++)
      {
       aerei.add(new Aereo(i));
       viaggi.add(new Viaggio("Destinazione",aerei.get(i),5+i));
      }

      //Creazione piste
      List<Pista> piste = new ArrayList<>();
      for (int i = 0; i < pisteCount; i++)
      {
         piste.add(new Pista());
      }

      //creazione piste
      for (int i = 0; i < 3; i++)
      {
         piste.add(new Pista());
      }
      List<Gate> gates= new ArrayList<>();
      List<Parcheggio> parcheggiGate = new ArrayList<>();
      List<Parcheggio> parcheggiEmergenza = new ArrayList<>();

      //creazione parcheggi
      for (int i = 0; i < parcheggiGateCount; i++)
      {
         gates.add(new Gate());
         gates.get(i).num = i;
         parcheggiGate.add(new Parcheggio(i,gates.get(i)));
      }


      List<Hangar> hangars = new ArrayList<>();


      // Creazione dell'oggetto TorreControllo
      TorreControllo torreControllo = new TorreControllo(
              viaggi, piste, parcheggiGate, parcheggiEmergenza, hangars,
              parcheggiGateCount, parcheggiEmergenzaCount, pisteCount, hangarCount
      );

      // Esegui la torre di controllo in un nuovo thread
      Thread torreThread = new Thread(torreControllo);
      torreThread.start();

      // Aggiungi codice per gestire il resto del tuo programma, se necessario
   }
}
