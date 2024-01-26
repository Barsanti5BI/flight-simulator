package Aereo;

import Utils.Coda;

import java.util.ArrayList;
import java.util.Random;

public class Aereo extends  Thread {
    public int id;
    public String destinazione;
    public int posizione;
    public Gate gate;

    private Bagno bagnidavanti;

    private Bagno bagnoretro;
    private ScatolaNera scatolaNera;
    private ArrayList<Turbina> turbine;
    private Stiva stiva;
    private Serbatoio serbatoio;
    public Alieni alieni;
    public boolean einvolo;
    public boolean maltempo;
    public Random r;
    public F_Turista[][] matricePostiAereo;
    public int nPosti;
    public Entrata entrata;
    public Uscita uscita;
    public  boolean serbatoio_pieno;
    public boolean turisti_imbarcati;
    public boolean stiva_piena;
    public boolean turbine_funzionanti;
    public boolean aereo_pronto;

    public Aereo(int Id) {
        this.id = Id;
        maltempo = false;
        stiva_piena = false;
        turbine_funzionanti = false;
        turisti_imbarcati = false;
        serbatoio_pieno = false;

        bagnidavanti = new Bagno();
        bagnoretro = new Bagno();


        bagnidavanti = new Bagno();
        bagnoretro = new Bagno();

        scatolaNera = new ScatolaNera (this);

        turbine = new ArrayList<Turbina>();
        for (int i = 0; i < 4; i++) {
            Turbina n = new Turbina(this, i);
            turbine.add(n);
        }
        stiva = new Stiva(this);
        serbatoio = new Serbatoio();


        einvolo = false;

        alieni = new Alieni(this);
        //alieni.start();

        matricePostiAereo = new F_Turista[4][10];
        nPosti = 40;
        entrata = new Entrata();
        uscita = new Uscita(this);
    }


    public void run() {
        //Modificare metodo aereo nelle condizioni del while
        if (sciopero()) {
            try {

                Thread.sleep(5000);
            } catch (Exception e) {
            }
        }
        Prepara_Aereo();
        while (posizione < 100) {
            System.out.println("L'aereo è partito!");
            try {
                //Feature Riccardo Pettenuzzo
                if (alieni.aereo_rubato) {
                    break;
                }
                Thread.sleep(1000);
            } catch (Exception e) {}

            if (maltempo) {
                posizione += 1;
            } else {
                posizione += 2;
            }

           //parte per far funzionare il bagno in aereo
            Coda<F_Turista> getbagno= Givebagno();

            if(getbagno.size() % 2==0)
            {
                bagnidavanti.DareBisogno(getbagno.pop());
            }
            if( getbagno.size() % 2==1)
            {
                bagnoretro.DareBisogno(getbagno.pop());
            }

            try{
                bagnidavanti.run();

            serbatoio.consuma_carburante();

                bagnoretro.run();
                while (bagnidavanti.finito().size() > 0) {
                    Imbarca(bagnidavanti.finito());
                }
                while (bagnoretro.finito().size() > 0) {
                    Imbarca(bagnoretro.finito());
                }
            } catch (Exception e) {
                Coda<F_Turista> nob =  aiposti();

                   Imbarca(nob);

                   while (bagnidavanti.finito().size()>0)
                   {
                       Imbarca(bagnidavanti.finito());
                   }
                   while (bagnoretro.finito().size()>0)
                   {
                       Imbarca(bagnoretro.finito());
                   }
            }
        }
        Atterra();
        System.out.println("L'aereo è atterrato!");
    }
   //fa ritornare ai posti le persone in coda per il bagno dopo 85% di viaggio
    public Coda<F_Turista> aiposti()
    {
        Coda<F_Turista> nobagno = new Coda<F_Turista>();
        if(posizione>=85)
        {
            bagnidavanti.setpos();
             bagnoretro.setpos();
             while(bagnidavanti.getCoda().size()>0){
                F_Turista ft = bagnidavanti.getCoda().pop();
                nobagno.push(ft);
            }
            while(bagnoretro.getCoda().size()>0){
                F_Turista ft = bagnoretro.getCoda().pop();
                nobagno.push(ft);
            }
        }

        return nobagno;
    }



