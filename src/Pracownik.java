import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Pracownik {
    private static Map<Integer, Pracownik> mapaPracownikow = new HashMap<>();
    private final int numerPracownika;
    private static int iloscPracownikow = 0;

    protected String imie, nazwisko;
    protected LocalDate dataUrodzenia;
    protected DzialPracownikow przypisanyDzial;

    public Pracownik(String imie, String nazwisko, String dataUrodzenia, DzialPracownikow przypisanyDzial) {
        this.numerPracownika = ++iloscPracownikow;
        this.imie = imie;
        this.nazwisko = nazwisko;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dataUrodzenia = LocalDate.parse(dataUrodzenia, formatter);
        this.przypisanyDzial = przypisanyDzial;
        this.przypisanyDzial.dodajPracownika(this);
        mapaPracownikow.put(numerPracownika, this);
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

    // OVERRIDES
    @Override
    public String toString() {
        return "<Pracwonik " + numerPracownika + "> " +
                this.imie + " " + this.nazwisko;
    }

}
