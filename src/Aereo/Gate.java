package Aereo;
import Utils.Coda;
import java.util.Timer;
import java.util.TimerTask;


public class Gate extends Thread{
    Timer timer;
    TimerTask timerTask;
    String nomeGate;
    String nomeAereo;
    Coda<Turista> codaPrioritaria;
    Coda<Turista> codaNormale;
    Boolean TerminatiIControlli;
    Coda<Turista> codaNavetta;
    Boolean GateAperto;
    Coda<Turista> coda;
    public Gate(String nomeGate, Coda<Turista> coda, String nomeAereo){
        GateAperto = false;
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                TerminatiIControlli = true;
                System.out.println("Terminato " + nomeGate);
            }
        };
        GateAperto = false;
        codaPrioritaria = new Coda<>();
        codaNormale = new Coda<>();
        codaNavetta = new Coda<>();

        this.coda = coda;
        this.nomeAereo = nomeAereo;
        TerminatiIControlli = false;
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
                        if(t.tipoCoda.equals("Prioritaria")){
                            codaPrioritaria.push(t);
                            sleep(1000);
                            System.out.println("Il turista " + t.Nome + " è entrato nella coda prioritaria nel gate " + nomeGate);
                        }
                        else{
                            codaNormale.push(t);
                            sleep(1000);
                            System.out.println("Il turista " + t.Nome + " è entrato nella coda normale nel gate " + nomeGate);
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
    public Boolean TerminatiIControlli(){
        return TerminatiIControlli;
    }
    public void EffettuaControllo(Turista t){
        try{
            if(nomeAereo.equals(t.nomeAereo)){
                sleep(1000);
                System.out.println("    Il turista " + t.Nome + " ha effettuato il controllo effettuato nel gate " + nomeGate);
            }
            else{
                sleep(1000);
                System.out.println("    Il turista " + t.Nome + " ha sbagliato gate");
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
}
