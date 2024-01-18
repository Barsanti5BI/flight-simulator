package Aereo;

import java.util.Random;

public class Aereo {
    private int carburante;
    private int ID;

    public Aereo(int id)
    {
        this.ID = id;
        Random r = new Random();
        carburante = r.nextInt(0,100);
    }

    public int DammiCarburante()
    {
        return carburante;
    }
    public  int DammiID(){
        return  ID;
    }
}
