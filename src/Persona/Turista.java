package Persona;

import Aereo.Gate;
import Aereoporto.ZonaArrivi.Dogana;
import Aereoporto.ZonaArrivi.RitiroBagagli;
import Aereoporto.ZonaArrivi.ZonaArrivi;
import Aereoporto.ZonaCheckIn.Banco;
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

public class Turista extends Thread {

    // Determinazione turista
    public boolean inPartenza;
    public boolean inArrivo;

    // ZONA CHECKIN
    public ZonaCheckIn zonaCheckIn;
    public int indiceSettore;
    public boolean deveFareCheckIn;
    private CartaImbarco cartaImbarco;

    // ZONA CONTROLLI
    public ZonaControlli zonaControlli;
    public int IndiceSettore;
    public boolean deveFareControlli;
    public boolean devePoggiareBagagliAiControlli;
    public boolean deveFareControlliAlMetalDetector;
    public boolean deveRitirareBagagliAiControlli;
    public boolean controlliFattiConSuccesso;
    public boolean bagaglioSospetto;
    public boolean controlloMetalDetectorSospetto;
    public boolean perquisizioneTerminata;

    // ZONA NEGOZI
    public ZonaNegozi zonaNegozi;
    public int indiceNegozio;
    public boolean vuoleFareAcquisto;
    public ArrayList<Prodotto> oggettiDaComprare;
    private @Nullable Bagaglio bagaglio;
    private boolean pagato;

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
    private int codAereo;

    public Turista(Documento doc, Bagaglio bag, CartaImbarco cartaImbarco, List<Oggetto> oggetti, int codAereo,
            ZonaCheckIn zonaCheckIn, ZonaControlli zonaControlli, ZonaNegozi zonaNegozi, ZonaPartenze zonaPartenze) {
        this.bagaglio = bag;
        this.cartaImbarco = cartaImbarco;
        this.oggetti = oggetti;
        this.doc = doc;
        setName(doc.getCognome() + " " + doc.getNome());
        r = new Random();
        vuoleFareAcquisto = r.nextBoolean();
        this.codAereo = codAereo;

        deveFareCheckIn = true;
        deveFareControlli = false;
        devePoggiareBagagliAiControlli = true;
        deveFareControlliAlMetalDetector = true;
        deveRitirareBagagliAiControlli = true;
        controlliFattiConSuccesso = false;
        bagaglioSospetto = false;
        controlloMetalDetectorSospetto = false;
        perquisizioneTerminata = false;
        prontoPerImbarcarsi = false;
        passatoControlliGate = false;
        esitoControlloGate = false;
        gateGiusto = false;
        arrivatoAreaArrivi = false;
        haPassatoControlliArr = false;
        esitoControlli = false;
        haRitiratoBagagliArr = false;
        haFinitoArr = false;
        inPartenza = true;
        this.zonaCheckIn = zonaCheckIn;
        this.zonaControlli = zonaControlli;
        this.zonaNegozi = zonaNegozi;
        this.zonaPartenze = zonaPartenze;
        oggettiDaComprare = new ArrayList<>();
    }

    public void run() {
        System.out.println("Il turista " + getName() + " è stato generato");
        try {
            // IL TURISTA DEVE PARTIRE
            if (inPartenza) {
                System.out.println("Il turista" + getName() + " si prepara ad affrontare il suo viaggio!");

                // ZONA CHECK-IN
                System.out.println("Il turista " + getName()
                        + " si mette in attesa al banco del check-in per prendere la sua carta d'imbarco");
                Banco banco = zonaCheckIn.getBanco();
                banco.getCodaTuristi().push(this);

                // attendo notify dall'impiegato
                synchronized (banco) {
                    while (deveFareCheckIn) {
                        banco.wait();
                    }
                }
                System.out.println("Il turista " + getName() + " ha la sua carta d'imbarco e va ai controlli");
                deveFareControlli = true;

                // ZONA CONTROLLI
                System.out.println("Il turista " + getName() + " è arrivato ai controlli");

                Settore settore = zonaControlli.getSettore(0);
                MetalDetector metalDetector = settore.getMetalDetector();
                Scanner scanner = settore.getScannerBagagali();

                if (bagaglio != null) {
                    Thread.sleep(1000);
                    scanner.getCodaBagagli().push(bagaglio);
                    System.out.println("Il turista " + getName() + " ha poggiato i bagagli sul rullo");
                    devePoggiareBagagliAiControlli = false;
                    bagaglio = null;
                    deveRitirareBagagliAiControlli = true;
                } else {
                    devePoggiareBagagliAiControlli = false;
                    deveRitirareBagagliAiControlli = false;
                }

                System.out.println("Il turista " + getName() + " si sta mettendo in coda per il metal detector");
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
                            return;
                        } else {
                            // il turista continua a cercare il bagaglio finchè non lo
                            System.out.println("Il turista " + getName() + " sta cercando il suo bagaglio...");
                            cercaBagaglio(scanner.getCodaBagagliControllati());
                            System.out.println("Il turista " + getName() + " ha preso il suo bagaglio");
                        }
                    }
                } else {
                    System.out.println("Scansione di potenziali oggetti metallicie effettuata");
                    synchronized (metalDetector.getImpiegatoControlli()) {
                        while (!perquisizioneTerminata) {
                            metalDetector.getImpiegatoControlli().wait();
                        }
                    }
                    return;
                }

