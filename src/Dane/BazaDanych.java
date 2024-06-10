package Dane;

import Logika.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BazaDanych implements Serializable {
    public static BazaDanych sharedDb;

    private Map<Integer, Logika.Brygada> mapaBrygad;
    private Map<Integer, Logika.Brygadzista> mapaBrygadzistow;
    private Map<Integer, Logika.DzialPracownikow> mapaDzialow;
    private Map<Integer, Logika.Praca> mapaPrac;
    private Map<Integer, Logika.Pracownik> mapaPracownikow;
    private Map<Integer, Logika.Uzytkownik> mapaUzytkownikow;
    private Map<Integer, Logika.Zlecenie> mapaZlecen;
    private int iloscBrygad, iloscBrygadzistow, iloscDzialow, iloscPrac, iloscPracownikow, iloscUzytkownikow, iloscZlecen;

    public BazaDanych() {
        sharedDb = this;

        mapaBrygad = new HashMap<>();
        mapaBrygadzistow = new HashMap<>();
        mapaDzialow = new HashMap<>();
        mapaPrac = new HashMap<>();
        mapaPracownikow = new HashMap<>();
        mapaUzytkownikow = new HashMap<>();
        mapaZlecen = new HashMap<>();

        iloscBrygad =
                iloscBrygadzistow =
                        iloscDzialow =
                                iloscPrac =
                                        iloscPracownikow =
                                                iloscUzytkownikow =
                                                        iloscZlecen = 0;
    }

    // METHODS
    public void increaseIloscBrygad() { iloscBrygad += 1; }
    public void increaseIloscBrygadzistow() { iloscBrygadzistow += 1; }
    public void increaseIloscDzialow() { iloscDzialow += 1; }
    public void increaseIloscPrac() { iloscPrac += 1; }
    public void increaseIloscPracownikow() { iloscPracownikow += 1; }
    public void increaseIloscUzytkownikow() { iloscUzytkownikow += 1; }
    public void increaseIloscZlecen() { iloscZlecen += 1; }

    public void readDbFromFile() {
        Dane.FileOperations.readFile(this);
    }

    public void safeDbToFile() {
        Dane.FileOperations.safeToFile(this);
    }

    // SETTERS
    public void setMapaBrygad(Map<Integer, Logika.Brygada> nowaMapa) { this.mapaBrygad = nowaMapa; }
    public void setMapaBrygadzistow(Map<Integer, Logika.Brygadzista> nowaMapa) { this.mapaBrygadzistow = nowaMapa; }
    public void setMapaDzialow(Map<Integer, Logika.DzialPracownikow> nowaMapa) { this.mapaDzialow = nowaMapa; }
    public void setMapaPrac(Map<Integer, Logika.Praca> nowaMapa) { this.mapaPrac = nowaMapa; }
    public void setMapaPracownikow(Map<Integer, Logika.Pracownik> nowaMapa) { this.mapaPracownikow = nowaMapa; }
    public void setMapaUzytkownikow(Map<Integer, Logika.Uzytkownik> nowaMapa) { this.mapaUzytkownikow = nowaMapa; }
    public void setMapaZlecen(Map<Integer, Logika.Zlecenie> nowaMapa) { this.mapaZlecen = nowaMapa; }

    public void setIloscBrygad(int nowaIlosc) { this.iloscBrygad = nowaIlosc; }
    public void setIloscBrygadzistow(int nowaIlosc) { this.iloscBrygadzistow = nowaIlosc; }
    public void setIloscDzialow(int nowaIlosc) { this.iloscDzialow = nowaIlosc; }
    public void setIloscPrac(int nowaIlosc) { this.iloscPrac = nowaIlosc; }
    public void setIloscPracownikow(int nowaIlosc) { this.iloscPracownikow = nowaIlosc; }
    public void setIloscUzytkownikow(int nowaIlosc) { this.iloscUzytkownikow = nowaIlosc; }
    public void setIloscZlecen(int nowaIlosc) { this.iloscZlecen = nowaIlosc; }

    // GETTERS
    public Map<Integer, Brygada> getMapaBrygad() { return mapaBrygad; }
    public Map<Integer, Brygadzista> getMapaBrygadzistow() { return mapaBrygadzistow; }
    public Map<Integer, DzialPracownikow> getMapaDzialow() { return mapaDzialow; }
    public Map<Integer, Praca> getMapaPrac() { return mapaPrac; }
    public Map<Integer, Logika.Pracownik> getMapaPracownikow() { return this.mapaPracownikow; }
    public Map<Integer, Uzytkownik> getMapaUzytkownikow() { return mapaUzytkownikow; }
    public Map<Integer, Zlecenie> getMapaZlecen() { return mapaZlecen; }

    public int getIloscBrygad() { return iloscBrygad; }
    public int getIloscBrygadzistow() { return iloscBrygadzistow; }
    public int getIloscDzialow() { return iloscDzialow; }
    public int getIloscPrac() { return iloscPrac; }
    public int getIloscPracownikow() { return  this.iloscPracownikow; }
    public int getIloscUzytkownikow() { return iloscUzytkownikow; }
    public int getIloscZlecen() { return iloscZlecen; }
}
