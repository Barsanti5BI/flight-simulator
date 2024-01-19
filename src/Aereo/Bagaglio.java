package Aereo;
import Persona.Turista;

import java.util.Random;

public class Bagaglio {
    public Turista proprietario;
    public int peso;
    public boolean stato_controllo;
    public boolean bag_mano;

    public Bagaglio(Turista prop){
        Random rand = new Random();
        this.proprietario = prop;
        peso = rand.nextInt(0, 25);
        stato_controllo = false;
        if(peso > 5){
            bag_mano = false;
        }
        else{
            bag_mano = true;
        }
    }

    public String toString(){
        if(bag_mano){
            return "Bagaglio a mano di " + proprietario.id + ", peso: " + peso;
        }
        else {
            return "Bagaglio da stiva di " + proprietario.id + ", peso: " + peso;
        }
    }
}
