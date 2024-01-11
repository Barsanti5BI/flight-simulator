package Persona;

import java.util.List;

public class Turista extends Persona{
    private List<Bagaglio> listaBagagli;
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

    public List<Bagaglio> GetBagagli(){
        return listaBagagli;
    }
}
