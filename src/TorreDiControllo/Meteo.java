package TorreDiControllo;
import java.util.Random;
import Aereo.*;
import Persona.*;
import Aereoporto.*;
public class Meteo extends Thread
{

    private Boolean fine;

    private int tempo;
    private Random random;
    //numero generato dal random, in un intervallo da 0 a 100
    private int numeroMeteo;
    private int numeroNome;

    //range in cui c'è maltempo, l'aereo non può partire
    private int maltempoMin;
    private int maltempoMax;

    //variabile boolean che definisce se un aereo può partire
    //TRUE = può partire
    //FALSE = non può partire
    private boolean meteo;
    private String[] condizioniMeteo;

    private String[] CondizioneProibite;

    private String motivazioneTempo;

    private String[] maltempi = {
            "Pioggia leggera",
            "Nebbia",
            "Temporale",
            "Pioggia intensa",
            "Neve leggera",
            "Vento forte",
            "Pioggia moderata",
            "Neve intensa",
            "Nuvoloso",
            "Grandine",
            "Temporale con fulmini",
            "Fresco e ventoso",
            "Nevischio",
            "Pioggia torrenziale",
            "Neve fitta"
    };

    private String[] buontempi = {
            "Soleggiato",
            "Sereno",
            "Nuvoloso",
            "Sereno con nuvole sparse"
    };

    public Meteo()
    {
        this.fine = false;
        random = new Random();
        maltempoMin = 78;
        maltempoMax = 85;
        meteo = true;


        CondizioneProibite = new String[]{"vento forte", "nebbia densa", "tempesta", "tornado", "bufera di neve", "ghiaccio", "pioggia di catinelle"};
        motivazioneTempo = "";
    }
    @Override
    public  void run()
    {
        while(!fine)
        {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            CambiaMeteo();
        }
    }

    //questo meteodo genera un numero random e controlla se il numero generato
    //è all'interno del range del maltempo.
    //SE SI = meteo è falso, quindi l'aereo non può partire
    //SE NO = meteo rimane true come da costruttore, quindi l'aereo può partire
    public void CambiaMeteo() {
        int maltempoONo = GeneraNumeroTempo();
        if(maltempoONo < 30)
        {
            motivazioneTempo = maltempi[GeneraNumeroMaltempo()];
            meteo = false;
        }
        else
        {
            motivazioneTempo = buontempi[GeneraNumeroBuonTempo()];
            meteo = true;
        }
    }

    public String DammiMotivazione() {
        return motivazioneTempo;
    }

    public int GeneraNumeroTempo() {
        return random.nextInt(100);
    }

    public int GeneraNumeroMaltempo() {
        return random.nextInt(14);
    }

    public int GeneraNumeroBuonTempo()
    {
        return random.nextInt(4);
    }

    public  boolean DammiMeteoAttuale(){
        return meteo;
    }
}