package Persona;

public class Turista extends Persona{
    private Bagaglio bag;
    private String cartaImbarco;
    private String destinazione;

    public Turista(Documento doc,Bagaglio bag, String cartaImbarco) {
        super(doc);
        this.bag = bag;
        this.cartaImbarco = cartaImbarco;
    }

    public void run(){

    }
}
