package Persona.untitled.src.main.java;

import java.time.LocalDate;

public class ImpiegatoControlliPartenze extends Persona{
    public ImpiegatoControlliPartenze(Documento doc){
        super(doc);
    }
    public void run(){

    }
    public boolean ControlloBagaglio(Bagaglio bag){
        if (bag.getPeso() < 15)
        {
            return true;
        }
        else
        {
            return false;
        }
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
