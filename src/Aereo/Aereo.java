package Aereo;

import Utils.Coda;

import java.util.ArrayList;
import java.util.Random;

public class Aereo extends  Thread {
    private String id;
    private String ap_destinazione;
    private String ap_attuale;
    private int posizione;
    private Gate gate;
    private Bagno bagnidavanti;
    private Bagno bagnoretro;
    private ScatolaNera scatolaNera;
    private ArrayList<Turbina> turbine;
    private Stiva stiva;
    private Serbatoio serbatoio;
    private Alieni alieni;
    private boolean einvolo;
    private boolean maltempo;
    private Random r;
    private F_Turista[][] matricePostiAereo;
    private int nPosti;
    private Entrata entrata;
    private Uscita uscita;
    private boolean serbatoio_pieno;
    private boolean turisti_imbarcati;
    private boolean stiva_piena;
    private boolean turbine_funzionanti;
    private boolean aereo_pronto;
    private boolean gateTerminato;
    private Random rnd;

    public Aereo(String Id, String ap_att) {
        rnd = new Random();

        this.id = Id;
        maltempo = false;

        //aereoporti
        ap_destinazione = "";
        ap_attuale = ap_att;

        //componenti aereo
        serbatoio = new Serbatoio();
        stiva = new Stiva(this);
        scatolaNera = new ScatolaNera(this);
        turbine = new ArrayList<Turbina>();
        bagnidavanti = new Bagno();
        bagnoretro = new Bagno();
        entrata = new Entrata();
        uscita = new Uscita(this);
        uscita.start();
        for (int i = 0; i < 4; i++) {
            Turbina n = new Turbina(this, i);
            turbine.add(n);
        }
        matricePostiAereo = new F_Turista[4][10];
        nPosti = 40;

        //condizioni partenza
        stiva_piena = false;
        turbine_funzionanti = false;
        turisti_imbarcati = false;
        serbatoio_pieno = false;
        aereo_pronto = false;

        einvolo = false;

        //Feature Riccardo Pettenuzzo
        alieni = new Alieni();


        this.start();
    }

    public void run() {
        boolean alieni_partiti = false;
        boolean aereo_partito = false;
        boolean sciopero_piloti = false;
        while (ap_destinazione != ap_attuale && !serbatoio.Get_CarburanteTerminato()) {
            if (aereo_pronto && gateTerminato) {
                try {
                    if (!alieni_partiti){
                        alieni.start();
                        alieni_partiti = true;
                    }
                    if(!aereo_partito){
                        if (sciopero() && !sciopero_piloti) {
                            this.sleep(5000);
                            sciopero_piloti = true;
                        }
                        System.out.println("(AE)   L'aereo " + this.Get_ID() + " è partito!");
                        aereo_partito = true;
                    }
                    if(this.Get_Posizione() % 10 == 0){
                        System.out.println("(AE)   Posizione aereo " + this.Get_ID() + " = " + this.Get_Posizione() +".");
                        System.out.println("(AE)   Posizione attuale = " + this.Get_AP_Attuale()+".");
                        System.out.println("(AE)   Destinazione = " + this.Get_AP_Destinazione()+".");
                    }
                    //Feature Riccardo Pettenuzzo
                    if (alieni.Get_Aereo()) {
                        break;
                    }
                    this.sleep(1000);

                    //parte del movimento dell'aereo
                    if (maltempo) {
                        posizione += 1;
                    } else {
                        posizione += 2;
                    }
                    serbatoio.consuma_carburante();

                    //parte dei bagni dell'aereo
                    /*Coda<F_Turista> getbagno = Givebagno();
                    if (getbagno.size() % 2 == 0) {
                        bagnidavanti.DareBisogno(getbagno.pop());
                    }
                    if (getbagno.size() % 2 == 1) {
                        bagnoretro.DareBisogno(getbagno.pop());
                    }

                    bagnidavanti.run();
                    bagnoretro.run();

                    while (bagnidavanti.finito().size() > 0) {
                        Imbarca(bagnidavanti.finito());
                    }
                    while (bagnoretro.finito().size() > 0) {
                        Imbarca(bagnoretro.finito());
                    }

                } catch (Exception e) {
                    Coda<F_Turista> nob = aiposti();
                    Imbarca(nob);
                    while (bagnidavanti.finito().size() > 0) {
                        Imbarca(bagnidavanti.finito());
                    }
                    while (bagnoretro.finito().size() > 0) {
                        Imbarca(bagnoretro.finito());
                    }*/
                }
                catch (Exception e){}
            }
            else{
                try {
                    System.out.println("(AE)   E' in corso la preparazione dell'aereo " + this.Get_ID() + ".");
                    this.sleep(5000);
                }catch (Exception e){}
            }
        }
        if(alieni.Get_Aereo()){
            System.out.println("(PILOTI) Signori e Signore siamo desolati, sfortunatamente oggi non ci sarà ");
            System.out.println("         possibile raggiungere la destinazione " + this.Get_AP_Destinazione() + " ");
            System.out.println("         poichè l'aereo " + this.Get_ID() + " è stato intercettato da un velivolo ");
            System.out.println("         non identificato. Grazie per l'attenzione e grazie di aver scelto  ITT Barsanti Airlines, ");
            System.out.println("         tutto il personale di volo vi augura Buona Fortuna!");
            System.exit(0);
        }
        else if(serbatoio.Get_CarburanteTerminato()){
            System.out.println("(PILOTI) Signori e Signore stiamo perdendo quota....");
            try{
                this.sleep(5000);
                System.out.println("(PILOTI) Moriremo a breve siete pregati di mantenere la calma");
                this.sleep(3000);
                System.out.println("(PILOTI) Prepararsi all'impatto!");
                this.sleep(1000);
                System.out.println("L'aereo è precipitato!");
                System.exit(0);
            }catch (Exception e){}
        }
        else{
            Atterra();
            System.out.println("(PILOTI)   L'aereo " + this.Get_ID() + " è atterrato!");
            Get_Uscita().Set_Stato_Uscita(true);
            try {
                this.sleep(3000);
            }catch (Exception e){}
        }
    }


