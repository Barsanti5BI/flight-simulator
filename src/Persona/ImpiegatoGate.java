package Persona;
import Aereoporto.ZonaPartenze.Gate;
import Utils.Coda;
import java.util.List;

public class ImpiegatoGate extends Persona {
    Coda<Turista>codaPrioritaria;
    Coda<Turista>codaNormale;
    Gate gate;

    public ImpiegatoGate(Gate gate){
        codaPrioritaria = new Coda<Turista>();
        codaNormale = new Coda<Turista>();
        this.gate = gate;
    }

    public void run(){

    }

    public void GestisciClienti(){
        while(codaPrioritaria.size() >0){
            Turista t = codaPrioritaria.pop();
            //if(ControllaCartaImbarco(t)){push}
            //else{}
        }
        while(codaNormale.size() >0){
            Turista t = codaNormale.pop();
            //if(ControllaCartaImbarco(t)){push}
            //else{}
        }
    }

//    public boolean ControllaCartaImbarco(Turista t){
//        return t.GetCartaImbarco().GetGate() == Gate.id
//    }

    //volo carta imbraco = volo gate
}
