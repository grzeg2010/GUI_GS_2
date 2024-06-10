package Logika;

import java.io.Serializable;

public class Praca implements Serializable {
    private final Dane.BazaDanych db;
    private final int numerPracy;

    public enum Rodzaj {Ogolna, Montaz, Demontaz, Wymiana}
    private final Rodzaj rodzajPracy;
    private final int czasPracy;
    private boolean czyZrealizowane;
    private final String opis;

    public Praca(Praca.Rodzaj rodzaj, int czasPracy, String opis) {
        this.db = Dane.BazaDanych.sharedDb;
        db.increaseIloscPrac();
        this.numerPracy = db.getIloscPrac();
        this.rodzajPracy = rodzaj;
        this.czasPracy = czasPracy;
        this.czyZrealizowane = false;
        this.opis = opis;
        db.getMapaPrac().put(numerPracy, this);
    }

    // METHODS
    public String toStringLong() {
        return toString() +
                "\n\tRodzaj: " + rodzajPracy +
                "\n\tCzas wykonywania: " + czasPracy + " min" +
                "\n\tCzy zostalo zrealizowane: " + (czyZrealizowane?"tak":"nie");
    }

    // OVERRIDES
    public String toString() {
        return "<Praca " + numerPracy + "> " + "\"" + opis + "\"";
    }
}
