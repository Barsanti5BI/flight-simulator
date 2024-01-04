package Persona.untitled.src.main.java;

import java.util.Date;

public class Documento {
    private String tipoDocumento;
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String sesso;
    private String nazionalita;
    private String indirizzo;
    private String dataScadenza;

    public Documento(String tipoDocumento,String nome,String cognome,Date dataNascita,String sesso,String nazionalita,String indirizzo,String dataScadenza){
        this.tipoDocumento = tipoDocumento;
        this.nazionalita = nazionalita;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.indirizzo = indirizzo;
        this.nome = nome;
        this.sesso = sesso;
        this.dataScadenza = dataScadenza;
    }
    //metodi get
}
