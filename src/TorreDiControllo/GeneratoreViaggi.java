package TorreDiControllo;

import Aereo.*;
import Persona.*;
import Aereoporto.*;

import java.util.Random;

public class GeneratoreViaggi {

    private Random random;
    private String[] destinazioni =
            {
                    "Roma",
                    "Parigi",
                    "New York",
                    "Tokyo",
                    "Barcellona",
                    "Londra",
                    "Berlino",
                    "Sydney",
                    "Rio de Janeiro",
                    "Dubai",
                    "Città del Messico",
                    "Toronto",
                    "Hong Kong",
                    "Cape Town",
                    "Bangkok",
                    "Amsterdam",
                    "Singapore",
                    "San Francisco",
                    "Seoul",
                    "Mosca"
            };
    private Aereo[] aerei;
    private Pilota[] piloti;
    private int numGate;

    public GeneratoreViaggi(Aereo[] a, Pilota[] p)
    {
        aerei = a;
        piloti = p;
        random = new Random();
    }

    public Viaggio GeneraViaggioInUscita(Aereo a, Pilota p, int nGate)
    {
        return new Viaggio(destinazioni[random.nextInt(destinazioni.length)], a, p, true, nGate);
    }

    public Viaggio GeneraViatggioInEntrata(Aereo a, Pilota p, int nGate)
    {
        return new Viaggio("da " + destinazioni[random.nextInt(destinazioni.length)], a, p, false, nGate);
    }

    public Viaggio GeneraViaggioRandom()
    {
        if(random.nextBoolean())
        {
            return new Viaggio(destinazioni[random.nextInt(destinazioni.length)], aerei[random.nextInt(aerei.length)], piloti[random.nextInt(piloti.length)], random.nextBoolean(), random.nextInt(numGate));
        }
        else
        {
            return new Viaggio("da " + destinazioni[random.nextInt(destinazioni.length)], aerei[random.nextInt(aerei.length)], piloti[random.nextInt(piloti.length)], random.nextBoolean(), random.nextInt(numGate));
        }
    }
}
