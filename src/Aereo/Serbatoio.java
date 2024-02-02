package Aereo;
//il serbartoio viene svuotato ogni tot di tempo mentre l'aereo è in volo

public class Serbatoio {
    private int capacità;
    private int capacità_critica;
    private boolean carburanteTerminato;

    private int consumo_carburante;

    public Serbatoio(){
        capacità = 100;
        capacità_critica = 30;
        carburanteTerminato = false;
        consumo_carburante = 1;
    }

    public void riempi(){
        capacità = 100;
    }

    public void consuma_carburante(){
        if(capacità>0){
            capacità -= consumo_carburante;
            if(capacità == 75){
                System.out.println("(SB)Carburante attuale = " + Get_Capacità() + ".");
            }
            else if(capacità == 50){
                System.out.println("(SB)Carburante attuale = " + Get_Capacità() + ".");
            }
            else if(capacità == 25){
                System.out.println("(SB)Carburante attuale = " + Get_Capacità() + ".");
            }
        }
        else{
            System.out.println("(SB)Carburante nell'aereo terminato!");
            carburanteTerminato = true;
        }
    }

    public int Get_Capacità(){
        return capacità;
    }
    public void Set_Consumo_Carburante(int valore) {this.consumo_carburante = valore;}
    public int Get_Consumo_Carburante(){return  this.consumo_carburante;}
    public int Get_Capacità_Critica() {return capacità_critica;}
    public boolean Get_CarburanteTerminato() {return carburanteTerminato;}

}
