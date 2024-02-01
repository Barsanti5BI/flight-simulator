package Aereoporto.ZonaEntrata;

import Aereo.Aereo;
import Aereoporto.Common.ListaOggetti;
import Aereoporto.Common.ZonaAeroporto;
import Aereoporto.ZonaCheckIn.ZonaCheckIn;
import Aereoporto.ZonaControlli.ZonaControlli;
import Aereoporto.ZonaNegozi.ZonaNegozi;
import Aereoporto.ZonaPartenze.ZonaPartenze;
import Persona.*;
import TorreDiControllo.Viaggio;
import Utils.Coda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ZonaEntrata extends ZonaAeroporto {
    private Coda<Turista> codaTuristi;
    private ArrayList<Viaggio> viaggi;

    // qui entrano i turisti generati
    public ZonaEntrata(ArrayList<Viaggio> viaggi, ZonaCheckIn zonaCheckIn, ZonaControlli zonaControlli, ZonaNegozi zonaNegozi, ZonaPartenze zonaPartenze) {
        this.viaggi = viaggi;
        codaTuristi = generaTuristi(zonaCheckIn,zonaControlli,zonaNegozi,zonaPartenze);
    }

    public Coda<Turista> generaTuristi(ZonaCheckIn zonaCheckIn, ZonaControlli zonaControlli, ZonaNegozi zonaNegozi, ZonaPartenze zonaPartenze) {
        Coda<Turista> coda = new Coda<>();

        for (int i = 0; i < 2; i++) {

            ArrayList<Oggetto> listOggetti = generaListaOggetti(0, 6);
            Viaggio viaggio = randomViaggio();
            Bagaglio bagaglio = generaBagagli(viaggio);
            Turista turista = new Turista(generaDocumenti(i), bagaglio, null, listOggetti, viaggio,zonaCheckIn,zonaControlli,zonaNegozi,zonaPartenze);
            bagaglio.setTurista(turista);
            coda.push(turista);
            turista.start();
        }
        return coda;
    }

    public Viaggio randomViaggio(){
        Random rand = new Random();
        return viaggi.get((int)(Math.random()*viaggi.size()));
    }

    private Bagaglio generaBagagli(Viaggio viaggio) {

        boolean daStiva = false;

        Random rand = new Random();
        if (rand.nextInt(0, 5) == 0) {
            daStiva = true;
        }

        int peso = rand.nextInt(0, 30);
        int lunghezza = rand.nextInt(0, 20);
        int altezza = rand.nextInt(0, 30);
        int profondita = rand.nextInt(0, 10);

        String misure = lunghezza + "-" + altezza + "-" + profondita;

        Bagaglio bagaglio = new Bagaglio(daStiva, peso, misure, null, generaListaOggetti(0,6));
        return bagaglio;
    }

    private Documento generaDocumenti(int numId) {
        Random rand = new Random();

        ArrayList<String> listTipoDocumento = new ArrayList<String>();
        listTipoDocumento.add("Carta d'identità");
        listTipoDocumento.add("Passaporto");
        listTipoDocumento.add("Patente");

        ArrayList<String> listNome = new ArrayList<String>();
        listNome.add("Mario");
        listNome.add("Luigi");
        listNome.add("Giovanni");
        listNome.add("Giacomo");

        ArrayList<String> listCognome = new ArrayList<String>();
        listCognome.add("Rossi");
        listCognome.add("Verdi");
        listCognome.add("Bianchi");
        listCognome.add("Neri");
        listCognome.add("Gialli");
        listCognome.add("Blu");
        listCognome.add("Arancioni");
        listCognome.add("Viola");
        listCognome.add("Rosa");

        ArrayList<String> listSesso = new ArrayList<String>();
        listSesso.add("M");
        listSesso.add("F");

        ArrayList<String> listNazionalita = new ArrayList<String>();
        listNazionalita.add("Italiano");
        listNazionalita.add("Francese");
        listNazionalita.add("Spagnolo");
        listNazionalita.add("Inglese");
        listNazionalita.add("Senegalese");

        ArrayList<String> listIndirizzo = new ArrayList<String>();
        listIndirizzo.add("Via Roma");
        listIndirizzo.add("Via Milano");
        listIndirizzo.add("Via Napoli");
        listIndirizzo.add("Via Firenze");
        listIndirizzo.add("Via Venezia");
        listIndirizzo.add("Via Torino");
        listIndirizzo.add("Via Bologna");

        String tipoDocumento = listTipoDocumento.get(rand.nextInt(0, listTipoDocumento.size()));
        String nome = listNome.get(rand.nextInt(0, listNome.size())) + " " + numId;
        String cognome = listCognome.get(rand.nextInt(0, listCognome.size()));
        LocalDate dataNascita = generaDataNascita();
        String sesso = listSesso.get(rand.nextInt(0, listSesso.size()));
        String nazionalita = listNazionalita.get(rand.nextInt(0, listNazionalita.size()));
        String indirizzo = listIndirizzo.get(rand.nextInt(0, listIndirizzo.size()));
        LocalDate dataScadenza = generaDataScadenza();

        return new Documento(tipoDocumento, nome, cognome, dataNascita, sesso, nazionalita, indirizzo, dataScadenza);
    }

    private LocalDate generaDataNascita() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private LocalDate generaDataScadenza() {
        long minDay = LocalDate.of(2026, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2058, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private ArrayList<Oggetto> generaListaOggetti(int numMin, int numMax) {

        Random rand = new Random();
        ArrayList<Oggetto> listOggetti = new ArrayList<>();

        int i = rand.nextInt(numMin, numMax);
        for (int numero = 0; numero < i; numero++) {
            if (rand.nextInt(0, 40) == 0) {
                // oggetto pericoloso
                int posizione = rand.nextInt(0, ListaOggetti.getOggettiPericolosi().size());
                listOggetti.add(new Oggetto(ListaOggetti.getOggettiPericolosi().get(posizione)));
            } else {
                // oggetto safe
                int posizione = rand.nextInt(0, ListaOggetti.getOggetti().size());
                listOggetti.add(new Oggetto(ListaOggetti.getOggetti().get(posizione)));
            }
        }

        return listOggetti;
    }

    // generator of etichette

}
