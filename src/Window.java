import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {
    static JTabbedPane centerPanel = new JTabbedPane();

    public Window() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.getContentPane().setLayout(new BorderLayout());
        this.drawLayout();
        this.setVisible(true);
    }

    public void drawLayout() {
        // CENTER PANEL


        // Create the first tab (page1) and add a JLabel to it
        JPanel page1 = new JPanel();
        page1.add(new JLabel("This is Tab 1"));

        // Create the second tab (page2) and add a JLabel to it
        JPanel page2 = new JPanel();
        page2.add(new JLabel("This is Tab 2"));

        // Create the third tab (page3) and add a JLabel to it
        JPanel page3 = new JPanel();
        page3.add(new JLabel("This is Tab 3"));

        // Add the three tabs to the JTabbedPane
        centerPanel.addTab("Tab 1", page1);
        centerPanel.addTab("Tab 2", page2);
        centerPanel.addTab("Tab 3", page3);

        // LEFT PANEL
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(8, 1));

        JButton bDzialPracownikow = new JButton("Dział pracowników");
        JButton bPracownik = new JButton("Pracownik");
        JButton bUzytkownik = new JButton("Użytkownik");
        JButton bBrygadzista = new JButton("Brygadzista");
        JButton bBrygada = new JButton("Brygada");
        JButton bZlecenie = new JButton("Zlecenie");
        JButton bPraca = new JButton("Praca");
        JButton bWyloguj = new JButton("Wyloguj");
        bWyloguj.addActionListener(e -> {
            new LoginWindow();
            this.dispose(); // TODO
        });

        //centerPanel.addTab(title, tabBody);
        //int index = tabPane.indexOfTab(title);


        bDzialPracownikow.addActionListener(
                new ActionListener() {
                    boolean tabOpen = false;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JPanel newTab = new JPanel();

                        newTab.add(new JLabel(
                                "Nowa karta"));

                        if (!tabOpen) {
                            tabOpen = true;
                            centerPanel.addTab(
                                    "Dział pracowników",
                                    newTab);
                        }
                    }
                });

        leftPanel.add(bDzialPracownikow); leftPanel.add(bPracownik); leftPanel.add(bUzytkownik); leftPanel.add(bBrygadzista);
        leftPanel.add(bBrygada); leftPanel.add(bZlecenie); leftPanel.add(bPraca); leftPanel.add(bWyloguj);

        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
        this.getContentPane().add(leftPanel, BorderLayout.WEST);
    }
}