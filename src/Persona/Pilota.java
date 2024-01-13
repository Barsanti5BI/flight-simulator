package Persona;

public class Pilota extends Persona{
    public Aereo a;
    public TorreControllo tC;
    public Pista p;
    public Pilota(Documento doc, Aereo a, TorreControllo tC){
        super(doc);
        this.a = a;
        this.tC = tC;
        p = null;
    }
    public void run(){
        while(true)
        {
            if(comunicaConTorre())
            {
                a.InVolo = true;
                break;
            }
        }
    }

    public boolean comunicaConTorre()
    {
       //mi metto in wait sulla torreDiControllo
        // mi da ok
        // mi dai pista
        

        return true;
    }

    public void daiInfo(String azione)
    {
        a.InsersciDati(azione);
    }
}
