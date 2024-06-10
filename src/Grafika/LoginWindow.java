package Grafika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class LoginWindow extends JFrame {
    private final Map<String, String> mapaLogowania;

    public LoginWindow() {
        mapaLogowania = new HashMap<>();

        Dane.BazaDanych.sharedDb.getMapaUzytkownikow().forEach((integer, uzytkownik) -> {
            mapaLogowania.put(uzytkownik.getLogin(), uzytkownik.getHaslo());
        });

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300, 200);
        this.setLayout(new BorderLayout());
        this.drawLayout();
        this.setTitle("Baza pracowników");
        this.setVisible(true);
    }

    private void drawLayout() {
        JPanel textFields = new JPanel();
        textFields.setLayout(new BoxLayout(textFields, BoxLayout.Y_AXIS));

        JPanel loginRow = new JPanel();
        loginRow.setLayout(new FlowLayout());
        JLabel lLogin = new JLabel("Login:");
        JTextField fLogin = new JTextField();
        fLogin.setColumns(20);

        JPanel passwordRow = new JPanel();
        passwordRow.setLayout(new FlowLayout());
        JLabel lPassword = new JLabel("Hasło:");
        JPasswordField fPassword = new JPasswordField();
        fPassword.setColumns(20);

        JButton bLogin = new JButton("Login");
        bLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(fLogin.getText());
                System.out.println(String.valueOf(fPassword.getPassword()));

                if (
                        mapaLogowania.get(fLogin.getText())
                                .equals(String.valueOf(fPassword.getPassword()))
                ) {
                    new Window();
                    LoginWindow.this.dispose();
                }
            }
        });

        loginRow.add(lLogin); loginRow.add(fLogin);
        textFields.add(loginRow);

        passwordRow.add(lPassword); passwordRow.add(fPassword);
        textFields.add(passwordRow);

        this.add(textFields, BorderLayout.CENTER);

        this.add(bLogin, BorderLayout.SOUTH);
    }
}
