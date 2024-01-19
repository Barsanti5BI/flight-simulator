package TorreDiControllo;
import Aereo.*;
import Persona.*;
import Aereoporto.*;
import Utils.Coda;

import java.util.ArrayList;
import java.util.List;
import Utils.Coda;
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

   private Meteo meteo;
   private ArrayList<Viaggio> viaggi;
   private  ArrayList<Viaggio> viaggiTerminati; // lista dei viaggi che non hanno completato il viaggio
   private ArrayList<Pista> piste;

   private Coda<Pilota> richiestaPistaPiloti;
   private Coda<Pilota> richiestaParcheggioPiloti;

   private ArrayList<Parcheggio> parcheggiGate;
   private ArrayList<Parcheggio> parcheggiEmergenza;//boh forse è inutile
   private ArrayList<Hangar> hangars;
   //Counters
   private int parcheggiGateCount;
   private int parcheggiEmergenzaCount;
   private int pisteCount;
   private int hangarCount;


   public TorreControllo(ArrayList<Viaggio> viaggi ,ArrayList<Pista> piste,ArrayList<Parcheggio> parcheggiGate,ArrayList<Parcheggio> parcheggiEmergenza,ArrayList<Hangar> hangars,int parcheggiGateCount,int parcheggiEmergenzaCount,int pisteCount,int hangarCount)
   {
      //avviare viaggi

      super();
      this.viaggi = viaggi;
      this.viaggiTerminati = new ArrayList<>();
      this.fine = true;
      this.piste = piste;
      this.parcheggiGate = parcheggiGate;
      this.parcheggiEmergenza = parcheggiEmergenza;
      this.hangars = hangars;
      this.parcheggiGateCount = parcheggiGateCount;
      this.parcheggiEmergenzaCount = parcheggiEmergenzaCount;
      this.pisteCount = pisteCount;
      this.hangarCount = hangarCount;
      this.richiestaPistaPiloti = null;
   }

   public synchronized void AggiungiPilota(Pilota p)
   {
      richiestaPistaPiloti.push(p);
   }

   @Override
   public void run ()
   {
      System.out.println("La torre di controllo è attiva.");

      //variabili d'appoggio
      Pista primaPistaDisp;
      ArrayList<Aereo> aereiInArrivo;
      ArrayList<Viaggio> viaggiFiniti = new ArrayList<>();


      //LOOP
      while (fine)
      {
         if (meteo.DammiMeteoAttuale())
         {
            System.out.println("Condizioni meteorologiche favorevoli. Elaborazione richieste di atterraggio.");
         }else {
            System.out.println("Condizioni meteorologiche avverse. Attesa miglioramento.");
         }

         if (!richiestaPistaPiloti.isEmpty())
         {
            if (meteo.DammiMeteoAttuale())
            {
               for (int i = 0; i < richiestaPistaPiloti.size(); i++)
               {
                  Viaggio viaggioArrivato = CercaViaggio(richiestaPistaPiloti.pop());
                  primaPistaDisp = PrimaPistaDisp();
                  viaggioArrivato.p.setPista(primaPistaDisp);//ora il pilota ha la pista di atterraggio e aspetta conferma di atterrare
                  System.out.println("Richiesta di atterraggio da " +  viaggioArrivato.p.toString() +
                          ". Pista selezionata: " + (primaPistaDisp != null ? primaPistaDisp : "Nessuna pista disponibile"));
                  if (primaPistaDisp != null && parcheggiGate.get(viaggi.get(i).GetNumGate()).GetAereo() == null)// controllo pista libera e parcheggioGate libero
                  {
                     viaggioArrivato.p.notify();
                     System.out.println("L'aereo " + viaggioArrivato.a.Get_ID()+ " è atterrato");
                  } else {
                     //caso in cui bisogna mandare l'aereo in un altro aereoporto o farlo girare intorno, quando finisce la benzina si cancella il volo

                     if (viaggioArrivato.a.Get_Serbatoio().getStatoSerbatoio() < 10)
                     {
                        System.out.println("Attenzione: Carburante basso. Viaggio terminato per " + viaggioArrivato.a.Get_ID());
                        viaggiTerminati.add(viaggioArrivato);
                     } else {
                       richiestaPistaPiloti.push(viaggioArrivato.p); //ritorna nella lista delle richieste per entrare nella pista
                           System.out.println("Atterraggio rimandato. Pista o parcheggio non disponibili.");
                     }
                  }
               }
            }
            //Parcheggio aerei che sono dentro le piste

            for (int i = 0; i < piste.size(); i++)
            {
               if(piste.get(i).aereo != null)
               {

                  Viaggio v = CercaViaggio(piste.get(i).aereo);
                  Pilota p = v.p;

                  if(v == null)
                  {
                     if(hangarCount < hangars.size() ){

                     }
                     hangars.get(hangarCount).aggiungiAereo(piste.get(i).aereo);
                     System.out.println("L'aereo " + piste.get(i).aereo.Get_ID() + " parcheggiato nel hangar " + hangarCount);
                     piste.remove(i);
                  }
                  else
                  {
                     p.setParcheggio(CercaParcheggio(v.GetNumGate()));//aspettare pullappmains
                     System.out.println("L'aereo " + piste.get(i).aereo.Get_ID() + " assegnato al parcheggio al gate " + v.GetNumGate());
                  }

               }
            }


          for (int k = 0; k < parcheggiGateCount; k++)
            {
               Parcheggio p = parcheggiGate.get(k);
               AereoPasseggeri a = (AereoPasseggeri) p.GetAereo();
               //controllo se il parcheggio è occupato e se il gate è pronto a portare i turisti nell'aereo
               if (!p.isFree() && p.GateFree()) {
                  //controllo la distanza, se TRUE allora navetta
                  if (p.GetDistanza()) {
                     Navetta n = new Navetta(p.GetGate(), p.GetAereo());
                     n.navettaAndata();
                     System.out.println("Navetta in viaggio verso l'aereo al gate " + p.GetGate().Get_Nome_Gate());
                  } else {
                     //prende i turisti e li porta nell'entrata dell'aereo
                     Coda<Turista> codaTuristi = p.GetGate().getCodaTurista();
                     a.getEntrata().DareEntranti(codaTuristi);
                     System.out.println("Turisti a bordo dell'aereo al gate " + p.GetGate().Get_Nome_Gate());
                  }
               }

            }

         }

      }



   }
   //Metodi comunicazione pilota
   //parti() // il pilota
   //ritardo()
   //atterra()
   //Metodi Parcheggio

   public String dimmiMeteoAttuale()
   {
      return meteo.DammiMotivazione();
   }
   public Coda<Pilota> getCodaPilotiRichiestePista(){return richiestaPistaPiloti;}
   public Coda<Pilota> getCodaPilotiRichiesteParcheggio()
   {
    return richiestaParcheggioPiloti;
   }

   public Viaggio CercaViaggio(Pilota piloto)
   {
      for (int i = 0; i < viaggi.size(); i++)
      {
         if(viaggi.get(i).p == piloto){
            return viaggi.get(i);
         }
      }
      return null;
   }

   public Viaggio CercaViaggio(Aereo aereo)
   {
      for (int i = 0; i < viaggi.size(); i++)
      {
         if(viaggi.get(i).a == aereo){
            return viaggi.get(i);
         }
      }
      return null;
   }
 public  List<Pista> DammiPiste(){
      return piste;
 }

