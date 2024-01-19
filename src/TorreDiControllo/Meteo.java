package TorreDiControllo;
import java.util.Random;
public class Meteo extends Thread{
    public Random random;
    //numero generato dal random, in un intervallo da 0 a 100
    public int numero;

    //range in cui c'è maltempo, l'aereo non può partire
    public int maltempoMin;
    public int maltempoMax;

    public int tempo;

    //variabile boolean che definisce se un aereo può partire
    //TRUE = può partire
    //FALSE = non può partire
    public boolean meteo;
    public boolean continua;

    public Meteo()
    {
        tempo = 0;
        random = new Random();
        maltempoMin = 78;
        maltempoMax = 85;
        meteo = true;
        continua = true;
    }

    //questo meteodo genera un numero random e controlla se il numero generato
    //è all'interno del range del maltempo.
    //SE SI = meteo è falso, quindi l'aereo non può partire
    //SE NO = meteo rimane true come da costruttore, quindi l'aereo può partire
    public boolean GetMeteo()
    {
        int n = GeneraNumero();
        if (n >= maltempoMin && n <= maltempoMax) {
            meteo = false;
        }
        return meteo;
    }

    public int GeneraNumero() {
        numero = random.nextInt(100);
        return numero;
    }
}
