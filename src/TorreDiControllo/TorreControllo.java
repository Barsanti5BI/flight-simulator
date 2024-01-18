package TorreDiControllo;
import Aereo.Aereo;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
/**
 * TorreDiControllo
 * 
 * 1-Atterraggio
 * Mancante: Comunicazione con il pilota
 * prende gli aerei da viaggi e li mette nella prima pista libera ,ma prima controlla se il parcheggio specifico del gate  o la pista in cui deve atterrare sono libreri,
 * ,se non è libero verrà tenuto nella lista aerei in arrivo facendo diminuire il carburante per simulare il giro di attesa intorno all'aereoporto 
 * (questo solo se è arrivato in prossimità di esso)verrà fatto atterrare non appena il gate si libera; 
 * se è in prossimità di finire il carburante verrà comunque fatto atterrare in emergenza e messo nel primo hangar disponibile o parcheggio di emergenza,
 *  se nessun hangar è disponibile...?
 * 
 * 2-Parcheggio
 * Dalla pista un aereo appena atterato arriverà al gate prestabilito,in quel momento verrà sottoposto a delle ispezioni di sicurezza prima di ripartire, 
 * nel caso non superasse i controlli verrà messo in un Hangar in attesa di manutenzioni,?---implementare aereo di riserva---?.
 * Nel caso contrario verrano fatti salire i passeggeri del gate(NON ANCORA CHIARO COME),dopo l'aereo verrà messo sulla prima pista disponibile per ripartire.
 * quando un aereo parte viene obliterato.
 * 
 * 3-Emergenza Meteo
 * in caso la classe meteo comunicasse dei problemi, in quel caso tutti i voli in partenza andrebbero bloccati in attesa di condizioni migliori.
 * 
 * 
 * 
 */

public class TorreControllo extends Thread
{  
   public boolean fine;
   private List<Viaggio> viaggi;
   private List<Pista> piste;
   private List<Parcheggio> parcheggiGate;
   private List<Parcheggio> parcheggiEmergenza;//boh forse è inutile
   private List<Hangar> hangars;
   //Counters
   private int parcheggiGateCount;
   private int parcheggiEmergenzaCount;
   private int pisteCount;
   private int hangarCount;


   public TorreControllo(List<Viaggio> viaggi ,List<Pista> piste,List<Parcheggio> parcheggiGate,List<Parcheggio> parcheggiEmergenza,List<Hangar> hangars,int parcheggiGateCount,int parcheggiEmergenzaCount,int pisteCount,int hangarCount)
   {
      //avviare viaggi

      super();
      this.viaggi = viaggi;
      this.fine = true;
      this.piste = piste;
      this.parcheggiGate = parcheggiGate;
      this.parcheggiEmergenza = parcheggiEmergenza;
      this.hangars = hangars;
      this.parcheggiGateCount = parcheggiGateCount;
      this.parcheggiEmergenzaCount = parcheggiEmergenzaCount;
      this.pisteCount = pisteCount;
      this.hangarCount = hangarCount;

      for(Viaggio viaggio : this.viaggi){
         viaggio.avviaViaggio();
      }
   }
   @Override
   public void run ()
   {

      //variabili d'appoggio
      int primaPistaDisp;
      List<Aereo> aereiInArrivo;
      List<Viaggio> viaggiFiniti = new ArrayList<>();

      //LOOP
      while (fine)
      {
         //System.out.println(PrimaPistaDisp());

         try {Thread.sleep(3);}catch (Exception e){    System.out.println(e);}
        //prende la lista degli aerei che hanno finito il viaggio
         viaggiFiniti = DammiViaggiFiniti(viaggiFiniti);

         try {Thread.sleep(1000);}catch (Exception e){    System.out.println(e);}//debug----------------------------------------------------------------------

         //Mette gli aerei dentro le varie piste disponibili, controllando anche il gate specifico, se una di queste variabili non
         //non è disponibile allora verrà avviato un timer in modo che l'aereo viaggi ancora fino a che non verrà trovata una pista libera
         if(!viaggiFiniti.isEmpty()) {

            for (int i = 0; i < viaggiFiniti.size(); i++)
            {
               Aereo arrivato = viaggiFiniti.get(i).a;
               primaPistaDisp = PrimaPistaDisp();
               if(primaPistaDisp != -1 && parcheggiGate.get(i).aereo == null)// controllo pista libera e parcheggioGate libero
               {
                  piste.get(primaPistaDisp).aereo = arrivato;//metto l'aereo nella pista
                  System.out.println("L'aereo " + arrivato.DammiID() + " è atterrato");

                  if (ParcheggiaAereo(viaggiFiniti.get(i)))
                  {
                     //Aereo parcheggiato con successo
                     piste.get(primaPistaDisp).aereo = null;
                     System.out.println("L'aereo " + i + " ha liberato la pista " + primaPistaDisp+ " e ha trovato parcheggiato");
                  }

               }
               else {
                  //caso in cui bisogna mandare l'aereo in un altro aereoporto o farlo girare intorno se ha benzina fino a che non avrà
                  //il posto, quindi qui lo si pusha dentro viaggi con un timer proporzionato e controllando il livelo di benzina
               }
               viaggiFiniti.clear();//pulizia della lista ad ogni giro
            }


         }




      }

   }


