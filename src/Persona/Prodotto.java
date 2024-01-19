package Persona;

public class Prodotto {
    private String nome;
    private double prezzo;

    public Prodotto(String n, double p){
        this.nome = n;
        this.prezzo = p;
    }

    public String getNome(){
        return nome;
    }

    public double getPrezzo(){
        return prezzo;
    }
}
