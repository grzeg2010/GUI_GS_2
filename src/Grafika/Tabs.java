package Grafika;

import Logika.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Map;

public class Tabs {
    public static JTable utworzTabele(Map<Integer, ?> mapa, int iloscObiektow) {
        String[] columnNames = {
                "ID", "Nazwa"
        };

        // String[][] daneObiektow = new String[iloscObiektow][2];

        TableModel tableModel = new DefaultTableModel(columnNames, iloscObiektow);


        mapa.forEach(((integer, obiekt) -> {
            // System.out.println(obiekt.getClass().toString()); // TODO for debugging purposes
            integer = integer-1;

            switch (obiekt.getClass().toString()) {
                case "class Logika.Brygada" -> {
                    Brygada castedObject = (Brygada) obiekt;
                    // daneObiektow[integer - 1] = new String[]{ String.valueOf(castedObject.getNumer()), castedObject.getNazwa() };
                    tableModel.setValueAt(castedObject.getNumer(), integer, 0);
                    tableModel.setValueAt(castedObject.getNazwa(), integer, 1);
                }
                case "class Logika.Brygadzista" -> {
                    Brygadzista castedObject = (Brygadzista) obiekt;
                    // daneObiektow[integer - 1] = new String[]{ String.valueOf(castedObject.getNumer()), castedObject.getNazwa() };
                    tableModel.setValueAt(castedObject.getNumer(), integer, 0);
                    tableModel.setValueAt(castedObject.getNazwa(), integer, 1);
                }
                case "class Logika.DzialPracownikow" -> {
                    DzialPracownikow castedObject = (DzialPracownikow) obiekt;
                    // daneObiektow[integer - 1] = new String[]{ String.valueOf(castedObject.getNumer()), castedObject.getNazwa() };
                    tableModel.setValueAt(castedObject.getNumer(), integer, 0);
                    tableModel.setValueAt(castedObject.getNazwa(), integer, 1);
                }
                case "class Logika.Praca" -> {
                    Praca castedObject = (Praca) obiekt;
                    // daneObiektow[integer - 1] = new String[]{ String.valueOf(castedObject.getNumer()), castedObject.getNazwa() };
                    tableModel.setValueAt(castedObject.getNumer(), integer, 0);
                    tableModel.setValueAt(castedObject.getNazwa(), integer, 1);
                }
                case "class Logika.Pracownik" -> {
                    Pracownik castedObject = (Pracownik) obiekt;
                    // daneObiektow[integer - 1] = new String[]{ String.valueOf(castedObject.getNumer()), castedObject.getNazwa() };
                    tableModel.setValueAt(castedObject.getNumer(), integer, 0);
                    tableModel.setValueAt(castedObject.getNazwa(), integer, 1);
                }
                case "class Logika.Uzytkownik" -> {
                    Uzytkownik castedObject = (Uzytkownik) obiekt;
                    // daneObiektow[integer - 1] = new String[]{ String.valueOf(castedObject.getNumer()), castedObject.getNazwa() };
                    tableModel.setValueAt(castedObject.getNumer(), integer, 0);
                    tableModel.setValueAt(castedObject.getNazwa(), integer, 1);
                }
                case "class Logika.Zlecenie" -> {
                    Zlecenie castedObject = (Zlecenie) obiekt;
                    // daneObiektow[integer - 1] = new String[]{ String.valueOf(castedObject.getNumer()), castedObject.getNazwa() };
                    tableModel.setValueAt(castedObject.getNumer(), integer, 0);
                    tableModel.setValueAt(castedObject.getNazwa(), integer, 1);
                }
                default -> throw new RuntimeException();
            }
        }));

        return new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
    }
}
