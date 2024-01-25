package Aereo;

import Utils.Coda;

import java.util.ArrayList;


public class AereoPasseggeri extends Aereo {
    private F_Turista[][] matricePostiAereo;
    private int nPosti;
    private Entrata entrata;
    private Uscita uscita;

    public AereoPasseggeri(int id) {
        super(id);
        matricePostiAereo = new F_Turista[4][10];
        nPosti = 40;
        entrata = new Entrata();
        uscita = new Uscita(this);
    }

    public void run(){
        avvia();
        super.run();
    }

    public void ImbarcaPasseggieri() {
        Imbarca(entrata.GetsalitiDavanti());
        Imbarca(entrata.GetsalitiDietro());
    }

    public void Imbarca(Coda<F_Turista> c) {
        //fixare
        //1° modo creiamo carta di imbarco fake
        //2° modo non la creiamo e i valori li mettiamo dentro il F_Turista
        for (int i = 0; i < c.size(); i++) {
            F_Turista t = c.pop();
            String[] posto = t.GetCartaImbarco().getCodicePosto().split(",");

            int riga = Integer.parseInt(posto[0]);
            int colonna =Integer.parseInt(posto[1]);
            matricePostiAereo[colonna][riga] = t;

        }
    }

    public Coda<F_Turista> FaiScendere() {

        Coda<F_Turista> coda = new Coda<F_Turista>();
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 10; r++) {
                if (matricePostiAereo[c][r] != null) {
                    coda.push(matricePostiAereo[c][r]);
                    matricePostiAereo[c][r] = null;
                }

            }
        }
        return coda;
    }

    public Entrata getEntrata(){
        return entrata;
    }

    public Uscita getUscita(){
        return uscita;
    }


    public void avvia() {
        super.avvia();
        ImbarcaPasseggieri();
    }
}
    
