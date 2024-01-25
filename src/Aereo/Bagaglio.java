package Aereo;
import Persona.Turista;

import java.util.Random;

public class Bagaglio {
    public int Turista_id;
    public int peso;
    public boolean stato_controllo;

    public Bagaglio(int turista_id){
        Random rand = new Random();
        this.Turista_id = turista_id;
        peso = rand.nextInt(0, 25);
        stato_controllo = true;
    }

    public int get_peso() {return this.peso;}
}