   //Metodi Parcheggio
   public Boolean ParcheggiaAereo(Viaggio V)
   {
      int parcheggioAereo = CercaParcheggio(V.numGate);
     if(parcheggioAereo != -1)
     {
        parcheggiGate.get(parcheggioAereo).aereoArrivato(V.a);
        return  true;
     }
     else
     {
      return false;
     }
   }
   public int CercaParcheggio(int gate) //ora restituisce l'index del parcheggio, altrimenti sarebbe stato un oggetto dissociato dalla lista
   {
      for(int i = 0; i < parcheggiGate.size(); i++)
      {
         if(parcheggiGate.get(i).gate.num == gate)//aggiunto controllo per vedere se il gate è libero direttamente sul metodo
         {
            return i;
         }
      }
      return -1;
   }
   public int PrimaPistaDisp() // -1 se non ce ne sono
   {
      for (int i = 0; i < piste.size(); i++)
      {
         if(piste.get(i).aereo == null){return i;}
      }
      return -1;
   }

   public List<Integer> PisteDisponibili() // -1 se non ce ne sono
   {
      List<Integer> pisteDisponibili = new ArrayList<>();
      for (int i = 0; i < piste.size(); i++)
      {
         if (piste.get(i).aereo == null)
         {
            pisteDisponibili.add(i);
         }
      }
      return pisteDisponibili;
   }


   public void ViaggioFinito() // blocca il thread fino a che tutti i viaggi sono finiti in pratica,da rivedere.
   {
      // metodo per vedere quali timer hanno finito
      int counter = 0;
      while (counter < viaggi.size())
      {
         for (Viaggio viaggio : viaggi) {
            if(viaggio.DimmiSeFinito()){
               try {
                  Thread.sleep(5 );
               }catch (Exception e){}
               System.out.println(viaggio.toString() + " -----FINITO----");
               counter++;
            }
         }
      }
   }

   public List<Viaggio> DammiViaggiFiniti(List<Viaggio> listaViaggiArrivati) // restituisce tutti i viaggi finiti nella lista viaggi,e li rimuove.
   {


      for (int i = 0; i < viaggi.size() ; i++)
      {
         if (viaggi.get(i).DimmiSeFinito()){
            listaViaggiArrivati.add(viaggi.get(i)); // aggiungo viaggio
            viaggi.remove(i); //Rimuovo viaggio
         }
      }

      return  listaViaggiArrivati;
   }

   public void IniziaViaggio(Viaggio V)
   {
      try
      {
         int pista = -1;
         while(pista == -1)
         {
            pista = PrimaPistaDisp();
            sleep(10);
         }
         piste.get(pista).aereo = V.a;
         parcheggiGate.get(CercaParcheggio(V.numGate)).AereoInPartenza();
         sleep(1000);
      }
      catch(Exception e)
      {
         System.out.println("errore in partenza");
      }
   }


}