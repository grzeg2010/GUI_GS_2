package Logika;

import java.io.Serializable;
import java.util.*;

public class Brygadzista extends Uzytkownik implements Serializable {
    private Dane.BazaDanych db;
    private final int numerBrygadzisty;

    private List<Brygada> przypisaneBrygady;

    public Brygadzista(
            String imie, String nazwisko, String dataUrodzenia, DzialPracownikow przypisanyDzial,
            String login, String haslo
    ) {
        super(imie, nazwisko, dataUrodzenia, przypisanyDzial, login, haslo);
        db = Dane.BazaDanych.sharedDb;
        db.increaseIloscBrygadzistow();
        this.numerBrygadzisty = db.getIloscBrygadzistow();
        this.przypisaneBrygady = new ArrayList<>();
        db.getMapaBrygadzistow().put(numerBrygadzisty, this);
    }

    public Brygadzista(
            String imie, String nazwisko, String dataUrodzenia, int nrPrzypisanegoDzialu,
            String login, String haslo
    ) {
        this(imie, nazwisko, dataUrodzenia, DzialPracownikow.getListaDzialow().get(nrPrzypisanegoDzialu), login, haslo);
    }

    // METHODS
    public void przypiszDoBrygady(Brygada nowaBrygada) { this.przypisaneBrygady.add(nowaBrygada); }

    public String toStringLong() {
        return "<Brygadzista " + numerBrygadzisty + "><Uzytkownik " + super.getNumer() + "> " +
                login + " | " +
                imie + " " + nazwisko + " " + inicjal + " | " +
                dataUrodzenia +
                "\n\tDzial: " + przypisanyDzial +
                "\n\tBrygady: " + przypisaneBrygady;
    }

    // OVERRIDES
    @Override
    public String toString() {
        return "<Brygadzista " + numerBrygadzisty + "> " +
                login;
    }

    // GETTERS
    public int getNumer() { return numerBrygadzisty; }
}
