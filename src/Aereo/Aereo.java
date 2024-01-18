package Aereo;

import java.util.ArrayList;

public abstract class Aereo extends  Thread{
    public String nome;
    public String destinazione;
    public String posizione;
    public Gate gate;
    public ArrayList<Bagno> bagni;
    public ArrayList<Pilota> piloti;
    public ScatolaNera scatolaNera;
    public ArrayList<Turbina> turbine;
    public Stiva stiva;
    public Serbatoio serbatoio;
    public boolean pilotaAutomatico;


    public boolean einvolo;




    public Aereo(String nome,ArrayList<Pilota> piloti){
        this.nome = nome;

        bagni = new ArrayList<Bagno>();
        scatolaNera = new ScatolaNera (this);
       
        turbine = new ArrayList<Turbina>();
        stiva = new Stiva(this);
        serbatoio = new Serbatoio();
        pilotaAutomatico = false;

        einvolo = false;



    }

    public void run(){

       avvia();
        while(einvolo && serbatoio.getStatoSerbatoio()>0){

            try{
                Thread.sleep(50);
            }catch (Exception e){}
            serbatoio.consuma();


        }

    }

    public void avvia(){

    }

    public  void atterra(){
        einvolo = false;
    }
}