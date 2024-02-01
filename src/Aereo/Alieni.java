package Aereo;

import java.util.Random;

//Feature Riccardo Pettenuzzo
public class Alieni extends Thread{
    private boolean aereo_rubato;
    public Alieni(){
        setDaemon(true);
        aereo_rubato = false;
    }

    public void run(){
        while (true){
            try {
                if(Aereo_Rubato_Alieni()) {
                    aereo_rubato = true;
                    break;
                }
                this.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("(ALIENI) HIHIHIHA!! VI ABBIAMO RUBATO!!!");
    }

    public boolean Aereo_Rubato_Alieni(){
        boolean risultato = false;
        Random rnd = new Random();
        int rand = rnd.nextInt(0, 100);
        if(rand == 69){
            risultato = true;
        }
        return  risultato;
    }

    public boolean Get_Aereo(){return this.aereo_rubato;}
}
