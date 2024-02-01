package Aereo;
import Utils.Coda;

import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Gate extends Thread{
    private Timer timer;
    private TimerTask timerTask;
    private int nomeGate;
    private String destinazione;
    private Coda<F_Turista> codaPrioritaria;
    private Coda<F_Turista> codaNormale;
    private Boolean TerminatiIControlli;
    private Boolean GateAperto;
    private Coda<F_Turista> codaGenerale;
    private Coda<F_Turista> codaEntrata;
    private boolean GateStop;
    private Aereo aereo;

    //TOLGLIERE AEREO DAL COSTRUTTORE E FARE CICLO CHE LA TORRE DI CONTROLLO MODIFICA
    //PER FAR PARTIRE IL GATE
    public Gate(int nomeGate, Coda<F_Turista> codaGenerale){
        /*timer = new Timer();
        if(timer != null){
            timer.cancel();
        }
        timerTask = new TimerTask() {
            @Override
            public void run() {
                TerminatiIControlli = true;
                aereo.Get_Entrata().DareEntranti(codaEntrata);
                System.out.println("(GT) Il gate " + nomeGate + " si è chiuso.");
                //timer.cancel();
            }
        };*/
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
                    sleep(7000);
                    System.out.println("(GT) Gate " + this.Get_Id() + " aspetta.....");
                }
                //System.out.println("(GT) sono uscito!");
                //entra solo se è arrivato un aereo con i passeggeri
                if(!aereo.Get_Uscita().GetUsciti().isEmpty()){  //ciclo che fa uscire dall'aereo i turisti
                    LinkedList<Bagaglio> lista_bagagli = this.aereo.Get_Stiva().Svuota_Stiva();
                    while(!aereo.Get_Uscita().GetUsciti().isEmpty()) {
                        F_Turista t = aereo.Get_Uscita().GetUsciti().pop();
                        for(Bagaglio b : lista_bagagli){
                            if(b.Turista_id == t.Get_id()){
                                t.Set_Bagaglio(b);
                            }
                        }
                        System.out.println("(GT) Il turista " + t.Get_id() + " è arrivato a destinazione.");
                    }
                    aereo.Set_Stato_Stiva(false);
                    System.out.println("!!!! stiva false");
                    aereo.Set_Stato_Turisti(false);
                    System.out.println("!!!! turisti false");
                }


                //entra solo se c'è un prossimo volo
               // System.out.println("(GT) Destinazione gate " + this.Get_Id() + " " + this.destinazione+".");
                if(destinazione != null){
                    //startTimer();
                    while(!codaGenerale.isEmpty()){   //creo la coda prioritaria e la coda normale
                        F_Turista t = codaGenerale.pop();
                        if(t.Get_Prioritario() || isPasseggeroInPrioritaria()){
                            if (!t.Get_Prioritario())
                            {
                                System.out.println("(GT) Il turista " + t.Get_id() + " è stato fortunato ed è stato fatta entrare nella coda prioritaria.");
                                sleep(100);
                            }
                            codaPrioritaria.push(t);
                            //sleep(100);
                            //System.out.println("(GT) Il turista "+ t.Get_id() + " è entrato nella coda prioritaria nel gate " + nomeGate + ".");
                        }
                        else{
                            codaNormale.push(t);
                            //sleep(100);
                            //System.out.println("(GT) Il turista " + t.Get_id() + " è entrato nella coda normale nel gate " + nomeGate + ".");
                        }
                    }
                    while (!codaPrioritaria.isEmpty()) {  //prima la coda prioritaria
                        F_Turista t = codaPrioritaria.pop();
                        EffettuaControllo(t);
                    }
                    if(codaPrioritaria.isEmpty()){
                        System.out.println("(GT) Sono entrati tutti i turisti della coda prioritaria");
                    }
                    while (!codaNormale.isEmpty()) { //dopo la coda normale
                        F_Turista t = codaNormale.pop();
                        EffettuaControllo(t);
                    }
                    if(codaNormale.isEmpty()){
                        System.out.println("(GT) Sono entrati tutti i turisti della coda normale");
                        if(!TerminatiIControlli){
                            TerminatiIControlli = true;
                            GateAperto = false;
                            aereo.Get_Entrata().DareEntranti(codaEntrata);
                            aereo.Set_Stato_gate(true);
                            System.out.println("(GT) Il gate " + nomeGate + " si è chiuso.");
                        }
                        //GateAperto = false;
                    }
                    this.aereo.Set_Stato_Stiva(true);
                  //  System.out.println("!!!! stiva true");
                    // TerminatiIControlli verrà impostato su true dal TimerTask
                }
                //aereo = null;

            }catch(InterruptedException ex){
                System.out.println(ex.getMessage());
            }

            //GateAperto=false;
            try{
                this.sleep(2000);
            }catch (Exception e){}

        }
    }

    //Metodo che controllo se la destinazione del gate corrisponde alla destinazione segnata
    //nella carta d'imbarco dei turisti, e effettua controllo su turisti pericolosi
    public void EffettuaControllo(F_Turista t){
        try{
            if(destinazione.equals(t.Get_Destinazione())){
                sleep(100);
               // System.out.println("(GT) Il turista " + t.Get_id() + " ha effettuato il controllo effettuato nel gate " + nomeGate + ".");
                Imbarca_Bagaglio(this.aereo, t);
                t.Set_Bagaglio(null);
                codaEntrata.push(t); //se passa il gate metto il turista nella coda per entrare nell'aereo
            }
            else{
                System.out.println("(GT) Il turista " + t.Get_id() + " ha sbagliato gate" + ".");
                sleep(100);
            }
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void startTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if(!TerminatiIControlli){
                    TerminatiIControlli = true;
                    Set_Gate_Chiuso();
                    aereo.Get_Entrata().DareEntranti(codaEntrata);
                    aereo.Set_Stato_gate(true);
                    System.out.println("(GT) Il gate " + nomeGate + " si è chiuso.");

                }

            }
        };
        timer.schedule(timerTask, 5000);
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
        //questo metodo nei paramentri potrebbe avere una coda che deve essere riempita
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
    public Aereo Get_Aereo(){
        return this.aereo;
    }
    public void Esplodi_Aereo() {this.aereo = null;}
    public int Get_Id(){return this.nomeGate;}

    public void Set_Gate_Chiuso() {this.GateAperto = false;}

}