package Aereo;

import Utils.Coda;
import java.util.ArrayList;
import java.util.Random;

public class Aereo extends  Thread {
    //Caratteristiche Aereo
    private String id;
    private String ap_destinazione;
    private String ap_attuale;
    private double posizione;
    //Oggetti dell'aereo
    private Gate gate;
    private Bagno bagnidavanti;
    private Bagno bagnoretro;
    private ScatolaNera scatolaNera;
    private ArrayList<Turbina> turbine;
    private Stiva stiva;
    private Serbatoio serbatoio;
    private Alieni alieni;
    private Uscita uscita;
    private Entrata entrata;
    private F_Turista[][] matricePostiAereo;
    private Random rnd;
    //Variabili di controllo
    private boolean einvolo;
    private boolean maltempo;
    private boolean serbatoio_pieno;
    private boolean turisti_imbarcati;
    private boolean stiva_piena;
    private boolean turbine_funzionanti;
    private boolean aereo_pronto;
    private boolean gateTerminato;
    private  boolean aereo_partito;
    private  boolean alieni_partiti;
    private  boolean sciopero_piloti;

    public Aereo(String Id, String ap_att) {
        //caratteristiche
        this.id = Id;
        ap_destinazione = "";
        ap_attuale = ap_att;

        //componenti aereo
        rnd = new Random();
        matricePostiAereo = new F_Turista[4][10];
        serbatoio = new Serbatoio();
        stiva = new Stiva(this);
        scatolaNera = new ScatolaNera(this);
        bagnidavanti = new Bagno();
        bagnoretro = new Bagno();
        entrata = new Entrata();
        alieni = new Alieni();//Feature Riccardo Pettenuzzo
        Hacker h = new Hacker(scatolaNera);//Feature Borsato Marco
        h.start();
        uscita = new Uscita(this);
        uscita.start();
        turbine = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Turbina n = new Turbina(this, i);
            turbine.add(n);
        }

        //condizioni partenza
        stiva_piena = false;
        turbine_funzionanti = false;
        turisti_imbarcati = false;
        serbatoio_pieno = false;
        aereo_pronto = false;
        alieni_partiti = false;
        aereo_partito = false;
        sciopero_piloti = false;
        maltempo = false;
        einvolo = false;

