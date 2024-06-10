import Dane.BazaDanych;
import Grafika.LoginWindow;
import Logika.DzialPracownikow;
import Logika.Uzytkownik;

import javax.swing.*;

public class S29792 {
    public static void main(String[] args) {
        Dane.BazaDanych db = new BazaDanych();

        DzialPracownikow dzial1 = DzialPracownikow.createDzial("Dzial1");
        Uzytkownik user1 = new Uzytkownik("Grzegorz", "Sni", "2001-01-01", 1, "grzeg", "abc");

        JFrame loginWindow = new LoginWindow();
    }
}
