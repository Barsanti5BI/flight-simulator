package Aereo;
import Utils.Coda;
import Persona.Turista;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Gate extends Thread{
    Timer timer;
    TimerTask timerTask;
    int nomeGate;
    String destinazione;
    Coda<Turista> codaPrioritaria;
    Coda<Turista> codaNormale;
    Boolean TerminatiIControlli;
    Coda<Turista> codaNavetta;
    Boolean GateAperto;
    Coda<Turista> coda;
    public Gate(int nomeGate, Coda<Turista> coda,String destinazione){
        GateAperto = false;
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                TerminatiIControlli = true;
                System.out.println("Il gate " + nomeGate + " si è chiuso");
            }
        };
        codaPrioritaria = new Coda<>();
        codaNormale = new Coda<>();
        codaNavetta = new Coda<>();

        this.coda = coda;
        TerminatiIControlli = false;
        this.destinazione = destinazione;
        this.nomeGate = nomeGate;
    }
    public void run(){
        try {
            timer.schedule(timerTask, 60000); //Programma il TimerTask per eseguirlo dopo un ritardo specificato

            while (true) {
                if (!GateAperto) {  //controllo che il gate sia aperto
                    System.out.println("gate chiuso " + nomeGate);
                    sleep(100);
                }
                else{
                    while(!coda.isEmpty()){   //creo la coda prioritaria e la coda normale
                        Turista t = coda.pop();
                        if(t.cartaImbarco.getPrioritario() || isPasseggeroInPrioritaria()){
                            codaPrioritaria.push(t);
                            sleep(1000);
                            System.out.println("Il turista " + t.cartaImbarco.getCognomePasseggero() + " " + t.cartaImbarco.getNomePasseggero() + " è entrato nella coda prioritaria nel gate " + nomeGate);
                        }
                        else{
                            codaNormale.push(t);
                            sleep(1000);
                            System.out.println("Il turista " + t.cartaImbarco.getCognomePasseggero() + " " + t.cartaImbarco.getNomePasseggero() + " è entrato nella coda normale nel gate " + nomeGate);
                        }
                    }
                    while (!codaPrioritaria.isEmpty()) {  //prima la coda prioritaria
                        Turista t = codaPrioritaria.pop();
                        EffettuaControllo(t);
                        codaNavetta.push(t);
                    }
                    while (!codaNormale.isEmpty()) { //dopo la coda normale
                        Turista t = codaNormale.pop();
                        EffettuaControllo(t);
                        codaNavetta.push(t);
                    }
                    // TerminatiIControlli verrà impostato su true dal TimerTask
                }
            }
        }catch(InterruptedException ex){
            System.out.println(ex);
        }

    }
    public Boolean getTerminatiIControlli(){
        return TerminatiIControlli;
    }
    public void EffettuaControllo(Turista t){
        try{
            if(destinazione.equals(t.cartaImbarco.getViaggio())){
                sleep(1000);
                System.out.println("    Il turista " + t.cartaImbarco.getCognomePasseggero() + " " + t.cartaImbarco.getNomePasseggero() + " ha effettuato il controllo effettuato nel gate " + nomeGate);
            }
            else{
                sleep(1000);
                System.out.println("    Il turista " + t.cartaImbarco.getCognomePasseggero() + " " + t.cartaImbarco.getNomePasseggero() + " ha sbagliato gate");
            }
        }catch (InterruptedException ex){
            System.out.println(ex);
        }
    }

    public Coda<Turista> getCodaNavetta() {
        return codaNavetta;
    }
    public void openGate(){ //mi fa partire il gate
        GateAperto = true;
        this.start();
    }

    //FEATURE
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
}