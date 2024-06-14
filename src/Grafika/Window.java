package Grafika;

import Logika.Brygadzista;
import Logika.Uzytkownik;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Window extends JFrame {
    private static final Dane.BazaDanych db = Dane.BazaDanych.sharedDb;
    private final Uzytkownik currentUser;
    static JTabbedPane centerPanel = new JTabbedPane();

    public Window(Uzytkownik user) {
        this.currentUser = user;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.getContentPane().setLayout(new BorderLayout());
        this.drawLayout();
        this.setTitle("Baza pracowników | " + currentUser.getInicjal());
//        if(user.getClass() == Logika.Brygadzista.class)
//            centerPanel.addTab("Moje zlecenia", new JLabel("Moje zlecenia!"));
        this.setVisible(true);
    }

    public void drawLayout() {
        // LEFT PANEL
        JPanel leftPanel = new JPanel();
        if(currentUser.getClass() == Logika.Uzytkownik.class)
            leftPanel.setLayout(new GridLayout(9, 1));
        else {
            leftPanel.setLayout(new GridLayout(10, 1));
            JButton bMojeZlecenia = new JButton("Moje zlecenia");
            bMojeZlecenia.addActionListener(this::openTab);
            leftPanel.add(bMojeZlecenia);
        }

        JButton bDzialPracownikow = new JButton("Dział pracowników");
        bDzialPracownikow.addActionListener(this::openTab);

        JButton bPracownik = new JButton("Pracownik");
        bPracownik.addActionListener(this::openTab);

        JButton bUzytkownik = new JButton("Użytkownik");
        bUzytkownik.addActionListener(this::openTab);

        JButton bBrygadzista = new JButton("Brygadzista");
        bBrygadzista.addActionListener(this::openTab);

        JButton bBrygada = new JButton("Brygada");
        bBrygada.addActionListener(this::openTab);

        JButton bZlecenie = new JButton("Zlecenie");
        bZlecenie.addActionListener(this::openTab);

        JButton bPraca = new JButton("Praca");
        bPraca.addActionListener(this::openTab);

        JButton bZmienHaslo = new JButton("Zmień hasło");
        bZmienHaslo.addActionListener(e -> {
            JDialog oknoZmianyHasla = new JDialog();
            oknoZmianyHasla.setTitle("Zmień hasło");
            oknoZmianyHasla.setSize(300, 200);
            oknoZmianyHasla.setLayout(new BoxLayout(oknoZmianyHasla.getContentPane(), BoxLayout.Y_AXIS));

            oknoZmianyHasla.add(new JLabel("Nowe hasło:"));
            JPasswordField fNoweHaslo = new JPasswordField();
            oknoZmianyHasla.add(fNoweHaslo);
            JButton bZapisz = new JButton("Zapisz");
            oknoZmianyHasla.add(bZapisz);

            bZapisz.addActionListener(e1 -> {
                StringBuilder noweHaslo = new StringBuilder();
                for (char c : fNoweHaslo.getPassword()) {
                    noweHaslo.append(c);
                }

                currentUser.setHaslo(noweHaslo.toString());
                System.out.println(noweHaslo.toString());
                oknoZmianyHasla.dispose();
            });

            oknoZmianyHasla.setVisible(true);
        });

        JButton bWyloguj = new JButton("Wyloguj");
        bWyloguj.setBackground(Color.lightGray);
        bWyloguj.addActionListener(e -> {
            new LoginWindow();
            Window.centerPanel.removeAll();
            Window.this.dispose();
        });

        leftPanel.add(bDzialPracownikow); leftPanel.add(bPracownik); leftPanel.add(bUzytkownik); leftPanel.add(bBrygadzista);
        leftPanel.add(bBrygada); leftPanel.add(bZlecenie); leftPanel.add(bPraca); leftPanel.add(bZmienHaslo); leftPanel.add(bWyloguj);

        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
        this.getContentPane().add(leftPanel, BorderLayout.WEST);
    }

    private void openTab(ActionEvent e) {
        JButton o = (JButton) e.getSource();
        String title = o.getText();

        JPanel newTab = new JPanel();
        newTab.setLayout(new BorderLayout());


        JButton bAddObject = new JButton("Nowy");
        bAddObject.setBackground(Color.lightGray);
        JButton bEditObject = new JButton("Edycja");
        bEditObject.setBackground(Color.lightGray);
        JButton bRemoveObject = new JButton("Usuń");
        bRemoveObject.setBackground(Color.pink);
        JLabel lUser = new JLabel("Witaj " + currentUser.getInicjal());
        JPanel buttonRow = new JPanel();
        buttonRow.add(bAddObject); buttonRow.add(bEditObject); buttonRow.add(bRemoveObject); buttonRow.add(lUser);

        Map<String, JTable> mapaTabel = new HashMap<>();
        JTable table;

        if (Window.centerPanel.indexOfTab(title) == -1) {
            switch (title) {
                case "Moje zlecenia" -> {
                    table = Tabs.utworzTabele(db.getMapaZlecen());
                    newTab.add(new JLabel("Moje zlecenia"), BorderLayout.SOUTH);
                }
                case "Brygada" -> {
                    table = Tabs.utworzTabele(db.getMapaBrygad());
                    mapaTabel.put("Brygada", table);

                }
                case "Brygadzista" -> {
                    table = Tabs.utworzTabele(db.getMapaBrygadzistow());
                    mapaTabel.put("Brygadzista", table);
                }
                case "Dział pracowników" -> {
                    table = Tabs.utworzTabele(db.getMapaDzialow());
                    mapaTabel.put("Dzial", table);
                }
                case "Praca" -> {
                    table = Tabs.utworzTabele(db.getMapaPrac());
                    mapaTabel.put("Praca", table);
                }
                case "Pracownik" -> {
                    table = Tabs.utworzTabele(db.getMapaPracownikow());
                    mapaTabel.put("Pracownik", table);
                }
                case "Użytkownik" -> {
                    table = Tabs.utworzTabele(db.getMapaUzytkownikow());
                    mapaTabel.put("Uzytkownik", table);
                }
                case "Zlecenie" -> {
                    table = Tabs.utworzTabele(db.getMapaZlecen());
                    mapaTabel.put("Zlecenie", table);
                }
                default -> throw new RuntimeException();
            }

            JScrollPane tableScroller = new JScrollPane(table);

            bAddObject.addActionListener(e1 -> { // TODO
                switch (centerPanel.getTitleAt(centerPanel.getSelectedIndex())) {
                    case "Brygada" -> { new EditDialogBody(db.getMapaBrygad(), table, true); }
                    case "Brygadzista" -> { new EditDialogBody(db.getMapaBrygadzistow(), table, true); }
                    case "Dział pracowników" -> { new EditDialogBody(db.getMapaDzialow(), mapaTabel.get("Dzial"), true); }
                    case "Praca" -> { new EditDialogBody(db.getMapaPrac(), mapaTabel.get("Praca"), true); }
                    case "Pracownik" -> { new EditDialogBody(db.getMapaPracownikow(), mapaTabel.get("Pracownik"), true); }
                    case "Użytkownik" -> { new EditDialogBody(db.getMapaUzytkownikow(), mapaTabel.get("Uzytkownik"), true); }
                    case "Zlecenie" -> { new EditDialogBody(db.getMapaZlecen(), mapaTabel.get("Zlecenie"), true); }
                    default -> throw new RuntimeException();
                }
            });

            bEditObject.addActionListener(new ActionListener() { // TODO
                @Override
                public void actionPerformed(ActionEvent e) {


                    switch (centerPanel.getTitleAt(centerPanel.getSelectedIndex())) {
                        case "Brygada" -> { new EditDialogBody(db.getMapaBrygad(), mapaTabel.get("Brygada"), false); }
                        case "Brygadzista" -> { new EditDialogBody(db.getMapaBrygadzistow(), mapaTabel.get("Brygadzista"), false); }
                        case "Dział pracowników" -> { new EditDialogBody(db.getMapaDzialow(), mapaTabel.get("Dzial"), false); }
                        case "Praca" -> { new EditDialogBody(db.getMapaPrac(), mapaTabel.get("Praca"), false); }
                        case "Pracownik" -> { new EditDialogBody(db.getMapaPracownikow(), mapaTabel.get("Pracownik"), false); }
                        case "Użytkownik" -> { new EditDialogBody(db.getMapaUzytkownikow(), mapaTabel.get("Uzytkownik"), false); }
                        case "Zlecenie" -> { new EditDialogBody(db.getMapaZlecen(), mapaTabel.get("Zlecenie"), false); }
                        default -> throw new RuntimeException();
                    }

                    System.out.println(getIndexOfSelectedObject(table));
                }
            });

            bRemoveObject.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTable currentTable;

                    switch (centerPanel.getTitleAt(centerPanel.getSelectedIndex())) {
                        case "Brygada" -> {
                            currentTable = mapaTabel.get("Brygada");
                            if (currentTable.getSelectedRow() >= 0) {
                                db.getMapaBrygad().remove(getIndexOfSelectedObject(currentTable));
                                db.setIloscBrygad(db.getIloscBrygad() - 1);
                            }
                        }
                        case "Brygadzista" -> {
                            currentTable = mapaTabel.get("Brygadzista");
                            int selectedUser = Integer.parseInt((String) currentTable.getValueAt(currentTable.getSelectedRow(), 0));
                            if(selectedUser != currentUser.getNumer() || currentUser.getClass() != Brygadzista.class) {
                                if (currentTable.getSelectedRow() >= 0) {
                                    db.getMapaBrygadzistow().remove(getIndexOfSelectedObject(currentTable));
                                    db.setIloscBrygadzistow(db.getIloscBrygadzistow() - 1);
                                }
                            } else {
                                currentTable = null;
                            }
                        }
                        case "Dział pracowników" -> {
                            currentTable = mapaTabel.get("Dzial");
                            if (currentTable.getSelectedRow() >= 0) {
                                db.getMapaDzialow().remove(getIndexOfSelectedObject(currentTable));
                                db.setIloscDzialow(db.getIloscDzialow() - 1);
                            }
                        }
                        case "Praca" -> {
                            currentTable = mapaTabel.get("Praca");
                            if (currentTable.getSelectedRow() >= 0) {
                                db.getMapaPrac().remove(getIndexOfSelectedObject(currentTable));
                                db.setIloscPrac(db.getIloscPrac() - 1);
                            }
                        }
                        case "Pracownik" -> {
                            currentTable = mapaTabel.get("Pracownik");
                            if (currentTable.getSelectedRow() >= 0) {
                            db.getMapaPracownikow().remove(getIndexOfSelectedObject(currentTable));
                            db.setIloscPracownikow(db.getIloscPracownikow() - 1);
                            }
                        }
                        case "Użytkownik" -> {
                            currentTable = mapaTabel.get("Uzytkownik");
                            int selectedUser = Integer.parseInt((String) currentTable.getValueAt(currentTable.getSelectedRow(), 0));
                            if(selectedUser != currentUser.getNumer() || currentUser.getClass() != Uzytkownik.class) {
                                if (currentTable.getSelectedRow() >= 0) {
                                    db.getMapaUzytkownikow().remove(getIndexOfSelectedObject(currentTable));
                                    db.setIloscUzytkownikow(db.getIloscUzytkownikow() - 1);
                                }
                            } else {
                                currentTable = null;
                            }
                        }
                        case "Zlecenie" -> {
                            currentTable = mapaTabel.get("Zlecenie");
                            if (currentTable.getSelectedRow() >= 0) {
                                db.getMapaZlecen().remove(getIndexOfSelectedObject(currentTable));
                                db.setIloscZlecen(db.getIloscZlecen() - 1);
                            }
                        }
                        default -> throw new RuntimeException();
                    }

                    // System.out.println(currentTable.getSelectedRow());
                    if (currentTable != null && currentTable.getSelectedRow() >= 0)
                        ((DefaultTableModel) currentTable.getModel()).removeRow(currentTable.getSelectedRow());
                }
            });

            newTab.add(buttonRow, BorderLayout.NORTH);
            newTab.add(tableScroller, BorderLayout.CENTER);
            centerPanel.addTab(title, newTab);
            JPanel wygladKarty = new JPanel();

            JLabel tytulKarty = new JLabel(title);
            wygladKarty.add(tytulKarty);

            JButton bClose = new JButton("X");
            bClose.setBorderPainted(false);
            bClose.setBackground(Color.lightGray);
            bClose.setForeground(Color.white);
            wygladKarty.add(bClose);

            bClose.addActionListener(clickedCloseButton -> {
                JButton btn = (JButton) clickedCloseButton.getSource();
                JLabel selectedTab = (JLabel)btn.getParent().getComponent(0);
                String selectedText = selectedTab.getText();

                centerPanel.removeTabAt(centerPanel.indexOfTab(selectedText));
            });

            centerPanel.setTabComponentAt(centerPanel.indexOfTab(title), wygladKarty);
        }
    }

    protected int getIndexOfSelectedObject(JTable table) {
        if(table.getSelectedRow() != -1)
            return Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
        else
            return -1;
    }
}
