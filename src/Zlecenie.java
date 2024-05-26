import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zlecenie {
    private static Map<Integer, Zlecenie> mapaZlecen = new HashMap<>();
    private final int numerZlecenia;
    private static int iloscZlecen = 0;

    private List<Praca> listaPrac;
    private Brygada brygada;
    private enum Stan {Planowane, Nieplanowane, Realizowane, Zakonczone}
    private Stan stanZlecenia;
    private final LocalDateTime dataUtworzenia;
    private LocalDateTime dataRealizacji, dataZakonczenia;

    public Zlecenie(Brygada brygada, boolean czyPlanowane) {
        this.numerZlecenia = ++iloscZlecen;
        this.listaPrac = new ArrayList<>();
        this.brygada = brygada;

        if(czyPlanowane)
            this.stanZlecenia = Stan.Planowane;
        else
            this.stanZlecenia = Stan.Nieplanowane;

        this.dataUtworzenia = LocalDateTime.now();
        mapaZlecen.put(numerZlecenia, this);
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

    // OVERRIDES
    @Override
    public String toString() {
        return "<Zlecenie " + numerZlecenia + "> wykonywane przez: " + brygada;
    }
}
