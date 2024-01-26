package Aereo;
//il serbartoio viene svuotato ogni tot di tempo mentre l'aereo è in volo

public class Serbatoio {
    private int capacità;

    public Serbatoio(){
        capacità = 100;
    }

    public void riempi(){
        capacità = 100;
    }

    public void consuma_carburante(){
        if(capacità>0){
            capacità--;
            if(capacità == 75){
                System.out.println("Carburante attuale = " + Get_Capacità_Serbatoio() + ".");
            }
            else if(capacità == 50){
                System.out.println("Carburante attuale = " + Get_Capacità_Serbatoio() + ".");
            }
            else if(capacità == 25){
                System.out.println("Carburante attuale = " + Get_Capacità_Serbatoio() + ".");
            }
        }
        else{
            System.out.println("Carburante nell'aereo terminato!");
        }
    }

    public int Get_Capacità_Serbatoio(){
        return capacità;
    }

}
