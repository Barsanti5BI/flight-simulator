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
    public String getNomePasseggero(){return nomePasseggero;}
    public String getCognomePasseggero(){return cognomePasseggero;}
    public int getGate(){return gate;}
    public LocalDate getOrario(){return orario;}
    public String getArrivo(){return arrivo;}
    public String getDestinazione(){return destinazione;}
    public String getIdRiconoscimentoBagaglio(){return idRiconoscimentoBagaglio;}
    public int GetGate(){return gate;}
}
