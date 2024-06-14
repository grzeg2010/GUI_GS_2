package Grafika;

import Logika.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Map;

public class Tabs {
    public static JTable utworzTabele(Map<Integer, ?> mapa) {

        String[] columnNames = {
                "ID", "Nazwa"
        };

        System.out.println(mapa.size());
        TableModel tableModel = new DefaultTableModel(columnNames, 0);

        mapa.forEach(((integer, obiekt) -> {
            // integer = integer-1;

            switch (obiekt.getClass().toString()) {
                case "class Logika.Brygada" -> {
                    Brygada castedObject = (Brygada) obiekt;
                    ((DefaultTableModel) tableModel).addRow(new String[] {String.valueOf(castedObject.getNumer()), castedObject.getNazwa()});
                }
                case "class Logika.Brygadzista" -> {
                    Brygadzista castedObject = (Brygadzista) obiekt;
                    ((DefaultTableModel) tableModel).addRow(new String[] {String.valueOf(castedObject.getNumer()), castedObject.getNazwa()});
                }
                case "class Logika.DzialPracownikow" -> {
                    DzialPracownikow castedObject = (DzialPracownikow) obiekt;
                    ((DefaultTableModel) tableModel).addRow(new String[] {String.valueOf(castedObject.getNumer()), castedObject.getNazwa()});
                }
                case "class Logika.Praca" -> {
                    Praca castedObject = (Praca) obiekt;
                    ((DefaultTableModel) tableModel).addRow(new String[] {String.valueOf(castedObject.getNumer()), castedObject.getNazwa()});
                }
                case "class Logika.Pracownik" -> {
                    Pracownik castedObject = (Pracownik) obiekt;
                    ((DefaultTableModel) tableModel).addRow(new String[] {String.valueOf(castedObject.getNumer()), castedObject.getNazwa()});
                }
                case "class Logika.Uzytkownik" -> {
                    Uzytkownik castedObject = (Uzytkownik) obiekt;
                    ((DefaultTableModel) tableModel).addRow(new String[] {String.valueOf(castedObject.getNumer()), castedObject.getNazwa()});
                }
                case "class Logika.Zlecenie" -> {
                    Zlecenie castedObject = (Zlecenie) obiekt;
                    ((DefaultTableModel) tableModel).addRow(new String[] {String.valueOf(castedObject.getNumer()), castedObject.getNazwa()});
                }
                default -> throw new RuntimeException();
            }
        }));

        return new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
    }
}
