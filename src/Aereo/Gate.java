package Aereo;
import Utils.Coda;

public class Gate extends Thread{
    String nomeGate;
    String nomeAereo;
    Coda<Turista> codaPrioritaria;
    Coda<Turista> codaNormale;
    Boolean TerminatiIControlli;
    Coda<Turista> codaNavetta;
    public Gate(Coda<Turista> coda, String nomeAereo){
        try{
            codaPrioritaria = new Coda<>();
            codaNormale = new Coda<>();
            codaNavetta = new Coda<>();
            while(!coda.isEmpty()){
                Turista t = coda.pop();
                if(t.tipoCoda.equals("Prioritaria")){
                    codaPrioritaria.push(t);
                    sleep(1000);
                    System.out.println("Il turista " + t.Nome + " è entrato nella coda prioritaria");
                }
                else{
                    codaNormale.push(t);
                    sleep(1000);
                    System.out.println("Il turista " + t.Nome + " è entrato nella coda normale");
                }
            }
            this.nomeAereo = nomeAereo;
            TerminatiIControlli = false;
        }catch (InterruptedException ex){
            System.out.println(ex);
        }

    }
    public void run(){
        while (!codaPrioritaria.isEmpty()){
            Turista t = codaPrioritaria.pop();
            EffettuaControllo(t);
            codaNavetta.push(t);
        }
        while (!codaNormale.isEmpty()){
            Turista t = codaNormale.pop();
            EffettuaControllo(t);
            codaNavetta.push(t);
        }
        TerminatiIControlli = true;
    }
    public Boolean TerminatiIControlli(){
        return TerminatiIControlli;
    }
    public void EffettuaControllo(Turista t){
        try{
            if(nomeAereo.equals(t.nomeAereo)){
                sleep(1000);
                System.out.println("    Il turista " + t.Nome + " ha effettuato il controllo effettuato");
            }
            else{
                sleep(1000);
                System.out.println("    Il turista " + t.Nome + " ha sbagliato gate");
            }
        }catch (InterruptedException ex){
            System.out.println(ex);
        }
    }

    public Coda<Turista> getCodaNavetta() {
        return codaNavetta;
    }
}
