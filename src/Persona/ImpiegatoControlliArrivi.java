package Persona;

import Utils.Coda;

import java.time.LocalDate;

public class ImpiegatoControlliArrivi extends Persona{
    private Coda<Turista> codaArrivi;
    private Coda<Turista> CodaDopoControlli;

    public ImpiegatoControlliArrivi(Coda<Turista> codaArrivi, Coda<Turista> codaDopoControlli) {
        this.codaArrivi = codaArrivi;
        this.CodaDopoControlli = codaDopoControlli;
    }

    public void run(){
        while(true)
        {
            while(!codaArrivi.isEmpty())
            {
                Turista t = codaArrivi.pop();
                boolean controllo = ControlloDocumento(t.getDoc());

                if(controllo) //se non è scaduto
                {
                    CodaDopoControlli.push(t);
                    System.out.println("Il turista" + t.getDoc().getCognome() + " " + t.getDoc().getNome() + " è passato con successo");
                }
                else
                {
                    System.out.println("Attenzione, turista " + t.getDoc().getCognome() + " " + t.getDoc().getNome() + " non è ammesso nel paese poichè ha il documento scaduto");
                }
            }
        }
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
}
