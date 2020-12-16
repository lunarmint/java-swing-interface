package hw04;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;

public class Frame extends JFrame {

    private final JPanel contentPane;
    private final JProgressBar progressBar;
    private final Map<Flag, ImageIcon> map;
    private final DefaultListModel<Flag> flagModel;
    private final Map<String, Flag> flagMap;
    private JLabel imageLabel;

    // Scalable elements
    int flagMaxWidth = 320;
    int flagMaxHeight = 320;
    int topPadding = 3;
    int leftPadding = 3;
    int bottomPadding = 3;
    int rightPadding = 3;
    int canvasWidth = flagMaxWidth * 2 + leftPadding + rightPadding;
    int canvasHeight = flagMaxHeight + topPadding + bottomPadding;

    public Frame() {

        map = new HashMap<>();
        flagMap = new HashMap<>();
        flagModel = new DefaultListModel<>();
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
        progressBar = new JProgressBar(0, 100);
        progressBar.setPreferredSize(new Dimension(canvasWidth, 50));
        Color teal = new Color(111, 172, 175);
        progressBar.setForeground(teal);
        progressBar.setBackground(Color.white);
        progressBar.setStringPainted(true);
        contentPane.add(progressBar);

        // Pre-load using Swing worker
        new BackgroundLoader(this).execute();
    }

    // Map the flag and its name
    public void addFlag(String string, Flag flag) {
        flagMap.put(string, flag);
        flagModel.addElement(flag);
    }

    // Display progress in percentage
    public void setProgress(int progress) {
        progressBar.setValue(progress);
        progressBar.setString(progress + "%");
    }

    // Set the image for different country names
    public void setImage(String string) {
        if(string.length() > 1) {
            Flag flag = flagMap.get(string);
            ImageIcon image = map.get(flag);
            if(flag == null) {
                System.err.println("Couldn't match a flag with: " + string);
                return;
            }
            if(image == null) {
                Image i = flag.getImage();
                image = new ImageIcon(i);
                map.put(flag, image);
            }
            imageLabel.setIcon(image);
        }
    }

    public void display() {
        // Use MigLayout for contentPane
        contentPane.setLayout(new MigLayout()); // Use "fillx, debug" constraint to debug
        contentPane.repaint();
        contentPane.remove(progressBar);

        // LHS scroll pane
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, "width min(50%), height min(90%)");
        JList<Flag> flagList = new JList<>();
        flagList.setDragEnabled(true);
        flagList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(flagList);
        flagList.setModel(flagModel);

        // RHS image pane
        imageLabel = new JLabel();
        contentPane.add(imageLabel, "wrap, width max(50%), alignx center, aligny center");

        // Text field at the bottom
        JTextField textField = new JTextField();
        textField.getDocument().addDocumentListener(new TextField(this));
        contentPane.add(textField, "grow, span 2");
        setContentPane(contentPane);
    }
}
