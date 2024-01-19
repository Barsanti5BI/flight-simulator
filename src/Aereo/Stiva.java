package Aereo;

import java.util.LinkedList;

public class Stiva {
    Stack<Bagaglio> pila_bag;
    int peso_attuale;
    AereoPasseggeri aereo;
    public Stiva(Aereo aereo_pass){
        pila_bag = new Stack<Bagaglio>();
        peso_attuale = 0;
        this.aereo = (AereoPasseggeri) aereo_pass;
    }

    public LinkedList<Bagaglio> Svuota_Stiva(){
        LinkedList<Bagaglio> lista_bagaglio = new LinkedList<Bagaglio>();
        while (!pila_bag.isEmpty()){
            Bagaglio bag = pila_bag.pop();
            lista_bagaglio.add(bag);
        }
        return lista_bagaglio;
    }

    public boolean Riempi_Stiva(LinkedList<Bagaglio> lista_bag){
        for(Bagaglio b : lista_bag){
            pila_bag.push(b);
            peso_attuale += b.peso;
        }
        return true;
    }

    public boolean Aggiungi_Bagaglio_Stiva(Bagaglio b){
        pila_bag.push(b);
        peso_attuale += b.peso;
        return true;
    }
}
