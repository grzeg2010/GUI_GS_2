package Logika;

import java.io.Serializable;

public class Praca implements Serializable {
    private final Dane.BazaDanych db;
    private final int numerPracy;

    public enum Rodzaj {Ogolna, Montaz, Demontaz, Wymiana}
    private Rodzaj rodzajPracy;
    private int czasPracy;
    private boolean czyZrealizowane;
    private String opis;

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
    @Override
    public String toString() {
        return "<Praca " + numerPracy + "> " + "\"" + opis + "\"";
    }

    // SETTERS
    public void setOpis(String nowyOpis) { this.opis = nowyOpis; }
    public void setRodzajPracy(Rodzaj nowyRodzaj) { this.rodzajPracy = nowyRodzaj; }
    public void setCzasPracy(int nowyCzas) { this.czasPracy = nowyCzas; }
    public void setCzyZrealizowane(boolean nowyStatus) { this.czyZrealizowane = nowyStatus; }

    // GETTERS
    public String getNazwa() { return opis; }
    public int getNumer() { return numerPracy; }
    public Rodzaj getRodzaj() { return rodzajPracy; }
    public boolean getStatus() { return czyZrealizowane; }
    public int getCzas() { return czasPracy; }
}
