package Persona;

import Aereoporto.ZonaCheckIn.CartaImbarco;
import java.util.List;

public class Turista extends Persona{
    public boolean devePoggiareBagagliAiControlli;
    public boolean deveFareControlliAlMetalDetector;
    private Bagaglio bag;
    private CartaImbarco cartaImbarco;
    private String destinazione;
    private List<Oggetto> oggetti;
    

    public Turista(Documento doc,Bagaglio bag, CartaImbarco cartaImbarco, List<Oggetto> oggetti) {
        super(doc);
        this.bag = bag;
        this.cartaImbarco = cartaImbarco;
        this.oggetti = oggetti;
    }

    public void run(){

    }

    public List<Oggetto> GetListaOggetti()
    {
        return oggetti;
    }
}
