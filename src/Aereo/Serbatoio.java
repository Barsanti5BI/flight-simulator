package Aereo;

public class Serbatoio {
    private int capacità;



    public Serbatoio(){
        capacità = 100;

    }

    public void riempi(){
        capacità = 100;
    }

    public void consuma(){

        if(capacità>0){
            capacità--;
        }

    }

    public int getStatoSerbatoio(){
        return capacità;
    }

}
