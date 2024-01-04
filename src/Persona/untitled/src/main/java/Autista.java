package Persona.untitled.src.main.java;

public class Autista extends Persona{
    public Autista(Documento doc){
        super(doc);
    }

    //prende turisti dal gate fino all'aereo
    public void run(){
        while(true){
            //SpostaTuristi(codaGate, codaAereo);
        }
    }
    private void SpostaTuristi(Coda<Persona>codaGate, Coda<Persona>codaAereo){
        //modificare aggiungengo capienza gate e aereo
        Persona p = codaGate.pop();
        codaAereo.push(p);
    }
}
