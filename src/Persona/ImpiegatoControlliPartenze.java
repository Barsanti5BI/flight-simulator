package Persona;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class ImpiegatoControlliPartenze extends Persona{

    private List<String> OggettiProibiti = new ArrayList<>();
    public ImpiegatoControlliPartenze(Documento doc){
        super(doc);

        //Lista oggetti proibiti dell'imbarco:

        OggettiProibiti.add("Dinamite");
        OggettiProibiti.add("Pistola");
        OggettiProibiti.add("Granata");
        OggettiProibiti.add("Coltello");
        OggettiProibiti.add("Pugnale");
        OggettiProibiti.add("Polvere da sparo");
        OggettiProibiti.add("Bottiglia d'acqua");
        OggettiProibiti.add("Sostanza allucinogena");
        OggettiProibiti.add("Oggetto metallico");
    }


    public void run(){

    }
    public boolean ControlloBagaglio(Bagaglio bag){
        Boolean BagaglioSicuro = true;

        for(int i = 0; i < bag.getOggettiContenuti().size(); i++)
        {
            for(int j = 0; j < OggettiProibiti.size(); j++)
            {
                if(bag.getOggettiContenuti().get(i) == OggettiProibiti.get(j))
                {
                    System.out.println("Oggetto proibito trovato, il cliente non potrà salire sull'aereo");
                    BagaglioSicuro = false;
                }
            }
        }
        if(BagaglioSicuro)
        {
            if (bag.getPeso() < 15)
            {
                System.out.println("Il bagaglio è sicuro, il passeggero potrà salire sull'aereo");
                BagaglioSicuro = true;
            }
            else
            {
                BagaglioSicuro = false;
            }
        }
        return BagaglioSicuro;
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
