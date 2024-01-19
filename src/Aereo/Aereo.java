package Aereo;
import java.util.ArrayList;
import java.util.Random;
public abstract class Aereo{
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
    private int carburante;
    private int ID;

    public boolean inVolo;

    public Aereo(String nome,ArrayList<Pilota> piloti){
        this.nome = nome;

        bagni = new ArrayList<Bagno>();
        scatolaNera = new ScatolaNera (this);
       
        turbine = new ArrayList<Turbina>();
        stiva = new Stiva(this);
        serbatoio = new Serbatoio();
        pilotaAutomatico = false;
        this.ID = id;
        Random r = new Random();
        carburante = r.nextInt(0,100);
    }

    public ScatolaNera getScatolaNera()
    {
        return scatolaNera;
    }
   
    public int DammiCarburante() {
        return carburante;
    }

    public int DammiID() {
        return ID;
    }


}
