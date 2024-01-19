import Persona.Pilota;
import TorreDiControllo.*;
import Aereo.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

   public static void main(String[] args) {

      // Creazione di oggetti necessari come esempio
      TorreControllo torreControllo = null;

      int parcheggiGateCount = 10;  // Sostituisci con il valore corretto
      int parcheggiEmergenzaCount = 5;  // Sostituisci con il valore corretto
      int pisteCount = 3;  // Sostituisci con il valore corretto
      int hangarCount = 5;  // Sostituisci con il valore corretto

      //Creazione Viaggi e Aerei
      List<Viaggio> viaggi = new ArrayList<>();
      List<Aereo> aerei = new ArrayList<>();
      List<Pilota>  pilotas = new ArrayList<>();
      for (int i = 0; i < 9; i++)
      {
       aerei.add(new Aereo(i));
       pilotas.add((new Pilota(aerei.get(i),torreControllo,false)));
       viaggi.add(new Viaggio("Destinazione",aerei.get(i),pilotas.get(i),5+i));
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
       torreControllo = new TorreControllo(
              viaggi, piste, parcheggiGate, parcheggiEmergenza, hangars,
              parcheggiGateCount, parcheggiEmergenzaCount, pisteCount, hangarCount
      );

      // Esegui la torre di controllo in un nuovo thread
      Thread torreThread = new Thread(torreControllo);
      torreThread.start();

      // Aggiungi codice per gestire il resto del tuo programma, se necessario
   }
}
