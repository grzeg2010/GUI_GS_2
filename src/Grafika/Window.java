package Grafika;

import Logika.Uzytkownik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
        if(user.getClass() == Logika.Brygadzista.class)
            centerPanel.addTab("Moje zlecenia", new JLabel("Moje zlecenia!"));
        this.setVisible(true);
    }

    public void drawLayout() {
        // LEFT PANEL
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(8, 1));

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

        JScrollPane newTab;

        if (Window.centerPanel.indexOfTab(title) == -1) {
            switch (title) {
                case "Brygada" -> newTab = new JScrollPane(Tabs.utworzTabele(db.getMapaBrygad(), db.getIloscBrygad()));
                case "Brygadzista" -> newTab = new JScrollPane(Tabs.utworzTabele(db.getMapaBrygadzistow(), db.getIloscBrygadzistow()));
                case "Dział pracowników" -> newTab = new JScrollPane(Tabs.utworzTabele(db.getMapaDzialow(), db.getIloscDzialow()));
                case "Praca" -> newTab = new JScrollPane(Tabs.utworzTabele(db.getMapaPrac(), db.getIloscPrac()));
                case "Pracownik" -> newTab = new JScrollPane(Tabs.utworzTabele(db.getMapaPracownikow(), db.getIloscPracownikow()));
                case "Użytkownik" -> newTab = new JScrollPane(Tabs.utworzTabele(db.getMapaUzytkownikow(), db.getIloscUzytkownikow()));
                case "Zlecenie" -> newTab = new JScrollPane(Tabs.utworzTabele(db.getMapaZlecen(), db.getIloscZlecen()));
                default -> throw new RuntimeException();
            }

            centerPanel.addTab(title, newTab);
        }
    }
}
