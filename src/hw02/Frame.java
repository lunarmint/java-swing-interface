package hw02;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Frame extends JFrame {

	private final Circle circle;
	private final JSlider leftSlider;
	private final JSlider bottomSlider;
	private final JButton colorTable;
	private final JButton showHide;

	public Frame() {

		// Preset values for the elements and work with the frame in a CSS-like box model.
		int topPadding = 10;
		int leftPadding = 0;
		int sliderWidth = 50;
		int sliderLength = 500;
		int canvasWidth = 700;
		int canvasHeight = 700;

		setTitle("Circle Painter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, canvasWidth, canvasHeight);
		setLocationRelativeTo(null); // Set the location of the canvas to the center of the screen.
		setResizable(false); // Not resizable since the elements does not scale.

		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Left slider.
		leftSlider = new JSlider();
		leftSlider.addChangeListener((ChangeEvent e) -> responsiveLeftSlider());
		leftSlider.setOrientation(SwingConstants.VERTICAL);
		leftSlider.setBounds(leftPadding, topPadding, sliderWidth, sliderLength);
		//leftSlider.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(leftSlider);


		// Bottom slider.
		bottomSlider = new JSlider();
		bottomSlider.addChangeListener((ChangeEvent e) -> responsiveBottomSlider());

		// TODO: figure out why the slider overflows the canvas (right side only) and get rid of the -70.
		bottomSlider.setBounds(sliderWidth, topPadding + sliderLength, canvasWidth - 70, sliderWidth);
		//bottomSlider.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		contentPane.add(bottomSlider);


		// The circle
		circle = new Circle();
		// This one does not overflow the canvas like the bottom slider but it needs to have
		// the same width with the bottom slider... so yeah, canvasWidth - 70. Eye sore.
		circle.setBounds(sliderWidth, topPadding, canvasWidth - 70, sliderLength);
		contentPane.setLayout(null);
		contentPane.add(circle);

		// The wonky maths are useful when the canvas size needs to be changed without messing
		// all the other elements up.
		colorTable = new JButton("Color Table");
		showHide = new JButton("Hide");
		colorTable.addActionListener((ActionEvent e) -> colorTable());
		showHide.addActionListener((ActionEvent e) -> display());
		colorTable.setBounds((canvasWidth / 8), (topPadding + sliderLength + sliderWidth) + ((canvasHeight - (topPadding + sliderLength + sliderWidth)) / 8), (canvasWidth / 4), ((canvasHeight - (topPadding + sliderLength + sliderWidth)) / 2));
		showHide.setBounds((canvasWidth / 8) * 5, (topPadding + sliderLength + sliderWidth) + ((canvasHeight - (topPadding + sliderLength + sliderWidth)) / 8), (canvasWidth / 4), ((canvasHeight - (topPadding + sliderLength + sliderWidth)) / 2));
		contentPane.add(colorTable); // Choose color.
		contentPane.add(showHide); // Show/hide the circle.

		responsiveLeftSlider();
		responsiveBottomSlider();
	}

	// Make the circle react to the left slider by getting
	// the Y-axis value % and repaint the circle when it changes.
	private void responsiveLeftSlider() {
		circle.getY(leftSlider.getValue());
		circle.repaint();
	}

	// Make the circle react to the bottom slider by getting
	// the X-axis value % and repaint the circle when it changes.
	private void responsiveBottomSlider() {
		circle.getX(bottomSlider.getValue());
		circle.repaint();
	}

	// Much simpler to implement than a JComboBox while also being a lot more useful.
	private void colorTable() {
		circle.setColor(JColorChooser.showDialog(null, "Pick a Color", Color.BLUE));
	}

	// Change the state of the canvas accordingly to the state of the show/hide button.
	private void display() {
		circle.display();
		showHide.setText(circle.isDisplayed() ? "Hide" : "Show");
	}
}
