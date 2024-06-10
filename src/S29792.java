public class S29792 {
    public static void main(String[] args) {
        Dane.BazaDanych db = new Dane.BazaDanych();
        db.readDbFromFile();

        new Grafika.LoginWindow();
    }
}
