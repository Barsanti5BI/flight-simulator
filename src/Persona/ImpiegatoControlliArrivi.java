package Persona;

import Utils.Coda;

import java.time.LocalDate;

public class ImpiegatoControlliArrivi extends Persona{

    private Coda<Turista> CodaTuristi;
    public ImpiegatoControlliArrivi(Documento doc, Coda<Turista> coda) {super(doc);}

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
    public void ControlloCartaImbarco(Documento doc){

    }
}
