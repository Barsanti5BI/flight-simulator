package TorreDiControllo;

import Aereo.Aereo;

import java.util.ArrayList;
import java.util.List;

public class Hangar {
    private List<Aereo> listaDiAerei;
    private int id;
    private int capacitaMax;
    private String chiaveDiAccesso;
    private boolean sicurezzaAttiva;

    public Hangar(int id) {
        this.id = id;
        this.capacitaMax = 2;
        this.listaDiAerei = new ArrayList<>();
        this.sicurezzaAttiva = true; // Sicurezza attiva di default
    }

    public void impostaChiaveDiAccesso(String chiave) {
        if (!sicurezzaAttiva) {
            this.chiaveDiAccesso = chiave;
            System.out.println("Chiave di accesso impostata con successo.");
        } else {
            System.out.println("Impossibile impostare la chiave di accesso mentre la sicurezza è attiva.");
        }
    }

    public void attivaSicurezza() {
        sicurezzaAttiva = true;
        System.out.println("Sicurezza attivata per l'hangar " + id);
    }

    public void disattivaSicurezza() {
        sicurezzaAttiva = false;
        System.out.println("Sicurezza disattivata per l'hangar " + id);
    }

    public void aggiungiAereoConSicurezza(Aereo aereo, String chiave) {
        if (!sicurezzaAttiva || (chiaveDiAccesso != null && chiave.equals(chiaveDiAccesso))) {
            aggiungiAereo(aereo);
            System.out.println("Aereo aggiunto all'hangar " + id);
        } else {
            System.out.println("Operazione non consentita. Chiave di accesso non valida o sicurezza attiva.");
        }
    }

    public void rimuoviAereoConSicurezza(Aereo aereo, String chiave) {
        if (!sicurezzaAttiva || (chiaveDiAccesso != null && chiave.equals(chiaveDiAccesso))) {
            rimuoviAereo(aereo);
            System.out.println("Aereo rimosso dall'hangar " + id);
        } else {
            System.out.println("Operazione non consentita. Chiave di accesso non valida o sicurezza attiva.");
        }
    }

    public void aggiungiAereo(Aereo aereo) {
        if (listaDiAerei.size() < capacitaMax) {
            listaDiAerei.add(aereo);
            System.out.println("Aereo aggiunto all'hangar " + id);
        } else {
            System.out.println("L'hangar è pieno, impossibile aggiungere l'aereo");
        }
    }

    public void rimuoviAereo(Aereo aereo) {
        if (listaDiAerei.contains(aereo)) {
            listaDiAerei.remove(aereo);
            System.out.println("Aereo rimosso dall'hangar " + id);
        } else {
            System.out.println("L'aereo specificato non si trova nell'hangar " + id);
        }
    }

    public List<Aereo> getListaDiAerei() {
        return listaDiAerei;
    }

    public int getId() {
        return id;
    }

    public int getCapacitaMax() {
        return capacitaMax;
    }

}