package Persona;

import java.time.LocalDate;

public class CartaImbarco {
    private String nomePasseggero;
    private String cognomePasseggero;
    private int gate;
    private LocalDate orario;
    private String partenza;
    private String destinazione;
    private String idRiconoscimentoBagaglio;
    private String codicePosto; // esempio 1-4 Colonna , 1-10 Riga (1-4)

    //Metodi get
    public String getNomePasseggero(){return nomePasseggero;}
    public String getCognomePasseggero(){return cognomePasseggero;}
    public int getGate(){return gate;}
    public LocalDate getOrario(){return orario;}
    public String getPartenza(){return partenza;}
    public String getDestinazione(){return destinazione;}
    public String getIdRiconoscimentoBagaglio(){return idRiconoscimentoBagaglio;}
}
