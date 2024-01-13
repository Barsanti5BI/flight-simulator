package Persona;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImpiegatoNegozi extends Persona{
    private String nome;
    private int id;
    private List<Prodotto> prodottoInVendita;
    private Prodotto prezzo;
    private double importo;

    public ImpiegatoNegozi(Documento doc, String n, int id, List<Prodotto> prodottoInVendita){
        super(doc);
        this.nome = n;
        this.id = id;
        this.prodottoInVendita = prodottoInVendita;
    }
    
    public void run(){
        System.out.println("Nuovo cliente entrato nel negozio");
        int numeroProdottiAcquistati = new Random().nextInt(19) + 1;
        for (int i = 0; i < numeroProdottiAcquistati; i++) {
            List<Prodotto> prodottiDisponibili = getProdottiDisponibili();
            if (!prodottiDisponibili.isEmpty()) {
                Prodotto prodottoScelto = prodottiDisponibili.get(new Random().nextInt(prodottiDisponibili.size()));
                System.out.println("Prodotto/i venduto/i: " + prodottoScelto.getNome());
                System.out.println("Prezzo del prodotto: " + prodottoScelto.getPrezzo());
                importo = prezzo.getPrezzo() + prodottoScelto.getPrezzo();
                System.out.println("Importo parziale della vendita: " + importo);
            }
        }
        System.out.println("Importo totale della vendita: " + importo);
    }

    private List<Prodotto> getProdottiDisponibili() {
        List<Prodotto> prodotti = new ArrayList<Prodotto>();
        prodotti.add(new Prodotto("Calzini", 10.0));
        prodotti.add(new Prodotto("Tazza", 20.0));
        prodotti.add(new Prodotto("Sacchetto cioccolatini", 15.0));
        prodotti.add(new Prodotto("Bottiglia alcol", 25.0));
        prodotti.add(new Prodotto("T-Shirt", 30.0));
        prodotti.add(new Prodotto("Cuscino per aereo", 35.0));
        prodotti.add(new Prodotto("Coperta", 40.0));
        prodotti.add(new Prodotto("Camicia", 45.0));
        prodotti.add(new Prodotto("Felpa", 50.0));
        prodotti.add(new Prodotto("Set calamite", 55.0));
        prodotti.add(new Prodotto("Quadro", 60.0));
        prodotti.add(new Prodotto("Auricolari", 65.0));
        prodotti.add(new Prodotto("Adattatore", 70.0));
        prodotti.add(new Prodotto("Mouse", 75.0));
        prodotti.add(new Prodotto("Cuffie", 80.0));
        prodotti.add(new Prodotto("Giocattolo", 85.0));
        prodotti.add(new Prodotto("Scarpe", 90.0));
        prodotti.add(new Prodotto("Lego", 95.0));
        prodotti.add(new Prodotto("Occhiali da sole", 100.0));
        return prodotti;
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
