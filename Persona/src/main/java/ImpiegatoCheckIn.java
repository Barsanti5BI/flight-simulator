import java.security.SecureRandom;

public class ImpiegatoCheckIn extends Persona{

    public ImpiegatoCheckIn(Documento doc){
        super(doc);
    }
    public void run(){

    }
    public String GeneraEtichetta(String AeroportoPartenza, String AeroportoArrivo){
        String codUnivoco = "";
        String etichetta = AeroportoPartenza +" "+ AeroportoArrivo + " CODICE UNIVOCO QUI";

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
    public String GeneraCartaImbarco(/*param*/){
        String cartaImbarco = "";
        //to do
        return cartaImbarco;
    }
    public void SpedicisciBagaglio(/*param*/){

    }
}