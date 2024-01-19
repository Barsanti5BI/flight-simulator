package Aereoporto.ZonaBagno;

import Persona.Persona;
import Utils.Coda;

public class Bagno {
   Coda<Persona> personaCoda;
   public Bagno(){
      personaCoda = new Coda<>();
   }
   public void addPersona(Persona persona){
      personaCoda.push(persona);
   }
   public Persona getPersona(){
      return personaCoda.pop();
   }
}
