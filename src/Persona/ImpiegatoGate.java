package Persona;

import Aereoporto.ZonaPartenze.Gate;

public class ImpiegatoGate extends Persona {
    Gate gate;

    public ImpiegatoGate(Gate gate){
        this.gate = gate;
    }

    public void run(){
        while(true)
        {
            if (gate.getAperto() && !gate.getCodaPrioritaria().isEmpty() && !gate.getCodaNormale().isEmpty())
            {
                GestisciClienti();
            }
            else
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void GestisciClienti(){
        while(!gate.getCodaPrioritaria().isEmpty()){
            Turista t = gate.getCodaPrioritaria().pop();

            if(ControllaCartaImbarco(t))
            {
                gate.getCodaNavetta().push(t);
            }
        }
        while(!gate.getCodaNormale().isEmpty()){
            Turista t = gate.getCodaNormale().pop();

            if(ControllaCartaImbarco(t))
            {
                gate.getCodaNavetta().push(t);
            }
        }
    }

    public boolean ControllaCartaImbarco(Turista t){
        return t.GetCartaImbarco().getGate() == gate.getId(); // nel gate serve un id per permettere il controllo
    }
}
