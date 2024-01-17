package Persona;

import java.time.LocalDate;

public class CartaImbarco {
    private String nomePasseggero;
    private String cognomePasseggero;
    private int gate;
    private LocalDate orario;
    private String arrivo;
    private String destinazione;
    private String idRiconoscimentoBagaglio;

    //Metodi get
    public String GetNomePasseggero(){return nomePasseggero;}
    public String GetCognomePasseggero(){return cognomePasseggero;}
    public LocalDate GetOrario(){return orario;}
    public String GetArrivo(){return arrivo;}
    public String GetDestinazione(){return destinazione;}
    public String GetIdRiconoscimentoBagaglio(){return idRiconoscimentoBagaglio;}
    public int GetGate(){return gate;}
}
