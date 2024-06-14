package Logika;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pracownik implements Serializable {
    private static final Dane.BazaDanych db = Dane.BazaDanych.sharedDb;
    private int numerPracownika;

    protected String imie, nazwisko;
    protected LocalDate dataUrodzenia;
    protected DzialPracownikow przypisanyDzial;

    public Pracownik(String imie, String nazwisko, String dataUrodzenia, DzialPracownikow przypisanyDzial) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dataUrodzenia = LocalDate.parse(dataUrodzenia, formatter);
        this.przypisanyDzial = przypisanyDzial;
        this.przypisanyDzial.dodajPracownika(this);

        if(this.getClass() == Pracownik.class) {
            db.increaseIloscPracownikow();
            this.numerPracownika = db.getIloscPracownikow();
            db.getMapaPracownikow().put(numerPracownika, this);
        }
    }

    public Pracownik(String imie, String nazwisko, String dataUrodzenia, int numerPrzypisanegoDzialu) {
        this(imie, nazwisko, dataUrodzenia, DzialPracownikow.getListaDzialow().get(numerPrzypisanegoDzialu));
    }

    // METHODS
    public String toStringLong() {
        return "<Pracownik " + numerPracownika + "> " +
                imie + " " + nazwisko + " | " +
                dataUrodzenia + " | " +
                "Dzial: " + przypisanyDzial;
    }

    public void zmienNazwe(String noweImie, String noweNazwisko) {
        this.imie = noweImie;
        this.nazwisko = noweNazwisko;
    }

    public void zmienImie(String noweImie) { this.imie = noweImie; }
    public void zmienNazwisko(String noweNazwisko) { this.imie = noweNazwisko; }

    // OVERRIDES
    @Override
    public String toString() {
        return "<Pracwonik " + numerPracownika + "> " +
                this.imie + " " + this.nazwisko;
    }

    // SETTERS
    public void setImie(String noweImie) { this.imie = noweImie; }
    public void setNazwisko(String noweNazwisko) { this.nazwisko = noweNazwisko; }
    public void setDataUrodzenia(LocalDate nowaData) { this.dataUrodzenia = nowaData; }
    public void setDzial(DzialPracownikow nowyDzial) { this.przypisanyDzial = nowyDzial; }

    // GETTERS
    public String getNazwa() { return imie + nazwisko; }
    public int getNumer() { return numerPracownika; }
    public String getImie() { return imie; }
    public String getNazwisko() { return nazwisko; }
    public LocalDate getDataUrodzenia() { return dataUrodzenia; }
    public DzialPracownikow getDzial() { return przypisanyDzial; }
}
