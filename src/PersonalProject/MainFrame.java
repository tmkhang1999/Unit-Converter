package PersonalProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    JPanel panel;
    JButton currency, mass, length, volume;

    public MainFrame(){
        setTitle("Converter");
        setBounds(1000, 50, 250, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        currency = new JButton("Currency");
        currency.setBounds(30,20,180,40);
        currency.addActionListener((ActionEvent e) -> createCurrency());
        panel.add(currency);

        mass = new JButton("Mass");
        mass.setBounds(30,80,180,40);
        mass.addActionListener((ActionEvent e) -> createMass());
        panel.add(mass);

        length = new JButton("Length");
        length.setBounds(30,140,180,40);
        length.addActionListener((ActionEvent e) -> createLength());
        panel.add(length);

        volume = new JButton("Volume");
        volume.setBounds(30,200,180,40);
        volume.addActionListener((ActionEvent e) -> createVolume());
        panel.add(volume);
    }

    private void createVolume() {
        setVisible(false);
        Frame frame = new Frame("Volume");
        frame.setVisible(true);
    }

    private void createLength() {
        setVisible(false);
        Frame frame = new Frame("Length");
        frame.setVisible(true);
    }

    private void createMass() {
        setVisible(false);
        Frame frame = new Frame("Mass");
        frame.setVisible(true);
    }

    private void createCurrency() {
        setVisible(false);
        Frame frame = new Frame("Currency");
        frame.setVisible(true);
    }
}
