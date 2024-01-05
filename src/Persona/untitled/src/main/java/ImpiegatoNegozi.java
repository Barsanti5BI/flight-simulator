package Persona.untitled.src.main.java;

import java.util.List;

public class ImpiegatoNegozi extends Persona{
    private String nome;
    private int id;
    private List<Prodotto> prodottoInVendita;
    private Prodotto prezzo;

    public ImpiegatoNegozi(Documento doc, String n, int id, List<Prodotto> prodottoInVendita){
        super(doc);
        this.nome = n;
        this.id = id;
        this.prodottoInVendita = prodottoInVendita;
    }
    public void run(){

    }

    //Topic Squizzato -> superato un determinato importo applicare uno sconto
    public void Vendi(){
        if(prodottoInVendita != null && !prodottoInVendita.isEmpty()){
            double importo = prezzo.getPrezzo();
            for (Prodotto prodotto : prodottoInVendita) {
                System.out.println("Prodotto venduto: " + prodotto.getNome());
                System.out.println("Prezzo: " + prodotto.getPrezzo());
                importo += prodotto.getPrezzo();
            }
            System.out.println("Importo totale della vendita: " + importo);
        }
        else {
            System.out.println("Nessun prodotto in vendita o lista prodotti non disponibile.");
        }
    }
}
