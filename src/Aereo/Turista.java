package Aereo;

import java.util.LinkedList;
import java.util.Random;

public class Turista {
    String nomeAereo;
    String tipoCoda;
    String Nome;
    String Posto;
    int id;
    LinkedList<Bagaglio> lista_bagagli;
    public Turista(String tipoCoda, String Nome, String Posto,String nomeAereo, int id_tur){
        Random rand = new Random();
        this.tipoCoda = tipoCoda;
        this.Nome = Nome;
        this.Posto = Posto;
        this.nomeAereo = nomeAereo;
        this.id = id_tur;
        for(int i = 1; i <= rand.nextInt(1, 3); i++){
            lista_bagagli.add(new Bagaglio(this));
        }
    }
}
