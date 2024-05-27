import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Brygadzista extends Uzytkownik {
    private static Map<Integer, Brygadzista> mapaBrygadzistow = new HashMap<>();
    private int numerBrygadzisty;
    private static int iloscBrygadzistow = 0;

    private List<Brygada> przypisaneBrygady;

    public Brygadzista(
            String imie, String nazwisko, String dataUrodzenia, DzialPracownikow przypisanyDzial,
            String login, String haslo
    ) {
        super(imie, nazwisko, dataUrodzenia, przypisanyDzial, login, haslo);
        this.numerBrygadzisty = ++iloscBrygadzistow;
        this.przypisaneBrygady = new ArrayList<>();
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
        return "<Brygadzista " + numerBrygadzisty + "><Uzytkownik " + super.getNumerUzytkownika() + "> " +
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
}
