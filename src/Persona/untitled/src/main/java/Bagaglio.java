package Persona.untitled.src.main.java;

import java.util.List;

public class Bagaglio {
    private boolean daStiva;
    private int peso;
    private String misure;
    private String etichetta;
    private List<String>oggettiContenuti;

    public Bagaglio(boolean daStiva,int peso,String misure,String etichetta,List<String>oggettiContenuti){
        this.daStiva = daStiva;
        this.oggettiContenuti = oggettiContenuti;
        this.etichetta = etichetta;
        this.misure = misure;
        this.peso = peso;
    }

    //metodi get

}