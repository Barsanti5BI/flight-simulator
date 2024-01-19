package Aereo;

import java.util.ArrayList;
import java.util.Random;

public abstract class Aereo extends  Thread{
    public String nome;
    public String destinazione;
    public int posizione;
    public Gate gate;
    private ArrayList<Bagno> bagni;
    private ArrayList<Pilota> piloti;
    private ScatolaNera scatolaNera;
    private ArrayList<Turbina> turbine;
    private Stiva stiva;
    private Serbatoio serbatoio;
    private boolean pilotaAutomatico;
    public Alieni alieni;
    public boolean einvolo;

    public Aereo(String nome,ArrayList<Pilota> piloti){
        this.nome = nome;

        bagni = new ArrayList<Bagno>();
        scatolaNera = new ScatolaNera (this);
        turbine = new ArrayList<Turbina>();
        for(int i = 0; i<4;i++){
            Turbina n = new Turbina(this,i);
            turbine.add(n);
        }
        stiva = new Stiva(this);
        serbatoio = new Serbatoio();
        pilotaAutomatico = false;

        einvolo = false;

        alieni = new Alieni(this);
        alieni.start();
    }

    public void run(){

        avvia();
        while(einvolo && serbatoio.getStatoSerbatoio()>0 && posizione<100) {

            try{
                if (alieni.aereo_rubato){
                    break;
                }
                Thread.sleep(50);
            }catch (Exception e){}
            serbatoio.consuma();
            posizione+=2;
        }
    }

    public void avvia(){
        for(int i = 0; i< 4;i++){
            turbine.get(i).start();
        }
        scatolaNera.start();

    }

    public  void atterra(){
        einvolo = false;
    }
}