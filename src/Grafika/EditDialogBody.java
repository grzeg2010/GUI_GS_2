package Grafika;

import Dane.BazaDanych;
import Logika.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;

public class EditDialogBody extends JDialog {
    protected static LocalDate sharedDateVar;
    private int numerObiektu;

    private JPanel rNumer = new JPanel();
    private JLabel lNumer = new JLabel("Numer: ");
    private JTextField fNumer = new JTextField();

    private JPanel rNazwa = new JPanel();
    private JLabel lNazwa = new JLabel("Nazwa: ");
    private JTextField fNazwa = new JTextField();

    private JPanel rNazwisko = new JPanel();
    private JLabel lNazwisko = new JLabel("Nazwisko: ");
    private JTextField fNazwisko = new JTextField();

    private JPanel rLogin = new JPanel();
    private JLabel lLogin = new JLabel("Login: ");
    private JTextField fLogin = new JTextField();

    private JPanel rHaslo = new JPanel();
    private JLabel lHaslo = new JLabel("Hasło: ");
    private JPasswordField fHaslo = new JPasswordField();

    private JPanel rDataUrodzenia = new JPanel();
    private JLabel lDataUrodzenia = new JLabel("Data urodzenia: ");
    private JTextField fDataUrodzenia = new JTextField();

    private JPanel rDzial = new JPanel();
    private JLabel lDzial = new JLabel("Dział: ");
    private JComboBox<DzialPracownikow> cDzial;

    private JButton bZapisz = new JButton("Zapisz");

    private void addRepeatingElements(int numerObiektu, boolean isNewObject) {
        this.setModal(true);
        if(!isNewObject)
            this.setTitle("Edycja");
        else
            this.setTitle("Dodawanie");
        this.setSize(300, 400);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        rNumer.add(lNumer);
        if(!isNewObject)
            fNumer.setText(String.valueOf(numerObiektu));
        else
            fNumer.setText(String.valueOf(BazaDanych.sharedDb.getIloscBrygad() + 1));
        fNumer.setEditable(false);
        fNumer.setColumns(5);
        rNumer.add(fNumer);

        this.add(rNumer);
    }