                deveFareControlli = false;
                controlliFattiConSuccesso = true;
                prontoPerImbarcarsi = true;

                // ZONA NEGOZI
                if (vuoleFareAcquisto) {
                    indiceNegozio = r.nextInt(0, zonaNegozi.getListaNegozi().size());

                    Negozio n = zonaNegozi.getListaNegozi().get(indiceNegozio);

                    Thread.sleep(1000);

                    System.out.println("Il turista " + getName() + " è entrato nel negozio " + n.getNome());
                    decidiCosaComprare(n);
                    n.getCodaCassa().push(this);

                    synchronized (n.getImpiegatoNegozi()) {
                        while (!pagato) {
                            n.getImpiegatoNegozi().wait();
                        }
                    }
                    vuoleFareAcquisto = false;
                }

                System.out.println("Il turista è pronto per imbarcarsi");
                List<Gate> gates = zonaPartenze.getListaGate();
                Gate mioGate = null;

                for (Gate g : gates) {
                    if (g.getgateId() == cartaImbarco.getGate()) {
                        mioGate = g;
                        break;
                    }
                }
                if (mioGate == null) {
                    System.out.println("Il turista " + getName() + " non ha trovato il suo gate");

                } else {
                    synchronized (mioGate) {
                        while (!passatoControlliGate) {
                            mioGate.wait();
                        }
                    }
                }

                if (gateGiusto) {
                    System.out.println("Il turista è entrato nel gate giusto");
                    if (esitoControlloGate) {
                        System.out.println("Turista imbarcato");
                        return;
                    } else {
                        System.out.println("Turista arrestato");
                        return;
                    }
                } else {
                    System.out.println("Gate sbagliatp");
                    return;
                }

            }

            if (inArrivo) {
                while (!arrivatoAreaArrivi) {
                    if (arrivatoAreaArrivi) {
                        System.out.println("Il turista " + getName() + " è arrivato all'aeroporto");
                        break;
                    } else {
                        Thread.sleep(5);
                    }
                }

                if (!haPassatoControlliArr) {
                    Dogana dogana = zonaArrivi.getDogana();
                    System.out.println("Il bagaglio di " + getName() + " è stato ritirato");
                    RitiroBagagli ritiroBagagli = zonaArrivi.getRitiroBagagli();

                    synchronized (dogana.getControllore()) {
                        System.out.println("In attesa dei controlli alla dogana...");
                        while (!haPassatoControlliArr) {
                            dogana.getControllore().wait();
                        }
                    }

                    if (esitoControlli) {
                        if (bagaglio == null) {
                            System.out.println("Il turista " + getName() + " sta cercando il suo bagaglio");
                            // c'è un while che va avanti finchè non trova il bagaglio
                            cercaBagaglio(ritiroBagagli.getCodaBagagli());
                        }

                        haRitiratoBagagliArr = true;
                        haFinitoArr = true;
                        return;
                    } else {
                        System.out.println("Il turista " + getName() + " sta cercando il suo bagaglio");
                        return;
                    }
                }
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    public int getCodAereo() {
        return codAereo;
    }

    public Bagaglio getBagaglio() {
        return bagaglio;
    }

    public void setBagaglio(@Nullable Bagaglio bagaglio) {
        this.bagaglio = bagaglio;
    }

    public List<Oggetto> GetListaOggetti() {
        return oggetti;
    }

    public Documento getDoc() {
        return doc;
    }

    public CartaImbarco GetCartaImbarco() {
        return cartaImbarco;
    }

    public void setCartaImbarco(CartaImbarco c) {
        cartaImbarco = c;
    }

    public void cercaBagaglio(Coda<Bagaglio> codaBag) {
        while (true) {
            Bagaglio b = codaBag.pop();
            if (b.getEtichetta().getIdRiconoscimentoBagaglio() == cartaImbarco.getIdRiconoscimentoBagaglio()) {
                bagaglio = b;
                break;
            } else {
                codaBag.push(b);
            }
        }
    }

    public void decidiCosaComprare(Negozio n) {
        List<Prodotto> coseDisponibili = n.getOggettiInVendita();

        for (int i = 0; i <= r.nextInt(0, 11); i++) {
            int indiceElemento = (int) (Math.random() * coseDisponibili.size());
            oggettiDaComprare.add(coseDisponibili.get(indiceElemento));
        }
    }

    public void inserisciTuristaGate(Gate gate) {
        gate.getCodaGenerale().push(this);
        prontoPerImbarcarsi = false;
    }

    public void setGateGiusto(boolean var) {
        gateGiusto = var;
    }

    public void setEsitoControlloGate(boolean var) {
        esitoControlloGate = var;
    }

    public void setPassatoControlliGate(Boolean var) {
        esitoControlloGate = var;
    }

    public void setArrivatoAreaArrivi(Boolean var) {
        arrivatoAreaArrivi = var;
    }
}
