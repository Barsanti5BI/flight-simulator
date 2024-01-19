package TorreDiControllo;

import Aereo.Aereo;

import java.time.LocalDateTime;

public class Pista {

    private boolean occupata;                               //Variabile boolean per indicare se la pista sia libera o occupata
    private int id;                                         //Variabile int per rappresentare la pista
    public Aereo aereo;                                     //Aereo
    private LocalDateTime istanteOccupazione;

    public Pista(int id){           //Costruttore
        aereo = null;
        this.id = id;
        this.occupata = false;
    }

    //controlla se la pista sia occupata o meno
    public boolean isOccupata() {
        return occupata;
    }

    //identifica la pista
    public int getId() {
        return id;
    }

    //Feauture Matteo
    // Verifica se il meteo consente di occupare la pista
    //Se la funzione GetMeteo mi restituisce "true" la pista potra' essere occupata
    public void occupaPista(Meteo meteo) {
        if (meteo.DammiMeteoAttuale()) {
            if (!occupata) {
                System.out.println("La pista " + id + " è ora occupata.");
                occupata = true;
                istanteOccupazione = LocalDateTime.now();
            } else {
                System.out.println("La pista " + id + " è già occupata.");
            }
        } else {
            System.out.println("Impossibile occupare la pista " + id + " a causa delle condizioni meteorologiche.");
        }
    }

    //Feauture Matteo
    public void liberaPista() {
        if (occupata) {
            System.out.println("La pista " + id + " è ora libera.");
            occupata = false;
            istanteOccupazione = null;
        } else {
            System.out.println("La pista " + id + " è già libera.");
        }
    }


    //Stato della pista
    //Questa funzione (semplice ToString) mostra lo stato generale della pista
    public String toString() {
        return "Pista{" +
                "id=" + id +
                ", occupata=" + occupata +
                ", istanteOccupazione=" + istanteOccupazione +
                '}';
    }

    public void SetAereo(Aereo a)
    {
        aereo = a;
    }
}