    public EditDialogBody(Map<Integer, ?> mapa, JTable currentTable, boolean isNewObject) {
        numerObiektu = -1;
        if(currentTable.getSelectedRow() != -1)
            numerObiektu = Integer.parseInt(currentTable.getValueAt(currentTable.getSelectedRow(), 0).toString());

        switch (Window.centerPanel.getTitleAt(Window.centerPanel.getSelectedIndex())) {
            case "Brygada" -> {
                Brygada currentObject = (Brygada) mapa.get(numerObiektu);

                JPanel rBrygadzista = new JPanel();
                JLabel lBrygadzista = new JLabel("Brygadzista: ");
                Brygadzista[] listaBrygadzistow = new Brygadzista[BazaDanych.sharedDb.getIloscBrygadzistow()];
                BazaDanych.sharedDb.getMapaBrygadzistow().forEach(((integer, brygadzista) -> listaBrygadzistow[integer - 1] = brygadzista));
                JComboBox<Brygadzista> cBrygadzista = new JComboBox<>(listaBrygadzistow);
                if(!isNewObject)
                    cBrygadzista.setSelectedItem(currentObject.getBrygadzista());

                this.addRepeatingElements(numerObiektu, isNewObject);

                rNazwa.add(lNazwa);
                if(!isNewObject)
                    fNazwa.setText(currentObject.getNazwa());
                fNazwa.setColumns(20);
                rNazwa.add(fNazwa);
                this.add(rNazwa);

                rBrygadzista.add(lBrygadzista);
                rBrygadzista.add(cBrygadzista);
                this.add(rBrygadzista);

                bZapisz.addActionListener(e -> {
                    if(!isNewObject) {
                        currentObject.setNazwa(fNazwa.getText());
                        currentObject.setBrygadzista((Brygadzista) cBrygadzista.getSelectedItem());
                        currentTable.getModel().setValueAt(fNazwa.getText(), currentTable.getSelectedRow(), 1);
                    } else {
                        Brygada nowaBrygada = new Brygada(fNazwa.getText(), (Brygadzista) cBrygadzista.getSelectedItem());
                        ((DefaultTableModel) currentTable.getModel()).addRow(new String[]{String.valueOf(nowaBrygada.getNumer()), nowaBrygada.getNazwa()});
                    }
                    Window.db.safeDbToFile();
                    this.dispose();
                });
            }
            case "Brygadzista" -> {
                Brygadzista currentObject = (Brygadzista) mapa.get(numerObiektu);

                this.addRepeatingElements(numerObiektu, isNewObject);

                lNazwa.setText("Imię: ");
                rNazwa.add(lNazwa);
                if(!isNewObject)
                    fNazwa.setText(currentObject.getImie());
                fNazwa.setColumns(20);
                rNazwa.add(fNazwa);
                this.add(rNazwa);

                rNazwisko.add(lNazwisko);
                if(!isNewObject)
                    fNazwisko.setText(currentObject.getNazwisko());
                fNazwisko.setColumns(20);
                rNazwisko.add(fNazwisko);
                this.add(rNazwisko);

                rLogin.add(lLogin);
                if(!isNewObject)
                    fLogin.setText(currentObject.getLogin());
                fLogin.setColumns(20);
                rLogin.add(fLogin);
                this.add(rLogin);

                if(isNewObject) {
                    rHaslo.add(lHaslo);
                    fHaslo.setColumns(20);
                    rHaslo.add(fHaslo);
                    this.add(rHaslo);
                }

                rDataUrodzenia.add(lDataUrodzenia);
                if(!isNewObject)
                    fDataUrodzenia.setText(String.valueOf(currentObject.getDataUrodzenia()));
                fDataUrodzenia.setColumns(12);
                fDataUrodzenia.setEditable(false);
                JButton bKalendarz = new JButton("Kalendarz");
                bKalendarz.addActionListener(e -> {
                    new DateEditor(currentObject);
                });
                rDataUrodzenia.add(fDataUrodzenia);
                rDataUrodzenia.add(bKalendarz);
                this.add(rDataUrodzenia);

                DzialPracownikow[] listaDzialow = new DzialPracownikow[BazaDanych.sharedDb.getMapaDzialow().size()];
                System.out.println(BazaDanych.sharedDb.getMapaDzialow());
                BazaDanych.sharedDb.getMapaDzialow().forEach((integer, dzialPracownikow) -> listaDzialow[integer - 1] = dzialPracownikow );

                cDzial = new JComboBox<>(listaDzialow);
                if(!isNewObject)
                    cDzial.setSelectedItem(currentObject.getDzial());

                rDzial.add(lDzial);
                rDzial.add(cDzial);
                this.add(rDzial);

                bZapisz.addActionListener(e -> {
                    if(!isNewObject) {
                        currentObject.zmienNazwe(fNazwa.getText(), fNazwisko.getText());
                        // TODO data urodzenia
                        currentObject.setDzial((DzialPracownikow) cDzial.getSelectedItem());
                        currentTable.getModel().setValueAt(currentObject.getNazwa(), currentTable.getSelectedRow(), 1);
                    } else {
                        StringBuilder noweHaslo = new StringBuilder();
                        for (char c : fHaslo.getPassword()) { noweHaslo.append(c); }

                        Brygadzista nowyObiekt = new Brygadzista(
                                fNazwa.getText(),
                                fNazwisko.getText(),
                                "1970-01-01", // TODO
                                (DzialPracownikow)cDzial.getSelectedItem(),
                                fLogin.getText(),
                                noweHaslo.toString()
                        );

                        ((DefaultTableModel) currentTable.getModel()).addRow(new String[]{String.valueOf(nowyObiekt.getNumer()), nowyObiekt.getNazwa()});
                    }
                    Window.db.safeDbToFile();
                    this.dispose();
                });
            }
            case "Dział pracowników" -> {
                DzialPracownikow currentObject = (DzialPracownikow) mapa.get(numerObiektu);

                this.addRepeatingElements(numerObiektu, isNewObject);

                rNazwa.add(lNazwa);
                if(!isNewObject)
                    fNazwa.setText(currentObject.getNazwa());
                fNazwa.setColumns(20);
                rNazwa.add(fNazwa);
                this.add(rNazwa);

                bZapisz.addActionListener(e -> {
                    if(!isNewObject) {
                        currentObject.setNazwa(fNazwa.getText());
                        currentTable.getModel().setValueAt(currentObject.getNazwa(), currentTable.getSelectedRow(), 1);
                    } else {
                        DzialPracownikow nowyObiekt = DzialPracownikow.createDzial(fNazwa.getText());
                        ((DefaultTableModel) currentTable.getModel()).addRow(new String[]{String.valueOf(nowyObiekt.getNumer()), nowyObiekt.getNazwa()});
                    }
                    Window.db.safeDbToFile();
                    this.dispose();
                });
            }
            case "Praca" -> {
                Praca currentObject = (Praca) mapa.get(numerObiektu);

                this.addRepeatingElements(numerObiektu, isNewObject);

                JTextArea aOpis = new JTextArea();

                lNazwa.setText("Opis: ");
                rNazwa.add(lNazwa);
                if(!isNewObject)
                    aOpis.setText(currentObject.getNazwa());
                aOpis.setColumns(20);
                aOpis.setRows(3);
                rNazwa.add(aOpis);
                this.add(rNazwa);

                JPanel rRodzaj = new JPanel();
                JLabel lRodzaj = new JLabel("Rodzaj: ");
                JComboBox<Praca.Rodzaj> cRodzaj;


                Praca.Rodzaj[] listaRodzajow = {Praca.Rodzaj.Ogolna, Praca.Rodzaj.Montaz, Praca.Rodzaj.Demontaz, Praca.Rodzaj.Wymiana};
                cRodzaj = new JComboBox<>(listaRodzajow);
                if(!isNewObject)
                    cRodzaj.setSelectedItem(currentObject.getRodzaj());

                rRodzaj.add(lRodzaj);
                rRodzaj.add(cRodzaj);
                this.add(rRodzaj);

                JPanel rCzas = new JPanel();
                JLabel lCzas = new JLabel("Czas: ");
                NumberFormat integerNumberInstance = NumberFormat.getIntegerInstance();
                JFormattedTextField fCzas = new JFormattedTextField(integerNumberInstance);

                rCzas.add(lCzas);
                if(!isNewObject)
                    fCzas.setText(String.valueOf(currentObject.getCzas()));
                fCzas.setColumns(10);
                rCzas.add(fCzas);
                this.add(rCzas);

                JPanel rStatus = new JPanel();
                JLabel lStatus = new JLabel("Czy zrealizowane: ");
                JCheckBox cStatus = new JCheckBox();

                rStatus.add(lStatus);
                if(!isNewObject)
                    cStatus.setSelected(currentObject.getStatus());
                rStatus.add(cStatus);
                this.add(rStatus);

                bZapisz.addActionListener(e -> {
                    if(!isNewObject) {
                        currentObject.setOpis(aOpis.getText());
                        currentObject.setCzasPracy(Integer.parseInt(fCzas.getText()));
                        currentObject.setCzyZrealizowane(cStatus.isSelected());
                        currentObject.setRodzajPracy((Praca.Rodzaj) cRodzaj.getSelectedItem());
                        currentTable.getModel().setValueAt(currentObject.getNazwa(), currentTable.getSelectedRow(), 1);
                    } else {
                        Praca nowyObiekt = new Praca((Praca.Rodzaj) cRodzaj.getSelectedItem(), Integer.parseInt(fCzas.getText()), aOpis.getText());
                        ((DefaultTableModel) currentTable.getModel()).addRow(new String[]{String.valueOf(nowyObiekt.getNumer()), nowyObiekt.getNazwa()});
                    }
                    Window.db.safeDbToFile();
                    this.dispose();
                });
            }
            case "Pracownik" -> {
                Pracownik currentObject = (Pracownik) mapa.get(numerObiektu);

                this.addRepeatingElements(numerObiektu, isNewObject);

                lNazwa.setText("Imię: ");
                rNazwa.add(lNazwa);
                if(!isNewObject)
                    fNazwa.setText(currentObject.getImie());
                fNazwa.setColumns(20);
                rNazwa.add(fNazwa);
                this.add(rNazwa);

                rNazwisko.add(lNazwisko);
                if(!isNewObject)
                    fNazwisko.setText(currentObject.getNazwisko());
                fNazwisko.setColumns(20);
                rNazwisko.add(fNazwisko);
                this.add(rNazwisko);

                rDataUrodzenia.add(lDataUrodzenia);
                if(!isNewObject)
                    fDataUrodzenia.setText(String.valueOf(currentObject.getDataUrodzenia()));
                fDataUrodzenia.setColumns(20);
                fDataUrodzenia.setEditable(false);
                JButton bKalendarz = new JButton("Kalendarz");
                bKalendarz.addActionListener(e -> {
                    new DateEditor(currentObject);
                });
                rDataUrodzenia.add(fDataUrodzenia);
                rDataUrodzenia.add(bKalendarz);
                this.add(rDataUrodzenia);

                DzialPracownikow[] listaDzialow = new DzialPracownikow[BazaDanych.sharedDb.getIloscDzialow()];
                BazaDanych.sharedDb.getMapaDzialow().forEach(((integer, dzialPracownikow) -> listaDzialow[integer - 1] = dzialPracownikow));
                cDzial = new JComboBox<>(listaDzialow);
                if(!isNewObject)
                    cDzial.setSelectedItem(currentObject.getDzial());

                rDzial.add(lDzial);
                rDzial.add(cDzial);
                this.add(rDzial);

                bZapisz.addActionListener(e -> {
                    if(!isNewObject) {
                        currentObject.zmienNazwe(fNazwa.getText(), fNazwisko.getText());
                        // TODO data urodzenia
                        currentObject.setDzial((DzialPracownikow) cDzial.getSelectedItem());
                        currentTable.getModel().setValueAt(currentObject.getNazwa(), currentTable.getSelectedRow(), 1);
                    } else {
                        Pracownik nowyObiekt = new Pracownik(
                                fNazwa.getText(),
                                fNazwisko.getText(),
                                "1970-01-01", // TODO
                                (DzialPracownikow)cDzial.getSelectedItem()
                        );

                        ((DefaultTableModel) currentTable.getModel()).addRow(new String[]{String.valueOf(nowyObiekt.getNumer()), nowyObiekt.getNazwa()});
                    }
                    Window.db.safeDbToFile();
                    this.dispose();
                });
            }
            case "Użytkownik" -> {
                Uzytkownik currentObject = (Uzytkownik) mapa.get(numerObiektu);

                this.addRepeatingElements(numerObiektu, isNewObject);

                lNazwa.setText("Imię: ");
                rNazwa.add(lNazwa);
                if(!isNewObject)
                    fNazwa.setText(currentObject.getImie());
                fNazwa.setColumns(20);
                rNazwa.add(fNazwa);
                this.add(rNazwa);

                rNazwisko.add(lNazwisko);
                if(!isNewObject)
                    fNazwisko.setText(currentObject.getNazwisko());
                fNazwisko.setColumns(20);
                rNazwisko.add(fNazwisko);
                this.add(rNazwisko);

                rLogin.add(lLogin);
                if(!isNewObject)
                    fLogin.setText(currentObject.getLogin());
                fLogin.setColumns(20);
                rLogin.add(fLogin);
                this.add(rLogin);

                if(isNewObject) {
                    rHaslo.add(lHaslo);
                    fHaslo.setColumns(20);
                    rHaslo.add(fHaslo);
                    this.add(rHaslo);
                }

                rDataUrodzenia.add(lDataUrodzenia);
                if(!isNewObject)
                    fDataUrodzenia.setText(String.valueOf(currentObject.getDataUrodzenia()));
                fDataUrodzenia.setColumns(20);
                fDataUrodzenia.setEditable(false);
                JButton bKalendarz = new JButton("Kalendarz");
                bKalendarz.addActionListener(e -> {
                    new DateEditor(currentObject);
                });
                rDataUrodzenia.add(fDataUrodzenia);
                rDataUrodzenia.add(bKalendarz);
                this.add(rDataUrodzenia);

                DzialPracownikow[] listaDzialow = new DzialPracownikow[BazaDanych.sharedDb.getIloscDzialow()];
                BazaDanych.sharedDb.getMapaDzialow().forEach(((integer, dzialPracownikow) -> listaDzialow[integer - 1] = dzialPracownikow));
                cDzial = new JComboBox<>(listaDzialow);
                if(!isNewObject)
                    cDzial.setSelectedItem(currentObject.getDzial());

                rDzial.add(lDzial);
                rDzial.add(cDzial);
                this.add(rDzial);

                bZapisz.addActionListener(e -> {
                    if(!isNewObject) {
                        currentObject.zmienNazwe(fNazwa.getText(), fNazwisko.getText());
                        // TODO data urodzenia
                        currentObject.setDzial((DzialPracownikow) cDzial.getSelectedItem());
                        currentTable.getModel().setValueAt(currentObject.getNazwa(), currentTable.getSelectedRow(), 1);
                    } else {
                        StringBuilder noweHaslo = new StringBuilder();
                        for (char c : fHaslo.getPassword()) { noweHaslo.append(c); }

                        Uzytkownik nowyObiekt = new Uzytkownik(
                                fNazwa.getText(),
                                fNazwisko.getText(),
                                "1970-01-01", // TODO
                                (DzialPracownikow)cDzial.getSelectedItem(),
                                fLogin.getText(),
                                noweHaslo.toString()
                        );

                        ((DefaultTableModel) currentTable.getModel()).addRow(new String[]{String.valueOf(nowyObiekt.getNumer()), nowyObiekt.getNazwa()});
                    }
                    Window.db.safeDbToFile();
                    this.dispose();
                });
            }
            case "Zlecenie" -> {
                Zlecenie currentObject = (Zlecenie) mapa.get(numerObiektu);

                this.addRepeatingElements(numerObiektu, isNewObject);

                JPanel rDataRealizacji = new JPanel();
                JLabel lDataRealizacji = new JLabel("Data realizacji");
                JTextField fDataRealizacji = new JTextField();

                rDataRealizacji.add(lDataRealizacji);
                if(!isNewObject)
                    fDataRealizacji.setText(String.valueOf(currentObject.getDataRealizacji()));
                fDataRealizacji.setColumns(20);
                fDataRealizacji.setEditable(false);
                JButton bKalendarz = new JButton("Kalendarz");
                bKalendarz.addActionListener(e -> {
                    new DateEditor(currentObject);
                });
                rDataRealizacji.add(fDataRealizacji);
                rDataRealizacji.add(bKalendarz);
                this.add(rDataRealizacji);

                bZapisz.addActionListener(e -> {
                    if(!isNewObject) {
                        currentTable.getModel().setValueAt(currentObject.getNazwa(), currentTable.getSelectedRow(), 1);
                    } else {
                        DzialPracownikow nowyObiekt = DzialPracownikow.createDzial(currentObject.getNazwa());
                        ((DefaultTableModel) currentTable.getModel()).addRow(new String[]{String.valueOf(nowyObiekt.getNumer()), nowyObiekt.getNazwa()});
                    }
                    Window.db.safeDbToFile();
                    this.dispose();
                });
            }
        }

        this.add(bZapisz);
        this.setVisible(true);
    }

    public EditDialogBody(DzialPracownikow dzial) {
        this.setModal(true);
        this.setTitle("Pracownicy działu");
        this.setSize(300, 400);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        Set<Pracownik> setPracownikow = dzial.getListaPracownikow();

        Object[] listaPracownikow = setPracownikow.toArray();

        JList<Object> podgladPracownikow = new JList<>(listaPracownikow);

        this.add(podgladPracownikow);

        this.setVisible(true);
    }

    public EditDialogBody(Zlecenie zlecenie) {
        this.setModal(true);
        this.setTitle("Lista prac: ");
        this.setSize(300, 400);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        List<Praca> setPrac = zlecenie.getListaPrac();

        Object[] listaPrac = setPrac.toArray();

        JList<Object> podgladPrac = new JList<>(listaPrac);

        this.add(podgladPrac);

        this.setVisible(true);
    }
}
