import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class LoginWindow extends JFrame {
    private static Uzytkownik user;

    public LoginWindow() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setLayout(new BorderLayout());
        this.drawLayout();
        this.setVisible(true);
    }

    private void drawLayout() {
        JPanel textFields = new JPanel();
        textFields.setLayout(new GridLayout(2, 2));
        JLabel lLogin = new JLabel("Login:");
        JTextField fLogin = new JTextField();

        JLabel lPassword = new JLabel("Hasło:");
        JTextField fPassword = new JTextField();
        fPassword.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Uzytkownik u : Uzytkownik.getMapaUzytkownikow().values()) {
                    if (Objects.equals(u.login, fLogin.getText())) {
                        LoginWindow.user = u;
                        System.out.println(LoginWindow.user);
                    }
                }
            }
        });

        JButton bLogin = new JButton("Login");
        bLogin.addActionListener(e -> {
            if(user != null) {
                if (Objects.equals(user.haslo, fPassword.getText())) {
                    System.out.println("Method 1");
                    new Window();
                    this.dispose();
                    //this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                }
            }
            else {
                for (Uzytkownik u : Uzytkownik.getMapaUzytkownikow().values()) {
                    if (Objects.equals(u.login, fLogin.getText())) {
                        LoginWindow.user = u;
                        if (Objects.equals(user.haslo, fPassword.getText())) {
                            System.out.println("Method 2");
                            new Window();
                            this.dispose(); // TODO
                        }
                    }
                }
            }
        });

        textFields.add(lLogin); textFields.add(fLogin); textFields.add(lPassword); textFields.add(fPassword);
        this.add(textFields, BorderLayout.CENTER);

        this.add(bLogin, BorderLayout.SOUTH);
    }
}
