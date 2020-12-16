package hw03;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.util.*;

public class Frame extends JFrame {
	
	private final JPanel contentPane;
	private final JProgressBar progressBar;
	private final Map<Flag, ImageIcon> map;
	private final DefaultListModel<Flag> flagsModel;
	private JList<Flag> flagList;
	private JLabel imageLabel;

	public Frame() {

		int flagMaxWidth = 320;
		int flagMaxHeight = 390;
		int topPadding = 3;
		int leftPadding = 1;
		int bottomPadding = 3;
		int rightPadding = 1;
		int canvasWidth = flagMaxWidth * 2 + leftPadding + rightPadding;
		int canvasHeight = flagMaxHeight + topPadding + bottomPadding;

		map = new HashMap<>();
		flagsModel = new DefaultListModel<>();
		setResizable(false);
		setTitle("Flags");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, canvasWidth, canvasHeight);
		setLocationRelativeTo(null);

		// Initialize content pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
		setContentPane(contentPane);

		// Initialize progress bar
		// TODO: change text color
		progressBar = new JProgressBar(0,100);
		progressBar.setPreferredSize(new Dimension(canvasWidth, 50));
		Color teal = new Color(111, 172, 175);
		progressBar.setForeground(teal);
		progressBar.setBackground(Color.white);
		progressBar.setStringPainted(true);
		contentPane.add(progressBar);

		// Pre-load using Swing worker
		new BackgroundLoader(this).execute();
	}

	public void addFlag(Flag f) {
		flagsModel.addElement(f);
	}

	// Display progress in percentage
	public void setProgress(int progress) {
		progressBar.setValue(progress);
		progressBar.setString(progress + "%");
	}

	// Set the image for different country names
	private void setImage(ListSelectionEvent e) {
		Flag flag = flagList.getSelectedValue();
		ImageIcon image = map.get(flag);
		if(image == null) {
			image = new ImageIcon(flag.getImage());
			map.put(flag, image);
		}
		imageLabel.setIcon(image);
	}

	public void display() {
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		contentPane.repaint();
		contentPane.remove(progressBar);
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		flagList = new JList<>();
		flagList.addListSelectionListener(this::setImage);
		scrollPane.setViewportView(flagList);
		flagList.setModel(flagsModel);
		imageLabel = new JLabel("");
		contentPane.add(imageLabel);
		setContentPane(contentPane);
	}
}
