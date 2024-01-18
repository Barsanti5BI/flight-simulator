package Persona;

public class Etichetta {
    private String codiceVeivolo;
    private String idRiconoscimentoBagaglio;

    public Etichetta(String codiceVeivolo, String idRiconoscimentoBagaglio) {
        codiceVeivolo = codiceVeivolo;
        idRiconoscimentoBagaglio = idRiconoscimentoBagaglio;
    }

    //Metodi get
    public String getCodiceVeivolo(){return codiceVeivolo;}
    public String getIdRiconoscimentoBagaglio(){return idRiconoscimentoBagaglio;}
}
