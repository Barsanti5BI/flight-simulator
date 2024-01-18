package Persona;

import Aereo.Gate;
import Aereoporto.ZonaArrivi.ZonaArrivi;
import Aereoporto.ZonaCheckIn.CartaImbarco;
import Aereoporto.ZonaCheckIn.ZonaCheckIn;
import Aereoporto.ZonaControlli.ZonaControlli;
import Aereoporto.ZonaNegozi.Negozio;
import Aereoporto.ZonaNegozi.ZonaNegozi;
import Aereoporto.ZonaPartenze.AreaAttesa;
import Aereoporto.ZonaPartenze.ZonaPartenze;
import Utils.Coda;

import java.util.List;
import java.util.Random;

public class Turista extends Persona{

    // determinazione turista
    public boolean inPartenza;
    public boolean inArrivo;

    //ZONA CHECKIN
    public ZonaCheckIn zonaCheckIn;
    public int indiceSettore;
    public  boolean deveFareCheckIn;
    public boolean devePrendereBiglietto;
    public  boolean devePoggiareBagagliAlCheckIn;
    private CartaImbarco cartaImbarco;

    //ZONA CONTROLLI
    public ZonaControlli zonaControlli;
    public int IndiceSettore;
    public boolean deveFareControlli;
    public boolean devePoggiareBagagliAiControlli;
    public boolean deveFareControlliAlMetalDetector;
    public  boolean deveRitirareBagagli;
    public  boolean controlliFattiConSuccesso;
    public  boolean bagaglioSospetto;
    public  boolean controlloMetalDetectorSospetto;
    public  boolean perquisizioneTerminata;

    //ZONA NEGOZI
    public ZonaNegozi zonaNegozi;
    public int indiceNegozio;
    public  boolean vuoleFareAcquisto;
    public List<Oggetto> oggettiDaComprare;
    private Bagaglio bag;
    private boolean pagato;

    // ZONA GATE
    public ZonaPartenze zonaPartenze;
    public boolean prontoPerImbarcarsi;

    // ZONA ARRIVI

    public ZonaArrivi zonaArrivi;
    public boolean arrivatoAreaArrivi;
    public boolean haPassatoControlliArr;
    public boolean esitoControlli;
    public boolean haRitiratoBagagliArr;
    public boolean haFinitoArr;

    // elementi utili
    private List<Oggetto> oggetti;
    private Documento doc;
    private Random r;

    public Turista(Documento doc,Bagaglio bag, CartaImbarco cartaImbarco, List<Oggetto> oggetti) {
        this.bag = bag;
        this.cartaImbarco = cartaImbarco;
        this.oggetti = oggetti;
        this.doc = doc;
        r = new Random();
        vuoleFareAcquisto = r.nextBoolean();
    }

