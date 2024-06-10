package Grafika;

import Logika.Uzytkownik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginWindow extends JFrame {
    private final Map<String, Uzytkownik> mapaLogowania;

    public LoginWindow() {
        mapaLogowania = new HashMap<>();

        Dane.BazaDanych.sharedDb.getMapaUzytkownikow().forEach((integer, uzytkownik) -> {
            mapaLogowania.put(uzytkownik.getLogin(), uzytkownik);
        });

        Dane.BazaDanych.sharedDb.getMapaBrygadzistow().forEach(((integer, brygadzista) -> {
            mapaLogowania.put(brygadzista.getLogin(), brygadzista);
        }));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 200);
        this.setLayout(new BorderLayout());
        this.drawLayout();
        this.setTitle("Bazy pracowników - logowanie");
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
        fLogin.setText("grzeg");

        JPanel passwordRow = new JPanel();
        passwordRow.setLayout(new FlowLayout());
        JLabel lPassword = new JLabel("Hasło:");
        JPasswordField fPassword = new JPasswordField();
        fPassword.setColumns(20);

        JLabel wrongCredentialsInfo = new JLabel("Wprowadzono zły login lub hasło");
        wrongCredentialsInfo.setForeground(Color.red);
        wrongCredentialsInfo.setVisible(false);

        JButton bLogin = new JButton("Login");
        bLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyCredentials(fLogin, fPassword, wrongCredentialsInfo);
            }
        });

        fPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    verifyCredentials(fLogin, fPassword, wrongCredentialsInfo);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) { }
        });

        loginRow.add(lLogin); loginRow.add(fLogin);
        textFields.add(loginRow);

        passwordRow.add(lPassword); passwordRow.add(fPassword);
        textFields.add(passwordRow);

        textFields.add(wrongCredentialsInfo);

        this.add(textFields, BorderLayout.CENTER);

        this.add(bLogin, BorderLayout.SOUTH);
    }

    private void verifyCredentials(JTextField fLogin, JPasswordField fPassword, JLabel wrongCredentialsInfo) {
        Uzytkownik user = mapaLogowania.get(fLogin.getText());
        if (!Objects.equals(user, null)) {
            if (
                    Objects.equals(mapaLogowania.get(fLogin.getText()).getHaslo(), String.valueOf(fPassword.getPassword()))
                //.equals(String.valueOf(fPassword.getPassword()))
            ) {
                new Window(mapaLogowania.get(fLogin.getText()));
                LoginWindow.this.dispose();
            } else {
                wrongCredentialsInfo.setVisible(true);
            }
        } else {
            wrongCredentialsInfo.setVisible(true);
        }
    }
}
