package Logika;

import java.io.Serializable;
import java.util.*;

public class DzialPracownikow implements Serializable {
    private static final Dane.BazaDanych staticDb = Dane.BazaDanych.sharedDb;
    private final Dane.BazaDanych db;
    private final int numerDzialu;

    private String nazwa;

    private Set<Pracownik> pracownicyDzialu; // Set pozwala unikac duplikatow

    private DzialPracownikow(String nazwa) {
        this.db = Dane.BazaDanych.sharedDb;
        db.increaseIloscDzialow();
        this.numerDzialu = db.getIloscDzialow();
        this.nazwa = nazwa;
        pracownicyDzialu = new HashSet<>();
        db.getMapaDzialow().put(numerDzialu, this);
    }

    public static DzialPracownikow createDzial(String nazwa) {
        if(staticDb.getMapaDzialow()
                .values()
                .stream()
                .anyMatch(e -> Objects.equals(e.nazwa, nazwa))
        ) {
            System.out.println(AbstractMessages.warning("[UWAGA!]") +
                    " Dzial o nazwie " +
                    AbstractMessages.gray(nazwa) +
                    " istnieje. Nie utworzono nowego dzialu."
            );
            return null;
        }
        else
            return new DzialPracownikow(nazwa);
    }

    // METHODS
    public void dodajPracownika(Pracownik pracownik) {
        pracownicyDzialu.add(pracownik);
    }

    // OVERRIDES
    @Override
    public String toString() {
        return "<Dzial " + this.numerDzialu + "> " + this.nazwa;
    }

    // SETTERS
    public void setNazwa(String nowaNazwa) {
        this.nazwa = nowaNazwa;
    }

    // GETTERS
    public static Map<Integer, DzialPracownikow> getListaDzialow() { return staticDb.getMapaDzialow(); }
    public String getNazwa() { return nazwa; }
    public int getNumer() { return numerDzialu; }
    public Set<Pracownik> getPracownicyDzialu() {
            return pracownicyDzialu;
    }

}

