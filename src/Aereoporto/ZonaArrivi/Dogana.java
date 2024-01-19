package Aereoporto.ZonaArrivi;

import Persona.ImpiegatoControlliArrivi;
import Persona.Turista;
import Utils.Coda;

import java.util.Random;

public class Dogana {
   ImpiegatoControlliArrivi controllore;
   Coda<Turista> codaTuristiDaControllare;
   Coda<Turista> codaTuristiControllatiBuoni;

   Random rand;

    public Dogana() {
        rand = new Random();
        codaTuristiDaControllare = new Coda<>();
        codaTuristiControllatiBuoni = new Coda<>();
        controllore = new ImpiegatoControlliArrivi(codaTuristiDaControllare, codaTuristiControllatiBuoni, rand.nextInt(0, 1000));
    }

    public ImpiegatoControlliArrivi getControllore()
    {
        return controllore;
    }
}