        this.start();
    }

    public void run() {
        int stampa_dati_aereo = 0;
        while (ap_destinazione != ap_attuale && !serbatoio.Get_CarburanteTerminato()) {
            if (aereo_pronto && gateTerminato) {
                try {
                    if (!aereo_partito) {
                        if (sciopero() && !sciopero_piloti) {
                            this.sleep(8000);
                            sciopero_piloti = true;
                        }
                        System.out.println("(AE)    L'aereo " + this.Get_ID() + " è partito!");
                        aereo_partito = true;
                    }
                    if (!alieni_partiti) {
                        alieni.start();
                        alieni_partiti = true;
                    }
                    if (stampa_dati_aereo % 10 == 0) {
                        System.out.println("(AE)    Posizione aereo " + this.Get_ID() + " = " + this.Get_Posizione() + ".");
                        System.out.println("(AE)    Posizione attuale = " + this.Get_AP_Attuale() + ".");
                        System.out.println("(AE)    Destinazione = " + this.Get_AP_Destinazione() + ".");
                    }
                    //Feature Riccardo Pettenuzzo
                    if (alieni.Get_Aereo()) {
                        break;
                    }

                    //parte del movimento dell'aereo
                    if (rnd.nextInt(1, 20) == 15) {
                        boolean stato_attuale = maltempo;
                        CambiaStatoMaltempo();
                        if (stato_attuale) {
                            System.out.println("(PILOTI) Gentili passeggeri dell'aereo " + this.Get_ID() + " siamo usciti dalla zona di turbolenza.");
                        } else {
                            System.out.println("(PILOTI) Gentili passeggeri dell'aereo " + this.Get_ID() + " siamo entrati dalla zona di turbolenza.");
                        }

                    }
                    if (maltempo) {
                        posizione += 0.5;
                    } else {
                        posizione += 2.5;
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
                    }
                }*/

                    this.sleep(2000);
                    stampa_dati_aereo++;
                }catch (Exception e) {}
            }
            else{
                try {
                    System.out.println("(AE)    E' in corso la preparazione dell'aereo " + this.Get_ID() + ".");
                    this.sleep(5000);
                }catch (Exception e){}
            }
        }
        if(alieni.Get_Aereo()){
            System.out.println("(PILOTI) Signori e Signore siamo desolati, sfortunatamente oggi non ci sarà ");
            System.out.println("         possibile raggiungere la destinazione " + this.Get_AP_Destinazione() + " ");
            System.out.println("         poichè l'aereo " + this.Get_ID() + " è stato intercettato da un velivolo alieno ");
            System.out.println("         non identificato e potenzialmente pericoloso, siete pregati di mantenere la calma. ");
            System.out.println("         Grazie per l'attenzione e grazie di aver scelto  ITT Barsanti Airlines, ");
            System.out.println("         tutto il personale di volo vi augura Buona Fortuna!");
            scatolaNera.EstraiLogs();
            System.exit(0);
        }
        else if(serbatoio.Get_CarburanteTerminato()){
            for(Turbina t :  turbine){
                if(!t.funzionante){
                    t.Disabilita();
                }
            }
            System.out.println("(PILOTI) Signori e Signore stiamo perdendo quota....");
            try{
                System.out.println("(PILOTI) Moriremo a breve siete pregati di mantenere la calma");
                System.out.println("(PILOTI) Prepararsi all'impatto!");
                this.sleep(2000);
                System.out.println("L'aereo è precipitato!");
                scatolaNera.EstraiLogs();
                System.exit(0);
            }catch (Exception e){}
        }
        else{
            System.out.println("(PILOTI)   L'aereo " + this.Get_ID() + " è atterrato!");
            Atterra();
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
        System.out.println("(AE)    Le turbine dell'aereo " + this.Get_ID() + " sono state riparate.");
    }

    public void Rifornisci_Aereo() {
        serbatoio.riempi();
        serbatoio_pieno = true;
        System.out.println("(AE)    Il serbatoio " + this.Get_ID() + " è stato riempito.");
    }

    public void Atterra() {
        einvolo = false;
        for(Turbina t : turbine){
            t.Disabilita();
        }
        turbine_funzionanti = false;
        serbatoio_pieno = false;
        alieni_partiti = false;
        sciopero_piloti = false;
        aereo_partito = false;
        maltempo = false;
        posizione = 0;
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
        int i = rnd.nextInt(1, 7);
        if (i == 5) {
            System.out.println("(PILOTI)   I piloti stanno scioperando...");
            return true;
        } else {
            return false;
        }
    }

    public Coda<F_Turista> Givebagno() {
        Coda<F_Turista> coda = new Coda<F_Turista>();

        int rr = rnd.nextInt() * entrata.Getnperson();
        while (rr > 0) {

            int c = rnd.nextInt() * matricePostiAereo.length;
            int r = rnd.nextInt() * matricePostiAereo[0].length;

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
        System.out.println("(AE)    Numero Turisti Saliti Davanti " + entrata.GetsalitiDavanti().size());
        System.out.println("(AE)    Numero Turisti Saliti Dietro  " + entrata.GetsalitiDietro().size());
        Imbarca(entrata.GetsalitiDavanti());
        Imbarca(entrata.GetsalitiDietro());
        turisti_imbarcati = true;
    }

    public void Imbarca(Coda<F_Turista> c) {
        int n_turisti = 0;
        while(c.size() > 0) {
            F_Turista t = c.pop();
            matricePostiAereo[t.Get_posto_colonna()][t.Get_posto_riga()] = t;
            n_turisti++;
        }
        System.out.println("(AE)    " + n_turisti + " sono saliti nell'aereo " + this.Get_ID() + " in direzione " + this.Get_AP_Destinazione() + ".");
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
        System.out.println("(AE)    I Turisti sono quasi scesi dall'aereo " + this.Get_ID() + " in direzione " + this.Get_AP_Destinazione() + ".");
        turisti_imbarcati = false;
        return coda;

    }

    public Entrata Get_Entrata() {
        return entrata;
    }
    public Uscita Get_Uscita() {
        return uscita;
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
    public ScatolaNera Get_Scatola_Nera() {
        return this.scatolaNera;
    }
    public Serbatoio Get_Serbatoio() {
        return this.serbatoio;
    }
    public String Get_ID() {
        return this.id;
    }
    public double Get_Posizione() {
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