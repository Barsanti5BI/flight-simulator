import java.util.ArrayList;

public class AereoMerci extends Aereo{

    public String descrizione;

     public AereoMerci(String nome,ArrayList<Pilota> piloti,String descrizione){
        super(nome, piloti);
        this.descrizione = descrizione;
    }
    
}
