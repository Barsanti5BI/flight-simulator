package Persona;

import java.util.List;

public class Bagaglio {

    private boolean daStiva;
    private int peso;
    private String misure;
    private Etichetta etichetta;
    private List<Oggetto> oggettiContenuti;
    private boolean ritirato;

    public Bagaglio(boolean daStiva,int peso,String misure,Etichetta etichetta,List<Oggetto>oggettiContenuti){
        this.daStiva = daStiva;
        this.oggettiContenuti = oggettiContenuti;
        this.etichetta = etichetta;
        this.misure = misure;
        this.peso = peso;
        ritirato = false;
    }

    //metodi get
    public boolean getDaStiva(){ return daStiva;}
    public int getPeso()
    {
        return peso;
    }
    public String getMisure(){ return misure;}
    public Etichetta getEtichetta(){ return etichetta;}
    public List<Oggetto> getOggettiContenuti(){return oggettiContenuti;}
    public boolean getRitirato()
    {
        return ritirato;
    }
}
