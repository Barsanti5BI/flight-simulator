package Persona;

import Utils.Coda;

import javax.naming.ldap.Control;
import java.time.LocalDate;

public class ImpiegatoControlliArrivi extends Persona{

    CartaImbarco CartaImbarco;
    private Coda<Turista> CodaTuristi;

    private Coda<Turista> CodaArrivo;
    public ImpiegatoControlliArrivi(Coda<Turista> coda, CartaImbarco cartaImbarco, Coda<Turista> codaTurista) {
        this.CartaImbarco = cartaImbarco;
        this.CodaArrivo = codaTurista;
    ;}

    public ImpiegatoControlliArrivi(Coda<Turista> coda) {CodaTuristi = coda;}


    public void run(){
        while(true)
        {
            while(CodaTuristi != null)
            {
                Turista t = CodaTuristi.pop();
                boolean controllo = ControlloDocumento(t.getDoc());

                if(controllo) //se non è scaduto
                {
                    CodaArrivo.push(t);
                    System.out.println("Il turista" + t.getDoc().getCognome() + " " + t.getDoc().getNome() + " è passato con successo");
                }
                else
                {
                    System.out.println("Attenzione, turista " + t.getDoc().getCognome() + " " + t.getDoc().getNome() + " non può entrare nel gate perché il documento non è valido");
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
    public void ControlloCartaImbarco(Documento doc){
        
    }
}
