import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Pracownik {
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
    }
}
