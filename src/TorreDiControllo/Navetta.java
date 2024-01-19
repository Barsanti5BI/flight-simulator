//package TorreDiControllo;
//
//public class Navetta extends Thread {
//    public Gate gate;
//    public Aereo aereo;
//    public Coda<Turista> codaTuristi;
//
//
//    public void navettaAndata()
//    {
//        //prendo la coda dei turisti dal gate
//        codaTuristi = gate.GetCodaTurista();
//
//        //la navetta porta i turisti all'aereo
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            return;
//        }
//
//        //prendo la coda dei turisti e la metto nell'entrata dell'aereo
//        aereo.getEntrata().DareEntranti(codaTuristi);
//    }
//
//    public void navettaRitorno(){
//        //prendo la coda dei turisti dall'uscita dell'aereo
//        aereo.getUscita().Getusciti();
//
//        //la navetta porta i turisti all'aereoporto
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            return;
//        }
//
//        //passare i turisti all'aeroporto
//    }
//}
