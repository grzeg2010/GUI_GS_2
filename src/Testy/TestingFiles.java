package Testy;

import Dane.BazaDanych;
import Logika.AbstractMessages;
import Logika.Brygada;
import Logika.Pracownik;

public class TestingFiles {
    public static void main(String[] args) {
        Dane.BazaDanych db = new BazaDanych();
        db.readDbFromFile();

        System.out.println(AbstractMessages.info("Ilosc brygad: ") + db.getIloscBrygad());
        System.out.println(db.getMapaBrygad().get(1));
        new Brygada("Brygada zapisowa");
        System.out.println("Ilosc brygad: " + db.getIloscBrygad());
        System.out.println(db.getMapaBrygad().get(2));

        System.out.println(AbstractMessages.info("Ilosc brygadzistow: ") + db.getIloscBrygadzistow());
        System.out.println(db.getMapaBrygadzistow().get(1).toStringLong());

        System.out.println(AbstractMessages.info("Ilosc dzialow: ") + db.getIloscDzialow());
        System.out.println(db.getMapaDzialow().get(1));

        System.out.println(AbstractMessages.info("Ilosc prac: ") + db.getIloscPrac());
        System.out.println(db.getMapaPrac().get(1).toStringLong());

        System.out.println(AbstractMessages.info("Ilosc pracownikow: ") + db.getIloscPracownikow());
        db.getMapaPracownikow().forEach(
                (pracownikKey, pracownik) -> System.out.println(db.getMapaPracownikow().get(pracownikKey))
        );

        System.out.println(AbstractMessages.info("Ilosc uzytkownikow: ") + db.getIloscUzytkownikow());
        System.out.println(db.getMapaUzytkownikow().get(1));

        System.out.println(AbstractMessages.info("Ilosc zlecen: ") + db.getIloscZlecen());
        System.out.println(db.getMapaZlecen().get(1));

        db.safeDbToFile();
    }
}
