package Aereoporto.ZonaArrivi;

import Aereoporto.Common.ZonaAeroporto;
import Persona.Turista;

public class ZonaArrivi extends ZonaAeroporto {
    Dogana dogana;
    RitiroBagagli ritiroBagagli;

    public ZonaArrivi()
    {
        dogana = new Dogana();
        ritiroBagagli = new RitiroBagagli();
    }

    public Dogana getDogana()
    {
        return dogana;
    }

    public RitiroBagagli ritiroBagagli()
    {
        return ritiroBagagli;
    }
}
