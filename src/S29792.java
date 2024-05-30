import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class S29792 {
    public static void main(String[] args) {
        DzialPracownikow dzial1 = DzialPracownikow.createDzial("Dzial1");
        Uzytkownik user1 = new Uzytkownik("Grzegorz", "Sni", "2001-01-01", 1, "grzeg", "abc");

        JFrame loginWindow = new LoginWindow();
    }
}
