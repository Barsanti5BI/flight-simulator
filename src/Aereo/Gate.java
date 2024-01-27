package Aereo;
import Utils.Coda;

import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Gate extends Thread{
    Timer timer;
    TimerTask timerTask;
    int nomeGate;
    String destinazione;
    Coda<F_Turista> codaPrioritaria;
    Coda<F_Turista> codaNormale;
    Boolean TerminatiIControlli;
    Boolean GateAperto;
    Coda<F_Turista> codaGenerale;
    Coda<F_Turista> codaEntrata;
    boolean GateStop;
    Aereo aereo;

    //TOLGLIERE AEREO DAL COSTRUTTORE E FARE CICLO CHE LA TORRE DI CONTROLLO MODIFICA
    //PER FAR PARTIRE IL GATE
    public Gate(int nomeGate, Coda<F_Turista> codaGenerale){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                TerminatiIControlli = true;
                aereo.Get_Entrata().DareEntranti(codaEntrata);
                System.out.println("Il gate " + nomeGate + " si è chiuso");
            }
        };
        codaPrioritaria = new Coda<>();
        codaNormale = new Coda<>();
        codaEntrata = new Coda<>();

        this.codaGenerale = codaGenerale;
        this.nomeGate = nomeGate;

        GateStop = true;  //per fermare del tutto il gate(variabile gestita
                          //dalla torre di controllo)
        TerminatiIControlli = false;
        GateAperto = false;
    }

    public void run(){
        while (GateStop){
            try {
                while(!GateAperto){
                    sleep(10);
                }

                //entra solo se è arrivato un aereo con i passeggeri
                if(!aereo.Get_Uscita().GetUsciti().isEmpty()){  //ciclo che fa uscire dall'aereo i turisti
                    LinkedList<Bagaglio> lista_bagagli = this.aereo.Get_Stiva().Svuota_Stiva();
                    while(!aereo.Get_Uscita().GetUsciti().isEmpty()) {
                        F_Turista t = aereo.Get_Uscita().GetUsciti().pop();
                        for(Bagaglio b : lista_bagagli){
                            if(b.Turista_id == t.id){
                                t.bagaglio = b;
                            }
                        }
                        System.out.println("Il turista " + t.Get_id() + " è arrivato a destinazione.");
                    }
                }


                //entra solo se c'è un prossimo volo
                if(destinazione != null){
                    timer.schedule(timerTask, 20000); //Programma il TimerTask per eseguirlo dopo un ritardo specificato

                    while(!codaGenerale.isEmpty()){   //creo la coda prioritaria e la coda normale
                        F_Turista t = codaGenerale.pop();
                        if(t.Get_Prioritario() || isPasseggeroInPrioritaria()){
                            if (!t.prioritario)
                            {
                                System.out.println("Il turista " + t.Get_id() + " è stato fortunato ed è stato fatta entrare nella coda prioritaria.");
                            }
                            codaPrioritaria.push(t);
                            sleep(100);
                            System.out.println("Il turista "+ t.Get_id() + " è entrato nella coda prioritaria nel gate " + nomeGate + ".");
                        }
                        else{
                            codaNormale.push(t);
                            sleep(100);
                            System.out.println("Il turista " + t.Get_id() + " è entrato nella coda normale nel gate " + nomeGate + ".");
                        }
                    }
                    while (!codaPrioritaria.isEmpty()) {  //prima la coda prioritaria
                        F_Turista t = codaPrioritaria.pop();
                        EffettuaControllo(t);

                    }
                    while (!codaNormale.isEmpty()) { //dopo la coda normale
                        F_Turista t = codaNormale.pop();
                        EffettuaControllo(t);
                    }
                    this.aereo.stiva_piena = true;
                    // TerminatiIControlli verrà impostato su true dal TimerTask
                }
                aereo = null;

            }catch(InterruptedException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    //Metodo che controllo se la destinazione del gate corrisponde alla destinazione segnata
    //nella carta d'imbarco dei turisti, e effettua controllo su turisti pericolosi
    public void EffettuaControllo(F_Turista t){
        try{
            if(destinazione.equals(t.Get_Destinazione())){
                sleep(100);
                System.out.println("    Il turista " + t.Get_id() + " ha effettuato il controllo effettuato nel gate " + nomeGate + ".");
                Imbarca_Bagaglio(this.aereo, t);
                t.bagaglio = null;
                codaEntrata.push(t); //se passa il gate metto il turista nella coda per entrare nell'aereo
            }
            else{
                sleep(100);
                System.out.println("    Il turista " + t.Get_id() + " ha sbagliato gate" + ".");
            }
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void Imbarca_Bagaglio(Aereo a, F_Turista f){
        a.Get_Stiva().Aggiungi_Bagaglio_Stiva(f.Get_Bag());
    }

    //Feature Marco Perin
    private boolean isPasseggeroInPrioritaria() {
        int minRange = 0;
        int maxRange = 500;

        // Utilizzo una probabilità del 2% per spostare un passeggero nella coda prioritaria
        double probabilita = 0.02;

        int randomValue = new Random().nextInt(maxRange - minRange + 1) + minRange;

        // Restituisci true se il numero casuale è inferiore alla probabilità desiderata moltiplicata per il range massimo
        return randomValue < probabilita * maxRange;
    }

    public void openGate(Aereo a, String destinazione){ //metodo per aprire il gate
        this.aereo = a;
        this.destinazione = destinazione;
        GateAperto = true;
        Coda<F_Turista> coda_turisti = Genera_Turisti(a, destinazione);
        //sarebbe da creare un metodo che quando apri il gate
        //crei dei turisti con quella destinazione
    }

    public Coda<F_Turista> Genera_Turisti(Aereo a, String destinazione){
        Random rnd = new Random();
        Coda<F_Turista> coda_turisti = new Coda<F_Turista>();
        int id_tur = 0;
        for(int i = 1; i <= 4; i++){
            for(int j = 1; i <= 10; i++){
                if(rnd.nextInt(20) % 2 == 0){
                    F_Turista turista = new F_Turista(id_tur, a.Get_ID(), i, j, true, destinazione);
                    coda_turisti.push(turista);
                }
                else{
                    F_Turista turista = new F_Turista(id_tur, a.Get_ID(), i, j, false, destinazione);
                    coda_turisti.push(turista);
                }
                id_tur++;
            }
        }
        return coda_turisti;
    }

    public Boolean Get_Terminati_Controlli(){return TerminatiIControlli;}
    public Coda<F_Turista> Get_CodaGenerale() {return codaGenerale;}
    public boolean Get_GateStop(){return GateStop;}
    public void Stop_Gate(){ //metodo per fermare il gate
        GateStop = false;
    }
    public String Get_Destinazione(){return destinazione;}
    public boolean Get_Gate_Aperto(){ return GateAperto;}
}