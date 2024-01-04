package Persona.untitled.src.main.java;

import java.time.LocalDate;

public class ImpiegatoControlliPartenze extends Persona{
    public ImpiegatoControlliPartenze(Documento doc){
        super(doc);
    }
    public void run(){

    }
    public void ControlloBagaglio(Bagaglio bag){
    }
    public boolean ControlloPasseggero(Documento doc){
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
}
