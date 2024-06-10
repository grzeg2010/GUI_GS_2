package Grafika;

import Logika.*;

import javax.swing.*;
import java.util.Map;

public class Tabs {
    public static JTable utworzTabele(Map<Integer, ?> mapa, int iloscObiektow) {
        String[] columnNames = {
                "ID", "Nazwa"
        };

        String[][] daneObiektow = new String[iloscObiektow][2];

        mapa.forEach(((integer, obiekt) -> {
            System.out.println(obiekt.getClass().toString()); // TODO for debugging purposes

            switch (obiekt.getClass().toString()) {
                case "class Logika.Brygada" -> {
                    Brygada castedObject = (Brygada) obiekt;
                    daneObiektow[integer - 1] = new String[]{ String.valueOf(castedObject.getNumer()), castedObject.getNazwa() };
                }
                case "class Logika.Brygadzista" -> {
                    Brygadzista castedObject = (Brygadzista) obiekt;
                    daneObiektow[integer - 1] = new String[]{ String.valueOf(castedObject.getNumer()), castedObject.getNazwa() };
                }
                case "class Logika.DzialPracownikow" -> {
                    DzialPracownikow castedObject = (DzialPracownikow) obiekt;
                    daneObiektow[integer - 1] = new String[]{ String.valueOf(castedObject.getNumer()), castedObject.getNazwa() };
                }
                case "class Logika.Praca" -> {
                    Praca castedObject = (Praca) obiekt;
                    daneObiektow[integer - 1] = new String[]{ String.valueOf(castedObject.getNumer()), castedObject.getNazwa() };
                }
                case "class Logika.Pracownik" -> {
                    Pracownik castedObject = (Pracownik) obiekt;
                    daneObiektow[integer - 1] = new String[]{ String.valueOf(castedObject.getNumer()), castedObject.getNazwa() };
                }
                case "class Logika.Uzytkownik" -> {
                    Uzytkownik castedObject = (Uzytkownik) obiekt;
                    daneObiektow[integer - 1] = new String[]{ String.valueOf(castedObject.getNumer()), castedObject.getNazwa() };
                }
                case "class Logika.Zlecenie" -> {
                    Zlecenie castedObject = (Zlecenie) obiekt;
                    daneObiektow[integer - 1] = new String[]{ String.valueOf(castedObject.getNumer()), castedObject.getNazwa() };
                }
                default -> throw new RuntimeException();
            }
        }));

        return new JTable(daneObiektow, columnNames);
    }
}
