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
            leftPanel.setLayout(new GridLayout(8, 1));
        else {
            leftPanel.setLayout(new GridLayout(9, 1));
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

        JButton bWyloguj = new JButton("Wyloguj");
        bWyloguj.setBackground(Color.lightGray);
        bWyloguj.addActionListener(e -> {
            new LoginWindow();
            Window.centerPanel.removeAll();
            Window.this.dispose();
        });

        leftPanel.add(bDzialPracownikow); leftPanel.add(bPracownik); leftPanel.add(bUzytkownik); leftPanel.add(bBrygadzista);
        leftPanel.add(bBrygada); leftPanel.add(bZlecenie); leftPanel.add(bPraca); leftPanel.add(bWyloguj);

        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
        this.getContentPane().add(leftPanel, BorderLayout.WEST);
    }

    private void openTab(ActionEvent e) {
        JButton o = (JButton) e.getSource();
        String title = o.getText();

        JPanel newTab = new JPanel();
        newTab.setLayout(new BorderLayout());


        JButton bAddObject = new JButton("Nowy");

        JButton bEditObject = new JButton("Edycja");
        JButton bRemoveObject = new JButton("Usuń");
        JLabel lUser = new JLabel("Witaj " + currentUser.getInicjal());
        JPanel buttonRow = new JPanel();
        buttonRow.add(bAddObject); buttonRow.add(bEditObject); buttonRow.add(bRemoveObject); buttonRow.add(lUser);

        Map<String, JTable> mapaTabel = new HashMap<>();
        JTable table;

        if (Window.centerPanel.indexOfTab(title) == -1) {
            switch (title) {
                case "Moje zlecenia" -> {
                    table = Tabs.utworzTabele(db.getMapaZlecen(), db.getIloscZlecen());
                    newTab.add(new JLabel("Moje zlecenia"), BorderLayout.SOUTH);
                }
                case "Brygada" -> {
                    table = Tabs.utworzTabele(db.getMapaBrygad(), db.getIloscBrygad());
                    mapaTabel.put("Brygada", table);

                }
                case "Brygadzista" -> {
                    table = Tabs.utworzTabele(db.getMapaBrygadzistow(), db.getIloscBrygadzistow());
                    mapaTabel.put("Brygadzista", table);
                }
                case "Dział pracowników" -> {
                    table = Tabs.utworzTabele(db.getMapaDzialow(), db.getIloscDzialow());
                    mapaTabel.put("Dzial", table);
                }
                case "Praca" -> {
                    table = Tabs.utworzTabele(db.getMapaPrac(), db.getIloscPrac());
                    mapaTabel.put("Praca", table);
                }
                case "Pracownik" -> {
                    table = Tabs.utworzTabele(db.getMapaPracownikow(), db.getIloscPracownikow());
                    mapaTabel.put("Pracownik", table);
                }
                case "Użytkownik" -> {
                    table = Tabs.utworzTabele(db.getMapaUzytkownikow(), db.getIloscUzytkownikow());
                    mapaTabel.put("Uzytkownik", table);
                }
                case "Zlecenie" -> {
                    table = Tabs.utworzTabele(db.getMapaZlecen(), db.getIloscZlecen());
                    mapaTabel.put("Zlecenie", table);
                }
                default -> throw new RuntimeException();
            }

            JScrollPane tableScroller = new JScrollPane(table);

/*            bEditObject.addActionListener(new ActionListener() { // TODO
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (centerPanel.getTitleAt(centerPanel.getSelectedIndex())) {
                        case "Brygadzista" -> System.out.println(
                                db.getMapaBrygadzistow().get( getIndexOfSelectedObject(table) )
                        );
                        case "Brygada" -> System.out.println(
                                db.getMapaBrygad().get( getIndexOfSelectedObject(table) )
                        );
                        case "Dział pracowników" -> System.out.println(
                                db.getMapaDzialow().get( getIndexOfSelectedObject(table) )
                        );
                        default -> throw new RuntimeException();
                    }

                    System.out.println(getIndexOfSelectedObject(table));
                }
            });*/

            bRemoveObject.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTable currentTable;

                    switch (centerPanel.getTitleAt(centerPanel.getSelectedIndex())) {
                        case "Brygada" -> {
                            currentTable = mapaTabel.get("Brygada");
                            db.getMapaBrygad().remove(getIndexOfSelectedObject(currentTable));
                            db.setIloscBrygad(db.getIloscBrygad() - 1);
                        }
                        case "Brygadzista" -> {
                            currentTable = mapaTabel.get("Brygadzista");
                            int selectedUser = (int) currentTable.getValueAt(currentTable.getSelectedRow(), 0);
                            if(selectedUser != currentUser.getNumer() || currentUser.getClass() != Brygadzista.class) {
                                db.getMapaBrygadzistow().remove(getIndexOfSelectedObject(currentTable));
                                db.setIloscBrygadzistow(db.getIloscBrygadzistow() - 1);
                            } else {
                                currentTable = null;
                            }
                        }
                        case "Dział pracowników" -> {
                            currentTable = mapaTabel.get("Dzial");
                            db.getMapaDzialow().remove(getIndexOfSelectedObject(currentTable));
                            db.setIloscDzialow(db.getIloscDzialow() - 1);
                        }
                        case "Praca" -> {
                            currentTable = mapaTabel.get("Praca");
                            db.getMapaPrac().remove(getIndexOfSelectedObject(currentTable));
                            db.setIloscPrac(db.getIloscPrac() - 1);
                        }
                        case "Pracownik" -> {
                            currentTable = mapaTabel.get("Pracownik");
                            db.getMapaPracownikow().remove(getIndexOfSelectedObject(currentTable));
                            db.setIloscPracownikow(db.getIloscPracownikow() - 1);
                        }
                        case "Użytkownik" -> {
                            currentTable = mapaTabel.get("Uzytkownik");
                            int selectedUser = (int) currentTable.getValueAt(currentTable.getSelectedRow(), 0);
                            if(selectedUser != currentUser.getNumer() || currentUser.getClass() != Uzytkownik.class) {
                                db.getMapaUzytkownikow().remove(getIndexOfSelectedObject(currentTable));
                                db.setIloscUzytkownikow(db.getIloscUzytkownikow() - 1);
                            } else {
                                currentTable = null;
                            }
                        }
                        case "Zlecenie" -> {
                            currentTable = mapaTabel.get("Zlecenie");
                            db.getMapaZlecen().remove(getIndexOfSelectedObject(currentTable));
                            db.setIloscZlecen(db.getIloscZlecen() - 1);
                        }
                        default -> throw new RuntimeException();
                    }

                    System.out.println(currentTable.getSelectedRow());
                    ((DefaultTableModel) currentTable.getModel()).removeRow(currentTable.getSelectedRow());
                }
            });

            newTab.add(buttonRow, BorderLayout.NORTH);
            newTab.add(tableScroller, BorderLayout.CENTER);
            centerPanel.addTab(title, newTab);
        }
    }

    private int getIndexOfSelectedObject(JTable table) {
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
    }
}
