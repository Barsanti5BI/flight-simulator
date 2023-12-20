package Persona;

import Aereoporto.ZonaControlli.MetalDetector;
import Aereoporto.ZonaControlli.Scanner;
import Aereoporto.ZonaControlli.Settore;

import java.util.ArrayList;

public class Turista {
    public ArrayList<Bagaglio> bagagli;

    // condizioni per la zona controlli
    public Settore settoreControlli;
    public boolean deveFareControlliAlMetalDetector;
    public boolean devePoggiareBagagliAiControlli;


}
