package Persona;

import java.time.LocalDate;

public class Documento {
    private String tipoDocumento;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String sesso;
    private String nazionalita;
    private String indirizzo;
    private LocalDate dataScadenza;

    public Documento(String tipoDocumento, String nome, String cognome, LocalDate dataNascita, String sesso, String nazionalita, String indirizzo, LocalDate dataScadenza){
        this.tipoDocumento = tipoDocumento;
        this.nazionalita = nazionalita;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.indirizzo = indirizzo;
        this.nome = nome;
        this.sesso = sesso;
        this.dataScadenza = dataScadenza;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public String getSesso() {
        return sesso;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public String getIndirizzo() {
        return indirizzo;
    }


    public LocalDate getDataScadenza()
    {
        return dataScadenza;
    }
}
