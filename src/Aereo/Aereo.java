package Aereo;

import Utils.Coda;

import java.util.ArrayList;
import java.util.Random;

public class Aereo extends  Thread{
    public int id;
    public String destinazione;
    public int posizione;
    public Gate gate;
    private ArrayList<Bagno> bagni;
    private ScatolaNera scatolaNera;
    private ArrayList<Turbina> turbine;
    private Stiva stiva;
    private Serbatoio serbatoio;
    private boolean pilotaAutomatico;
    public Alieni alieni;
    public boolean einvolo;
    private boolean maltempo;
    private Random r;

    private F_Turista[][] matricePostiAereo;
    private int nPosti;
    private Entrata entrata;
    private Uscita uscita;

    public Aereo(int Id){
        this.id =Id;
        maltempo = false;

        r = new Random();

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

        matricePostiAereo = new F_Turista[4][10];
        nPosti = 40;
        entrata = new Entrata();
        uscita = new Uscita(this);
    }

    public void run(){
        if(sciopero()){
            try{

                Thread.sleep(5000);
            }catch (Exception e){}
        }
        avvia();
        while(einvolo && serbatoio.getStatoSerbatoio()>0 && posizione<100 && ControllaTurbine()) {

            try{
                //Feature Pettenuzzo
                if (alieni.aereo_rubato){
                    break;
                }
                Thread.sleep(1000);
            }catch (Exception e){}
            serbatoio.consuma_carburante();
            if (maltempo) {
                posizione+=1;
            }else{
                posizione+=2;
            }

        }
    }

    //Metodo che accende le Turbine dell'aereo e accende la scatola nera
    public void avvia(){
        ImbarcaPasseggieri();
        for(int i = 0; i< 4;i++){
            turbine.get(i).start();
        }
        scatolaNera.start();
    }

    //Metodo per riparare le turbine e ricaricare la batteria della scatola nera
    public void Ripara(){
        for(int i = 0; i<4;i++){
            turbine.get(i).Ripara();
        }
        scatolaNera.Ricarica();
    }

    public void Rifornisci(){
        serbatoio.riempi();
    }

    public  void atterra(){
        einvolo = false;
        for(int i = 0; i< 4;i++){
            turbine.get(i).Disabilita();
        }
    }

    //Metodo di Controllo che controlla lo stato delle turbine e nel caso 3 o più turbine siano
    //non funzionanti l'aereo precipita
    public boolean ControllaTurbine(){
        boolean ris = true;
        int n = 0;
        for(int i = 0; i<4;i++){
            if(!turbine.get(i).funzionante){
                n++;
            }
        }
        if(n>2){
            ris = false;
        }
        return ris;
    }

    public void CambiaStatoMaltempo(){
        maltempo = !maltempo;
    }
    //Feature Alessio Campagnaro, l'aereo per colpa di uno sciopera partirà in ritardo
    public boolean sciopero(){
        Random r = new Random();
        int i = r.nextInt(100);
        if(i==69){
            return true;
        }
        else{return false;}
    }

    public int Get_ID(){
        return this.id;
    }
    public int Get_Posizione(){
        return this.posizione;
    }
    public boolean Get_Stato_Aereo(){
        return this.einvolo;
    }
    public Stiva Get_Stiva(){
        return this.stiva;
    }
    public ArrayList<Turbina> Get_Turbine(){
        return this.turbine;
    }
    public String Get_Destinazione(){
        return this.destinazione;
    }
    public Gate Get_Gate(){
        return this.gate;
    }
    public ScatolaNera Get_Scatola_Nera(){
        return this.scatolaNera;
    }
    public Serbatoio Get_Serbatoio(){
        return this.serbatoio;
    }

    public void ImbarcaPasseggieri() {
        Imbarca(entrata.GetsalitiDavanti());
        Imbarca(entrata.GetsalitiDietro());
    }

    public void Imbarca(Coda<F_Turista> c) {

        for (int i = 0; i < c.size(); i++) {
            F_Turista t = c.pop();


            matricePostiAereo[t.posto_colonna][t.posto_riga] = t;

        }
    }

    public Coda<F_Turista> FaiScendere() {

        Coda<F_Turista> coda = new Coda<F_Turista>();
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 10; r++) {
                if (matricePostiAereo[c][r] != null) {
                    coda.push(matricePostiAereo[c][r]);
                    matricePostiAereo[c][r] = null;
                }

            }
        }
        return coda;
    }

    public Entrata getEntrata(){
        return entrata;
    }

    public Uscita getUscita(){
        return uscita;
    }

}






