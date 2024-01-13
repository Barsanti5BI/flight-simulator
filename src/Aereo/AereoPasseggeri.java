package Aereo;

import java.util.ArrayList;


public class AereoPasseggeri extends Aereo{


    private Turista[][] matricePostiAereo;
    private int nPosti;
    private Entrata entrata;


    public AereoPasseggeri(String nome,ArrayList<Pilota> piloti){
        super(nome, piloti);
        matricePostiAereo = new Turista[4][10];
        nPosti = 40;
        entrata = new Entrata();

    }

    public void ImbarcaPasseggieri(){
        Imbarca(entrata.GetsalitiDavanti);
        Imbarca(entrata.GetsalitiDavanti);
    }

    public void Imbarca(Coda<Turista> c){
        for (int i=0;i<c.size();i++){
            Turista t = c.pop();
            matricePostiAereo[t.colonna][t.riga] = t;

        }


        }
    }
}
    
