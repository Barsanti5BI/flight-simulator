package Persona;

import Aereoporto.ZonaCheckIn.CartaImbarco;
import Aereoporto.ZonaCheckIn.ZonaCheckIn;
import Aereoporto.ZonaControlli.ZonaControlli;

import java.util.List;

public class Turista extends Persona{

    //ZONA CHECKIN
    public ZonaCheckIn zonaCheckIn;
    public  boolean deveFareCheckIn;
    public boolean devePrendereBiglietto;
    public  boolean devePoggiareBagagliAlCheckIn;

    //ZONA CONTROLLI
    public ZonaControlli zonaControlli;
    public boolean devePoggiareBagagliAiControlli;
    public boolean deveFareControlliAlMetalDetector;
    public  boolean deveRitirareBagli;
    public  boolean controlliFattiConSuccesso;

    private Bagaglio bag;
    private CartaImbarco cartaImbarco;
    private String destinazione;
    private List<Oggetto> oggetti;
    private Documento doc;
    

    public Turista(Documento doc,Bagaglio bag, CartaImbarco cartaImbarco, List<Oggetto> oggetti) {
        this.bag = bag;
        this.cartaImbarco = cartaImbarco;
        this.oggetti = oggetti;
        this.doc = doc;
    }

    public void run(){
    }

    public String getDestinazione(){
        return destinazione;
    }

    public Bagaglio getBag() {
        return bag;
    }

    public List<Oggetto> GetListaOggetti()
    {
        return oggetti;
    }
    public Documento getDoc(){
        return doc;
    }

    public CartaImbarco GetCartaImbarco(){return cartaImbarco;}
    public void setCartaImbarco(CartaImbarco c) { cartaImbarco = c; }
}
