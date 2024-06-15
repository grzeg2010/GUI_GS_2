package Logika;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Zlecenie implements Serializable {
    private final Dane.BazaDanych db;
    private final int numerZlecenia;

    private List<Praca> listaPrac;
    private Brygada brygada;
    private enum Stan {Planowane, Nieplanowane, Realizowane, Zakonczone}
    private Stan stanZlecenia;
    private final LocalDateTime dataUtworzenia;
    private LocalDateTime dataRealizacji, dataZakonczenia;

    public Zlecenie(Brygada brygada, boolean czyPlanowane) {
        this.db = Dane.BazaDanych.sharedDb;
        db.increaseIloscZlecen();
        this.numerZlecenia = db.getIloscZlecen();
        this.listaPrac = new ArrayList<>();
        this.brygada = brygada;

        if(czyPlanowane)
            this.stanZlecenia = Stan.Planowane;
        else
            this.stanZlecenia = Stan.Nieplanowane;

        this.dataUtworzenia = LocalDateTime.now();
        db.getMapaZlecen().put(numerZlecenia, this);
    }

    // METHODS
    public void dodajPrace(Praca nowaPraca) {
        listaPrac.add(nowaPraca);
    }

    public String toStringLong() {
        return toString() +
                "\n\tStan: " + stanZlecenia +
                "\n\tData utworzenia: " + dataUtworzenia +
                "\n\tData realizacji: " + dataRealizacji +
                "\n\tData zakonczenia: " + dataZakonczenia +
                "\n\tLista prac: " + listaPrac;
    }

    public void zakonczZlecenie() {
        listaPrac.forEach(Praca::zakonczPrace);

        dataZakonczenia = LocalDateTime.now();
        stanZlecenia = Stan.Zakonczone;
    }

    // OVERRIDES
    @Override
    public String toString() {
        return "<Logika.Zlecenie " + numerZlecenia + "> wykonywane przez: " + brygada;
    }

    // SETTERS
    public void setDataRealizacji(LocalDateTime nowaDataRealizacji) { this.dataRealizacji = nowaDataRealizacji; }

    // GETTERS
    public String getNazwa() { return listaPrac.toString(); }
    public int getNumer() { return numerZlecenia; }
    public List<Praca> getListaPrac() { return listaPrac; }
    public LocalDateTime getDataRealizacji() { return dataRealizacji; }
}
