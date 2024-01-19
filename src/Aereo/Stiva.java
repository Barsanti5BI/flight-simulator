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

    //Metodo che svuota l'intera stiva in una volta
    public LinkedList<Bagaglio> Svuota_Stiva(){
        LinkedList<Bagaglio> lista_bagaglio = new LinkedList<Bagaglio>();
        while (!pila_bag.isEmpty()){
            Bagaglio bag = pila_bag.pop();
            lista_bagaglio.add(bag);
        }
        return lista_bagaglio;
    }

    //Metodo che rimuove 1 bagaglio dalla stiva
    public Bagaglio Rimuovi_Bagaglio_Stiva(){
        Bagaglio b = pila_bag.pop();
        peso_attuale -= b.peso;
        return b;
    }

    //Metodo che riempie la stiva in una sola volta
    public boolean Riempi_Stiva(LinkedList<Bagaglio> lista_bag){
        for(Bagaglio b : lista_bag){
            pila_bag.push(b);
            peso_attuale += b.peso;
        }
        return true;
    }

    //Metodo che aggiunge 1 bagaglio alla volta nella stiva
    public boolean Aggiungi_Bagaglio_Stiva(Bagaglio b){
        pila_bag.push(b);
        peso_attuale += b.peso;
        return true;
    }

    public Stack<Bagaglio> Get_Stiva(){
        return this.pila_bag;
    }
    public int Get_Peso_Stiva(){
        return peso_attuale;
    }
}
