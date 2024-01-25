package Aereo;

import java.util.Random;

public class F_Turista {
    public int id;
    public int posto_riga;
    public int posto_colonna;
    //per assegnare riga e colonna del posto scorro la matrice e quando creo i turisti gli assegno un posto
    //numero turisti creati = 2 o 3 * posti aereo

    public int id_aereo;

    public Bagaglio bagaglio;

    public F_Turista(int id, int id_ae, int p_col, int p_rig){
        this.id_aereo = id_ae;
        this.id = id;
        this.bagaglio = new Bagaglio(this.id);
        this.posto_colonna = p_col;
        this.posto_riga = p_rig;
    }

    public Bagaglio get_Bag(){
        return this.bagaglio;
    }

    public int get_posto_riga() {return this.posto_riga;}

    public int get_posto_colonna() {return this.posto_colonna;}

    public int get_id_aereo() {return this.id_aereo;}

    public int get_id() {return this.id;}
}
