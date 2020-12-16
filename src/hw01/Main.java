package hw01;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JPanel implements ActionListener {

    JLabel flags;

    public Main() {
        super(new BorderLayout());

        String[] countryList = {"Albania", "Andorra", "Austria", "Belarus", "Belgium", "Bosnia and Herzegovina", "Bulgaria",
                "Croatia", "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Georgia", "Germany", "Greece",
                "Hungary", "Iceland", "Ireland", "Italy", "Kosovo", "Latvia", "Liechtenstein", "Lithuania", "Luxembourg",
                "Macedonia", "Malta", "Moldova", "Monaco", "Montenegro", "Netherlands", "Norway", "Poland", "Portugal",
                "Romania", "San Marino", "Serbia", "Slovakia", "Slovenia", "Spain", "Sweden", "Switzerland", "Ukraine",
                "United Kingdom", "Vatican City"};

        JComboBox comboBox = new JComboBox(countryList);
        comboBox.setSelectedIndex(0);
        comboBox.addActionListener(this);
        flags = new JLabel();
        flags.setFont(flags.getFont().deriveFont(Font.ITALIC));
        flags.setHorizontalAlignment(JLabel.CENTER);
        fileName(countryList[comboBox.getSelectedIndex()]);
        flags.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        flags.setPreferredSize(new Dimension(350, 250));
        add(comboBox, BorderLayout.PAGE_START);
        add(flags, BorderLayout.PAGE_END);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    public void actionPerformed(ActionEvent event) {
        JComboBox cb = (JComboBox)event.getSource();
        String country = (String)cb.getSelectedItem();
        assert country != null;
        fileName((country.replace(' ','-')).toLowerCase());
    }

    private static ImageIcon imageSource(String path) {
        java.net.URL imgURL = Main.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Can't find anything at: " + path);
            return null;
        }
    }

    private void fileName(String name) {
        ImageIcon image = imageSource("flags/" + name + "-flag-xs.png");
        flags.setIcon(image);
        if (image != null) {
            flags.setText(null);
        } else {
            flags.setText("There's... nothing here. How bizarre!");
        }
    }

    private static void display() {

        JFrame frame = new JFrame("Flag Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent childPane = new Main();
        childPane.setOpaque(true);
        frame.setContentPane(childPane);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(Main::display);
    }
}
