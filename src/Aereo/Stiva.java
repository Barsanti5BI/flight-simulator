package Aereo;

import Utils.Stack;

import java.util.LinkedList;

public class Stiva {
    private Stack<Bagaglio> pila_bag;
    private int peso_attuale;
    private Aereo aereo;
    public Stiva(Aereo a){
        pila_bag = new Stack<Bagaglio>();
        peso_attuale = 0;
        this.aereo = a;
    }

    //Metodo che svuota l'intera stiva in una volta
    public LinkedList<Bagaglio> Svuota_Stiva(){
        LinkedList<Bagaglio> lista_bagaglio = new LinkedList<Bagaglio>();
        while (!pila_bag.isEmpty()){
            Bagaglio bag = pila_bag.pop();
            lista_bagaglio.add(bag);
        }
        System.out.println("(STV)   Bagagli rimossi dalla stiva, stiva vuota.");
        return lista_bagaglio;
    }

    //Metodo che rimuove 1 bagaglio dalla stiva
    public Bagaglio Rimuovi_Bagaglio_Stiva(){
        Bagaglio b = pila_bag.pop();
        peso_attuale -= b.get_peso();
        if(peso_attuale == 0){
            System.out.println("(STV)  Bagaglio rimosso dalla stiva, stiva vuota.");
        }
        else{
            System.out.println("(STV)  Bagaglio rimosso dalla stiva.");
        }
        return b;
    }

    //Metodo che riempie la stiva in una sola volta
    public boolean Riempi_Stiva(LinkedList<Bagaglio> lista_bag){
        for(Bagaglio b : lista_bag){
            pila_bag.push(b);
            peso_attuale += b.get_peso();
        }
        System.out.println("(STV)  Bagagli inseriti nella stiva, stiva piena");
        return true;
    }

    //Metodo che aggiunge 1 bagaglio alla volta nella stiva
    public boolean Aggiungi_Bagaglio_Stiva(Bagaglio b){
        pila_bag.push(b);
        peso_attuale += b.get_peso();
        System.out.println("(STV)  Bagaglio inserito nella stiva.");
        return true;
    }

    public Stack<Bagaglio> Get_Stiva(){
        return this.pila_bag;
    }
    public int Get_Peso_Stiva(){
        return peso_attuale;
    }
}
