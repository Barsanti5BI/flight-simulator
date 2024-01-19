package TorreDiControllo;
import Aereo.*;
import Persona.*;
import Aereoporto.*;

import Aereo.Aereo;

import java.util.ArrayList;
import java.util.List;

public class Hangar {
    private List<Aereo> listaDiAerei;
    private int id;
    private int capacitaMax;

    // Costruttore
    public Hangar(int id) {
        this.id = id;
        this.capacitaMax = 2; // Impostiamo la capacità massima a 2 aerei per hangar
        this.listaDiAerei = new ArrayList<>();
    }

    // Metodo per aggiungere un aereo all'hangar
    public void aggiungiAereo(Aereo aereo) {
        if (listaDiAerei.size() < capacitaMax) {
            listaDiAerei.add(aereo);
            System.out.println("Aereo aggiunto all'hangar " + id);
        } else {
            System.out.println("L'hangar è pieno, impossibile aggiungere l'aereo");
        }
    }

    // Metodo per rimuovere un aereo dall'hangar
    public void rimuoviAereo(Aereo aereo) {
        if (listaDiAerei.contains(aereo)) {
            listaDiAerei.remove(aereo);
            System.out.println("Aereo rimosso dall'hangar " + id);
        } else {
            System.out.println("L'aereo specificato non si trova nell'hangar " + id);
        }
    }

    // Metodo per ottenere la lista degli aerei nell'hangar
    public List<Aereo> getListaDiAerei() {
        return listaDiAerei;
    }

    // Metodo per ottenere l'ID dell'hangar
    public int getId() {
        return id;
    }

    // Metodo per ottenere la capacità massima dell'hangar
    public int getCapacitaMax() {
        return capacitaMax;
    }
}