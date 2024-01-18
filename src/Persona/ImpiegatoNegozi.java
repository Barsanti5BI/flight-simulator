package Persona;

import java.util.*;

public class ImpiegatoNegozi extends Persona{
    private String nome;
    private int id;
    private List<Prodotto> prodottoInVendita;
    private Prodotto prezzo;
    private double importo;
    private Queue<Persona> codaClienti;

    public ImpiegatoNegozi(String n, int id, List<Prodotto> prodottoInVendita,Queue<Persona> cC){
        this.nome = n;
        this.id = id;
        this.prodottoInVendita = prodottoInVendita;
        importo = 0.0;
        this.codaClienti = new LinkedList<>();
    }

    public void run(){
        while (!codaClienti.isEmpty()){
            ImpiegatoNegozi impiegato = codaClienti.poll();
            if(impiegato != null){
                impiegato.Vendi();
            }
        }
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

    public void Vendi() {
        double sconto;
        System.out.println("Nuovo cliente entrato nel negozio: " + nome);
        if (prodottoInVendita != null && !prodottoInVendita.isEmpty()) {
            for (Prodotto prodotto : prodottoInVendita) {
                System.out.println("Prodotto venduto: " + prodotto.getNome() + " a prezzo: " + prodotto.getPrezzo());
                importo += prodotto.getPrezzo();
            }
            System.out.println("Importo totale della vendita: " + importo);
            // Sconto 20% se l'importo supera i 100 euro
            if (importo > 100.0) {
                sconto = importo * 0.20;
                importo -= sconto;
                System.out.println("Sconto applicato: " + sconto);
            }
            System.out.println("Importo totale della vendita dopo lo sconto: " + importo);
        }
        else {
            System.out.println("Nessun prodotto in vendita o lista prodotti non disponibile.");
        }
    }
}
