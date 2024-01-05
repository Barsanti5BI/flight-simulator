package Persona;

import Utils.Coda;

public class Persona extends Thread{
    private Documento doc;
    public Persona(Documento doc){
        this.doc = doc;
    }

    public void run(){
    }
    public void InserimentoCoda(Coda coda){
        coda.push(this);
    }
    public void PopCoda(Coda coda){
        coda.pop();
    }
}