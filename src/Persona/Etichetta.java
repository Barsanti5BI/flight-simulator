package Persona;

public class Etichetta {
    private String codiceVeivolo;
    private String idRiconoscimentoBagaglio;

    public Etichetta(String codiceVeivolo, String idRiconoscimentoBagaglio) {
        this.codiceVeivolo = codiceVeivolo;
        this.idRiconoscimentoBagaglio = idRiconoscimentoBagaglio;
    }

    //Metodi get
    public String getCodiceVeivolo(){return codiceVeivolo;}
    public String getIdRiconoscimentoBagaglio(){return idRiconoscimentoBagaglio;}
}
