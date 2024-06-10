import javax.swing.*;

public class S29792 {
    public static void main(String[] args) {
        Dane.BazaDanych db = new Dane.BazaDanych();
        db.readDbFromFile();

        System.out.println(db.getMapaUzytkownikow().get(1));

        JFrame loginWindow = new Grafika.LoginWindow();
    }
}
