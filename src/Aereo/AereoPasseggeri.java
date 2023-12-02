import java.util.ArrayList;

public class AereoPasseggeri extends Aereo{

    public int numeroPasseggeri;
    private Turista[][] matricePostiAereo;
    private int nPosti;

    public AereoPasseggeri(String nome,ArrayList<Pilota> piloti,int fileAereo,int righeAereo,int numeroPasseggeri){
        super(nome, piloti);
        matricePostiAereo = new Turista[fileAereo][righeAereo];
        nPosti = fileAereo*righeAereo;
        this.numeroPasseggeri = numeroPasseggeri;

    }
}
    
