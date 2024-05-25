import java.util.*;

public class DzialPracownikow {
    private static final Map<Integer, DzialPracownikow> mapaDzialow = new HashMap<>();
    private final int numerDzialu;
    private static int iloscDzialow = 0;

    private final String nazwa;

    private Set<Pracownik> pracownicyDzialu; // Set pozwala unikac duplikatow

    private DzialPracownikow(String nazwa) {
        this.numerDzialu = ++iloscDzialow;
        this.nazwa = nazwa;
        pracownicyDzialu = new HashSet<>();
        mapaDzialow.put(numerDzialu, this);
    }

    public static DzialPracownikow createDzial(String nazwa) {
        if(mapaDzialow
                .values()
                .stream()
                .anyMatch(e -> Objects.equals(e.nazwa, nazwa))
        ) {
            System.out.println(AbstractMessages.warning("[UWAGA!]") +
                    " Dzial o nazwie " +
                    AbstractMessages.gray(nazwa) +
                    " istnieje. Nie utworzono nowego dzialu."
            );
            return null;
        }
        else
            return new DzialPracownikow(nazwa);
    }

    // METHODS
    public void dodajPracownika(Pracownik pracownik) {
        pracownicyDzialu.add(pracownik);
    }

    // OVERRIDES
    @Override
    public String toString() {
        return "<Dzial " + this.numerDzialu + "> " + this.nazwa;
    }

    // GETTERS
    public static Map<Integer, DzialPracownikow> getListaDzialow() { return mapaDzialow; }

    public String getPracownicyDzialu() {
        if(pracownicyDzialu.isEmpty())
            return "[" + AbstractMessages.gray("BRAK") + "]";
        else
            return pracownicyDzialu.toString();
    }
}

