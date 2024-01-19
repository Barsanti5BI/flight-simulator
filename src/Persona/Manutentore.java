package Persona;

import Aereo.Aereo;

public class Manutentore{
    public Aereo a;
    public String nome;

    public Manutentore(Aereo a, String nome){
        this.a = a;
        this.nome = nome;
    }

    public void Manutenzione()
    {
        System.out.println("Il manutentore " + nome + " sta per controllare lo stato dell'aereo " + a.Get_ID());
        ControlloStatiApparecchiature();
        ControlloCarburante();
        System.out.println("Il manutentore " + nome + " ha finito il suo compito per l'aereo " + a.Get_ID());
    }

    public void ControlloStatiApparecchiature(){
        a.Ripara();
    }
    public void ControlloCarburante(){
        a.Rifornisci();
    }
}
