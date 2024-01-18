package Aereo;
//aereo deve diventare un thread che entra in un ciclo per un determinato periodo di tempo e nel frattempo il serbatoio inizia a svuotarsi

public class Stiva {
    Stack<Bagaglio> pila_bag;
    int peso_attuale;
    int perc_riempimento;
    AereoPasseggeri aereo;
    public Stiva(Aereo aereo_pass){
        pila_bag = new Stack<Bagaglio>();
        perc_riempimento = 0;
        peso_attuale = 0;
        this.aereo = (AereoPasseggeri) aereo_pass;
    }
}