    //Controlla le Turbine e nel caso siano funzionanti le avvia altrimenti le ripara
    //Controlla che ci sia abbastanza carburante e nel caso rifornisce aereo
    //Avvia la scatola nera
    //Imbarca i passeggeri
    //Dopo questi passaggi l'aereo è pronto per partire
    public boolean Prepara_Aereo() {
        try{
            this.sleep(21000);
        }catch (Exception e){}
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
        if(serbatoio.Get_Capacità() == 100){
            serbatoio_pieno = true;
        }
        else if(serbatoio.Get_Capacità() <= serbatoio.Get_Capacità_Critica()){
            Rifornisci_Aereo();
        }
        scatolaNera.start();
        if(turbine_funzionanti && stiva_piena && turisti_imbarcati && serbatoio_pieno){
            aereo_pronto = true;
        }
        return aereo_pronto;
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
        System.out.println("(AE)   Le turbine dell'aereo " + this.Get_ID() + " sono state riparate.");
    }

    public void Rifornisci_Aereo() {
        serbatoio.riempi();
        serbatoio_pieno = true;
        System.out.println("(AE)   Il serbatoio " + this.Get_ID() + " è stato riempito.");
    }

    public void Atterra() {
        einvolo = false;
        for(Turbina t : turbine){
            t.Disabilita();
        }
        turbine_funzionanti = false;
        serbatoio_pieno = false;
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
        int i = r.nextInt(1, 7);
        if (i == 5) {
            System.out.println("(PILOTI)   I piloti stanno scioperando...");
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

    public void Imbarca_Passeggieri() {
        System.out.println("(AE) Saliti Davanti Size = " + entrata.GetsalitiDavanti().size());
        System.out.println("(AE) Saliti Dietro Size = " + entrata.GetsalitiDietro().size());
        Imbarca(entrata.GetsalitiDavanti());
        Imbarca(entrata.GetsalitiDietro());
        turisti_imbarcati = true;
    }

    public void Imbarca(Coda<F_Turista> c) {
        int liggi = 0;
        while(c.size() > 0) {
            F_Turista t = c.pop();
            matricePostiAereo[t.Get_posto_colonna()][t.Get_posto_riga()] = t;
//            System.out.println("(AE) Turista " + t.Get_id() + " ha preso posto nell'aereo " + this.Get_ID()+".");
//            try{
//                this.sleep(10);
//            }catch (Exception e){}
            liggi++;
        }
        System.out.println("(AE) Numero turisti entrati nell'aereo = " + liggi +".");
        System.out.println("(AE)   I Turisti sono saliti nell'aereo " + this.Get_ID() + " in direzione " + this.Get_AP_Destinazione() + ".");
    }

    public Coda<F_Turista> FaiScendere() {
        Coda<F_Turista> coda = new Coda<F_Turista>();
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 10; r++) {
                if (matricePostiAereo[c][r] != null) {
                    coda.push(matricePostiAereo[c][r]);
//                    System.out.println("Turista " + matricePostiAereo[c][r].Get_id() + " è quasi uscito dall'aereo " + this.Get_ID()+".");
                    matricePostiAereo[c][r] = null;
//                    try{
//                        this.sleep(10);
//                    }catch (Exception e){}
                }

            }
        }
        System.out.println("(AE) Numero turisti quasi scesi = " + coda.size()+".");
        System.out.println("(AE)   I Turisti sono quasi scesi dall'aereo " + this.Get_ID() + " in direzione " + this.Get_AP_Destinazione() + ".");
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
    public String Get_AP_Destinazione() {
        return this.ap_destinazione;
    }
    public String Get_AP_Attuale() {
        return this.ap_attuale;
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
    public String Get_ID() {
        return this.id;
    }
    public int Get_Posizione() {
        return this.posizione;
    }
    public boolean Get_Stato_Aereo() {
        return this.einvolo;
    }
    public void Set_Stato_Stiva(boolean stato){
        this.stiva_piena = stato;
    }
    public void Set_Stato_gate(boolean stato){this.gateTerminato = stato;}

    public void SetPosizione(){
        ap_attuale = ap_destinazione;}

    public void Set_AP_Destinazione(String destinazione){
        this.ap_destinazione = destinazione;
    }
    public void Set_Stato_Turisti(boolean stato) {turisti_imbarcati = stato;}
}