package Grafika;

import Logika.Pracownik;
import Logika.Zlecenie;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateEditor extends JDialog {
    public DateEditor(Pracownik pracownik) {
        this.setModal(true);
        this.setTitle("Edycja daty");
        this.setSize(300, 400);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JLabel wskazowka = new JLabel("dd-mm-RRRR");
        this.add(wskazowka);

        JPanel rTextFields = new JPanel();
        JTextField fDzien = new JTextField();
        fDzien.setColumns(2);
        rTextFields.add(fDzien);
        JTextField fMiesiac = new JTextField();
        fMiesiac.setColumns(2);
        rTextFields.add(fMiesiac);
        JTextField fRok = new JTextField();
        fRok.setColumns(4);
        rTextFields.add(fRok);

        this.add(rTextFields);

        JButton bZapisz = new JButton("Zapisz");
        bZapisz.addActionListener(e -> {
            pracownik.setDataUrodzenia(LocalDate.of(
                    Integer.parseInt(fRok.getText()),
                    Integer.parseInt(fMiesiac.getText()),
                    Integer.parseInt(fDzien.getText())
            ));
            this.dispose();
        });

        this.add(bZapisz);
        this.setVisible(true);
    }

    public DateEditor(Zlecenie zlecenie) {
        this.setModal(true);
        this.setTitle("Edycja daty");
        this.setSize(300, 400);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JLabel wskazowka = new JLabel("dd-mm-RRRR");
        this.add(wskazowka);

        JPanel rTextFields = new JPanel();
        JTextField fDzien = new JTextField();
        fDzien.setColumns(2);
        rTextFields.add(fDzien);
        JTextField fMiesiac = new JTextField();
        fMiesiac.setColumns(2);
        rTextFields.add(fMiesiac);
        JTextField fRok = new JTextField();
        fRok.setColumns(4);
        rTextFields.add(fRok);

        this.add(rTextFields);

        JLabel wskazowkaGodzin = new JLabel("mm:hh");
        this.add(wskazowkaGodzin);

        JPanel rTimeTextFields = new JPanel();
        JTextField fGodzina = new JTextField();
        fGodzina.setColumns(2);
        rTimeTextFields.add(fGodzina);
        JTextField fMinuta = new JTextField();
        fMinuta.setColumns(2);
        rTimeTextFields.add(fMinuta);
        this.add(rTimeTextFields);

        JButton bZapisz = new JButton("Zapisz");
        bZapisz.addActionListener(e -> {
            zlecenie.setDataRealizacji(LocalDateTime.of(
                    Integer.parseInt(fRok.getText()),
                    Integer.parseInt(fMiesiac.getText()),
                    Integer.parseInt(fDzien.getText()),
                    Integer.parseInt(fGodzina.getText()),
                    Integer.parseInt(fMinuta.getText())
            ));
            this.dispose();
        });

        this.add(bZapisz);
        this.setVisible(true);
    }
}
