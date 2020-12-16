package project;

public class Main {

    private static void initialize() {

        Frame frame = new Frame();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(Main::initialize);
    }
}
