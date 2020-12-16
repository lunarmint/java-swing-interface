package project;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame implements ActionListener {

    JTextField subPanel2_2_timestampFile, subPanel2_5_subtitleFile, subPanel4_text;
    JButton subPane4_buttonA, subPane4_buttonB, subPanel5_button1, subPanel5_button2, subPanel5_button3, subPanel2_2_timestampFile_browse,
            subPanel2_5_subtitleFile_browse;
    JMenuItem newFile, openFile, saveFile, saveAs, language, preference, exit, undo, redo, cut, copy, paste, delete,
            help, bug, update, about;
    JTabbedPane tabbedPane;
    JLabel split;
    JComboBox<String> splitMode_cb;

    public Frame() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Input", inputPane());
        tabbedPane.addTab("Output", outputPane());
//        tabbedPane.addTab("Attachments", new JLabel());
        contentPane.add(tabbedPane);

        setJMenuBar(createMenuBar());

        setTitle("Media Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
    }

    private JPanel inputPane() {
         JPanel panel = new JPanel(new MigLayout());

         JLabel sourceFiles = new JLabel("Source files:");
         panel.add(sourceFiles);

         JLabel properties = new JLabel("Properties:");
         panel.add(properties, "wrap");

         JPanel subPanel1 = new JPanel(new MigLayout());
         subPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
         panel.add(subPanel1, "height min(40%), width min(50%)");

         JPanel subPanel2 = new JPanel(new MigLayout());
         subPanel2.setBorder(BorderFactory.createLineBorder(Color.black));
         JScrollPane scrollPane = new JScrollPane(subPanel2);

            JPanel subPanel2_1 = new JPanel(new MigLayout());
            subPanel2_1.setBorder(BorderFactory.createLineBorder(Color.black));
            JLabel general = new JLabel("General options:");
            subPanel2_1.add(general, "wrap");
            JLabel trackName = new JLabel("Track name:");
            subPanel2_1.add(trackName);
            JTextField subPanel2_1_trackName = new JTextField();
            subPanel2_1.add(subPanel2_1_trackName, "width min(96%), wrap");
            JLabel language = new JLabel("Language:");
            subPanel2_1.add(language);
            JTextField subPanel2_1_language = new JTextField();
            subPanel2_1.add(subPanel2_1_language, "width min(96%), wrap");
            JLabel defaultTrack = new JLabel("Default track flag:");
            defaultTrack.setToolTipText("Set a track to be played by default upon opening the media.");
            subPanel2_1.add(defaultTrack);
            String[] flags = {"Yes", "No", "Determine automatically"};
            JComboBox<String> defaultTrack_cb = new JComboBox<>(flags);
            defaultTrack_cb.setSelectedIndex(2);
            subPanel2_1.add(defaultTrack_cb, "width min(96%), wrap");
            JLabel tags = new JLabel("Tags:");
            subPanel2_1.add(tags);
            JTextField subPanel2_1_tags = new JTextField();
            subPanel2_1.add(subPanel2_1_tags, "width min(96%), wrap");
            JLabel compression = new JLabel("Compression:");
            subPanel2_1.add(compression);
            String[] compressionArr = {"Determine automatically", "None", "zlib"};
            JComboBox<String> compression_cb = new JComboBox<>(compressionArr);
            compression_cb.setSelectedIndex(1);
            subPanel2_1.add(compression_cb, "width min(96%), wrap");
            subPanel2.add(subPanel2_1, "width min(96%), wrap");

            JPanel subPanel2_2 = new JPanel(new MigLayout());
            subPanel2_2.setBorder(BorderFactory.createLineBorder(Color.black));
            JLabel timestamp = new JLabel("Time stamp and duration:");
            subPanel2_2.add(timestamp, "wrap");
            JLabel delay = new JLabel("Delay (in ms):");
            subPanel2_2.add(delay);
            JTextField subPanel2_1_delay = new JTextField();
            subPanel2_2.add(subPanel2_1_delay, "width min(96%), wrap");
            JLabel duration = new JLabel("Default duration:");
            subPanel2_2.add(duration);
            JTextField subPanel2_1_duration = new JTextField();
            subPanel2_2.add(subPanel2_1_duration, "width min(96%), wrap");
            JLabel timestampFile = new JLabel("Timestamp File:");
            subPanel2_2.add(timestampFile);
            subPanel2_2_timestampFile = new JTextField();
            subPanel2_2.add(subPanel2_2_timestampFile, "width min(100%)");
            subPanel2_2_timestampFile_browse = new JButton("Browse");
            subPanel2_2_timestampFile_browse.addActionListener(this);
            subPanel2_2.add(subPanel2_2_timestampFile_browse, "wrap");
            subPanel2.add(subPanel2_2, "width min(96%), wrap");

            JPanel subPanel2_3 = new JPanel(new MigLayout());
            subPanel2_3.setBorder(BorderFactory.createLineBorder(Color.black));
            JLabel videoProperties = new JLabel("Video properties:");
            subPanel2_3.add(videoProperties, "wrap");
            JLabel aspectRatio = new JLabel("Set aspect ratio:");
            subPanel2_3.add(aspectRatio);
            String[] ratioList = {"1:1 (square)", "6:5 (movietone)", "5:4 (classic)","4:3 (traditional)",
                    "3:2 (classic 35mm)", "16:10 (standard)", "16:9 (standard)", "1.85:1 (cinema)", "1.9:1 (IMAX)",
                    "32:9 (ultra wide)", "18:5 (ultra wide)"};
            JComboBox<String> aspectRatio_cb = new JComboBox<>(ratioList);
            aspectRatio_cb.setSelectedIndex(6);
            subPanel2_3.add(aspectRatio_cb, "width min(96%), wrap");
            JLabel width = new JLabel("Display width:");
            subPanel2_3.add(width);
            JTextField subPanel2_3_width = new JTextField();
            subPanel2_3.add(subPanel2_3_width, "width min(96%), wrap");
            JLabel height = new JLabel("Display height:");
            subPanel2_3.add(height);
            JTextField subPanel2_3_height = new JTextField();
            subPanel2_3.add(subPanel2_3_height, "width min(96%), wrap");
            subPanel2.add(subPanel2_3, "width min(96%), wrap");

            JPanel subPanel2_4 = new JPanel(new MigLayout());
            subPanel2_4.setBorder(BorderFactory.createLineBorder(Color.black));
            JLabel audioProperties = new JLabel("Audio properties:");
            subPanel2_4.add(audioProperties, "wrap");
            JLabel audioFormat = new JLabel("Audio format:");
            audioFormat.setToolTipText("Set format for the audio track. Recommended to choose \"as source\" to preserve " +
                    "audio quality unless compression is needed.");
            subPanel2_4.add(audioFormat);
            String[] audioFormats = {"FLAC (lossless)", "Wave (lossless)", "ALAC (lossless)", "AAC (lossy)", "MP3 (lossy)",
                    "As source"};
            JComboBox<String> audioFormat_cb = new JComboBox<>(audioFormats);
            audioFormat_cb.setSelectedIndex(5);
            subPanel2_4.add(audioFormat_cb, "width min(96%), wrap");
            JLabel bitDepth = new JLabel("Bit depth:");
            bitDepth.setToolTipText("Set bit depth for the audio track. Recommended to choose \"as source\" to preserve " +
                    "audio quality unless compression is needed.");
            subPanel2_4.add(bitDepth);
            String[] depths = {"8 bit", "16 bit (CD)", "24 bit (DVD)", "32 bit", "As source"};
            JComboBox<String> depth_cb = new JComboBox<>(depths);
            depth_cb.setSelectedIndex(4);
            subPanel2_4.add(depth_cb, "width min(96%), wrap");
            JLabel sampleRate = new JLabel("Sample rate:");
            sampleRate.setToolTipText("Set sample rate for the audio track. Recommended to choose \"as source\" to preserve " +
                    "audio quality unless compression is needed.");
            subPanel2_4.add(sampleRate);
            String[] sampleRateArr = {"8 KHz", "11 KHz", "12 KHz", "16 KHz", "22 KHz", "24 KHz", "32 KHz", "44.1 KHz (CD)",
                    "48 KHz (DAT)", "88.2 KHz", "96 KHz (DVD)", "176.4 KHz", "192 KHz", "384 KHz", "As source"};
            JComboBox<String> sampleRate_cb = new JComboBox<>(sampleRateArr);
            sampleRate_cb.setSelectedIndex(14);
            subPanel2_4.add(sampleRate_cb, "width min(96%), wrap");
            subPanel2.add(subPanel2_4, "width min(96%), wrap");

            JPanel subPanel2_5 = new JPanel(new MigLayout());
            subPanel2_5.setBorder(BorderFactory.createLineBorder(Color.black));
            JLabel subtitle = new JLabel("Subtitle:");
            subPanel2_5.add(subtitle, "wrap");
            JLabel location = new JLabel("Location:");
            subPanel2_5.add(location);
            subPanel2_5_subtitleFile = new JTextField();
            subPanel2_5.add(subPanel2_5_subtitleFile, "width min(100%)");
            subPanel2_5_subtitleFile_browse = new JButton("Browse");
            subPanel2_5_subtitleFile_browse.addActionListener(this);
            subPanel2_5.add(subPanel2_5_subtitleFile_browse, "wrap");
            JLabel characterSet = new JLabel("Character set:");
            subPanel2_5.add(characterSet);
            String[] characterSetArr = {"ISO-8859-15", "UTF-8", "MS-ANSI", "US-ASCII", "WINDOWS-1250", "WINDOWS-1252"};
            JComboBox<String> characterSet_cb = new JComboBox<>(characterSetArr);
            characterSet_cb.setSelectedIndex(1);
            subPanel2_5.add(characterSet_cb, "width min(96%), wrap");
            subPanel2.add(subPanel2_5, "width min(96%), wrap");

         panel.add(scrollPane, "height min(80%), width min(50%), span 1 3, wrap");

         JLabel tracks = new JLabel("Tracks, chapters and tags:");
         panel.add(tracks, "wrap");

         JPanel subPanel3 = new JPanel(new MigLayout());
         subPanel3.setBorder(BorderFactory.createLineBorder(Color.black));
         panel.add(subPanel3, "height min(40%), width min(50%), wrap");

         JLabel destination = new JLabel("Destination file:");
         panel.add(destination, "span 2, wrap");

         JPanel subPanel4 = new JPanel(new MigLayout());
         subPanel4.setBorder(BorderFactory.createLineBorder(Color.black));
         panel.add(subPanel4, "height min(10%), width min(100%), span 2, wrap");
         subPanel4_text = new JTextField();
         subPanel4.add(subPanel4_text, "width min(100%)");
         subPane4_buttonA = new JButton("Browse");
         subPane4_buttonA.addActionListener(this);
         subPanel4.add(subPane4_buttonA);

         JPanel subPanel5 = new JPanel(new MigLayout("center"));
         panel.add(subPanel5, "height min(10%), width min(100%), span 2");
         subPanel5_button1 = new JButton("Add source files");
         subPanel5.add(subPanel5_button1);
         subPanel5_button2 = new JButton("Execute");
         subPanel5.add(subPanel5_button2);
         subPanel5_button3 = new JButton("Add to job queue");
         subPanel5.add(subPanel5_button3);

         return panel;
    }

    private JPanel outputPane() {
        JPanel panel = new JPanel(new MigLayout());

        JLabel general = new JLabel("General");
        panel.add(general, "wrap");

        JPanel subPanel1 = new JPanel(new MigLayout());
        subPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(subPanel1, "width min(100%), wrap");
        JLabel fileTitle = new JLabel("File title:");
        subPanel1.add(fileTitle);
        JTextField textField = new JTextField();
        subPanel1.add(textField, "width min(100%), wrap");

        JLabel splitting = new JLabel("Splitting");
        panel.add(splitting, "wrap");

        JPanel subPanel2 = new JPanel(new MigLayout());
        subPanel2.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel splitMode = new JLabel("Split mode:");
        subPanel2.add(splitMode);
        String[] splitModeArr = {"After output size", "After output duration", "After specific timestamp", "By parts based on timestamp",
                "Do not split"};
        splitMode_cb = new JComboBox<>(splitModeArr);
        splitMode_cb.setSelectedIndex(4);
        splitMode_cb.addActionListener(this);
        subPanel2.add(splitMode_cb, "width min(100%), wrap");
        split = new JLabel("Options:");
        subPanel2.add(split);
        JTextField splitValue = new JTextField();
        subPanel2.add(splitValue, "width min(100%), wrap");
        JLabel numberOfFiles = new JLabel("# of files:");
        numberOfFiles.setToolTipText("Enter \"unlimited\" if # of files constraint is not needed.");
        subPanel2.add(numberOfFiles);
        JTextField numberOfFilesText = new JTextField();
        subPanel2.add(numberOfFilesText, "width min(100%), wrap");
        panel.add(subPanel2, "width min(100%), wrap");

        JLabel misc = new JLabel("Miscellaneous");
        panel.add(misc, "wrap");

        JPanel subPanel3 = new JPanel(new MigLayout());
        subPanel3.setBorder(BorderFactory.createLineBorder(Color.black));
        JCheckBox deleteBox = new JCheckBox("Delete source file upon completion.");
        deleteBox.setToolTipText("Remove all input files upon completion. Not recommended unless operating on low free " +
                "space hard drive.");
        subPanel3.add(deleteBox, "wrap");
        JCheckBox abortBox = new JCheckBox("Abort execution upon receiving warning.");
        abortBox.setToolTipText("Cancel all current job queues and stop if a warning is received.");
        subPanel3.add(abortBox, "wrap");
        JCheckBox flushBox = new JCheckBox("Flush cache memory to storage upon exit.");
        flushBox.setToolTipText("Flush all data cached in memory to storage when closing files during writing process " +
                "to prevent data loss on circumstances such as power loss, operating system or hard drive failure.");
        subPanel3.add(flushBox, "wrap");
        panel.add(subPanel3, "height min(80%), width min(100%), wrap");

        JPanel subPanel4 = new JPanel(new MigLayout());
        subPanel4.setBorder(BorderFactory.createLineBorder(Color.black));
        subPanel4_text = new JTextField();
        subPanel4.add(subPanel4_text, "width min(100%)");
        subPane4_buttonB = new JButton("Browse");
        subPane4_buttonB.addActionListener(this);
        subPanel4.add(subPane4_buttonB);
        panel.add(subPanel4, "height min(10%), width min(100%), span 2, wrap");

        JPanel subPanel5 = new JPanel(new MigLayout("center"));
        subPanel5_button1 = new JButton("Add source files");
        subPanel5.add(subPanel5_button1);
        subPanel5_button2 = new JButton("Execute");
        subPanel5.add(subPanel5_button2);
        subPanel5_button3 = new JButton("Add to job queue");
        subPanel5.add(subPanel5_button3);
        panel.add(subPanel5, "height min(10%), width min(100%), span 2");

        return panel;
    }

    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        // JMenu 1
        JMenu menu1 = new JMenu("File");
        menuBar.add(menu1);

        // JMenuItem
        newFile = new JMenuItem("New Project");
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        menu1.add(newFile);

        openFile = new JMenuItem("Open Project...");
        openFile.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK)));
        menu1.add(openFile);
        openFile.addActionListener(this);

        saveFile = new JMenuItem("Save");
        saveFile.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK)));
        menu1.add(saveFile);

        saveAs = new JMenuItem("Save As...");
        saveAs.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK)));
        menu1.add(saveAs);
        saveAs.addActionListener(this);

        // Separator
        menu1.addSeparator();

        // JMenuItem + sub menu
        JMenu settings = new JMenu("Settings...");
        language = new JMenuItem("Language");
        settings.add(language);
        preference = new JMenuItem("Preferences");
        settings.add(preference);
        menu1.add(settings);

        // Separator
        menu1.addSeparator();

        // JMenuItem
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        menu1.add(exit);

        // JMenu 2
        JMenu menu2 = new JMenu("Edit");
        menuBar.add(menu2);

        // JMenuItem
        undo = new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
        menu2.add(undo);

        redo = new JMenuItem("Redo");
        redo.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK)));
        menu2.add(redo);

        // Separator
        menu2.addSeparator();

        // JMenuItem
        cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        menu2.add(cut);

        copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        menu2.add(copy);

        paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        menu2.add(paste);

        delete = new JMenuItem("Delete");
        delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.BUTTON1_DOWN_MASK));
        menu2.add(delete);

        // JMenu 3
        JMenu menu3 = new JMenu("Help");
        menuBar.add(menu3);

        // JMenuItem
        help = new JMenuItem("Help");
        help.addActionListener(this);
        menu3.add(help);

        bug = new JMenuItem("Report a Bug...");
        bug.addActionListener(this);
        menu3.add(bug);

        update = new JMenuItem("Check for Updates...");
        update.addActionListener(this);
        menu3.add(update);

        about = new JMenuItem("About");
        about.addActionListener(this);
        menu3.add(about);

        return menuBar;
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        if (e.getSource() == openFile || e.getSource() == saveAs || e.getSource() == subPanel5_button1) {
            int result = fileChooser.showOpenDialog(Frame.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            }
        } else if (e.getSource() == subPanel2_2_timestampFile_browse) {
            int result = fileChooser.showOpenDialog(Frame.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                subPanel2_2_timestampFile.setText(selectedFile.getAbsolutePath());
            }
        } else if (e.getSource() == subPanel2_5_subtitleFile_browse) {
            int result = fileChooser.showOpenDialog(Frame.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                subPanel2_5_subtitleFile.setText(selectedFile.getAbsolutePath());
            }
        } else if (e.getSource() == subPane4_buttonA || e.getSource() == subPane4_buttonB) {
            int result = fileChooser.showOpenDialog(Frame.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                subPanel4_text.setText(selectedFile.getAbsolutePath());
            }
        } else if (e.getSource() == update) {
            JOptionPane.showMessageDialog(null, "Your software is up to date.");
        } else if (e.getSource() == bug) {
            Object[] options = { "Send Report", "Cancel"};
            JTextArea textArea = new JTextArea();
            JPanel panel = new JPanel(new BorderLayout());
            JLabel label = new JLabel("Please provide a detailed explanation:");
            panel.add(label, BorderLayout.NORTH);
            textArea.setLineWrap(true);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(200, 100));
            panel.add(scrollPane, BorderLayout.SOUTH);
            JOptionPane.showOptionDialog(null, panel, "Send a report",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, null);
        } else if (e.getSource() == help) {
            JOptionPane.showMessageDialog(null, "Hover on the options for more information.");
        } else if (e.getSource() == about) {
            JOptionPane.showMessageDialog(null, "UI project of CSC 420 by Minh Luu.");
        } else if (e.getSource() == exit) {
            System.exit(0);
        }
        String mode = (String)splitMode_cb.getSelectedItem();
        assert mode != null;
        switch (mode) {
            case "After output size" -> split.setText("Size:");
            case "After output duration" -> split.setText("Duration:");
            case "After specific timestamp" -> split.setText("Timestamp:");
            case "By parts based on timestamp" -> split.setText("Parts:");
            case "Do not split" -> split.setText("Options:");
        }
    }
}