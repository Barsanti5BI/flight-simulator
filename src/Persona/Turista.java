package Persona;

public class Turista extends Persona{
    private Bagaglio bag;
    private CartaImbarco cartaImbarco;
    private String destinazione;

    public Turista(Documento doc,Bagaglio bag, CartaImbarco cartaImbarco) {
        super(doc);
        this.bag = bag;
        this.cartaImbarco = cartaImbarco;
    }

    public void run(){

    }
}
