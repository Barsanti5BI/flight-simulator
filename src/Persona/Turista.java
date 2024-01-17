package Persona;

import Aereoporto.ZonaCheckIn.CartaImbarco;
import Aereoporto.ZonaCheckIn.ZonaCheckIn;
import Aereoporto.ZonaControlli.ZonaControlli;
import Aereoporto.ZonaNegozi.Negozio;
import Aereoporto.ZonaNegozi.ZonaNegozi;

import java.util.List;

public class Turista extends Persona{

    //ZONA CHECKIN
    public ZonaCheckIn zonaCheckIn;
    public int indiceSettore;
    public  boolean deveFareCheckIn;
    public boolean devePrendereBiglietto;
    public  boolean devePoggiareBagagliAlCheckIn;
    private CartaImbarco cartaImbarco;

    //ZONA CONTROLLI
    public ZonaControlli zonaControlli;
    public int IndiceSettore;
    public boolean deveFareControlli;
    public boolean devePoggiareBagagliAiControlli;
    public boolean deveFareControlliAlMetalDetector;
    public  boolean deveRitirareBagli;
    public  boolean controlliFattiConSuccesso;

    //ZONA NEGLOZI
    public ZonaNegozi zonaNegozi;
    public int indiceNegozio;
    public  boolean vuoleFareAcquisto;
    private Bagaglio bag;
    //ZONA PARTENZE


    private String destinazione;
    private List<Oggetto> oggetti;
    private Documento doc;
    

    public Turista(Documento doc,Bagaglio bag,  CartaImbarco cartaImbarco, List<Oggetto> oggetti) {
        this.bag = bag;
        this.cartaImbarco = cartaImbarco;
        this.oggetti = oggetti;
        this.doc = doc;
    }

    public void run(){
        while (true){
            try{
                if(deveFareCheckIn) {
                    if (devePrendereBiglietto) {
                        //ottieni biglietto
                        if (devePoggiareBagagliAlCheckIn) {
                            //controllo e spèostamento bagagli sul nastro trasportatore
                            devePoggiareBagagliAlCheckIn = false;
                        }
                        devePrendereBiglietto = false;
                    }
                    deveFareCheckIn = false;
                    deveFareControlli = true;
                }
                if (deveFareControlli){
                    //continuare
                }
                Thread.sleep(2);
            }
            catch (InterruptedException ex){
                System.out.println(ex);
            }

        }

    }

    public String getDestinazione(){
        return destinazione;
    }

    public Bagaglio GetBagaglio() {
        return bag;
    }

    public Bagaglio DaiBagaglio() {
        Bagaglio b = bag;
        bag = null;
        return b;
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
