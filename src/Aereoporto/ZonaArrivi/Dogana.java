package Aereoporto.ZonaArrivi;

import Persona.ImpiegatoControlliArrivi;
import Persona.Turista;
import Utils.Coda;

public class Dogana {
   ImpiegatoControlliArrivi controllore;
   Coda<Turista> codaTuristiDaControllare;
   Coda<Turista> codaTuristiControllatiBuoni;

    public Dogana() {
        codaTuristiDaControllare = new Coda<>();
        codaTuristiControllatiBuoni = new Coda<>();
        controllore = new ImpiegatoControlliArrivi(codaTuristiDaControllare, codaTuristiControllatiBuoni);
    }

    public ImpiegatoControlliArrivi getControllore()
    {
        return controllore;
    }
}
