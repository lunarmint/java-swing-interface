package hw02;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Circle extends Canvas {

	private static final int diameter = getDiameter();
	private boolean display = true;
	private Color color = Color.CYAN;
	private int x, y;

	// Prompt the user to enter the diameter of the circle with JOptionPane. More interactive!
	public static int getDiameter() {
		String dialog = JOptionPane.showInputDialog(null,"Enter circle diameter: ");
		Scanner scanner = new Scanner(dialog);
		return scanner.nextInt();
	}

	// If display boolean is true (button set to show), set color and paint the circle.
	public void paint(Graphics g) {
		if(display) {
			g.setColor(color);
			g.fillOval(x, y, diameter, diameter); // An oval with width = height is a circle.
		}
	}

	// Calculate the X-axis value of the slider (%).
	public void getX(int sliderVal) {
		double percent = sliderVal / 100.0;
		x = (int)Math.round(getWidth() * percent) - (diameter / 2);
	}

	// Calculate the Y-axis value of the slider (%).
	public void getY(int sliderVal) {
		double percent = Math.abs(sliderVal - 100) / 100.0;
		y = (int)Math.round(getHeight() * percent) - (diameter / 2);
	}

	// Pass the user-chosen color value to the parameter and repaint the canvas.
	public void setColor(Color c) {
		if (c != null) {
			color = c;
		}
		repaint();
	}

	// If the circle is being displayed, set it to false and vice-versa (shown/hidden) and repaint the canvas.
	public void display() {
		display = !display;
		repaint();
	}

	// Return the current state (shown/hidden) of the circle.
	public boolean isDisplayed() {
		return display;
	}
}
