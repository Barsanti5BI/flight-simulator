package Persona;

import Utils.Coda;

import java.time.LocalDate;

public class ImpiegatoControlliArrivi extends Persona{

    CartaImbarco CartaImbarco;
    private Coda<Turista> CodaTuristi;
    String caratteri = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public ImpiegatoControlliArrivi(Documento doc, Coda<Turista> coda, CartaImbarco cartaImbarco) {
        super(doc);
        this.CartaImbarco = cartaImbarco;
    ;}

    public void run(){

    }

    public boolean ControlloDocumento(Documento doc){
        LocalDate expirationDate = doc.getDataScadenza();

        if (expirationDate.isBefore(LocalDate.now()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public void ControlloCartaImbarco(CartaImbarco CartaImbarco){
        if(CartaImbarco.getIdRiconoscimentoBagaglio().length() != 15 || !CartaImbarco.getIdRiconoscimentoBagaglio().contains(caratteri))
        {
            //controllare il codice della carta d'imbarco
            //controllare che l'orario sia giusto
        }
    }
}
