package Logika;

import java.io.Serializable;
import java.util.*;

public class Brygada implements Serializable {
    private final Dane.BazaDanych db;
    private final int numerBrygady;

    private String nazwa;
    private Brygadzista brygadzista;
    private List<Pracownik> listaPracownikow;

    public Brygada(String nazwa) {
        db = Dane.BazaDanych.sharedDb;
        db.increaseIloscBrygad();
        this.numerBrygady = db.getIloscBrygad();
        this.nazwa = nazwa;
        this.listaPracownikow = new ArrayList<>();
        db.getMapaBrygad().put(numerBrygady, this);
    }

    public Brygada(String nazwa, Brygadzista brygadzista) {
        this(nazwa);
        this.brygadzista = brygadzista;
        this.brygadzista.przypiszDoBrygady(this);
        dodajPracownika(brygadzista);
    }

    public Brygada(String nazwa, Brygadzista brygadzista, List<Pracownik> listaPracownikow) {
        this(nazwa, brygadzista);
        dodajPracownika(listaPracownikow);
    }

    public Brygada(String nazwa, Brygadzista brygadzista, Set<Pracownik> listaPracownikow) {
        this(nazwa, brygadzista);
        dodajPracownika(listaPracownikow);
    }

    // METHODS
    public void dodajPracownika(Pracownik nowyPracownik) {
        if(nowyPracownik.getClass() != Uzytkownik.class) {
            if (this.listaPracownikow.contains(nowyPracownik)) {
                System.out.println(AbstractMessages.info("[INFO]") +
                        " Probowano dodac uzytkownika, ktory jest juz na liscie: " + AbstractMessages.gray(nowyPracownik.toString()) +
                        ". Uzytkownik nie zostal dodany"
                );
            }
            else
                this.listaPracownikow.add(nowyPracownik);
        }
        else {
            System.out.println(AbstractMessages.warning("[UWAGA!]") +
                    " Nie mozna bylo dodac uzytkownika " + AbstractMessages.gray(nowyPracownik.toString()) +
                    ". Zły rodzaj klasy."
            );
        }
    }

    public void dodajPracownika(List<Pracownik> pracownicy) {
        for(Pracownik p : pracownicy)
            dodajPracownika(p);
    }

    public void dodajPracownika(Set<Pracownik> pracownicy) {
        for(Pracownik p : pracownicy)
            dodajPracownika(p);
    }

    // OVERRIDES
    @Override
    public String toString() {
        return "<Brygada " + this.numerBrygady + "> " + this.nazwa;
    }

    // SETTERS
    public void setNazwa(String nowaNazwa) { this.nazwa = nowaNazwa; }
    public void setBrygadzista(Brygadzista nowyBrygadzista) { this.brygadzista = nowyBrygadzista; }

    // GETTERS
    public Brygadzista getBrygadzista() { return this.brygadzista; }
    public List<Pracownik> getListaPracownikow() { return this.listaPracownikow; }
    public String getNazwa() { return this.nazwa; }
    public int getNumer() { return this.numerBrygady; }
}
