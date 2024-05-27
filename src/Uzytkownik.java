import java.util.HashMap;
import java.util.Map;

public class Uzytkownik extends Pracownik {
    private static Map<Integer, Uzytkownik> mapaUzytkownikow = new HashMap<>();
    private final int numerUzytkownika;
    private static int iloscUzytkownikow;

    protected String login, haslo, inicjal;

    public Uzytkownik(
            String imie, String nazwisko, String dataUrodzenia, DzialPracownikow przypisanyDzial,
            String login, String haslo
    ) {
        super(imie, nazwisko, dataUrodzenia, przypisanyDzial);
        this.numerUzytkownika = ++iloscUzytkownikow;
        this.login = login;
        this.haslo = haslo;
        ustawInicjal();
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
                imie + " " + nazwisko + " " + inicjal + " | " +
                dataUrodzenia + " | " +
                "Dzial: " + przypisanyDzial;
    }

    public void ustawInicjal() {
        inicjal = "" + super.imie.charAt(0);
        inicjal += super.nazwisko.charAt(0);
    }

    // OVERRIDES
    @Override
    public String toString() {
        return "<Uzytkownik " + numerUzytkownika + "> " +
                this.login;
    }

    @Override
    public void zmienNazwe(String noweImie, String noweNazwisko) {
        super.zmienNazwe(noweImie, noweNazwisko);
        this.ustawInicjal();
    }

    @Override
    public void zmienImie(String noweImie) {
        super.zmienImie(noweImie);
        this.ustawInicjal();
    }

    @Override
    public void zmienNazwisko(String noweNazwisko) {
        super.zmienNazwisko(noweNazwisko);
        this.ustawInicjal();
    }

    // GETTERS
    public int getNumerUzytkownika() { return numerUzytkownika; }
}
