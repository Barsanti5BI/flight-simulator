package Persona;

import Utils.Coda;

import java.util.List;

public class ImpiegatoNegozi extends Persona{
    private int idNegozio;
    private List<Prodotto> prodottoInVendita;
    private double importo;
    private Coda<Turista> turisti;

    public ImpiegatoNegozi(String n, int id, List<Prodotto> prodottoInVendita, Coda<Turista> turisti){
        setName(n);
        this.idNegozio = id;
        this.prodottoInVendita = prodottoInVendita;
        this.turisti = turisti;
    }

    public void run(){
        while(true)
        {
            if (!turisti.isEmpty())
            {
                Turista turista = turisti.pop();
                System.out.println("L'impiegato dei negozi " + getName() + " sta servendo il turista " + turista.getName());

                Vendi(turista);
                synchronized (turista)
                {
                    turista.notify();
                }
            }
            else
            {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //Feauture Squizzato ----> impiegato a fine giornta fa le statistiche della giornata
    //                         incasso totale, totale degli sconti se fatti,
    //                         totale dei prodotti venduti
    public void Vendi(Turista turista) {
        double sconto;
        // Calcola l'importo basato sugli oggetti selezionati dal turista
        for (Prodotto prodotto : turista.oggettiDaComprare) {
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
}
