package Persona;

public class Etichetta {
    private int codiceVeivolo;
    private String idRiconoscimentoBagaglio;

    public Etichetta(int codiceVeivolo, String idRiconoscimentoBagaglio) {
        this.codiceVeivolo = codiceVeivolo;
        this.idRiconoscimentoBagaglio = idRiconoscimentoBagaglio;
    }

    //Metodi get
    public int getCodiceVeivolo(){return codiceVeivolo;}
    public String getIdRiconoscimentoBagaglio(){return idRiconoscimentoBagaglio;}
}
