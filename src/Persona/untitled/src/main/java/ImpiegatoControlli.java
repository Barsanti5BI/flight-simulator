package Persona.untitled.src.main.java;

import javax.print.Doc;
import java.time.LocalDate;

public class ImpiegatoControlli extends Persona{

    private Coda<Turista> CodaTuristi;
    public ImpiegatoControlli(Documento doc) {
        super(doc);
    }

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
