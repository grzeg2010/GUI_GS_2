import java.util.*;

public class Brygada {
    private static Map<Integer, Brygada> mapaBrygad = new HashMap<>();
    private final int numerBrygady;
    private static int iloscBrygad = 0;

    private String nazwa;
    private Brygadzista brygadzista;
    private List<Pracownik> listaPracownikow;

    public Brygada(String nazwa) {
        this.numerBrygady = ++iloscBrygad;
        this.nazwa = nazwa;
        this.listaPracownikow = new ArrayList<>();
        mapaBrygad.put(numerBrygady, this);
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
        return "<Brygada " + numerBrygady + "> " + nazwa;
    }

    // GETTERS
    public List<Pracownik> getListaPracownikow() { return listaPracownikow; }
}
