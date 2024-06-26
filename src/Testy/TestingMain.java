package Testy;

import Dane.BazaDanych;
import Logika.*;

public class TestingMain {
    public static void main(String[] args) {
        Dane.BazaDanych db = new BazaDanych();

        System.out.println(AbstractMessages.divider("TEST DZIALOW"));
        DzialPracownikow dzial1 = DzialPracownikow.createDzial("DzialTestowy");
        DzialPracownikow dzial2 = DzialPracownikow.createDzial("DzialDrugi");
        DzialPracownikow dzial3 = DzialPracownikow.createDzial("DzialTestowy");
        DzialPracownikow dzial4 = DzialPracownikow.createDzial("DzialTrzeci");

        System.out.println(DzialPracownikow.getListaDzialow().values());

        // -------------------------------------------------------------------------------------------------------------
        System.out.println(AbstractMessages.divider("TEST PRACOWNIKOW/UZYTKOWNIKOW"));

        Pracownik pracownik1 = new Pracownik("Adrian", "Z", "1990-05-14", 1);
        Pracownik pracownik2 = new Pracownik("Adrianna", "R", "1996-06-25", 2);
        Uzytkownik uzytkownik1 = new Uzytkownik("Grzegorz", "S", "1999-08-16", 1, "grzeg", "haslo");

        System.out.println(uzytkownik1.toStringLong());

        System.out.println(AbstractMessages.info("Pracownicy dzialu 1: ") + dzial1.getPracownicyDzialu());
        System.out.println(AbstractMessages.info("Pracownicy dzialu 2: ") + dzial2.getPracownicyDzialu());
        System.out.println(AbstractMessages.info("Pracownicy dzialu 3: ") + dzial4.getPracownicyDzialu());

        // -------------------------------------------------------------------------------------------------------------
        System.out.println(AbstractMessages.divider("TEST BRYGADZISTOW"));

        System.out.print(AbstractMessages.info("Przed zmiana imienia i nazwiska: "));
        Brygadzista brygadzista1 = new Brygadzista("Jan", "Zadanie", "1970-01-01", 1, "jaza", "brygadzista");
        Brygadzista brygadzista2 = new Brygadzista("Adam", "Admin", "1970-01-01", 1, "aa", "haslo");
        System.out.println(brygadzista1.toStringLong());

        System.out.print(AbstractMessages.info("\nPo zmianie imienia i nazwiska: "));
        brygadzista1.zmienNazwe("Adam", "Task");
        System.out.println(brygadzista1.toStringLong());

        System.out.println(AbstractMessages.info("\nPracownicy dzialu 1: ") + dzial1.getPracownicyDzialu());

        // -------------------------------------------------------------------------------------------------------------
        System.out.println(AbstractMessages.divider("TEST BRYGAD"));

        Brygada brygada1 = new Brygada("Brig", brygadzista1, dzial1.getPracownicyDzialu());
        brygada1.dodajPracownika(dzial1.getPracownicyDzialu());

        System.out.println(brygada1 + ": " + brygada1.getListaPracownikow());
        System.out.println(brygadzista1.toStringLong());

        // -------------------------------------------------------------------------------------------------------------
        System.out.println(AbstractMessages.divider("TEST ZLECEN"));

        Zlecenie zlecenie1 = new Zlecenie(brygada1, true);
        Zlecenie zlecenie2 = new Zlecenie(brygada1, false);

        System.out.println(zlecenie1);
        System.out.println(zlecenie2);

        System.out.println(zlecenie1.toStringLong());

        // -------------------------------------------------------------------------------------------------------------
        System.out.println(AbstractMessages.divider("TEST PRAC"));

        Praca praca1 = new Praca(Praca.Rodzaj.Ogolna, 15, "Praca testowa");
        zlecenie1.dodajPrace(praca1);

        System.out.println(praca1.toStringLong());
        System.out.println(zlecenie1.toStringLong());

        db.safeDbToFile();
    }
}
