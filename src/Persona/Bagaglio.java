package Persona;

import java.util.List;

public class Bagaglio {
    private boolean daStiva;
    private int peso;
    private String misure;
    private Etichetta etichetta;
    private List<String>oggettiContenuti;

    public Bagaglio(boolean daStiva,int peso,String misure,Etichetta etichetta,List<String>oggettiContenuti){
        this.daStiva = daStiva;
        this.oggettiContenuti = oggettiContenuti;
        this.etichetta = etichetta;
        this.misure = misure;
        this.peso = peso;
    }

    //metodi get
    public Boolean getDaStiva(){ return daStiva;}
    public int getPeso()
    {
        return peso;
    }
    public String getMisure(){ return misure;}
    public Etichetta getEtichetta(){ return etichetta;}
    public List<String> getOggettiContenuti(){return oggettiContenuti;}
}