    //Metodo che accende le Turbine dell'aereo e accende la scatola nera
    public void Prepara_Aereo() {
        Imbarca_Passeggieri();
        einvolo = true;
        if(ControllaTurbine()){
            for(Turbina t : turbine){
                t.Attiva();
                t.start();
            }
        }
        else{
            Ripara_Aereo();
        }
        if(serbatoio.Get_Capacità() <= serbatoio.Get_Capacità_Critica()){
            Rifornisci_Aereo();
        }
        scatolaNera.start();
        aereo_pronto = true;
    }

    //Metodo per riparare le turbine e ricaricare la batteria della scatola nera
    public void Ripara_Aereo() {
        for (Turbina t : turbine){
            t.Ripara();
            t.Attiva();
            t.start();
        }
        turbine_funzionanti = true;
        scatolaNera.Ricarica();
        System.out.println("L'aereo " + this.id + " è stato riparato.");
    }

    public void Rifornisci_Aereo() {
        serbatoio.riempi();
        serbatoio_pieno = true;
    }

    public void Atterra() {
        einvolo = false;
        for(Turbina t : turbine){
            t.Disabilita();
        }
        turbine_funzionanti = false;
        serbatoio_pieno = false;
        System.out.println("L'aereo " + this.id + " è atterrato.");
    }

    //Metodo di Controllo che controlla lo stato delle turbine e nel caso 3 o più turbine siano
    //non funzionanti l'aereo precipita
    public boolean ControllaTurbine() {
        boolean ris = true;
        int n = 0;
        for (int i = 0; i < 4; i++) {
            if (!turbine.get(i).funzionante) {
                n++;
            }
        }
        if (n > 2) {
            ris = false;
        }
        return ris;
    }

    public void CambiaStatoMaltempo() {
        maltempo = !maltempo;
    }

    //Feature Alessio Campagnaro
    public boolean sciopero() {
        Random r = new Random();
        int i = r.nextInt(100);
        if (i == 69) {
            System.out.println("I piloti stanno scioperando.");
            return true;
        } else {
            return false;
        }
    }

    public Coda<F_Turista> Givebagno() {
        Coda<F_Turista> coda = new Coda<F_Turista>();
        Random random = new Random();

        int rr = random.nextInt() * entrata.Getnperson();
        while (rr > 0) {

            int c = random.nextInt() * matricePostiAereo.length;
            int r = random.nextInt() * matricePostiAereo[0].length;

            if (matricePostiAereo[c][r] != null) {
                rr = 0;
            } else {
                coda.push(matricePostiAereo[c][r]);
                rr -= 1;
            }

        }
        return coda;
    }

    public void Imbarca_Passeggieri() {
        Imbarca(entrata.GetsalitiDavanti());
        Imbarca(entrata.GetsalitiDietro());
        turisti_imbarcati = true;
    }

    public void Imbarca(Coda<F_Turista> c) {
        for (int i = 0; i < c.size(); i++) {
            F_Turista t = c.pop();
            matricePostiAereo[t.posto_colonna][t.posto_riga] = t;
        }
        System.out.println("I Turisti sono saliti nell'aereo " + this.id + " in direzione " + this.destinazione + ".");
    }

    public Coda<F_Turista> FaiScendere() {
        Coda<F_Turista> coda = new Coda<F_Turista>();
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 10; r++) {
                if (matricePostiAereo[c][r] != null) {
                    coda.push(matricePostiAereo[c][r]);
                    matricePostiAereo[c][r] = null;
                }

            }
        }
        System.out.println("I Turisti sono scesi dall'aereo " + this.id + " a " + this.destinazione + ".");
        turisti_imbarcati = false;
        return coda;

    }

    public Entrata Get_Entrata() {
        return entrata;
    }

    public Uscita Get_Uscita() {
        return uscita;
    }

    public F_Turista[][] Get_Posti_Aereo() {
        return this.matricePostiAereo;
    }

    public void Set_Gate(Gate gate) {
        this.gate = gate;
    }

    public Stiva Get_Stiva() {
        return this.stiva;
    }

    public ArrayList<Turbina> Get_Turbine() {
        return this.turbine;
    }

    public String Get_Destinazione() {
        return this.destinazione;
    }

    public Gate Get_Gate() {
        return this.gate;
    }

    public ScatolaNera Get_Scatola_Nera() {
        return this.scatolaNera;
    }

    public Serbatoio Get_Serbatoio() {
        return this.serbatoio;
    }

    public int Get_ID() {
        return this.id;
    }

    public int Get_Posizione() {
        return this.posizione;
    }

    public boolean Get_Stato_Aereo() {
        return this.einvolo;
    }

}