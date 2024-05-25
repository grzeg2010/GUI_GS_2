import java.util.HashMap;
import java.util.Map;

public class Uzytkownik extends Pracownik {
    private static Map<Integer, Uzytkownik> mapaUzytkownikow = new HashMap<>();
    private int numerUzytkownika;
    private static int iloscUzytkownikow;

    protected String login, haslo;

    public Uzytkownik(
            String imie, String nazwisko, String dataUrodzenia, DzialPracownikow przypisanyDzial,
            String login, String haslo
    ) {
        super(imie, nazwisko, dataUrodzenia, przypisanyDzial);
        this.numerUzytkownika = ++iloscUzytkownikow;
        this.login = login;
        this.haslo = haslo;
        mapaUzytkownikow.put(numerUzytkownika, this);
    }

    public Uzytkownik(
            String imie, String nazwisko, String dataUrodzenia, int nrPrzypisanegoDzialu,
            String login, String haslo
    ) {
        this(imie, nazwisko, dataUrodzenia, DzialPracownikow.getListaDzialow().get(nrPrzypisanegoDzialu), login, haslo);
    }

    // METHODS
    public String toStringLong() {
        return "<Uzytkownik " + numerUzytkownika + "> " +
                this.login + " | " +
                imie + " " + nazwisko + " | " +
                dataUrodzenia + " | " +
                "Dzial: " + przypisanyDzial;
    }

    // OVERRIDES
    @Override
    public String toString() {
        return "<Uzytkownik " + numerUzytkownika + "> " +
                this.login;
    }

    // GETTERS
    public int getNumerUzytkownika() { return numerUzytkownika; }
}