//Meteo
   public boolean DimmiMeteo()
   {
      return meteo.DammiMeteoAttuale();
   }

   public void CambiaMeteo()
   {
      meteo.CambiaMeteo();
   }

   public int DammiViaggioAereo(Aereo aereo){
      for (int i = 0; i < viaggi.size(); i++)
      {
         if(viaggi.get(i).a == aereo)
         {
            return i;
         }
      }
      return -1;
   }
   public  int DammiPistePiene(){


      int count = 0;
      for (int i = 0; i < piste.size(); i++) {
         if(piste.get(i).aereo != null)
         {
            count++;
         }
      }
      return count;
   }
   public Boolean ParcheggiaAereo(Viaggio V)
   {
      Parcheggio parcheggioAereo = CercaParcheggio(V.GetNumGate());
     if(parcheggioAereo != null)
     {
        parcheggioAereo.aereoArrivato(V.a);
        return  true;
     }
     else
     {
      return false;
     }
   }


   public Parcheggio CercaParcheggio(int gate) //ora restituisce l'index del parcheggio, altrimenti sarebbe stato un oggetto dissociato dalla lista
   {
      for(int i = 0; i < parcheggiGate.size(); i++)
      {
         if(parcheggiGate.get(i).GetGate().Get_Nome_Gate() == gate)//aggiunto controllo per vedere se il gate è libero direttamente sul metodo
         {
            return parcheggiGate.get(i);
         }
      }
      return null;
   }
   public Pista PrimaPistaDisp() // -1 se non ce ne sono
   {
      for (int i = 0; i < piste.size(); i++)
      {
         if(piste.get(i).aereo == null){return piste.get(i);}
      }
      return null;
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
         Pista pista = null;
         while(pista == null)
         {
            pista = PrimaPistaDisp();
            sleep(10);
         }
         pista.aereo = V.a;
         CercaParcheggio(V.GetNumGate()).AereoInPartenza();
         sleep(1000);
      }
      catch(Exception e)
      {
         System.out.println("errore in partenza");
      }
   }


}
