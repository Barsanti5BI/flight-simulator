package Aereoporto.ZonaCheckIn;

import java.time.LocalDate;

public class CartaImbarco {
    private String nomePasseggero;
    private String cognomePasseggero;
    private int gate;
    private boolean prioritario;
    private LocalDate orario;
    private String viaggio;
    private String idRiconoscimentoBagaglio;

    public CartaImbarco(String nomePasseggero, String cognomePasseggero, int gate, LocalDate orario, String viaggio, String idRiconoscimentoBagaglio, boolean prioritario) {
        this.nomePasseggero = nomePasseggero;
        this.cognomePasseggero = cognomePasseggero;
        this.gate = gate;
        this.orario = orario;
        this.viaggio = viaggio;
        this.idRiconoscimentoBagaglio = idRiconoscimentoBagaglio;
        this.prioritario = prioritario;
    }

    //Metodi get
    public String getNomePasseggero(){return nomePasseggero;}
    public String getCognomePasseggero(){return cognomePasseggero;}
    public int getGate(){return gate;}
    public LocalDate getOrario(){return orario;}
    public String getViaggio(){return viaggio;}
    public String getIdRiconoscimentoBagaglio(){return idRiconoscimentoBagaglio;}
    public boolean getPrioritario(){return prioritario;}
}
