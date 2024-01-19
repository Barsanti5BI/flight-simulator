package Persona;

import Aereo.Gate;
import Aereoporto.ZonaArrivi.Dogana;
import Aereoporto.ZonaArrivi.RitiroBagagli;
import Aereoporto.ZonaArrivi.ZonaArrivi;
import Aereoporto.ZonaCheckIn.CartaImbarco;
import Aereoporto.ZonaCheckIn.ZonaCheckIn;
import Aereoporto.ZonaControlli.MetalDetector;
import Aereoporto.ZonaControlli.Scanner;
import Aereoporto.ZonaControlli.Settore;
import Aereoporto.ZonaControlli.ZonaControlli;
import Aereoporto.ZonaNegozi.Negozio;
import Aereoporto.ZonaNegozi.ZonaNegozi;
import Aereoporto.ZonaPartenze.ZonaPartenze;
import Utils.Coda;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
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
    private CartaImbarco cartaImbarco;

    //ZONA CONTROLLI
    public ZonaControlli zonaControlli;
    public int IndiceSettore;
    public boolean deveFareControlli;
    public boolean devePoggiareBagagliAiControlli;
    public boolean deveFareControlliAlMetalDetector;
    public  boolean deveRitirareBagagliAiControlli;
    public  boolean controlliFattiConSuccesso;
    public  boolean bagaglioSospetto;
    public  boolean controlloMetalDetectorSospetto;
    public  boolean perquisizioneTerminata;

    //ZONA NEGOZI
    public ZonaNegozi zonaNegozi;
    public int indiceNegozio;
    public  boolean vuoleFareAcquisto;
    public List<Prodotto> oggettiDaComprare;
    private @Nullable Bagaglio bagaglio;
    private boolean pagato;
    private List<String> possibiliInteressi = new ArrayList<>();

    // ZONA GATE
    public ZonaPartenze zonaPartenze;
    public boolean prontoPerImbarcarsi;
    public boolean passatoControlliGate;
    public boolean esitoControlloGate;
    public boolean gateGiusto;

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
    private String destinazione;

    public Turista(Documento doc,Bagaglio bag, CartaImbarco cartaImbarco, List<Oggetto> oggetti) {
        this.bagaglio = bag;
        this.cartaImbarco = cartaImbarco;
        this.oggetti = oggetti;
        this.doc = doc;
        r = new Random();
        vuoleFareAcquisto = r.nextBoolean();
        getPossibiliInteressi();
    }

    public void run(){
        while (true){
            try{

                // IL TURISTA DEVE PARTIRE
                if (inPartenza)
                {
                    // ZONA CHECK-IN
                    if(deveFareCheckIn) {
                        zonaCheckIn.getBanco().getCodaTuristi().push(this);

                        synchronized (zonaCheckIn.getBanco().getImpiegatoCheckIn()) {
                            while(deveFareCheckIn) {
                                zonaCheckIn.getBanco().getImpiegatoCheckIn().wait();
                            }
                        }
                        deveFareControlli = true;
                    }

                    // ZONA CONTROLLI
                    if (deveFareControlli) {
                        Settore settore = zonaControlli.getSettore(0);
                        MetalDetector metalDetector = settore.getMetalDetector();
                        Scanner scanner = settore.getScannerBagagali();
                        if (devePoggiareBagagliAiControlli) {
                            if (bagaglio != null) {
                                scanner.getCodaBagagli().push(bagaglio);
                                devePoggiareBagagliAiControlli = false;
                                bagaglio = null;
                                deveRitirareBagagliAiControlli = true;
                            }
                        }

                        if (deveFareControlliAlMetalDetector) {
                            metalDetector.getCodaTuristiAttesa().push(this);

                            synchronized (metalDetector) {
                                while (deveFareControlliAlMetalDetector) {
                                    metalDetector.wait();
                                }
                            }

                            if (!controlloMetalDetectorSospetto) {
                                if (deveRitirareBagagliAiControlli) {
                                    if (bagaglioSospetto) {
                                        System.out.println("Turista arrestato!");
                                        break;
                                    } else {
                                        // il turista continua a cercare il bagaglio finchè non lo trova
                                        cercaBagaglio(scanner.getCodaBagagliControllati());
                                    }
                                }
                            } else {
                                synchronized (metalDetector.getImpiegatoControlli()) {
                                    while (!perquisizioneTerminata) {
                                        metalDetector.getImpiegatoControlli().wait();
                                    }
                                }
                                break;
                            }

                            deveFareControlli = false;
                            controlliFattiConSuccesso = true;
                        }

                        // ZONA NEGOZI

                        // feature garbui --> i clienti vogliono acquistare in determinate categorie di negozi
                        if (vuoleFareAcquisto) {

                            String interesse = possibiliInteressi.get(r.nextInt(0, possibiliInteressi.size()));
                            int indice = -1;

                            for(Negozio negozio:zonaNegozi.getListaNegozi()){
                                if(negozio.getCategoria() == interesse){
                                    indice = negozio.getIdNeg();
                                }
                            }
                            if(indice != -1){
                                Negozio n = zonaNegozi.getListaNegozi().get(indiceNegozio);

                                Thread.sleep(1000);

                                System.out.println("Il turista " + getName() + " è entrato nel negozio " + n.getNome());
                                decidiCosaComprare(n);
                                n.getCodaCassa().push(this);

                                synchronized (n.getImpiegatoNegozi())
                                {
                                    while(!pagato)
                                    {
                                        n.getImpiegatoNegozi().wait();
                                    }
                                }
                                vuoleFareAcquisto = false;
                            }
                            else{
                                System.out.println("Il turista " + getName() + " è triste: nessun negozio nella categoria " + interesse);
                            }
                        }

                        if(prontoPerImbarcarsi)
                        {
                            List<Gate> gates = zonaPartenze.getListaGate();
                            Gate mioGate = null;

                            for(Gate g : gates)
                            {
                                if (g.getId() == cartaImbarco.getGate())
                                {
                                    mioGate = g;
                                    break;
                                }
                            }

                            synchronized (mioGate)
                            {
                                while (!passatoControlliGate)
                                {
                                    gates.wait();
                                }
                            }

                            if(gateGiusto)
                            {
                                if(esitoControlloGate)
                                {
                                    System.out.println("Turista imbarcato");
                                }
                                else
                                {
                                    System.out.println("Turista arrestato");
                                }
                            }
                            else
                            {
                                System.out.println("Gate sbagliatp");
                                break;
                            }
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
                        Dogana dogana = zonaArrivi.getDogana();
                        RitiroBagagli ritiroBagagli = zonaArrivi.getRitiroBagagli();

                        synchronized (dogana.getControllore())
                        {
                            while(!haPassatoControlliArr)
                            {
                                dogana.getControllore().wait();
                            }
                        }

                        if(esitoControlli)
                        {
                            if (bagaglio == null)
                            {
                                // c'è un while che va avanti finchè non trova il bagaglio
                                cercaBagaglio(ritiroBagagli.getCodaBagagli());
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

    public void getPossibiliInteressi(){
        possibiliInteressi.add("Abbigliamento");
        possibiliInteressi.add("Supermercato");
        possibiliInteressi.add("Libreria");
        possibiliInteressi.add("Farmacia");
    }

    public String getDestinazione(){
        return destinazione;
    }

    public Bagaglio getBagaglio() {
        return bagaglio;
    }
    public void setBagaglio(@Nullable Bagaglio bagaglio) {
        this.bagaglio = bagaglio;
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
                bagaglio = b;
                break;
            }
            else
            {
                codaBag.push(b);
            }
        }
    }

    public void decidiCosaComprare(Negozio n)
    {
        List<Prodotto> coseDisponibili = n.getOggettiInVendita();

        for(int i = 0; i <= r.nextInt(0, 11); i++)
        {
            int indiceElemento = r.nextInt(0, coseDisponibili.size());
            oggettiDaComprare.add(coseDisponibili.get(indiceElemento));
        }
    }

    public void inserisciTuristaGate(Gate gate)
    {
        gate.getCodaGenerale().push(this);
        prontoPerImbarcarsi = false;
    }

    public void setGateGiusto(boolean var)
    {
        gateGiusto = var;
    }

    public void setEsitoControlloGate(boolean var)
    {
        esitoControlloGate = var;
    }

    public void setPassatoControlliGate(Boolean var)
    {
        esitoControlloGate = var;
    }
}
