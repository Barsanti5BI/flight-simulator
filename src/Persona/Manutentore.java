package Persona;

import Aereo.Aereo;

public class Manutentore{
    public Aereo a;

    public Manutentore(Aereo a){
        this.a = a;
    }

    public void Manutenzione()
    {
        ControlloStatiApparecchiature();
        ControlloCarburante();
    }

    public void ControlloStatiApparecchiature(){
        a.Ripara();
    }
    public void ControlloCarburante(){
        a.Rifornisci();
    }
}
