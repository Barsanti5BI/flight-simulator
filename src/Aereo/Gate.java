package Aereo;
import Utils.Coda;

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
    Aereo aereo;
    public Gate(int nomeGate, Coda<F_Turista> codaGenerale, String destinazione,Aereo aereo){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                TerminatiIControlli = true;
                aereo.getEntrata().DareEntranti(codaEntrata);
                System.out.println("Il gate " + nomeGate + " si è chiuso");
            }
        };
        codaPrioritaria = new Coda<>();
        codaNormale = new Coda<>();
        codaEntrata = new Coda<>();

        this.codaGenerale = codaGenerale;
        TerminatiIControlli = false;
        this.destinazione = destinazione;
        this.nomeGate = nomeGate;
        this.aereo = aereo;
    }
    public void run(){
        try {
            timer.schedule(timerTask, 60000); //Programma il TimerTask per eseguirlo dopo un ritardo specificato

                    while(!codaGenerale.isEmpty()){   //creo la coda prioritaria e la coda normale
                        F_Turista t = codaGenerale.pop();
                        if(t.getPrioritario() || isPasseggeroInPrioritaria()){
                            if (!t.prioritario)
                            {
                                System.out.println("Il turista " + t.get_id() + " è stato fortunato ed è stato fatta entrare nella coda prioritaria");
                            }
                            codaPrioritaria.push(t);
                            sleep(100);
                            System.out.println("Il turista "+ t.get_id() + " è entrato nella coda prioritaria nel gate " + nomeGate);
                        }
                        else{
                            codaNormale.push(t);
                            sleep(100);
                            System.out.println("Il turista " + t.get_id() + " è entrato nella coda normale nel gate " + nomeGate);
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
                    // TerminatiIControlli verrà impostato su true dal TimerTask
        }catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }

    }
    public Boolean getTerminatiIControlli(){
        return TerminatiIControlli;
    }

    //Metodo che controllo se la destinazione del gate corrisponde alla destinazione segnata
    //nella carta d'imbarco dei turisti, e effettua controllo su turisti pericolosi
    public void EffettuaControllo(F_Turista t){
        try{
            if(destinazione.equals(t.getDestinazione())){
                sleep(100);
                System.out.println("    Il turista " + t.get_id() + " ha effettuato il controllo effettuato nel gate " + nomeGate);
                codaEntrata.push(t); //se passa il gate metto il turista nella coda per entrare nell'aereo
            }
            else{
                sleep(100);
                System.out.println("    Il turista " + t.get_id() + " ha sbagliato gate");
            }
        }catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }

    //Metodo che apre il gate
    public void openGate(){ //mi fa partire il gate
        GateAperto = true;
        this.start();
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
    public String getDestinazione(){return destinazione;}
    public boolean getGateAperto(){ return GateAperto;}

    public Coda<F_Turista> getCodaGenerale()
    {
        return codaGenerale;
    }
}