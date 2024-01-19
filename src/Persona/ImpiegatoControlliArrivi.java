package Persona;

import Utils.Coda;

import java.time.LocalDate;

public class ImpiegatoControlliArrivi extends Thread{
    private Coda<Turista> codaArrivi;
    private Coda<Turista> CodaDopoControlli;

    public ImpiegatoControlliArrivi(Coda<Turista> codaArrivi, Coda<Turista> codaDopoControlli, int id) {
        this.codaArrivi = codaArrivi;
        this.CodaDopoControlli = codaDopoControlli;
        setName(id+"");
    }

    public void run(){
        while(true)
        {
            if (!codaArrivi.isEmpty()) {
                Turista t = codaArrivi.pop();
                System.out.println("L'impiegato degli arrivi " + getName() + "sta controllando il turista " + t.getName());

                boolean controllo = ControlloDocumento(t.getDoc());

                if (controllo) //se non è scaduto
                {
                    System.out.println("Il turista" + t.getDoc().getCognome() + " " + t.getDoc().getNome() + " è passato con successo");
                    t.esitoControlli = true;
                } else {
                    System.out.println("Attenzione, turista " + t.getDoc().getCognome() + " " + t.getDoc().getNome() + " non è ammesso nel paese poichè ha il documento scaduto");
                    t.esitoControlli = false;
                }

                t.haPassatoControlliArr = true;
                t.notify();
            }
            else
            {
                try {
                    System.out.println("Impiegato controlli arrivi: \"Sto aspettando\"");
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
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
