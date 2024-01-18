package Aereo;

import java.util.ArrayList;
import java.util.Random;

public abstract class Aereo extends  Thread{
    public String nome;
    public String destinazione;
    public String posizione;
    public Gate gate;
    private ArrayList<Bagno> bagni;
    private ArrayList<Pilota> piloti;
    private ScatolaNera scatolaNera;
    private ArrayList<Turbina> turbine;
    private Stiva stiva;
    private Serbatoio serbatoio;
    private boolean pilotaAutomatico;
    public boolean inVolo;
    public boolean èinvolo;
    public Alieni alieni;

    public Aereo(String nome,ArrayList<Pilota> piloti){
        this.nome = nome;

        bagni = new ArrayList<Bagno>();
        scatolaNera = new ScatolaNera (this);
       
        turbine = new ArrayList<Turbina>();
        stiva = new Stiva(this);
        serbatoio = new Serbatoio();
        pilotaAutomatico = false;
        èinvolo = false;

        alieni = new Alieni(this);
        alieni.start();
    }

    public void run(){
        while(èinvolo){
            try{
                if (alieni.aereo_rubato){
                    break;
                }
                Thread.sleep(50);
            }catch (Exception e){}
            serbatoio.consuma();
        }
    }
}