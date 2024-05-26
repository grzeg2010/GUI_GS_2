import java.util.List;

public class TestingMain {
    public static void main(String[] args) {
        System.out.println(AbstractMessages.divider("TEST DZIALOW"));
        DzialPracownikow dzial1 = DzialPracownikow.createDzial("DzialTestowy");
        DzialPracownikow dzial2 = DzialPracownikow.createDzial("DzialDrugi");
        DzialPracownikow dzial3 = DzialPracownikow.createDzial("DzialTestowy");
        DzialPracownikow dzial4 = DzialPracownikow.createDzial("DzialTrzeci");

        System.out.println(DzialPracownikow.getListaDzialow().values());

        // -------------------------------------------------------------------------------------------------------------
        System.out.println(AbstractMessages.divider("TEST PRACOWNIKOW/UZYTKOWNIKOW"));

        Pracownik pracownik1 = new Pracownik("Adrian", "Z", "1990-05-14", 1);
        Uzytkownik uzytkownik1 = new Uzytkownik("Grzegorz", "S", "1999-08-16", 1, "grzeg", "haslo");

        System.out.println(uzytkownik1.toStringLong());

        System.out.println(AbstractMessages.info("Pracownicy dzialu 1: ") + dzial1.getPracownicyDzialu());
        System.out.println(AbstractMessages.info("Pracownicy dzialu 2: ") + dzial2.getPracownicyDzialu());
        System.out.println(AbstractMessages.info("Pracownicy dzialu 3: ") + dzial4.getPracownicyDzialu());

        // -------------------------------------------------------------------------------------------------------------
        System.out.println(AbstractMessages.divider("TEST BRYGADZISTOW"));

        Brygadzista brygadzista1 = new Brygadzista("Jan", "Zadanie", "1970-01-01", 1, "jaza", "brygadzista");
        System.out.println(brygadzista1.toStringLong());

        System.out.println(AbstractMessages.info("Pracownicy dzialu 1: ") + dzial1.getPracownicyDzialu());

        // -------------------------------------------------------------------------------------------------------------
        System.out.println(AbstractMessages.divider("TEST BRYGAD"));

        Brygada brygada1 = new Brygada("Brig", brygadzista1, dzial1.getPracownicyDzialu());
        brygada1.dodajPracownika(dzial1.getPracownicyDzialu());

        System.out.println(brygada1 + ": " + brygada1.getListaPracownikow());
    }
}
