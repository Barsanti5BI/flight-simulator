package Aereo;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class F_Turista {

    private int id;

    public Lock ded;
    private int posto_riga;
    private int posto_colonna;
    private String id_aereo;
    private Bagaglio bagaglio;
    private boolean prioritario;
    private String destinazione;

    public F_Turista(int id, String id_ae, int p_col, int p_rig,boolean prioritario,String destinazione){
        this.id_aereo = id_ae;
        this.id = id;
        this.bagaglio = new Bagaglio(this.id);
        this.posto_colonna = p_col;
        this.posto_riga = p_rig;
        this.prioritario = prioritario;
        this.destinazione = destinazione;
        this.ded= new ReentrantLock();

    }

    public Bagaglio Get_Bag(){
        return this.bagaglio;
    }
    public int Get_posto_riga() {return this.posto_riga;}
    public int Get_posto_colonna() {return this.posto_colonna;}
    public String Get_id_aereo() {return this.id_aereo;}
    public int Get_id() {return this.id;}
    public boolean Get_Prioritario(){return prioritario;}
    public String Get_Destinazione(){return destinazione;}
    public void Set_Bagaglio(Bagaglio b){this.bagaglio = bagaglio;}

}
