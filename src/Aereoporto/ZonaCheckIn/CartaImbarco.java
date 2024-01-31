package Aereoporto.ZonaCheckIn;

import TorreDiControllo.Viaggio;

import java.time.LocalDate;

public class CartaImbarco {
    private String nomePasseggero;
    private String cognomePasseggero;
    private boolean prioritario;
    private LocalDate orario;
    private Viaggio viaggio;
    private String idRiconoscimentoBagaglio;
    private String codicePosto; // esempio 1-4 Colonna , 1-10 Riga (1-4)

    public CartaImbarco(String nomePasseggero, String cognomePasseggero, LocalDate orario, Viaggio viaggio, String idRiconoscimentoBagaglio, boolean prioritario) {
        this.nomePasseggero = nomePasseggero;
        this.cognomePasseggero = cognomePasseggero;
        this.orario = orario;
        this.viaggio = viaggio;
        this.idRiconoscimentoBagaglio = idRiconoscimentoBagaglio;
        this.prioritario = prioritario;
    }

    //Metodi get
    public String getNomePasseggero(){return nomePasseggero;}
    public String getCognomePasseggero(){return cognomePasseggero;}
    public Viaggio getViaggio(){return viaggio;}
    public LocalDate getOrario(){return orario;}
    public String getIdRiconoscimentoBagaglio(){return idRiconoscimentoBagaglio;}
    public boolean getPrioritario(){return prioritario;}
    public String getCodicePosto(){return codicePosto;}
}
