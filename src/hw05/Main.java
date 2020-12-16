package hw05;

import javax.swing.*;

public class Main {

    private static void initialize() {
        Frame frame = new Frame();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::initialize);
    }
}
