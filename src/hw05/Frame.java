package hw05;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import java.util.HashMap;
import java.util.Map;

public class Frame extends JFrame {

    private final JPanel contentPane;
    private final Canvas canvas;
    private final JSlider leftSlider, rightSlider, topSlider, bottomSlider;
    private final JScrollPane scrollPane;
    private final DefaultListModel<Flag> flagModel;
    private final Map<String, Flag> flagMap;
    private final JList<Flag> flagList;
    private Flag currentFlag;

    // Element size
    int topPadding = 5;
    int leftPadding = 5;
    int bottomPadding = 5;
    int rightPadding = 5;
    int canvasWidth = 600;
    int canvasHeight = 600;

    public Frame() {

        // Initialize content pane's elements
        flagMap = new HashMap<>();
        flagList = new JList<>();
        flagModel = new DefaultListModel<>();
        leftSlider = new JSlider();
        topSlider = new JSlider();
        rightSlider = new JSlider();
        bottomSlider = new JSlider();
        canvas = new Canvas(this);
        scrollPane = new JScrollPane();
        contentPane = new JPanel();
        flagList.addListSelectionListener(this::changeFlag);
        setTitle("Flag Painter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, canvasWidth, canvasHeight);
        setLocationRelativeTo(null);
        contentPane.setBorder(new EmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding));
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("", "[grow, fill][][]", "[grow, fill][][][]"));
        flagList.setModel(flagModel);
        scrollPane.setViewportView(flagList);

        // Pre-load using Swing worker
        new BackgroundLoader(this).execute();

        // Synchronize the pairs of sliders
        topSlider.setModel(bottomSlider.getModel());
        leftSlider.setModel(rightSlider.getModel());

        // Add listeners to sliders
        topSlider.addChangeListener((ChangeEvent e) -> horizontalChange());
        leftSlider.addChangeListener((ChangeEvent e) -> verticalChange());

        // Docking the sliders
        leftSlider.setOrientation(SwingConstants.VERTICAL);
        rightSlider.setOrientation(SwingConstants.VERTICAL);
        contentPane.add(topSlider, "dock north, wrap");
        contentPane.add(scrollPane, "dock south");
        contentPane.add(bottomSlider, "dock south");
        contentPane.add(leftSlider, "dock west");
        contentPane.add(canvas, "wmin 10, hmin 10");
        contentPane.add(rightSlider, "dock east");
        verticalChange();
        horizontalChange();
        pack();
    }

    // Map the flag and its name
    public void addFlag(String string, Flag flag) {
        flagMap.put(string, flag);
        flagModel.addElement(flag);
    }

    public Flag getCurrentFlag() {
        return currentFlag;
    }

    public void changeFlag(ListSelectionEvent e) {
        if(currentFlag == null || !currentFlag.equals(flagList.getSelectedValue()))
        {
            currentFlag = flagList.getSelectedValue();
            canvas.repaint();
        }
    }

    private void horizontalChange() {
        canvas.setsliderX(topSlider.getValue());
        canvas.repaint();
    }

    private void verticalChange() {
        canvas.setSliderY(leftSlider.getValue());
        canvas.repaint();
    }
}
