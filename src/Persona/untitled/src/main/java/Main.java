import javax.tools.DocumentationTool;
import java.awt.*;
import java.util.Date;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Date date = new Date();
        Documento d = new Documento("","","",date,"","","","");
        Thread c = new ImpiegatoCheckIn(d);
        c.start();
    }
}