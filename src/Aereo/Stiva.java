package Aereo;

public class Stiva {
    Stack<Bagaglio> pila_bag;
    int peso_max;
    int peso_attuale;
    int perc_riempimento;
    Aereo aereo;
    public Stiva(Aereo aereo){
        pila_bag = new Stack<Bagaglio>();
        perc_riempimento = 0;
        peso_attuale = 0;
        this.aereo = aereo;

    }


}