    public void run(){
        while (true){
            try{

                // IL TURISTA DEVE PARTIRE
                if (inPartenza)
                {
                    // ZONA CHECK-IN
                    if(deveFareCheckIn) {
                        if (devePrendereBiglietto) {
                            if (devePoggiareBagagliAlCheckIn) {
                                zonaCheckIn.getBanco().getCodaBanco().push(this);

                                if (bag.getDaStiva())
                                {
                                    bag = null;
                                    devePoggiareBagagliAiControlli = false;
                                }

                                while(deveFareCheckIn && devePoggiareBagagliAlCheckIn && devePrendereBiglietto)
                                {
                                    Thread.sleep(5);

                                    if(!deveFareCheckIn)
                                    {
                                        break;
                                    }
                                }
                            }
                        }
                        deveFareControlli = true;
                    }

                    // ZONA CONTROLLI
                    if (deveFareControlli) {
                        if (devePoggiareBagagliAiControlli) {
                            if (bag != null) {
                                zonaControlli.getCodaBagagliDaControllare().push(bag);
                                devePoggiareBagagliAiControlli = false;
                            }
                        }

                        if (deveFareControlliAlMetalDetector) {
                            zonaControlli.getMetalDetector().getCodaTuristiAttesa().push(this);

                            while (deveFareControlliAlMetalDetector) {
                                Thread.sleep(5);

                                if (!deveFareControlliAlMetalDetector) {
                                    break;
                                }
                            }

                            if (!controlloMetalDetectorSospetto) {
                                if (bag != null && deveRitirareBagagli) {
                                    if (bagaglioSospetto) {
                                        System.out.println("Turista arrestato!");
                                        break;
                                    } else {
                                        cercaBagaglio(zonaControlli.getScanner().getCodaBagagliControllati());
                                    }

                                    deveRitirareBagagli = true;
                                }
                            } else {
                                while (!perquisizioneTerminata) {
                                    if (perquisizioneTerminata) {
                                        break;
                                    } else {
                                        Thread.sleep(5);
                                    }
                                }

                                break;
                            }

                            deveFareControlli = false;
                            controlliFattiConSuccesso = true;
                        }

                        // ZONA NEGOZI

                        if (vuoleFareAcquisto) {
                            indiceNegozio = r.nextInt(0, 1); // indici impostati dall'aeroporto
                            Negozio n = zonaNegozi.getNegozi().get(indiceNegozio);
                            Thread.sleep(1000);

                            System.out.println("Il turista " + getName() + " è entrato nel negozio " + n.getNome());
                            decidiCosaComprare(n);
                            n.getCodaCassa().push(this);

                            while (true) {
                                if (!pagato) {
                                    Thread.sleep(5);
                                } else {
                                    break;
                                }
                            }

                            vuoleFareAcquisto = false;
                        }

                        if(prontoPerImbarcarsi)
                        {
                            List<Gate> gates = zonaPartenze.getGates();
                            Gate mioGate = null;

                            for(Gate g : gates)
                            {
                                if (g.getId() == cartaImbarco.getGate())
                                {
                                    mioGate = g;
                                    break;
                                }
                            }

                            if (!gate.getGateAperto())
                            {
                                AreaAttesa a = zonaPartenze.getAreaPartenza();
                                a.getCoda().push(this);
                                // chiedere spiegazioni sull'area attesa
                            }

                            inserisciTuristaGate();
                        }
                    }
                } else if (inArrivo) // IL TURISTA E' ARRIVATO
                {
                    while(!arrivatoAreaArrivi)
                    {
                        if(arrivatoAreaArrivi)
                        {
                            break;
                        }
                        else
                        {
                            Thread.sleep(5);
                        }
                    }

                    if(!haPassatoControlliArr)
                    {
                        zonaArrivi.getCodaControlli().push(this);

                        while(!haPassatoControlliArr)
                        {
                            if (haPassatoControlliArr)
                            {
                                break;
                            }
                            else
                            {
                                Thread.sleep(5);
                            }
                        }

                        if(esitoControlli)
                        {
                            if (bag == null)
                            {
                                cercaBagaglio(zonaArrivi.getRitiroBagagli().getCodaBagagli());
                            }

                            haRitiratoBagagliArr = true;
                            haFinitoArr = true;
                            break;
                        }
                        else
                        {
                            break;
                        }
                    }
                }

                Thread.sleep(2);
            }
            catch (InterruptedException ex){
                System.out.println(ex);
            }

        }

    }

    public String getDestinazione(){
        return destinazione;
    }

    public Bagaglio GetBagaglio() {
        return bag;
    }

    public Bagaglio DaiBagaglio() {
        Bagaglio b = bag;
        bag = null;
        return b;
    }

    public List<Oggetto> GetListaOggetti()
    {
        return oggetti;
    }
    public Documento getDoc(){
        return doc;
    }
    public CartaImbarco GetCartaImbarco(){return cartaImbarco;}
    public void setCartaImbarco(CartaImbarco c) { cartaImbarco = c; }

    public void cercaBagaglio(Coda<Bagaglio> codaBag)
    {
        while(true)
        {
            Bagaglio b = codaBag.pop();

            if (b.getEtichetta().getIdRiconoscimentoBagaglio() == cartaImbarco.getIdRiconoscimentoBagaglio())
            {
                bag = b;
                break;
            }
            else
            {
                zonaControlli.getScanner().getCodaBagagliControllati().push(b);
            }
        }
    }

    public void decidiCosaComprare(Negozio n)
    {
        List<Oggetto> coseDisponibili = n.getCoseDisponibili();

        for(int i = 0; i <= r.nextInt(0, 11); i++)
        {
            int indiceElemento = r.nextInt(0, coseDisponibili.size());
            oggettiDaComprare.add(coseDisponibili.get(indiceElemento));
        }
    }

    public void inserisciTuristaGate()
    {
        if(cartaImbarco.getPrioritario())
        {
            gate.getCodaPrioritaria().push(this);
        }
        else
        {
            gate.getCodaNormale().push(this);
        }

        prontoPerImbarcarsi = false;
    }
}
