package Aereoporto.ZonaCheckIn;

import java.time.LocalDate;

public class CartaImbarco {
    private String nomePasseggero;
    private String cognomePasseggero;
    private int gate;
    private LocalDate orario;
    private String arrivo;
    private String destinazione;
    private String idRiconoscimentoBagaglio;

    public CartaImbarco(String nomePasseggero, String cognomePasseggero, int gate, LocalDate orario, String arrivo, String destinazione, String idRiconoscimentoBagaglio) {
        this.nomePasseggero = nomePasseggero;
        this.cognomePasseggero = cognomePasseggero;
        this.gate = gate;
        this.orario = orario;
        this.arrivo = arrivo;
        this.destinazione = destinazione;
        this.idRiconoscimentoBagaglio = idRiconoscimentoBagaglio;
    }

    //Metodi get
    public String getNomePasseggero(){return nomePasseggero;}
    public String getCognomePasseggero(){return cognomePasseggero;}
    public int getGate(){return gate;}
    public LocalDate getOrario(){return orario;}
    public String getArrivo(){return arrivo;}
    public String getDestinazione(){return destinazione;}
    public String getIdRiconoscimentoBagaglio(){return idRiconoscimentoBagaglio;}
}
