package hw02;

import javax.swing.*;

public class Main {

	// Initialize the frame object, set it to visible and display.
	private static void initialize() {
		Frame frame = new Frame();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Main::initialize);
	}
}
