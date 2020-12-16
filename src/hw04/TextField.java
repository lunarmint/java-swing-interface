package hw04;

import javax.swing.event.*;
import javax.swing.text.*;

public class TextField implements DocumentListener {

    private final Frame frame;

    @Override
    public void insertUpdate(DocumentEvent event) {
        try {
            Document document = event.getDocument();
            frame.setImage(document.getText(0, document.getLength()));
        }
        catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

    public TextField(Frame frame) {
        this.frame = frame;
    }
}
