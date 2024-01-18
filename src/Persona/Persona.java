package Persona;

import Utils.Coda;

public class Persona extends Thread{
    public void run(){
    }
    public void InserimentoCoda(Coda coda){
        coda.push(this);
    }
    public void PopCoda(Coda coda){
        coda.pop();
    }


}