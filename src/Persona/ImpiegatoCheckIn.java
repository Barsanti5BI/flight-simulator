package Persona;

import java.security.SecureRandom;

public class ImpiegatoCheckIn extends Persona{

    public ImpiegatoCheckIn(Documento doc){
        super(doc);
    }
    public void run(){
        System.out.println(GeneraEtichetta("partenza", "arrivo"));
    }
    public String GeneraEtichetta(String AeroportoPartenza, String AeroportoArrivo){
        String codUnivoco = generaCodiceUnivoco(15);
        String etichetta = AeroportoPartenza +" "+ AeroportoArrivo + " " + codUnivoco;

        return etichetta;
    }
    public String generaCodiceUnivoco(int length) {

        String caratteri = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(caratteri.length());
            code.append(caratteri.charAt(randomIndex));
        }

        return code.toString();
    }
    public String GeneraCartaImbarco(String partenza, String destinazione, int length){
        String cartaImbarco = "";
        String caratteri = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(caratteri.length());
            code.append(caratteri.charAt(randomIndex));
        }

        cartaImbarco = partenza + "-" + code.toString() + "-" + destinazione;
        return cartaImbarco;
    }
    public void SpedicisciBagaglio(/*param*/){

    }
}
