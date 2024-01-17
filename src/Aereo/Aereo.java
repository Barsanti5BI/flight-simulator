package Aereo;

import java.util.ArrayList;

public abstract class Aereo{
    public String nome;
    public String destinazione;
    public Gate gate;
    private ArrayList<Bagno> bagni;
    private ArrayList<Pilota> piloti;
    private ScatolaNera scatolaNera;
    private ArrayList<Turbina> turbine;
    private Stiva stiva;
    private Serbatoio serbatoio;
    private boolean pilotaAutomatico;

    public Aereo(String nome,ArrayList<Pilota> piloti){
        this.nome = nome;
        bagni = new ArrayList<Bagno>();
        scatolaNera = new ScatolaNera ();
       
        turbine = new ArrayList<Turbina>();
        stiva = new Stiva(this);
        serbatoio = new Serbatoio();
        pilotaAutomatico = false;
    }


   


    
}