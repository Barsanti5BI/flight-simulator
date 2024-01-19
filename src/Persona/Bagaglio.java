package Persona;

import java.util.List;

public class Bagaglio {

    private boolean daStiva;
    private int peso;
    private String misure;
    private Etichetta etichetta;
    private List<Oggetto> oggettiContenuti;
    private Turista Proprietario;

    public Bagaglio(boolean daStiva,int peso,String misure,Etichetta etichetta,List<Oggetto>oggettiContenuti){
        this.daStiva = daStiva;
        this.oggettiContenuti = oggettiContenuti;
        this.etichetta = etichetta;
        this.misure = misure;
        this.peso = peso;
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
    public void setEtichetta(Etichetta e){etichetta = e;}
    public void setTurista(Turista proprietario) {this.Proprietario = proprietario;}
    public Turista getProprietario() {return Proprietario;}
}
