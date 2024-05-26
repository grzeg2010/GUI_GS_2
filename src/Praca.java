import java.util.HashMap;
import java.util.Map;

public class Praca {
    private static Map<Integer, Praca> mapaPrac = new HashMap<>();
    private final int numerPracy;
    private static int iloscPrac = 0;

    public enum Rodzaj {Ogolna, Montaz, Demontaz, Wymiana}
    private final Rodzaj rodzajPracy;
    private final int czasPracy;
    private boolean czyZrealizowane;
    private final String opis;

    public Praca(Praca.Rodzaj rodzaj, int czasPracy, String opis) {
        this.numerPracy = ++iloscPrac;
        this.rodzajPracy = rodzaj;
        this.czasPracy = czasPracy;
        this.czyZrealizowane = false;
        this.opis = opis;
        mapaPrac.put(numerPracy, this);
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
