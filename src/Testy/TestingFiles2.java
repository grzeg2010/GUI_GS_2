package Testy;

import Dane.BazaDanych;
import Logika.AbstractMessages;

public class TestingFiles2 {
    public static void main(String[] args) {
        BazaDanych db = new BazaDanych();
        db.readDbFromFile();

        System.out.println(AbstractMessages.info("Ilosc brygad: ") + db.getIloscBrygad());
        System.out.println(db.getMapaBrygad().get(1));
        System.out.println("Ilosc brygad: " + db.getIloscBrygad());
        System.out.println(db.getMapaBrygad().get(2));

        db.safeDbToFile();
    }
}
