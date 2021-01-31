package PersonalProject;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class Frame extends JFrame {
    JPanel panel;
    JLabel from, to;
    JComboBox<String> fromValue, toValue;
    DefaultComboBoxModel<String> fromListModel, toListModel;
    JLabel fromLabel, toLabel;
    JTextField fromTxt, toTxt;
    JButton convertButton, swapButton, clearButton, backButton;
    Map<String, Double> data;

    String path = "";
    String input = "";
    double inputToOrigin = 0;
    double outputToOrigin = 0;
    double output = 0;

    public Frame(String s) {
        path = s;
        setTitle("Personal Project _ " + path +" Converter");
        setBounds(1000, 50, 420, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(new GridLayout(0,1,0,0));

        data = new HashMap<>();
        fromListModel = new DefaultComboBoxModel<>();
        toListModel = new DefaultComboBoxModel<>();
        new GetResource(this).execute();
    }

    public void GUI() {
        panel.setLayout(null);

        //
        fromLabel = new JLabel("From:");
        fromLabel.setBounds(30,10,150,30);
        add(fromLabel);

        toLabel = new JLabel("To:");
        toLabel.setBounds(30,130,150,30);
        add(toLabel);

        //2
        fromValue = new JComboBox<>();
        fromValue.setModel(fromListModel);
        fromValue.setBounds(30,40,170,30);
        AutoCompleteDecorator.decorate(fromValue);
        add(fromValue);

        toValue = new JComboBox<>();
        toValue.setModel(toListModel);
        toValue.setBounds(30,160,170,30);
        AutoCompleteDecorator.decorate(toValue);
        add(toValue);

        //3
        from = new JLabel("Enter the mount:");
        from.setBounds(230,10,150,30);
        from.setForeground(Color.RED);
        add(from);

        to = new JLabel("Converted Amount:");
        to.setBounds(230,130,150,30);
        to.setForeground(Color.RED);
        add(to);

        //4
        fromTxt = new JTextField("");
        fromTxt.setBounds(230,40,150,30);
        add(fromTxt);

        toTxt = new JTextField("");
        toTxt.setBounds(230,160,150,30);
        add(toTxt);

        //5
        swapButton = new JButton("Swap");
        swapButton.setBounds(30,90,80,30);
        add(swapButton);

        convertButton = new JButton("Convert");
        convertButton.setBounds(230,90,80,30);
        add(convertButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(30,210,170,40);
        add(clearButton);

        backButton = new JButton("Back");
        backButton.setBounds(230,210,150,40);
        add(backButton);

        //Interactions
        fromTxt.getDocument().addDocumentListener(new TextListener(this));
        swapButton.addActionListener((ActionEvent e) -> swap());
        convertButton.addActionListener((ActionEvent e) -> showOutput());
        clearButton.addActionListener((ActionEvent e) -> clearAll());
        backButton.addActionListener((ActionEvent e) -> goBack());

        setContentPane(panel);
    }

    private void swap() {
        Object value1 = fromValue.getSelectedItem();
        Object value2 = toValue.getSelectedItem();

        fromValue.setSelectedItem(value2);
        toValue.setSelectedItem(value1);
    }

    private void goBack() {
        setVisible(false);
        PersonalProject.MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }

    public void changeInput(String k) {
        input = k;
    }

    public String getPath(){
        return path;
    }

    private void showOutput() {
        inputToOrigin = data.get(fromValue.getSelectedItem());
        outputToOrigin = data.get(toValue.getSelectedItem());
        try{
            if(input != null){
                output = Double.parseDouble(input)* outputToOrigin/inputToOrigin;
                toTxt.setText(""+ new DecimalFormat("#.#####").format(output));
            } else {
                toTxt.setText("ERROR");
            }
        } catch (NumberFormatException ex){
            toTxt.setText("ERROR");
        }
    }

    private void clearAll() {
        fromTxt.setText("");
        toTxt.setText("");
    }

    public void addCurrency(String name, double currency){
        data.put(name, currency);
        fromListModel.addElement(name);
        toListModel.addElement(name);
    }

}