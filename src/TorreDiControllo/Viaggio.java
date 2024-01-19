package TorreDiControllo;
import Aereo.Aereo;

import java.util.Timer;
import java.util.TimerTask;
public class Viaggio // Classe che dovrebbe avere l'aereo
{

    //Gate di entrata
    //viaggio con destinazione
    //Timer,se arriva a 0 atterra
    //
    Boolean finito;
    public Aereo a;
    private String destinazione;
    private int durataSecondi;
    private Timer timer;
    private Boolean pa;
    public int numGate;
    public Boolean getFinito() {
        return finito;
    }

    public Aereo getAereo() {
        return a;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public int getDurataSecondi() {
        return durataSecondi;
    }

    public Timer getTimer() {
        return timer;
    }

    public int getNumGate() {
        return numGate;
    }

    public Viaggio(String destinazione,Aereo a, int durataS)
    {

        this.a = a;
        this.destinazione = destinazione;
        this.durataSecondi = durataS;
        this.timer = new Timer();
        this.finito = false;
    }
    public void avviaViaggio()
    {
        finito = false;
        // Crea un compito (Task) per rappresentare la durata del viaggio
        TimerTask compito = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Il viaggio verso " + destinazione + " è terminato. Durata: " + durataSecondi);
                // Puoi aggiungere qualsiasi azione desideri eseguire quando il viaggio termina

                timer.cancel(); // Ferma il temporizzatore quando il viaggio è terminato
                finito = true;
            }
        };
        // Programma il compito per essere eseguito dopo la durata specificata del viaggio

        timer.schedule(compito, durataSecondi * 1000); // Converti i secondi in millisecondi

        // Puoi eseguire altre azioni qui mentre il viaggio è in corso
        //a.Partenza();
    }



    public Boolean DimmiSeFinito()
    {
        return finito;
    }


}