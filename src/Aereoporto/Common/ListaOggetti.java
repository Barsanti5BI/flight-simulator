package Aereoporto.Common;

import java.util.ArrayList;

public class ListaOggetti {
    private static ArrayList<String> oggetti;
    private static ArrayList<String> oggettiPericolosi;

    public ListaOggetti(){
        oggetti = new ArrayList<>();
        oggetti.add("Abbigliamento");
        oggetti.add("Spazzolino da denti");
        oggetti.add("Libro");
        oggetti.add("Passaporto");
        oggetti.add("Caricabatterie");
        oggetti.add("Occhiali da sole");
        oggetti.add("Blocco note");
        oggetti.add("Asciugamano");
        oggetti.add("Fotocamera");
        oggetti.add("Borsa igienica");
        oggetti.add("Chiavi di casa");
        oggetti.add("Cuffie");
        oggetti.add("Snack");
        oggetti.add("Adattatore di corrente");
        oggetti.add("Medicinali");
        oggetti.add("Portafoglio");
        oggetti.add("Carta d'imbarco");
        oggetti.add("Telefono");
        oggetti.add("Gomma da masticare");
        oggetti.add("Mappa della destinazione");

        oggettiPericolosi = new ArrayList<>();
        oggettiPericolosi.add("Coltello");
        oggettiPericolosi.add("Pistola");
        oggettiPericolosi.add("Bastone");
        oggettiPericolosi.add("Banana");
        oggettiPericolosi.add("Righello");
        oggettiPericolosi.add("Arma da fuoco");
        oggettiPericolosi.add("Droga illecita");
        oggettiPericolosi.add("Esplosivo");
        oggettiPericolosi.add("Sostanze tossiche");
        oggettiPericolosi.add("Documenti falsificati");
        oggettiPericolosi.add("sasso");
        oggettiPericolosi.add("Strumenti da scasso");
        oggettiPericolosi.add("Materiale pornografico illegale");
        oggettiPericolosi.add("Prodotti contraffatti");
        oggettiPericolosi.add("Sostanze stupefacenti");
        oggettiPericolosi.add("Munizioni");
        oggettiPericolosi.add("Strumenti da taglio illegali");
        oggettiPericolosi.add("Oggetti rubati");
        oggettiPericolosi.add("Organo");
        oggettiPericolosi.add("Apparecchi di registrazione clandestini");
        oggettiPericolosi.add("Oggetti per il furto");
        oggettiPericolosi.add("Sostanze radioattive");
        oggettiPericolosi.add("Attrezzatura da hacker");
        oggettiPericolosi.add("Prodotti contraffatti");
        oggettiPericolosi.add("Materiali per la falsificazione");
    }

    public static ArrayList<String> getOggetti(){
        return oggetti;
    }
    public static ArrayList<String> getOggettiPericolosi(){
        return oggettiPericolosi;
    }
}
